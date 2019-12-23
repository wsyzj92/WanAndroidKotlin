package com.wsyzj.wanandroidkotlin.common.base

import android.os.Bundle
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import com.wsyzj.wanandroidkotlin.common.http.BaseRetrofit
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
abstract class BaseActivity : AppCompatActivity(), BaseIView {

    var presenter: BasePresenter? = null
    var progressDialog: BaseProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createPresenter()
        initLayout()
        initView()
        initData()
        registerEventBus()
    }

    /**
     * 创建MVP水的presenter
     */
    fun createPresenter() {
        presenter?.attachView(this)
    }

    /**
     * 初始化界面的布局，包括标题栏，状态栏，getLayoutId设置的布局
     */
    fun initLayout() {
        var viewGroup = findViewById<ViewGroup>(android.R.id.content)
        viewGroup.removeAllViews()

        var container = LinearLayout(this)
        container.layoutParams =
            LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

        val content = IContextCompat.inflate(layoutId())
        container.addView(content)
        ButterKnife.bind(this)
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
     * 是否在当前界面是否EventBus
     */
    fun isRegisterEventBus(): Boolean {
        return false
    }

    abstract fun presenter(): BasePresenter?

    abstract fun layoutId(): Int

    abstract fun initView()

    abstract fun initData()
}