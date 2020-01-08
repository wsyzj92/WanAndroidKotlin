package com.wsyzj.wanandroidkotlin.business.widget

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.wsyzj.wanandroidkotlin.R

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : wsyzj_92@163.com
 *     time   : 2020/01/08
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class BaseNavigationView : FrameLayout {

    @BindView(R.id.iv_back)
    lateinit var iv_back: ImageView

    constructor(context: Context) : super(context) {
        inits()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        inits()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        inits()
    }

    private fun inits() {
        val view = LayoutInflater.from(context)?.inflate(R.layout.widget_base_navigation, null)
        addView(view)
        ButterKnife.bind(this)
    }

    @OnClick(R.id.iv_back)
    fun onClick(view: View) {
        when (view.id) {
            R.id.iv_back -> {
                val activity = context as Activity
                activity.finish()
            }
        }
    }
}