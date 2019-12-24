package com.wsyzj.wanandroidkotlin.common.mvp

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : wsyzj_92@163.com
 *     time   : 2019/12/23
 *     desc   :
 *     version: 1.0
 * </pre>
 */
open class BasePresenter : BaseIPresenter {

    protected var mView: BaseIView? = null
    protected var mModel: BaseIModel? = null

    constructor()

    constructor(mView: BaseIView?, mModel: BaseIModel?) {
        this.mView = mView
        this.mModel = mModel
    }

    override fun attachView(v: BaseIView) {
        mView = v
    }

    override fun detachView() {
        mView = null
    }

}