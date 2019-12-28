package com.wsyzj.wanandroidkotlin.business.fragment

import android.annotation.SuppressLint
import android.widget.LinearLayout
import androidx.annotation.Nullable
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.BindView
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.wsyzj.wanandroidkotlin.R
import com.wsyzj.wanandroidkotlin.business.adapter.HomeAdapter
import com.wsyzj.wanandroidkotlin.business.bean.Article
import com.wsyzj.wanandroidkotlin.business.bean.DataX
import com.wsyzj.wanandroidkotlin.business.mvp.HomeContract
import com.wsyzj.wanandroidkotlin.business.mvp.HomePresenter
import com.wsyzj.wanandroidkotlin.common.base.BaseFragment
import com.wsyzj.wanandroidkotlin.common.constant.Constant
import com.wsyzj.wanandroidkotlin.common.http.BaseEntity
import com.wsyzj.wanandroidkotlin.common.http.BaseRequest
import com.wsyzj.wanandroidkotlin.common.http.BaseSchedulers
import com.wsyzj.wanandroidkotlin.common.http.BaseSubScriber
import com.wsyzj.wanandroidkotlin.common.mvp.BaseIModel
import com.wsyzj.wanandroidkotlin.common.mvp.BaseIPresenter
import com.wsyzj.wanandroidkotlin.common.mvp.BaseIView
import com.wsyzj.wanandroidkotlin.common.mvp.BasePresenter
import com.wsyzj.wanandroidkotlin.common.widget.BasePullToRefreshView
import io.reactivex.Flowable
import kotlinx.android.synthetic.main.fragment_home.view.*

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
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                getHomeList(false)
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                getHomeList(true)
            }
        })
    }

    override fun initData() {
        getHomeList(true)
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
            BaseRequest.instance.service.getHomeList(pageNumber).compose(BaseSchedulers.io_main()).subscribe() {
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
    fun setHomeList(articles: MutableList<DataX>) {
        if (homeAdapter == null) {
            homeAdapter = HomeAdapter(articles)
            base_pull_refresh.setLayoutManager(LinearLayoutManager(activity))
            base_pull_refresh.setAdapter(homeAdapter!!)
        } else {
            homeAdapter?.setNewData(articles)
        }
    }
}
