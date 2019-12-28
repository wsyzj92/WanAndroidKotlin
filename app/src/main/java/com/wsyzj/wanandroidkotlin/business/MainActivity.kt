package com.wsyzj.wanandroidkotlin.business

import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import butterknife.BindView
import com.google.android.material.tabs.TabLayout
import com.wsyzj.wanandroidkotlin.R
import com.wsyzj.wanandroidkotlin.business.fragment.HomeFragment
import com.wsyzj.wanandroidkotlin.common.base.BaseActivity
import com.wsyzj.wanandroidkotlin.common.http.BaseRequest
import com.wsyzj.wanandroidkotlin.common.mvp.BaseIModel
import com.wsyzj.wanandroidkotlin.common.mvp.BaseIView
import com.wsyzj.wanandroidkotlin.common.mvp.BasePresenter
import org.reactivestreams.Subscriber

/**
 * 主界面
 */
class MainActivity : BaseActivity<BasePresenter<BaseIView, BaseIModel>>() {

    @BindView(R.id.tab_layout)
    lateinit var tab_layout: TabLayout

    @BindView(R.id.view_pager)
    lateinit var view_pager: ViewPager

    override fun presenter(): BasePresenter<BaseIView, BaseIModel>? {
        return null
    }

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {

    }

    override fun initListener() {

    }

    override fun initData() {
        loadMainPage()
    }

    /**
     * 主界面的几个page
     */
    private fun loadMainPage() {
        var fragments: ArrayList<Fragment> = arrayListOf()
        fragments.add(HomeFragment())
    }
}
