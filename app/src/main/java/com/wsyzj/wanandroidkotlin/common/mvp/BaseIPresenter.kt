package com.wsyzj.wanandroidkotlin.common.mvp

/**
 * @项目名：
 * @包名：
 * @文件名:
 * @创建者: TengFei
 * @创建时间:
 * @描述：
 */
interface BaseIPresenter<V : BaseIView> {

    fun attachView(view: V)

    fun detachView()
}

