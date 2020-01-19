package com.wsyzj.wanandroidkotlin.common

import android.app.Application
import com.blankj.utilcode.util.Utils
import skin.support.SkinCompatManager
import skin.support.app.SkinAppCompatViewInflater
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
        initLibrary()
    }

    fun initLibrary() {
        // 工具类
        Utils.init(sBaseApp)

        // 换肤
        SkinCompatManager.withoutActivity(this)
            .addInflater(SkinAppCompatViewInflater())
            .setSkinStatusBarColorEnable(false)
            .setSkinWindowBackgroundEnable(false)
            .loadSkin()
    }

}