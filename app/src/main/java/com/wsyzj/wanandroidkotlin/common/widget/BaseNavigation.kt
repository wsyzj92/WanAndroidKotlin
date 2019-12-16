package com.wsyzj.wanandroidkotlin.common.widget

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View
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
class BaseNavigation : View {

    constructor(context: Context) : super(context, null) {

    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet, 0) {

    }

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr) {
        init()
    }

    fun init() {

    }

}