package com.wsyzj.wanandroidkotlin.common.http

import com.wsyzj.wanandroidkotlin.business.utils.StorageUtils
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : wsyzj_92@163.com
 *     time   : 2020/01/16
 *     desc   : 有些接口需要登录 所以把参数统一加到头部
 *     version: 1.0
 * </pre>
 */

class BaseHeaderInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val requestBuilder = original.newBuilder()
            .addHeader("Cookie", StorageUtils.getLoginUserName())
            .addHeader("Cookie", StorageUtils.getLoginPassword())

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}