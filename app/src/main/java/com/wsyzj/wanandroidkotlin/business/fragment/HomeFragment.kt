package com.wsyzj.wanandroidkotlin.business.fragment

import android.annotation.SuppressLint
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.BindView
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.wsyzj.wanandroidkotlin.R
import com.wsyzj.wanandroidkotlin.business.adapter.HomeAdapter
import com.wsyzj.wanandroidkotlin.business.adapter.HomeBannerAdapter
import com.wsyzj.wanandroidkotlin.business.bean.DataBanner
import com.wsyzj.wanandroidkotlin.business.bean.DataX
import com.wsyzj.wanandroidkotlin.business.manager.IntentManager
import com.wsyzj.wanandroidkotlin.common.base.BaseFragment
import com.wsyzj.wanandroidkotlin.common.constant.Constant
import com.wsyzj.wanandroidkotlin.common.http.BaseRequest
import com.wsyzj.wanandroidkotlin.common.http.BaseSchedulers
import com.wsyzj.wanandroidkotlin.common.utils.IContextCompat
import com.wsyzj.wanandroidkotlin.common.widget.BasePullToRefreshView
import com.zhpan.bannerview.BannerViewPager
import com.zhpan.bannerview.constants.IndicatorGravity
import com.zhpan.bannerview.constants.IndicatorSlideMode
import com.zhpan.bannerview.constants.PageStyle

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : wsyzj_92@163.com
 *     time   : 2019/12/24
 *     desc   : 首页
 *     version: 1.0
 * </pre>
 */
class HomeFragment : BaseFragment() {

    @BindView(R.id.pull_to_refresh)
    lateinit var pull_to_refresh: BasePullToRefreshView

    var pageNumber: Int = 0
    var articles: MutableList<DataX> = mutableListOf()
    var homeAdapter: HomeAdapter? = null

    override fun layoutId() = R.layout.layout_pull_to_refresh

    override fun initView() {

    }

    override fun initListener() {
        pull_to_refresh.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onRefresh(refreshLayout: RefreshLayout) {
                getBannerList()
                getHomeList(true)
            }

            override fun onLoadMore(refreshLayout: RefreshLayout) {
                getHomeList(false)
            }
        })
    }

    override fun initData() {
        setHomeList(articles)
        getBannerList()
        getHomeList(true)
    }

    /**
     * 获取首页banner
     */
    @SuppressLint("CheckResult")
    fun getBannerList() {
        BaseRequest.instance.service.getBannerList().compose(BaseSchedulers.io_main()).subscribe() {
            if (it.errorCode == Constant.HTTP_CODE) {
                var list = it.data

                val headerView = IContextCompat.inflate(R.layout.recycler_header_home_banner)
                var banner_view =
                    headerView.findViewById<BannerViewPager<DataBanner, HomeBannerAdapter>>(R.id.banner_view)

                banner_view.setCanLoop(false)
                    .setIndicatorSlideMode(IndicatorSlideMode.NORMAL)
                    .setIndicatorGravity(IndicatorGravity.CENTER)
                    .setHolderCreator { HomeBannerAdapter() }
                    .setPageStyle(PageStyle.MULTI_PAGE_OVERLAP)
                    .setPageMargin(getResources().getDimensionPixelOffset(R.dimen.margin_48pt))
                    .setAutoPlay(true)
                    .setOnPageClickListener {
                        IntentManager.webview(activity!!, list[it].url, list[it].id, false)
                    }
                    .create(list)

                if (pull_to_refresh.getHeaderLayoutCount() == 0) {
                    pull_to_refresh.addHeaderView(headerView)
                }
            }
        }
    }

    /**
     * 获取首页列表
     */
    @SuppressLint("CheckResult")
    fun getHomeList(refreshing: Boolean) {
        if (refreshing) {
            pageNumber = 0
        } else {
            pageNumber++
        }

        val subscribe =
            BaseRequest.instance.service.getHomeList(pageNumber).compose(BaseSchedulers.io_main())
                .subscribe() {
                    if (it.errorCode == Constant.HTTP_CODE) {
                        it.let {
                            var list = it.data?.datas!!
                            if (refreshing) {
                                articles = list
                            } else {
                                articles.addAll(list)
                            }
                            pull_to_refresh.finishRefresh()
                            pull_to_refresh.finishLoadMore()
                            pull_to_refresh.setNoMoreData(20 < list.size)
                            setHomeList(articles!!)
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
                activity!!,
                articles[position].link,
                articles[position].id,
                articles[position].collect
            )
        }
    }
}
