package com.wsyzj.wanandroidkotlin.business.mvp

import com.wsyzj.wanandroidkotlin.business.bean.Article
import com.wsyzj.wanandroidkotlin.common.http.BaseEntity
import com.wsyzj.wanandroidkotlin.common.http.BaseRequest
import com.wsyzj.wanandroidkotlin.common.http.BaseSchedulers
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
class HomeModel : HomeContract.Model {

    override fun getHomeList(): Flowable<BaseEntity<Article>> {
        return BaseRequest
            .instance
            .service
            .getHomeList(0)
            .compose(BaseSchedulers.io_main())
    }

}