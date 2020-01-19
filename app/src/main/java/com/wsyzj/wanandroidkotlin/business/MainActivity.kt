package com.wsyzj.wanandroidkotlin.business

import android.view.KeyEvent
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import butterknife.BindView
import com.blankj.utilcode.util.CleanUtils
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.wsyzj.wanandroidkotlin.R
import com.wsyzj.wanandroidkotlin.business.adapter.ViewPagerTabAdapter
import com.wsyzj.wanandroidkotlin.business.fragment.HomeFragment
import com.wsyzj.wanandroidkotlin.business.fragment.ProjectFragment
import com.wsyzj.wanandroidkotlin.business.utils.StorageUtils
import com.wsyzj.wanandroidkotlin.common.base.BaseActivity
import com.wsyzj.wanandroidkotlin.common.mvp.BaseIModel
import com.wsyzj.wanandroidkotlin.common.mvp.BaseIView
import com.wsyzj.wanandroidkotlin.common.mvp.BasePresenter
import com.wsyzj.wanandroidkotlin.common.widget.StatusLayout
import skin.support.SkinCompatManager

/**
 * 主界面
 */
class MainActivity : BaseActivity<BasePresenter<BaseIView, BaseIModel>>(),
    NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.drawer_layout)
    lateinit var drawer_layout: DrawerLayout

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.tab_layout)
    lateinit var tab_layout: TabLayout

    @BindView(R.id.view_pager)
    lateinit var view_pager: ViewPager

    @BindView(R.id.navigation_view)
    lateinit var navigation_view: NavigationView

    override fun presenter(): BasePresenter<BaseIView, BaseIModel>? {
        return null
    }

    override fun layoutId() = R.layout.activity_main

    override fun initView() {
        baseNavigationView.visibility = View.GONE
        baseStatusLayout.setStatusLayout(StatusLayout.SUCCESS)

        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        navigation_view.setNavigationItemSelectedListener(this)
    }

    override fun initListener() {

    }

    override fun initData() {
        loadMainPage()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
                drawer_layout.closeDrawer(GravityCompat.START)
                return false
            } else {
                moveTaskToBack(false)
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    /**
     * 主界面的几个page
     */
    private fun loadMainPage() {
        var fragments: ArrayList<Fragment> = arrayListOf()
        fragments.add(HomeFragment())
        fragments.add(ProjectFragment())

        var tabs: ArrayList<String> = arrayListOf()
        tabs.add("首页")
        tabs.add("项目")

        view_pager.adapter = ViewPagerTabAdapter(supportFragmentManager, fragments, tabs)
        tab_layout.setupWithViewPager(view_pager)
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        val id = p0.getItemId()
        when (id) {
            R.id.nav_collect -> {
                // 收藏
            }
            R.id.nav_day_night -> {
                // 日间/夜间模式切换
                val nightMode = StorageUtils.isNightMode()
                if (nightMode) {
                    p0.title = "日间模式"
                    SkinCompatManager.getInstance().restoreDefaultTheme()
                } else {
                    p0.title = "夜间模式"
                    SkinCompatManager.getInstance()
                        .loadSkin("night", null, SkinCompatManager.SKIN_LOADER_STRATEGY_BUILD_IN)
                }
                StorageUtils.setNightMode(!nightMode)
            }
            R.id.nav_clear_cache -> {
                // 清楚缓存
                clearCache()
            }
            R.id.nav_exit -> {
                // 退出登陆
                StorageUtils.setLoginStatus(false)
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    /**
     * 清理缓存
     */
    fun clearCache() {
        CleanUtils.cleanExternalCache()
    }
}
