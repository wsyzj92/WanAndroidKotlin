package com.wsyzj.wanandroidkotlin.common.base

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import butterknife.ButterKnife
import com.wsyzj.wanandroidkotlin.R
import com.wsyzj.wanandroidkotlin.common.http.BaseRetrofit
import com.wsyzj.wanandroidkotlin.common.mvp.BaseIModel
import com.wsyzj.wanandroidkotlin.common.mvp.BaseIView
import com.wsyzj.wanandroidkotlin.common.mvp.BasePresenter
import com.wsyzj.wanandroidkotlin.common.utils.EventBusUtils
import com.wsyzj.wanandroidkotlin.common.utils.IContextCompat
import io.reactivex.disposables.Disposable

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : wsyzj_92@163.com
 *     time   : 2019/12/24
 *     desc   : 所有Fragment的基类
 *     version: 1.0
 * </pre>
 */
abstract class BaseFragment<P : BasePresenter<BaseIView, BaseIModel>> : Fragment(), BaseIView {

    var activity: Activity? = null
    var presenter: P? = null
    var progressDialog: BaseProgressDialog? = null

    var isViewCreated: Boolean = false           // 控件是否初始化完成
    var isLoadDataCompleted: Boolean = false     // 数据是否已加载完毕

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.activity = getActivity()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (userVisibleHint) {
            isLoadDataCompleted = true
            initData()
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

        if (isVisibleToUser && activity != null) {
            // 表示可见(解决ViewPager切换不回调OnResume方法)
            onResume()
        }

        if (isVisibleToUser && isViewCreated && !isLoadDataCompleted) {
            isLoadDataCompleted = true
            initData()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        isViewCreated = true

        var container = FrameLayout(activity)
        container.layoutParams =
            FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)

        val content = IContextCompat.inflate(layoutId())
        container.addView(content)

        ButterKnife.bind(this, content)
        createPresenter()
        initView()
        initListener()
        registerEventBus()

        return container
    }

    override fun onDestroy() {
        super.onDestroy()

        BaseRetrofit.clear(javaClass.simpleName + javaClass.`package`)

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
     * Presenter的初始化操作
     */
    fun createPresenter() {
        presenter = presenter()
        if (presenter != null) {
            presenter?.attachView(this)
        }
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
     * 是否在当前界面是否EventBus
     */
    fun isRegisterEventBus(): Boolean {
        return false
    }

    /**
     * 显示dialog
     */
    override fun showProgress() {
        if (progressDialog == null) {
            progressDialog = activity?.let { BaseProgressDialog(it) }
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
        BaseRetrofit.add(javaClass.simpleName + javaClass.`package`, disposable)
    }

    abstract fun presenter(): P?

    abstract fun layoutId(): Int

    abstract fun initView()

    abstract fun initListener()

    abstract fun initData()
}