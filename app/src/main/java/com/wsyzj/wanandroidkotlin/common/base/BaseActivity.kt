package com.wsyzj.wanandroidkotlin.common.base

import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.SkinAppCompatDelegateImpl
import butterknife.ButterKnife
import com.blankj.utilcode.util.AdaptScreenUtils
import com.blankj.utilcode.util.ToastUtils
import com.wsyzj.wanandroidkotlin.common.http.BaseRetrofit
import com.wsyzj.wanandroidkotlin.common.mvp.BaseIModel
import com.wsyzj.wanandroidkotlin.common.mvp.BaseIView
import com.wsyzj.wanandroidkotlin.common.mvp.BasePresenter
import com.wsyzj.wanandroidkotlin.common.utils.EventBusUtils
import com.wsyzj.wanandroidkotlin.common.widget.BaseNavigationView
import com.wsyzj.wanandroidkotlin.common.widget.BaseStatusLayout
import io.reactivex.disposables.Disposable
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : wsyzj_92@163.com
 *     time   : 2019/12/23
 *     desc   : Activity的基类
 *     version: 1.0
 * </pre>
 */
abstract class BaseActivity<P : BasePresenter<BaseIView, BaseIModel>> : AppCompatActivity(),
    BaseIView {

    var presenter: P? = null
    var progressDialog: BaseProgressDialog? = null
    lateinit var baseNavigationView: BaseNavigationView
    lateinit var baseStatusLayout: BaseStatusLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createPresenter()
        initLayout()
        initView()
        initListener()
        initData()
        registerEventBus()
    }

    override fun onDestroy() {
        super.onDestroy()

        BaseRetrofit.clear("$packageName.$localClassName")

        if (presenter != null) {
            presenter?.detachView()
        }

        if (isRegisterEventBus()) {
            EventBusUtils.unregister(this)
        }

        if (progressDialog != null) {
            progressDialog?.dismiss()
            progressDialog = null
        }
    }

    /**
     * 创建MVP水的presenter
     */
    fun createPresenter() {
        presenter = presenter()
        if (presenter != null) {
            presenter?.attachView(this)
        }
    }

    /**
     * 初始化界面的布局，包括标题栏，状态栏，getLayoutId设置的布局
     */
    fun initLayout() {
        var viewGroup = findViewById<ViewGroup>(android.R.id.content)
        viewGroup.removeAllViews()

        var container = LinearLayout(this)
        container.orientation = LinearLayout.VERTICAL
        container.layoutParams =
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        viewGroup.addView(container)

        // 标题栏
        baseNavigationView = BaseNavigationView(this)
        baseNavigationView?.layoutParams =
            LinearLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                AdaptScreenUtils.pt2Px(128F)
            )
        container.addView(baseNavigationView)

        // 状态布局
        baseStatusLayout = BaseStatusLayout(this)
        baseStatusLayout?.layoutParams =
            FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
        container.addView(baseStatusLayout)

        // 真正显示的布局
        val content = View.inflate(this, layoutId(), null)
        content.layoutParams =
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        container.addView(content)
        ButterKnife.bind(this, container)
    }

    /**
     * 是否在当前界面是否EventBus
     */
    fun isRegisterEventBus(): Boolean {
        return false
    }

    /**
     * 注册eventbus
     */
    fun registerEventBus() {
        if (isRegisterEventBus()) {
            EventBusUtils.register(this)
        }
    }

    override fun showToast(message: String) {
        ToastUtils.showShort(message)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    open fun receiveEvent(event: BaseEventBus) {

    }

    /**
     * 显示dialog
     */
    override fun showProgress() {
        if (progressDialog == null) {
            progressDialog = BaseProgressDialog(this)
        }
        progressDialog?.show()
    }

    /**
     * 取消dialog
     */
    override fun dismissProgress() {
        if (progressDialog != null) {
            progressDialog?.dismiss()
        }
    }

    /**
     * 网络请求进行统一管理
     *
     * @param disposable
     */
    override fun addDisposable(disposable: Disposable) {
        BaseRetrofit.add("$packageName.$localClassName", disposable)
    }

    /**
     * 屏幕适配
     */
    override fun getResources(): Resources {
        return AdaptScreenUtils.adaptWidth(super.getResources(), 1080)
    }

    /**
     * 换肤用到
     */
    override fun getDelegate(): AppCompatDelegate {
        return SkinAppCompatDelegateImpl.get(this, this)
    }

    abstract fun layoutId(): Int

    abstract fun presenter(): P?

    abstract fun initView()

    abstract fun initListener()

    abstract fun initData()
}