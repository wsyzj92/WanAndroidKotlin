package com.wsyzj.wanandroidkotlin.common.mvp


import com.wsyzj.wanandroidkotlin.common.widget.StateLayout
import io.reactivex.disposables.Disposable

/**
 * @author: wsyzj
 * @date: 2017-03-18 10:09
 * @comment: MVP模式的View(通过Presenter将数据传入到该层 ， 负责View的展示相关)
 */
interface BaseIView {

    fun setStateLayout(stateLayout: StateLayout)

    fun setStateLayout(throwable: Throwable, data: List<*>)

    fun setStateLayout(throwable: Throwable, data: Any)

    fun showProgress()

    fun dismissProgress()

    fun showToast(message: String)

    fun addDisposable(disposable: Disposable)
}
