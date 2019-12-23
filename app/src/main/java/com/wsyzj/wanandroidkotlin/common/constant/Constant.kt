package com.wsyzj.wanandroidkotlin.common.constant

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : wsyzj_92@163.com
 *     time   : 2019/12/23
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class Constant {
    companion object {
        const val HTTP_URL = "https://www.wanandroid.com"
        const val HTTP_CODE = 0
        const val HTTP_CODE_RE_LOGIN = -1001       // 需要重新登录
        const val CONNECT_EXCEPTION = "网络连接异常，请检查您的网络状态"
        const val SOCKET_TIMEOUT_EXCEPTION = "网络连接超时，请检查您的网络状态，稍后重试"
        const val UNKNOWN_HOST_EXCEPTION = "网络异常，请检查您的网络状态"
    }
}