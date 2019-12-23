package com.wsyzj.wanandroidkotlin.common.utils

import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.core.content.ContextCompat
import com.wsyzj.wanandroidkotlin.common.BaseApp

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : wsyzj_92@163.com
 *     time   : 2019/12/23
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class IContextCompat : ContextCompat() {

    companion object {

        /**
         * 返回一个布局
         */
        fun inflate(resource: Int): View {
            return View.inflate(BaseApp.sBaseApp, resource, null)
        }

        /**
         * 获取一个drawable
         */
        fun getDrawable(id: Int): Drawable? {
            return ContextCompat.getDrawable(BaseApp.sBaseApp, id)
        }

        /**
         * 获取一个颜色值
         */
        fun getColor(id: Int): Int {
            return ContextCompat.getColor(BaseApp.sBaseApp, id)
        }

        /**
         * 获取一个颜色值
         */
        fun getDimension(id: Int): Float {
            return BaseApp.sBaseApp.resources.getDimension(id)
        }
    }
}