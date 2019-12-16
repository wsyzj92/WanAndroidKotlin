package com.wsyzj.wanandroidkotlin.common.widget

import android.app.Activity
import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.widget.DialogTitle
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.wsyzj.wanandroidkotlin.R
import java.time.format.DecimalStyle
import java.util.jar.Attributes

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : wsyzj_92@163.com
 *     time   : 2019/12/13
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class BaseNavigation : LinearLayout {

    @BindView(R.id.iv_back)
    lateinit var iv_back: ImageView

    @BindView(R.id.tv_title)
    lateinit var tv_title: TextView

    @BindView(R.id.view_divider)
    lateinit var view_divider: View

    private var onBackClickListener: OnClickListener? = null

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        init(context)
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int)
            : super(context, attributeSet, defStyleAttr) {
        init(context)
    }

    fun init(context: Context) {
        val view = View.inflate(context, R.layout.widget_base_navigation, null)
        orientation = VERTICAL
        addView(view)
        ButterKnife.bind(this)
    }

    fun setTitle(title: String): TextView {
        tv_title.text = title
        return tv_title
    }

    /**
     * 复写返回按键
     */
    fun setOnBackClickListener(onBackClickListener: OnClickListener?) {
        this.onBackClickListener = onBackClickListener
    }

    @OnClick(R.id.iv_back)
    fun onClick(view: View) {
        when (view.id) {
            R.id.iv_back -> {
                // 返回
                if (onBackClickListener != null) {
                    onBackClickListener?.onClick(view)
                } else {
                    if (context is Activity) {
                        (context as Activity).finish()
                    }
                }
            }
        }
    }
}