package com.wsyzj.wanandroidkotlin.business.mvp

import android.app.Activity
import com.wsyzj.wanandroidkotlin.business.bean.Article
import com.wsyzj.wanandroidkotlin.common.http.BaseEntity
import com.wsyzj.wanandroidkotlin.common.mvp.BaseIModel
import com.wsyzj.wanandroidkotlin.common.mvp.BaseIPresenter
import com.wsyzj.wanandroidkotlin.common.mvp.BaseIView
import io.reactivex.Flowable

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : wsyzj_92@163.com
 *     time   : 2019/12/24
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class HomeContract {

    interface View : BaseIView {
        fun setHomeList()
    }

    interface Model : BaseIModel {
        fun getHomeList(): Flowable<BaseEntity<Article>>
    }

    interface Presenter : BaseIPresenter<View> {
        fun getHomeList()
    }
}