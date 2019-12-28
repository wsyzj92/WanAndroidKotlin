package com.wsyzj.wanandroidkotlin.business.mvp

import com.wsyzj.wanandroidkotlin.business.fragment.HomeFragment
import com.wsyzj.wanandroidkotlin.common.mvp.BasePresenter

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : wsyzj_92@163.com
 *     time   : 2019/12/24
 *     desc   :
 *     version: 1.0
 * </pre>
 */

class HomePresenter(view: HomeContract.View) : BasePresenter<HomeContract.View, HomeContract.Model>(),
    HomeContract.Presenter {

    init {
        mModel = HomeModel()
    }

    override fun getHomeList() {
        mView?.setHomeList()
    }
}