package com.wsyzj.wanandroidkotlin.common.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.SpannableStringBuilder
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

import com.blankj.utilcode.util.NetworkUtils
import com.blankj.utilcode.util.ScreenUtils
import com.blankj.utilcode.util.SpanUtils
import com.wsyzj.wanandroidkotlin.R
import com.wsyzj.wanandroidkotlin.common.http.BaseServiceResponseThrowable
import com.wsyzj.wanandroidkotlin.common.http.ImageLoader
import com.wsyzj.wanandroidkotlin.common.utils.UiUtils
import com.wsyzj.wanandroidkotlin.common.utils.UiUtils.getDimension


/**
 * <pre>
 * author : 焦洋
 * e-mail : jiao35478729@163.com
 * time   : 2018/04/10
 * desc   : 根据界面状态加载不同的布局
</pre> *
 */
class BaseStateLayout : FrameLayout, View.OnClickListener {

    private var ll_loading: LinearLayout? = null
    private var tv_loading: TextView? = null
    private var iv_loading: ImageView? = null
    private var ll_error: LinearLayout? = null
    private var tv_error_text: TextView? = null
    private var tv_error: TextView? = null
    private var tv_empty: TextView? = null

    private var mContext: Context? = null
    private var mStateView: View? = null
    private val mLoadingTopMargin: Float = 0.toFloat()
    private val mErrorTopMargin: Float = 0.toFloat()
    private val mEmptyTopMargin: Float = 0.toFloat()

    private var mOnStateErrorListener: OnStateErrorListener? = null
    private var mOnStateEmptyListener: OnStateEmptyListener? = null


    fun setOnStateErrorListener(onStateErrorListener: OnStateErrorListener) {
        mOnStateErrorListener = onStateErrorListener
    }

    fun setOnStateEmptyListener(onStateEmptyListener: OnStateEmptyListener) {
        mOnStateEmptyListener = onStateEmptyListener
    }

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    private fun init(context: Context) {
        mContext = context
        mStateView = UiUtils.inflate(R.layout.widget_base_state_layout)

        ll_loading = mStateView!!.findViewById(R.id.ll_loading)
        tv_loading = mStateView!!.findViewById(R.id.tv_loading)
        iv_loading = mStateView!!.findViewById(R.id.iv_loading)
        ll_error = mStateView!!.findViewById(R.id.ll_error)
        tv_error_text = mStateView!!.findViewById(R.id.tv_error_text)
        tv_error = mStateView!!.findViewById(R.id.tv_error)
        tv_empty = mStateView!!.findViewById(R.id.tv_empty)

        tv_error!!.setOnClickListener(this)
        tv_empty!!.setOnClickListener(this)

        setErrorText("数据加载失败", "请检查你的手机是否联网")
        ImageLoader.with(mContext, R.drawable.empty_loading, iv_loading!!)
        addView(
            mStateView,
            FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtils.getScreenHeight())
        )
        setErrorNetState()
    }

    override fun onClick(view: View) {
        val connected = NetworkUtils.isConnected()

        val id = view.id
        if (id == R.id.tv_error) {
            if (mOnStateErrorListener != null) {
                setStateLayout(StateLayout.STATE_LOADING)

                if (!connected) {
                    ll_error!!.postDelayed({ setStateLayout(StateLayout.STATE_ERROR) }, NO_NETWORK_ANIM_TIME.toLong())
                }

                if (connected) {
                    mOnStateErrorListener!!.onStateError()
                }
            }
        } else if (id == R.id.tv_empty) {
            if (mOnStateEmptyListener != null) {
                setStateLayout(StateLayout.STATE_LOADING)

                if (!connected) {
                    tv_empty!!.postDelayed({ setStateLayout(StateLayout.STATE_EMPTY) }, NO_NETWORK_ANIM_TIME.toLong())
                }

                if (connected) {
                    mOnStateEmptyListener!!.onStateEmpty()
                }
            }
        }
    }

    /**
     * 设置加载中文字
     *
     * @param charSequence
     */
    fun setLoadingText(charSequence: CharSequence) {
        if (tv_loading != null) {
            tv_loading!!.text = charSequence
        }
    }

    /**
     * 加载中界面与顶部的距离
     */
    fun setLoadingTopMargin(dimenId: Int) {
        if (ll_loading != null) {
            val params = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT,
                Gravity.CENTER_HORIZONTAL
            )
            params.topMargin = getDimension(dimenId)
            ll_loading!!.layoutParams = params
        }
    }

    /**
     * 设置空数据文本描述
     */
    fun setEmptyText(charSequence: CharSequence) {
        if (tv_empty != null) {
            tv_empty!!.text = charSequence
        }
    }

    /**
     * 空界面与顶部的距离
     */
    fun setEmptyTopMargin(dimenId: Int) {
        if (tv_empty != null) {
            val params = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT,
                Gravity.CENTER_HORIZONTAL
            )
            params.topMargin = getDimension(dimenId)
            tv_empty!!.layoutParams = params
        }
    }

    /**
     * 设置异常文本
     */
    fun setErrorText(first: CharSequence, second: CharSequence) {
        if (tv_error_text != null) {
            tv_error_text!!.text = getErrorText(first, second)
        }
    }

    /**
     * 设置异常加载的文字，文字大小不一样
     *
     * @param first
     * @param second
     * @return
     */
    private fun getErrorText(first: CharSequence, second: CharSequence): SpannableStringBuilder {
        return SpanUtils()
            .appendLine(first)
            .setFontSize(28)
            .append(second)
            .setFontSize(24)
            .create()
    }

    /**
     * 异常界面与顶部的距离
     */
    fun setErrorTopMargin(dimenId: Int) {
        if (ll_error != null) {
            val params = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT,
                Gravity.CENTER_HORIZONTAL
            )
            params.topMargin = getDimension(dimenId)
            ll_error!!.layoutParams = params
        }
    }

    /**
     * 设置空界面的图片
     *
     * @param drawableId
     */
    fun setEmptyDrawable(drawableId: Int) {
        tv_empty!!.setCompoundDrawables(null, boundsDrawable(drawableId), null, null)
    }

    /**
     * 设置异常界面的图片
     *
     * @param drawableId
     */
    fun setErrorDrawable(drawableId: Int) {
        tv_error_text!!.setCompoundDrawables(null, boundsDrawable(drawableId), null, null)
    }

    /**
     * 获取一张可以显示的PaddingDrawable
     *
     * @param drawableId
     * @return
     */
    fun boundsDrawable(drawableId: Int): Drawable {
        val drawable = mContext!!.resources.getDrawable(drawableId)
        drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
        return drawable
    }

    /**
     * 设置异常网络状态
     */
    private fun setErrorNetState() {
        if (!NetworkUtils.isConnected()) {
            setStateLayout(StateLayout.STATE_ERROR)
        }
    }

    /**
     * 根据不同状态显示不同布局
     *
     * @param stateLayout
     */
    fun setStateLayout(stateLayout: StateLayout) {
        when (stateLayout) {
            StateLayout.STATE_LOADING -> showLoading()
            StateLayout.STATE_ERROR -> showError()
            StateLayout.STATE_EMPTY -> showEmpty()
            StateLayout.STATE_SUCCESS -> showSuccess()
            else -> {
            }
        }
    }

    /**
     * 根据数据来判断界面加载状态
     *
     * @param throwable
     * @param data
     */
    fun setStateLayout(throwable: Throwable?, data: Any?) {
        if (data == null && !NetworkUtils.isConnected()) {
            showError()
        } else if (data == null && throwable != null && throwable is BaseServiceResponseThrowable) {
            showError()
        } else if (data == null && throwable != null) {
            showError()
        } else if (data == null) {
            showEmpty()
        } else {
            showSuccess()
        }
    }

    /**
     * 根据集合来判断界面加载的状态
     *
     * @param throwable
     * @param list
     */
    fun setStateLayout(throwable: Throwable?, list: List<*>?) {
        if (list == null && !NetworkUtils.isConnected()) {
            showError()
        } else if (list == null && throwable != null && throwable is BaseServiceResponseThrowable) {
            showError()
        } else if (list == null && throwable != null) {
            showError()
        } else if (list == null || list.size == 0) {
            showEmpty()
        } else {
            showSuccess()
        }
    }

    /**
     * 加载中
     */
    private fun showLoading() {
        ll_loading!!.visibility = View.VISIBLE
        ll_error!!.visibility = View.INVISIBLE
        tv_empty!!.visibility = View.INVISIBLE
        mStateView!!.visibility = View.VISIBLE
    }

    /**
     * 异常界面
     */
    private fun showError() {
        ll_loading!!.visibility = View.INVISIBLE
        ll_error!!.visibility = View.VISIBLE
        tv_empty!!.visibility = View.INVISIBLE
        mStateView!!.visibility = View.VISIBLE
    }

    /**
     * 空界面
     */
    private fun showEmpty() {
        ll_loading!!.visibility = View.INVISIBLE
        ll_error!!.visibility = View.INVISIBLE
        tv_empty!!.visibility = View.VISIBLE
        mStateView!!.visibility = View.VISIBLE
    }

    /**
     * 成功状态
     */
    private fun showSuccess() {
        ll_loading!!.visibility = View.INVISIBLE
        ll_error!!.visibility = View.INVISIBLE
        tv_empty!!.visibility = View.INVISIBLE
        mStateView!!.visibility = View.GONE
    }

    interface OnStateErrorListener {
        fun onStateError()
    }

    interface OnStateEmptyListener {
        fun onStateEmpty()
    }

    companion object {
        // 判断网络状态时，有个默认的加载动画
        const val NO_NETWORK_ANIM_TIME = 300
    }
}
