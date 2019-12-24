package com.wsyzj.wanandroidkotlin.business.fragment

import butterknife.BindView
import com.wsyzj.wanandroidkotlin.R
import com.wsyzj.wanandroidkotlin.common.base.BaseFragment
import com.wsyzj.wanandroidkotlin.common.mvp.BasePresenter
import com.wsyzj.wanandroidkotlin.common.widget.BasePullToRefreshView

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : wsyzj_92@163.com
 *     time   : 2019/12/24
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class HomeFragment : BaseFragment() {

    @BindView(R.id.base_pull_refresh)
    lateinit var base_pull_refresh: BasePullToRefreshView

    override fun presenter(): BasePresenter? {
        return null
    }

    override fun layoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView() {

    }

    override fun initListener() {

    }

    override fun initData() {

    }

}