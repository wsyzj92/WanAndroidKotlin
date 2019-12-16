package com.wsyzj.wanandroidkotlin.common

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import com.wsyzj.wanandroidkotlin.R

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : wsyzj_92@163.com
 *     time   : 2019/12/13
 *     desc   :
 *     version: 1.0
 * </pre>
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layout()
    }

    /**
     * 初始化布局，包括 统一的标题栏，界面的状态布局，contentView布局
     */
    fun layout() {
        val parent: ViewGroup = findViewById<ViewGroup>(android.R.id.content)
        parent.removeAllViews()

        // contentView布局
        val contentView: View = View.inflate(this, contentView(), null)
        contentView.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        parent.addView(contentView)
        ButterKnife.bind(this, contentView)
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