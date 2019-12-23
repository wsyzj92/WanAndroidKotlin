package com.wsyzj.wanandroidkotlin.common.mvp

import android.view.View

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : wsyzj_92@163.com
 *     time   : 2019/12/23
 *     desc   :
 *     version: 1.0
 * </pre>
 */
interface BaseIPresenter {

    fun attachView(v: BaseIView)

    fun detachView()
}