package com.wsyzj.wanandroidkotlin

import android.app.Application
import android.content.Context

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : wsyzj_92@163.com
 *     time   : 2019/12/16
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class BaseApp : Application() {

    private var baseApp: Context? = null

    fun getApp(): Context? {
        return baseApp
    }

    override fun onCreate() {
        super.onCreate()
        baseApp = this

        initLibrary()   // 第三方库初始化
    }

    fun initLibrary() {

    }

}