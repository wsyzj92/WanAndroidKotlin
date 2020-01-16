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
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.wsyzj.wanandroidkotlin.R
import com.wsyzj.wanandroidkotlin.business.adapter.ViewPagerTabAdapter
import com.wsyzj.wanandroidkotlin.business.fragment.HomeFragment
import com.wsyzj.wanandroidkotlin.common.base.BaseActivity
import com.wsyzj.wanandroidkotlin.common.mvp.BaseIModel
import com.wsyzj.wanandroidkotlin.common.mvp.BaseIView
import com.wsyzj.wanandroidkotlin.common.mvp.BasePresenter

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

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        baseNavigationView.visibility = View.GONE
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
        fragments.add(HomeFragment())

        var tabs: ArrayList<String> = arrayListOf()
        tabs.add("首页")
        tabs.add("推荐")

        view_pager.adapter = ViewPagerTabAdapter(supportFragmentManager, fragments, tabs)
        tab_layout.setupWithViewPager(view_pager)
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        val id = p0.getItemId()
        if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_gallery) {

        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
