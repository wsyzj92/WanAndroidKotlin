package com.wsyzj.wanandroidkotlin.business.fragment

import butterknife.BindView
import com.wsyzj.wanandroidkotlin.R
import com.wsyzj.wanandroidkotlin.business.mvp.HomeContract
import com.wsyzj.wanandroidkotlin.business.mvp.HomePresenter
import com.wsyzj.wanandroidkotlin.common.base.BaseFragment
import com.wsyzj.wanandroidkotlin.common.mvp.BaseIModel
import com.wsyzj.wanandroidkotlin.common.mvp.BaseIView
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
class HomeFragment : BaseFragment<HomePresenter<HomeContract.View, HomeContract.Model>>(), HomeContract.View {

    @BindView(R.id.base_pull_refresh)
    lateinit var base_pull_refresh: BasePullToRefreshView

    override fun presenter(): HomePresenter = HomePresenter(this)

    override fun layoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView() {

    }

    override fun initListener() {

    }

    override fun initData() {

    }

    override fun setHomeList() {

    }
}