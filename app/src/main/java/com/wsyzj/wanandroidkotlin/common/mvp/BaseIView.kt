package com.wsyzj.wanandroidkotlin.common.mvp

import android.view.View
import io.reactivex.disposables.Disposable

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : wsyzj_92@163.com
 *     time   : 2019/12/23
 *     desc   :
 *     version: 1.0
 * </pre>
 */
interface BaseIView {

    fun showToast(message: String)

    fun showProgress()

    fun dismissProgress()

    fun addDisposable(disposable: Disposable)
}