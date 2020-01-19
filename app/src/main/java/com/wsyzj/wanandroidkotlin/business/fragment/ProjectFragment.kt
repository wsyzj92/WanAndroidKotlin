package com.wsyzj.wanandroidkotlin.business.fragment

import android.annotation.SuppressLint
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.BindView
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.wsyzj.wanandroidkotlin.R
import com.wsyzj.wanandroidkotlin.business.adapter.HomeAdapter
import com.wsyzj.wanandroidkotlin.business.bean.DataX
import com.wsyzj.wanandroidkotlin.business.manager.IntentManager
import com.wsyzj.wanandroidkotlin.common.base.BaseFragment
import com.wsyzj.wanandroidkotlin.common.constant.Constant
import com.wsyzj.wanandroidkotlin.common.http.BaseRequest
import com.wsyzj.wanandroidkotlin.common.http.BaseSchedulers
import com.wsyzj.wanandroidkotlin.common.widget.BasePullToRefreshView

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : wsyzj_92@163.com
 *     time   : 2020/01/19
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class ProjectFragment : BaseFragment() {

    @BindView(R.id.pull_to_refresh)
    lateinit var pull_to_refresh: BasePullToRefreshView

    var pageNumber: Int = 0
    var projectList: MutableList<DataX>? = null
    var homeAdapter: HomeAdapter? = null

    override fun layoutId(): Int {
        return R.layout.fragment_project
    }

    override fun initView() {

    }

    override fun initData() {
        getProjectList(true)
    }

    override fun initListener() {
        pull_to_refresh.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onRefresh(refreshLayout: RefreshLayout) {
                getProjectList(true)
            }

            override fun onLoadMore(refreshLayout: RefreshLayout) {
                getProjectList(false)
            }
        })
    }

    /**
     * 获取首页列表
     */
    @SuppressLint("CheckResult")
    fun getProjectList(refreshing: Boolean) {
        if (refreshing) {
            pageNumber = 0
        } else {
            pageNumber++
        }

        val subscribe =
            BaseRequest.instance.service.getProjectList(pageNumber, 294)
                .compose(BaseSchedulers.io_main())
                .subscribe() {
                    if (it.errorCode == Constant.HTTP_CODE) {
                        it.let {
                            var list = it.data?.datas!!
                            if (projectList == null || refreshing) {
                                projectList = list
                            } else {
                                projectList?.addAll(list)
                            }
                            pull_to_refresh.finishRefresh()
                            pull_to_refresh.finishLoadMore()
                            pull_to_refresh.setNoMoreData(20 < list.size)
                            setHomeList(projectList!!)
                        }
                    }
                }
        addDisposable(subscribe)
    }

    /**
     * 设置首页数据
     */
    fun setHomeList(articles: MutableList<DataX>) {
        if (homeAdapter == null) {
            homeAdapter = HomeAdapter(articles)
            pull_to_refresh.setLayoutManager(LinearLayoutManager(activity))
            pull_to_refresh.setAdapter(homeAdapter!!)
        } else {
            homeAdapter?.setNewData(articles)
        }

        homeAdapter?.setOnItemClickListener { adapter, view, position ->
            IntentManager.webview(
                activity,
                articles[position].link,
                articles[position].id
            )
        }
    }
}