package com.wsyzj.wanandroidkotlin.common

import android.app.Application
import android.content.Intent
import com.blankj.utilcode.util.Utils
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

    companion object {
        var sBaseApp: BaseApp by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        sBaseApp = this
    }

    fun initLibrary() {
        Utils.init(sBaseApp)
    }
}