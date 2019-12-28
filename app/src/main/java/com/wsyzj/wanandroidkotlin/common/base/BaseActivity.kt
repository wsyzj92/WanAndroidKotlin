package com.wsyzj.wanandroidkotlin.common.base

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import com.wsyzj.wanandroidkotlin.common.http.BaseRetrofit
import com.wsyzj.wanandroidkotlin.common.mvp.BaseIModel
import com.wsyzj.wanandroidkotlin.common.mvp.BaseIPresenter
import com.wsyzj.wanandroidkotlin.common.mvp.BaseIView
import com.wsyzj.wanandroidkotlin.common.mvp.BasePresenter
import com.wsyzj.wanandroidkotlin.common.utils.EventBusUtils
import com.wsyzj.wanandroidkotlin.common.utils.IContextCompat
import io.reactivex.disposables.Disposable

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : wsyzj_92@163.com
 *     time   : 2019/12/23
 *     desc   : Activity的基类
 *     version: 1.0
 * </pre>
 */
abstract class BaseActivity<P : BasePresenter<BaseIView, BaseIModel>> : AppCompatActivity(), BaseIView {

    var presenter: P? = null
    var progressDialog: BaseProgressDialog? = null

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
            LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        viewGroup.addView(container)

//        val content = IContextCompat.inflate(layoutId())
        val content = View.inflate(this, layoutId(), null)
        content.layoutParams =
            LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
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

    abstract fun presenter(): P?

    abstract fun layoutId(): Int

    abstract fun initView()

    abstract fun initListener()

    abstract fun initData()
}