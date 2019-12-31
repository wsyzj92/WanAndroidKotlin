package com.wsyzj.wanandroidkotlin.business

import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager.widget.ViewPager
import butterknife.BindView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.wsyzj.wanandroidkotlin.R
import com.wsyzj.wanandroidkotlin.business.adapter.ViewPagerTabAdapter
import com.wsyzj.wanandroidkotlin.business.fragment.HomeFragment
import com.wsyzj.wanandroidkotlin.common.base.BaseActivity
import com.wsyzj.wanandroidkotlin.common.mvp.BaseIModel
import com.wsyzj.wanandroidkotlin.common.mvp.BaseIView
import com.wsyzj.wanandroidkotlin.common.mvp.BasePresenter
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 主界面
 */
class MainActivity : BaseActivity<BasePresenter<BaseIView, BaseIModel>>() {

    @BindView(R.id.drawer_layout)
    lateinit var drawerLayout: DrawerLayout

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.tab_layout)
    lateinit var tab_layout: TabLayout

    @BindView(R.id.view_pager)
    lateinit var view_pager: ViewPager

    @BindView(R.id.navigation)
    lateinit var navigation: NavigationView

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun presenter(): BasePresenter<BaseIView, BaseIModel>? {
        return null
    }

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        setSupportActionBar(toolbar)
        val coordinator_layout = findNavController(R.id.coordinator_layout)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send
            ), drawerLayout
        )
//        setupActionBarWithNavController(coordinator_layout, appBarConfiguration)
//        navigation.setupWithNavController(coordinator_layout)
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
        fragments.add(HomeFragment())

        var tabs: ArrayList<String> = arrayListOf()
        tabs.add("首页")
        tabs.add("推荐")

        view_pager.adapter = ViewPagerTabAdapter(supportFragmentManager, fragments, tabs)
        tab_layout.setupWithViewPager(view_pager)
    }
}
