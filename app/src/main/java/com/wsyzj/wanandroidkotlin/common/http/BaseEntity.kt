package com.wsyzj.wanandroidkotlin.common.http

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : wsyzj_92@163.com
 *     time   : 2019/12/23
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class BaseEntity<T> {
    var errorCode = -1
    var errorMsg = ""
    var data: T? = null
}