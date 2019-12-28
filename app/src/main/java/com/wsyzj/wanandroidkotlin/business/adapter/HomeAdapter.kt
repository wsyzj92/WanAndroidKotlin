package com.wsyzj.wanandroidkotlin.business.adapter

import androidx.annotation.IdRes
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.wsyzj.wanandroidkotlin.R
import com.wsyzj.wanandroidkotlin.business.bean.Article

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : wsyzj_92@163.com
 *     time   : 2019/12/28
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class HomeAdapter(data: List<Article>?) : BaseQuickAdapter<Article, BaseViewHolder>(R.layout.item_home, data) {

    override fun convert(helper: BaseViewHolder, item: Article?) {

    }
}