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

    @BindView(R.id.iv_collect)
    lateinit var iv_collect: ImageView

    var onBackClickListener: OnClickListener? = null
    var onCollectClickListener: OnClickListener? = null


    fun setBackClickListener(listener: OnClickListener) {
        onBackClickListener = listener
    }

    fun setCollectClickListener(listener: OnClickListener) {
        onCollectClickListener = listener
    }

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView()
    }

    private fun initView() {
        val view = LayoutInflater.from(context)?.inflate(R.layout.widget_base_navigation, null)
        addView(view)
        ButterKnife.bind(this)
    }

    fun setCollectImageResource(resId: Int) {
        iv_collect.setImageResource(resId)
    }

    @OnClick(R.id.iv_back, R.id.iv_collect)
    fun onClick(view: View) {
        when (view.id) {
            R.id.iv_back -> {
                // 返回键
                if (onBackClickListener != null) {
                    onBackClickListener?.onClick(view)
                } else {
                    val activity = context as Activity
                    activity.finish()
                }
            }
            R.id.iv_collect -> {
                // 收藏
                if (onCollectClickListener != null) {
                    onCollectClickListener?.onClick(view)
                }
            }
        }
    }
}