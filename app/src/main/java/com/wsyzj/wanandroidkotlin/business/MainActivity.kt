package com.wsyzj.wanandroidkotlin.business

import com.wsyzj.wanandroidkotlin.R
import com.wsyzj.wanandroidkotlin.common.base.BaseActivity
import com.wsyzj.wanandroidkotlin.common.http.BaseRequest
import com.wsyzj.wanandroidkotlin.common.mvp.BasePresenter
import org.reactivestreams.Subscriber

/**
 * 主界面
 */
class MainActivity : BaseActivity() {

    override fun presenter(): BasePresenter? {
        return null
    }

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {

    }

    override fun initData() {

    }

}
