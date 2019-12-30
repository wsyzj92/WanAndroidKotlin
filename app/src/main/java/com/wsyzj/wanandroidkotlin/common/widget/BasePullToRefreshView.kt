package com.wsyzj.wanandroidkotlin.common.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.wsyzj.wanandroidkotlin.R

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : wsyzj_92@163.com
 *     time   : 2019/12/23
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class BasePullToRefreshView : LinearLayout {

    @BindView(R.id.smart_refresh)
    lateinit var smart_refresh: SmartRefreshLayout

    @BindView(R.id.recycler_view)
    lateinit var recycler_view: RecyclerView

    var recyclerAdapter: BaseQuickAdapter<*, *>? = null

    constructor(context: Context?) : super(context) {
        init(context)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    fun init(context: Context?) {
        val view = LayoutInflater.from(context).inflate(R.layout.widget_base_pull_to_refresh, null)
        addView(
            view,
            FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        )
        ButterKnife.bind(this)
    }

    fun getSmartRefreshView(): SmartRefreshLayout {
        return smart_refresh
    }

    /**
     * 是否启用下拉刷新（默认启用）
     */
    fun setEnableRefresh(enabled: Boolean) {
        smart_refresh.setEnableRefresh(enabled)
    }

    /**
     * 设置是否启用上拉加载更多（默认启用）
     */
    fun setEnableLoadMore(enabled: Boolean) {
        smart_refresh.setEnableLoadMore(enabled)
    }

    /**
     * 显示刷新动画并且触发刷新事件
     */
    fun autoRefresh() {
        smart_refresh.autoRefresh()
    }

    /**
     * 单独设置刷新监听器
     */
    fun setOnRefreshListener(listener: OnRefreshListener) {
        smart_refresh.setOnRefreshListener(listener)
    }

    /**
     * 完成刷新
     */
    fun finishRefresh() {
        smart_refresh.finishRefresh()
    }

    /**
     * 单独设置加载监听器
     */
    fun setOnLoadMoreListener(listener: OnLoadMoreListener) {
        smart_refresh.setOnLoadMoreListener(listener)
    }

    /**
     * 完成加载
     */
    fun finishLoadMore() {
        smart_refresh.finishLoadMore()
    }

    /**
     * 同时设置刷新和加载监听器
     */
    fun setOnRefreshLoadMoreListener(listener: OnRefreshLoadMoreListener) {
        smart_refresh.setOnRefreshLoadMoreListener(listener)
    }

    /**
     * 恢复没有更多数据的原始状态
     * @param noMoreData 是否有更多数据
     */
    fun setNoMoreData(noMoreData: Boolean) {
        smart_refresh.setNoMoreData(noMoreData)
    }

    fun getRecycerView(): RecyclerView {
        return recycler_view
    }

    /**
     * 添加头部
     */
    fun addHeaderView(view: View) {
        if (recyclerAdapter != null) {
            recyclerAdapter?.addHeaderView(view)
        }
    }

    /**
     * 获取已添加头部的数量
     */
    fun getHeaderLayoutCount(): Int? {
        if (recyclerAdapter != null) {
            return recyclerAdapter?.headerLayoutCount
        }
        return 0
    }

    /**
     * 设置布局管理器
     */
    fun setLayoutManager(layout: RecyclerView.LayoutManager) {
        recycler_view.layoutManager = layout
    }

    /**
     * 设置适配器
     */
    fun setAdapter(adapter: BaseQuickAdapter<*, *>) {
        recyclerAdapter = adapter
        recycler_view.adapter = adapter
    }
}