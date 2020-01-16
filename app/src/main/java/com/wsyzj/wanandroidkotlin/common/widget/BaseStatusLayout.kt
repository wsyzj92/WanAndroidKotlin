package com.wsyzj.wanandroidkotlin.common.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import butterknife.BindView
import butterknife.ButterKnife
import com.blankj.utilcode.util.NetworkUtils
import com.wsyzj.wanandroidkotlin.R

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : wsyzj_92@163.com
 *     time   : 2020/01/16
 *     desc   : 界面不同的状态布局
 *     version: 1.0
 * </pre>
 */
class BaseStatusLayout : FrameLayout {

    @BindView(R.id.pb_progress)
    lateinit var pb_progress: ProgressBar

    @BindView(R.id.tv_error)
    lateinit var tv_error: ProgressBar

    @BindView(R.id.tv_empty)
    lateinit var tv_empty: ProgressBar

    constructor(context: Context) : super(context) {
        initView()
        initStatusLayout()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView()
        initStatusLayout()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView()
        initStatusLayout()
    }

    fun initView() {
        var view = LayoutInflater.from(context).inflate(R.layout.widget_base_state_layout, null)
        addView(view)
        ButterKnife.bind(this)
    }

    /**
     * 初始化状态
     */
    fun initStatusLayout() {
        if (!NetworkUtils.isConnected()) {
            setStatusLayout(StatusLayout.ERROR)
        }
    }

    /**
     *
     */
    fun setStatusLayout(statusLayout: StatusLayout) {
        when (statusLayout) {
            StatusLayout.LOADING -> showLoading()
            StatusLayout.ERROR -> showError()
            StatusLayout.EMPTY -> showEmpty()
            StatusLayout.SUCCESS -> showSuccess()
        }
    }

    /**
     * 显示loading
     */
    fun showLoading() {
        pb_progress.visibility = View.VISIBLE
        tv_error.visibility = View.GONE
        tv_empty.visibility = View.GONE
        visibility = View.VISIBLE
    }

    /**
     * 显示异常界面
     */
    fun showError() {
        pb_progress.visibility = View.GONE
        tv_error.visibility = View.VISIBLE
        tv_empty.visibility = View.GONE
        visibility = View.VISIBLE
    }

    /**
     * 显示空界面
     */
    fun showEmpty() {
        pb_progress.visibility = View.GONE
        tv_error.visibility = View.GONE
        tv_empty.visibility = View.VISIBLE
        visibility = View.VISIBLE
    }

    /**
     * 成功界面
     */
    fun showSuccess() {
        pb_progress.visibility = View.GONE
        tv_error.visibility = View.GONE
        tv_empty.visibility = View.GONE
        visibility = View.GONE
    }

}