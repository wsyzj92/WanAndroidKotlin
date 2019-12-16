package com.wsyzj.wanandroidkotlin.common

import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import com.blankj.utilcode.util.AdaptScreenUtils
import com.wsyzj.wanandroidkotlin.R
import com.wsyzj.wanandroidkotlin.common.widget.BaseNavigation

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : wsyzj_92@163.com
 *     time   : 2019/12/13
 *     desc   :
 *     version: 1.0
 * </pre>
 */
open abstract class BaseActivity : AppCompatActivity() {

    protected var baseNavigation: BaseNavigation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layout()
    }

    /**
     * 初始化布局，包括 统一的标题栏，界面的状态布局，contentView布局
     */
    fun layout() {
        var viewGroup: ViewGroup = findViewById<ViewGroup>(android.R.id.content)
        viewGroup.removeAllViews()

        var rootView: LinearLayout = LinearLayout(this)
        rootView.orientation = LinearLayout.VERTICAL
        viewGroup.addView(rootView)

        // 导航栏
        baseNavigation = BaseNavigation(this)
        baseNavigation?.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        rootView.addView(baseNavigation)

        // contentView布局
        val contentView: View = View.inflate(this, contentView(), null)
        contentView.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        rootView.addView(contentView)
        ButterKnife.bind(this, contentView)
    }

    /**
     * 屏幕适配 1080
     */
    override fun getResources(): Resources {
        return AdaptScreenUtils.adaptWidth(super.getResources(), 1080)
    }

    /**
     * 返回布局id
     */
    abstract fun contentView(): Int

    /**
     * 初始化布局
     */
    abstract fun initView()

    /**
     * 初始化数据
     */
    abstract fun initData()
}