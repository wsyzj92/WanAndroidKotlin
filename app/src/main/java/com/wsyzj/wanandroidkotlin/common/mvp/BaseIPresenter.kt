package com.wsyzj.wanandroidkotlin.common.mvp

import android.view.View

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : wsyzj_92@163.com
 *     time   : 2019/12/13
 *     desc   :
 *     version: 1.0
 * </pre>
 */
interface BaseIPresenter<V : BaseIView> {

    fun attachView(v: V)

    fun detachView()
}