package com.wsyzj.wanandroidkotlin.common

import android.app.Application
import kotlin.properties.Delegates

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
        var sBaseApp: BaseApp by Delegates.notNull()
    }
}