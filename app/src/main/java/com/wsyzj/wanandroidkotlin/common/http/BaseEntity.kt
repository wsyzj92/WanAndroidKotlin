package com.wsyzj.wanandroidkotlin.common.http

/**
 * 创建时间 : 2017/10/26
 * 编写人 : 焦洋
 * 功能描述 :
 */

class BaseEntity<T> {
    var code: String? = null
    var msg: String? = null
    var data: T? = null
}
