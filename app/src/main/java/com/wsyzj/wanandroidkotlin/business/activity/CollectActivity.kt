package com.wsyzj.wanandroidkotlin.business.activity

import com.wsyzj.wanandroidkotlin.R
import com.wsyzj.wanandroidkotlin.common.base.BaseActivity
import com.wsyzj.wanandroidkotlin.common.mvp.BaseIModel
import com.wsyzj.wanandroidkotlin.common.mvp.BaseIView
import com.wsyzj.wanandroidkotlin.common.mvp.BasePresenter

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : wsyzj_92@163.com
 *     time   : 2020/01/17
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class CollectActivity : BaseActivity<BasePresenter<BaseIView, BaseIModel>>() {

    override fun layoutId() = R.layout.activity_collect

    override fun presenter(): BasePresenter<BaseIView, BaseIModel>? {
        return null
    }

    override fun initView() {

    }

    override fun initListener() {

    }

    override fun initData() {

    }

}