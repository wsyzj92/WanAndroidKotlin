package com.wsyzj.wanandroidkotlin.business.activity

import android.view.KeyEvent
import com.wsyzj.wanandroidkotlin.R
import com.wsyzj.wanandroidkotlin.business.widget.BaseWebView
import com.wsyzj.wanandroidkotlin.common.base.BaseActivity
import com.wsyzj.wanandroidkotlin.common.mvp.BaseIModel
import com.wsyzj.wanandroidkotlin.common.mvp.BaseIView
import com.wsyzj.wanandroidkotlin.common.mvp.BasePresenter

import butterknife.BindView

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

    private var url: String? = null

    override fun presenter(): BasePresenter<BaseIView, BaseIModel>? {
        return null
    }

    override fun layoutId(): Int {
        return R.layout.activity_webview
    }

    override fun initView() {

    }

    override fun initListener() {

    }

    override fun initData() {
        getIntentExtras()
    }

    private fun getIntentExtras() {
        if (intent != null) {

            url = intent.getStringExtra("url")
            webview?.loadUrl(url)
        }
    }

}
