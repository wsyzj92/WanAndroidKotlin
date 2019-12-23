package com.wsyzj.wanandroidkotlin.common.base

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : wsyzj_92@163.com
 *     time   : 2019/12/23
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class BaseEventBus {

    var code: Int = -1
    var data: Any? = null

    constructor(code: Int) {
        this.code = code
    }

    constructor(code: Int, data: Any?) {
        this.code = code
        this.data = data
    }

}