package com.wsyzj.wanandroidkotlin.common.mvp

import com.wsyzj.wanandroidkotlin.business.mvp.HomeContract
import com.wsyzj.wanandroidkotlin.business.mvp.HomeModel
import com.wsyzj.wanandroidkotlin.common.http.BaseEntity

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : wsyzj_92@163.com
 *     time   : 2019/12/23
 *     desc   :
 *     version: 1.0
 * </pre>
 */
open class BasePresenter<V : BaseIView, M : BaseIModel> : BaseIPresenter<V> {

    protected var mView: V? = null
    protected var mModel: M? = null

    override fun attachView(v: V) {
        mView = v
    }

    override fun detachView() {
        mView = null
    }

}