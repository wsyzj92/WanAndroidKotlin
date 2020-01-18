package com.wsyzj.wanandroidkotlin.business.adapter

import android.view.View
import android.widget.ImageView
import com.wsyzj.wanandroidkotlin.R
import com.wsyzj.wanandroidkotlin.business.bean.DataBanner
import com.wsyzj.wanandroidkotlin.business.manager.IntentManager
import com.wsyzj.wanandroidkotlin.common.http.ImageLoader
import com.zhpan.bannerview.holder.ViewHolder

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : wsyzj_92@163.com
 *     time   : 2020/01/18
 *     desc   : 首页banner的适配器
 *     version: 1.0
 * </pre>
 */
class HomeBannerAdapter : ViewHolder<DataBanner> {

    override fun getLayoutId(): Int {
        return R.layout.item_home_banner
    }

    override fun onBind(itemView: View, data: DataBanner, position: Int, size: Int) {
        var iv_banner = itemView.findViewById<ImageView>(R.id.iv_banner)
        ImageLoader.with(iv_banner.context, data.imagePath, iv_banner)
    }

}