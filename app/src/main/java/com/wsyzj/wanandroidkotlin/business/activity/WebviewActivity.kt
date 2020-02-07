package com.wsyzj.wanandroidkotlin.business.activity

import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import butterknife.BindView
import com.blankj.utilcode.util.IntentUtils
import com.blankj.utilcode.util.ToastUtils
import com.wsyzj.wanandroidkotlin.R
import com.wsyzj.wanandroidkotlin.business.constant.EventBusConstant
import com.wsyzj.wanandroidkotlin.business.manager.IntentManager
import com.wsyzj.wanandroidkotlin.business.utils.StorageUtils
import com.wsyzj.wanandroidkotlin.common.base.BaseActivity
import com.wsyzj.wanandroidkotlin.common.base.BaseEventBus
import com.wsyzj.wanandroidkotlin.common.constant.Constant
import com.wsyzj.wanandroidkotlin.common.http.BaseRequest
import com.wsyzj.wanandroidkotlin.common.http.BaseSchedulers
import com.wsyzj.wanandroidkotlin.common.mvp.BaseIModel
import com.wsyzj.wanandroidkotlin.common.mvp.BaseIView
import com.wsyzj.wanandroidkotlin.common.mvp.BasePresenter
import com.wsyzj.wanandroidkotlin.common.utils.EventBusUtils
import com.wsyzj.wanandroidkotlin.common.widget.BaseWebView
import com.wsyzj.wanandroidkotlin.common.widget.StatusLayout

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

    var url = ""
    var id = -1
    var collect = false

    override fun layoutId() = R.layout.activity_webview

    override fun presenter(): BasePresenter<BaseIView, BaseIModel>? {
        return null
    }

    override fun initView() {
        baseNavigationView.setShareImageResource(R.drawable.icon_share)
    }

    override fun initListener() {
        // 收藏
        baseNavigationView.onCollectClickListener = View.OnClickListener {
            if (StorageUtils.isLogin()) {
                BaseRequest
                    .instance
                    .service
                    .collect(id)
                    .compose(BaseSchedulers.io_main())
                    .subscribe({
                        if (it.errorCode == Constant.HTTP_CODE) {
                            collect = !collect
                            baseNavigationView.setCollectImageResource(if (collect) R.drawable.icon_collect else R.drawable.icon_un_collect)
                        } else {
                            ToastUtils.showShort(it.errorMsg)
                        }
                        EventBusUtils.post(BaseEventBus(EventBusConstant.EVENT_COLLECT))
                    })
            } else {
                IntentManager.login(this)
            }
        }

        // 分享
        baseNavigationView.onShareClickListener = View.OnClickListener {
            val shareTextIntent = IntentUtils.getShareTextIntent(url)
            startActivity(shareTextIntent)
        }

        webview.webViewClient = object : WebViewClient() {

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                baseStatusLayout.setStatusLayout(StatusLayout.SUCCESS)
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
