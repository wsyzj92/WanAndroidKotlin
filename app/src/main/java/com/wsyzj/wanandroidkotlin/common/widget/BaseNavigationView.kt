package com.wsyzj.wanandroidkotlin.common.widget

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.DialogTitle
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

    @BindView(R.id.tv_title)
    lateinit var tv_title: TextView

    @BindView(R.id.iv_collect)
    lateinit var iv_collect: ImageView

    @BindView(R.id.iv_share)
    lateinit var iv_share: ImageView

    var onBackClickListener: OnClickListener? = null
    var onCollectClickListener: OnClickListener? = null
    var onShareClickListener: OnClickListener? = null


    fun setBackClickListener(listener: OnClickListener) {
        onBackClickListener = listener
    }

    fun setCollectClickListener(listener: OnClickListener) {
        onCollectClickListener = listener
    }

    fun setShareClickListener(listener: OnClickListener) {
        onShareClickListener = listener
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

    /**
     * 设置标题
     */
    fun setTitle(title: String) {
        tv_title.setText(title)
    }

    /**
     * 分享 - 右边第一个
     */
    fun setShareImageResource(resId: Int) {
        iv_share.setImageResource(resId)
        iv_share.visibility = View.VISIBLE
    }

    /**
     * 收藏 - 右边第二个
     */
    fun setCollectImageResource(resId: Int) {
        iv_collect.setImageResource(resId)
        iv_collect.visibility = View.VISIBLE
    }

    @OnClick(R.id.iv_back, R.id.iv_collect, R.id.iv_share)
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
            R.id.iv_share -> {
                // 分享
                if (onShareClickListener != null) {
                    onShareClickListener?.onClick(view)
                }
            }
        }
    }
}