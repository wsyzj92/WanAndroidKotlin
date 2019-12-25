package com.wsyzj.wanandroidkotlin.business.fragment

import com.wsyzj.wanandroidkotlin.business.mvp.HomeContract
import com.wsyzj.wanandroidkotlin.business.mvp.HomePresenter
import com.wsyzj.wanandroidkotlin.common.base.BaseFragment
import com.wsyzj.wanandroidkotlin.common.mvp.BaseIModel
import com.wsyzj.wanandroidkotlin.common.mvp.BaseIView
import com.wsyzj.wanandroidkotlin.common.mvp.BasePresenter

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : wsyzj_92@163.com
 *     time   : 2019/12/25
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class TestFragment : BaseFragment<BasePresenter<BaseIView, BaseIModel>>(), HomeContract.View {

    override fun setHomeList() {

    }

    override fun presenter(): HomePresenter = HomePresenter(this)

    override fun layoutId(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initListener() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}