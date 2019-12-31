package com.wsyzj.wanandroidkotlin.business.widget

import android.content.Context
import android.widget.ImageView
import com.youth.banner.loader.ImageLoader

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : wsyzj_92@163.com
 *     time   : 2019/12/30
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class GlideImageLoader : ImageLoader() {

    override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
        com.wsyzj.wanandroidkotlin.common.http.ImageLoader.with(context, path as String, imageView)
    }

}