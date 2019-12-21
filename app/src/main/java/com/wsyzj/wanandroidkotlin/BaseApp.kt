package com.wsyzj.wanandroidkotlin

import android.app.Application

import com.blankj.utilcode.util.Utils

/**
 * <pre>
 * author : 焦洋
 * e-mail : wsyzj_92@163.com
 * time   : 2019/10/09
 * desc   :
 * version: 1.0
</pre> *
 */
class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        baseApp = this
        initLibrary()
    }

    /**
     * 初始化第三方库
     */
    private fun initLibrary() {
        Utils.init(baseApp)        // 工具类
    }

    companion object {
        var baseApp: BaseApp? = null
    }
}
