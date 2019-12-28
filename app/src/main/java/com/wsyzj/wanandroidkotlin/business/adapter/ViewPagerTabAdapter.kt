package com.wsyzj.wanandroidkotlin.business.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : wsyzj_92@163.com
 *     time   : 2019/12/27
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class ViewPagerTabAdapter(fm: FragmentManager, val fragments: ArrayList<Fragment>? = arrayListOf<Fragment>(), val titles: List<String>) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return fragments?.get(position)!!
    }

    override fun getCount(): Int {
        return fragments?.size ?: 0
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }
}