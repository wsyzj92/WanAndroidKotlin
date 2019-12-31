package com.wsyzj.wanandroidkotlin.business.bean

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : wsyzj_92@163.com
 *     time   : 2019/12/30
 *     desc   :
 *     version: 1.0
 * </pre>
 */
data class HomdeBanner(
    val `data`: MutableList<DataBanner>,
    val errorCode: Int,
    val errorMsg: String
)

data class DataBanner(
    val desc: String,
    val id: Int,
    val imagePath: String,
    val isVisible: Int,
    val order: Int,
    val title: String,
    val type: Int,
    val url: String
)