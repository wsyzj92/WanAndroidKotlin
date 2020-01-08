package com.wsyzj.wanandroidkotlin.business.widget

import android.annotation.SuppressLint
import android.content.Context
import android.net.http.SslError
import android.os.Build
import android.util.AttributeSet
import android.view.KeyEvent
import android.webkit.SslErrorHandler
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient

/**
 * <pre>
 * author : 焦洋
 * e-mail : jiao35478729@163.com
 * time   : 2019/03/05
 * desc   : 项目通用webview
</pre> *
 */
class BaseWebView : WebView {

    constructor(context: Context) : super(context) {
        inits()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        inits()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        inits()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun inits() {
        initSettings()
        initListener()
    }

    /**
     * 初始化通用的设置设置
     */
    @SuppressLint("SetJavaScriptEnabled")
    private fun initSettings() {
        val webSettings = settings

        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.javaScriptEnabled = true
        // 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）
        // 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即可

        //设置自适应屏幕，两者合用
        webSettings.useWideViewPort = true        //将图片调整到适合webview的大小
        webSettings.loadWithOverviewMode = true   // 缩放至屏幕的大小

        //缩放操作
        webSettings.setSupportZoom(false)            //支持缩放，默认为true。是下面那个的前提。
        webSettings.builtInZoomControls = false    //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.displayZoomControls = false   //隐藏原生的缩放控件

        //其他细节操作
        webSettings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK   //关闭webview中缓存
        webSettings.allowFileAccess = true                            //设置可以访问文件
        webSettings.javaScriptCanOpenWindowsAutomatically = true      //支持通过JS打开新窗口
        webSettings.loadsImagesAutomatically = true                   //支持自动加载图片
        webSettings.defaultTextEncodingName = "utf-8"                 //设置编码格式

        // 特别注意：5.1以上默认禁止了https和http混用，以下方式是开启
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }
    }

    /**
     * 部分事件复写
     */
    private fun initListener() {
        /**
         * 复写shouldOverrideUrlLoading()方法，使得打开网页时不调用系统浏览器， 而是在本WebView中显示
         */
        webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }
        }


        /**
         * webView默认是不处理https请求的，页面显示空白
         */
        webViewClient = object : WebViewClient() {
            override fun onReceivedSslError(view: WebView, handler: SslErrorHandler, error: SslError) {
                handler.proceed()    //表示等待证书响应
                // handler.cancel();      //表示挂起连接，为默认方式
                // handler.handleMessage(null);    //可做其他处理
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && canGoBack()) {
            goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}
