package com.wsyzj.wanandroidkotlin.common.http

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : wsyzj_92@163.com
 *     time   : 2019/12/30
 *     desc   : 图片加载框架
 *     version: 1.0
 * </pre>
 */
object ImageLoader {

    fun with(context: Context?, path: String, imageView: ImageView?) {
        if (context == null) {
            return
        }
        Glide.with(context).load(path).into(imageView!!)
    }

}