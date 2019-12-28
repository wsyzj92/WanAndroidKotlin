package com.wsyzj.wanandroidkotlin.business.fragment

import android.annotation.SuppressLint
import androidx.annotation.Nullable
import butterknife.BindView
import com.wsyzj.wanandroidkotlin.R
import com.wsyzj.wanandroidkotlin.business.bean.Article
import com.wsyzj.wanandroidkotlin.business.mvp.HomeContract
import com.wsyzj.wanandroidkotlin.business.mvp.HomePresenter
import com.wsyzj.wanandroidkotlin.common.base.BaseFragment
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
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class HomeFragment : BaseFragment() {

    @BindView(R.id.base_pull_refresh)
    lateinit var base_pull_refresh: BasePullToRefreshView

    var pageNumber: Int = 0

    override fun layoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView() {

    }

    override fun initListener() {

    }

    override fun initData() {
        getHomeList()
    }

    /**
     * 获取首页列表
     */
    @SuppressLint("CheckResult")
    private fun getHomeList() {
        var subscribe = object : BaseSubScriber<BaseEntity<Article>>() {
            override fun onFailure(throwable: Throwable?) {

            }

            override fun onSuccess(t: BaseEntity<Article>?, msg: String) {

            }
        }

//        BaseRequest
//            .instance
//            .service
//            .getHomeList(pageNumber)
//            .compose(BaseSchedulers.io_main())
//            .subscribeWith<BaseEntity<Article>>(subscribe)

//        val subscriber = mModel.indexBlockVoList()
//            .subscribeWith<>(object : BaseSubscriber<HomeCourse>() {
//                override fun onSuccess(data: HomeCourse, successMsg: String) {
//                    mView.setCourseList(data.multiItemEntity)
//                    mView.finishRefresh()
//                }
//
//                override fun onFailure(throwable: Throwable) {
//                    mView.finishRefresh()
//                }
//            })
//        mView.addDisposable(subscriber)
    }
}
