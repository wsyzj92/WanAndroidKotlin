package com.wsyzj.wanandroidkotlin.common.http

import com.wsyzj.wanandroidkotlin.business.utils.StorageUtils

import okhttp3.Interceptor
import okhttp3.Response

/**
 * 创建时间 : 2017/10/26
 * 编写人 : 焦洋
 * 功能描述 : retrofit请求请求头部的信息.
 */

class BaseHeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()

        val cookies = StorageUtils.getCookies()
        if (cookies != null && cookies.size != 0) {
            cookies.forEach {
                builder.addHeader("Cookie", it)
            }
        }

        return chain.proceed(builder.build())
    }
}
