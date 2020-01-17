package com.wsyzj.wanandroidkotlin.business.fragment

import android.annotation.SuppressLint
import android.app.Activity
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.BindView
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.enums.PopupAnimation
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.wsyzj.wanandroidkotlin.R
import com.wsyzj.wanandroidkotlin.business.adapter.HomeAdapter
import com.wsyzj.wanandroidkotlin.business.bean.DataBanner
import com.wsyzj.wanandroidkotlin.business.bean.DataX
import com.wsyzj.wanandroidkotlin.business.dialog.LoginDialog
import com.wsyzj.wanandroidkotlin.business.manager.IntentManager
import com.wsyzj.wanandroidkotlin.business.widget.GlideImageLoader
import com.wsyzj.wanandroidkotlin.common.base.BaseFragment
import com.wsyzj.wanandroidkotlin.common.constant.Constant
import com.wsyzj.wanandroidkotlin.common.http.BaseRequest
import com.wsyzj.wanandroidkotlin.common.http.BaseSchedulers
import com.wsyzj.wanandroidkotlin.common.utils.IContextCompat
import com.wsyzj.wanandroidkotlin.common.widget.BasePullToRefreshView
import com.youth.banner.Banner

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

    @BindView(R.id.base_pull_refresh)
    lateinit var base_pull_refresh: BasePullToRefreshView

    var pageNumber: Int = 0
    var articles: MutableList<DataX>? = null
    var homeAdapter: HomeAdapter? = null

    override fun layoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView() {

    }

    override fun initListener() {
        base_pull_refresh.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
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
        setHomeList(null)
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
                var banner = headerView.findViewById<Banner>(R.id.banner)

                banner.setImageLoader(GlideImageLoader())
                    .setImages(getBannerPahts(list))
                    .setOnBannerListener {
                        IntentManager.webview(activity, list[it].url, list[it].id)
                    }
                    .start()

                if (base_pull_refresh.getHeaderLayoutCount() == 0) {
                    base_pull_refresh.addHeaderView(headerView)
                }
            }
        }
    }

    fun getBannerPahts(list: MutableList<DataBanner>): List<String> {
        if (list.size == 0) {
            return listOf()
        }
        var paths = mutableListOf<String>()

        for (dataBanner in list) {
            paths.add(dataBanner.imagePath)
        }
        return paths
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
                            if (articles == null || refreshing) {
                                articles = list
                            } else {
                                articles?.addAll(list)
                            }
                            base_pull_refresh.finishRefresh()
                            base_pull_refresh.finishLoadMore()
                            base_pull_refresh.setNoMoreData(20 < list.size)
                            setHomeList(articles!!)
                        }
                    }
                }
        addDisposable(subscribe)
    }

    /**
     * 设置首页数据
     */
    fun setHomeList(articles: MutableList<DataX>?) {
        if (homeAdapter == null) {
            homeAdapter = HomeAdapter(articles)
            base_pull_refresh.setLayoutManager(LinearLayoutManager(activity))
            base_pull_refresh.setAdapter(homeAdapter!!)
        } else {
            homeAdapter?.setNewData(articles)
        }

        homeAdapter?.setOnItemClickListener { adapter, view, position ->
            IntentManager.webview(
                activity,
                articles?.get(position)?.link,
                articles?.get(position)?.id
            )
        }
    }
}
