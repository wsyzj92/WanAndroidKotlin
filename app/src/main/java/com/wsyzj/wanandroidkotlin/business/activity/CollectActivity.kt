package com.wsyzj.wanandroidkotlin.business.activity

import android.annotation.SuppressLint
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.BindView
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.wsyzj.wanandroidkotlin.R
import com.wsyzj.wanandroidkotlin.business.adapter.HomeAdapter
import com.wsyzj.wanandroidkotlin.business.bean.DataX
import com.wsyzj.wanandroidkotlin.business.constant.EventBusConstant
import com.wsyzj.wanandroidkotlin.business.manager.IntentManager
import com.wsyzj.wanandroidkotlin.common.base.BaseActivity
import com.wsyzj.wanandroidkotlin.common.base.BaseEventBus
import com.wsyzj.wanandroidkotlin.common.constant.Constant
import com.wsyzj.wanandroidkotlin.common.http.BaseRequest
import com.wsyzj.wanandroidkotlin.common.http.BaseSchedulers
import com.wsyzj.wanandroidkotlin.common.mvp.BaseIModel
import com.wsyzj.wanandroidkotlin.common.mvp.BaseIView
import com.wsyzj.wanandroidkotlin.common.mvp.BasePresenter
import com.wsyzj.wanandroidkotlin.common.widget.BasePullToRefreshView

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

    @BindView(R.id.pull_to_refresh)
    lateinit var pull_to_refresh: BasePullToRefreshView

    var pageNumber: Int = 0
    var collectList: MutableList<DataX>? = null
    var homeAdapter: HomeAdapter? = null

    override fun layoutId() = R.layout.layout_pull_to_refresh

    override fun presenter(): BasePresenter<BaseIView, BaseIModel>? {
        return null
    }

    override fun initView() {
        baseNavigationView.setTitle("收藏")
    }

    override fun initData() {
        getCollectList(true)
    }

    override fun initListener() {
        pull_to_refresh.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onRefresh(refreshLayout: RefreshLayout) {
                getCollectList(true)
            }

            override fun onLoadMore(refreshLayout: RefreshLayout) {
                getCollectList(false)
            }
        })
    }

    /**
     * 获取首页列表
     */
    @SuppressLint("CheckResult")
    fun getCollectList(refreshing: Boolean) {
        if (refreshing) {
            pageNumber = 0
        } else {
            pageNumber++
        }

        val subscribe =
            BaseRequest.instance.service.getCollectList(pageNumber)
                .compose(BaseSchedulers.io_main())
                .subscribe() {
                    if (it.errorCode == Constant.HTTP_CODE) {
                        it.let {
                            var list = it.data?.datas!!
                            if (collectList == null || refreshing) {
                                collectList = list
                            } else {
                                collectList?.addAll(list)
                            }
                            pull_to_refresh.finishRefresh()
                            pull_to_refresh.finishLoadMore()
                            pull_to_refresh.setNoMoreData(20 < list.size)
                            baseStatusLayout.setStatusLayout(null, collectList)
                            setHomeList(collectList!!)
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
            pull_to_refresh.setLayoutManager(LinearLayoutManager(this))
            pull_to_refresh.setAdapter(homeAdapter!!)
        } else {
            homeAdapter?.setNewData(articles)
        }

        homeAdapter?.setOnItemClickListener { adapter, view, position ->
            IntentManager.webview(
                this,
                articles[position].link,
                articles[position].id,
                true
            )
        }
    }

    override fun receiveEvent(event: BaseEventBus) {
        super.receiveEvent(event)
        if (event.code == EventBusConstant.EVENT_COLLECT) {
            getCollectList(true)
        }
    }
}