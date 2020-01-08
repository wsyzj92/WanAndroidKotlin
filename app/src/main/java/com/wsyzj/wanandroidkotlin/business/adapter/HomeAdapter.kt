package com.wsyzj.wanandroidkotlin.business.adapter

import android.app.Activity
import com.blankj.utilcode.util.ToastUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.wsyzj.wanandroidkotlin.R
import com.wsyzj.wanandroidkotlin.business.bean.DataX
import com.wsyzj.wanandroidkotlin.business.manager.IntentManager

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : wsyzj_92@163.com
 *     time   : 2019/12/28
 *     desc   : 首页适配器
 *     version: 1.0
 * </pre>
 */
class HomeAdapter(data: List<DataX>?) : BaseQuickAdapter<DataX, BaseViewHolder>(R.layout.item_home, data) {

    override fun convert(helper: BaseViewHolder, item: DataX?) {
        helper.setText(R.id.tv_source, item?.chapterName)
        helper.setText(R.id.tv_title, item?.title)
        helper.setText(R.id.tv_author, "作者 : " + item?.author)
        helper.setText(R.id.tv_data, "时间 : " + item?.niceDate)
    }
}