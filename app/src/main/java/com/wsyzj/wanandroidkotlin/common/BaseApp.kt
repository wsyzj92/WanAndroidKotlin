package com.wsyzj.wanandroidkotlin.common

import android.app.Application

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : wsyzj_92@163.com
 *     time   : 2019/12/23
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        sBaseApp = this
    }

    companion object {
        lateinit var sBaseApp: BaseApp
    }
}