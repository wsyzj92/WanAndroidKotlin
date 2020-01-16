package com.wsyzj.wanandroidkotlin.business.activity

import android.view.KeyEvent
import android.view.View
import com.wsyzj.wanandroidkotlin.R
import com.wsyzj.wanandroidkotlin.business.widget.BaseWebView
import com.wsyzj.wanandroidkotlin.common.base.BaseActivity
import com.wsyzj.wanandroidkotlin.common.mvp.BaseIModel
import com.wsyzj.wanandroidkotlin.common.mvp.BaseIView
import com.wsyzj.wanandroidkotlin.common.mvp.BasePresenter

import butterknife.BindView
import com.blankj.utilcode.util.ToastUtils
import com.wsyzj.wanandroidkotlin.business.manager.IntentManager
import com.wsyzj.wanandroidkotlin.business.utils.StorageUtils
import com.wsyzj.wanandroidkotlin.common.constant.Constant
import com.wsyzj.wanandroidkotlin.common.http.BaseRequest
import com.wsyzj.wanandroidkotlin.common.http.BaseRetrofit
import com.wsyzj.wanandroidkotlin.common.http.BaseSchedulers

/**
 * <pre>
 * author : 焦洋
 * e-mail : wsyzj_92@163.com
 * time   : 2020/01/08
 * desc   :
 * version: 1.0
</pre> *
 */
class WebviewActivity : BaseActivity<BasePresenter<BaseIView, BaseIModel>>() {

    @BindView(R.id.webview)
    lateinit var webview: BaseWebView

    var url: String = ""
    var id: Int = -1
    var collect: Boolean = false

    override fun presenter(): BasePresenter<BaseIView, BaseIModel>? {
        return null
    }

    override fun layoutId(): Int {
        return R.layout.activity_webview
    }

    override fun initView() {
        baseNavigationView.setCollectImageResource(R.drawable.icon_un_collect)
    }

    override fun initListener() {
        /**
         * 收藏
         */
        baseNavigationView.onCollectClickListener = View.OnClickListener {
            if (StorageUtils.isLogin()) {
                BaseRequest
                    .instance
                    .service
                    .collect(id)
                    .compose(BaseSchedulers.io_main())
                    .subscribe({
                        if (it.errorCode == Constant.HTTP_CODE) {

                        } else {
                            ToastUtils.showShort(it.errorMsg)
                        }
                    })
            } else {
                IntentManager.login(this)
            }
        }
    }

    override fun initData() {
        getIntentExtras()
    }

    private fun getIntentExtras() {
        if (intent != null) {

            url = intent.getStringExtra("url")
            id = intent.getIntExtra("id", -1)
            collect = intent.getBooleanExtra("collect", false)

            baseNavigationView.setCollectImageResource(if (collect) R.drawable.icon_collect else R.drawable.icon_un_collect)
            webview.loadUrl(url)
        }
    }

}
