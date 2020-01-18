package com.wsyzj.wanandroidkotlin.common.http

import com.wsyzj.wanandroidkotlin.business.utils.StorageUtils
import okhttp3.Interceptor
import okhttp3.Response

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : wsyzj_92@163.com
 *     time   : 2020/01/18
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class CookieIntercepter : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        val builder = request.newBuilder()

        val url = request.url().toString()
        if (url.contains("user/login") || url.contains("user/register")) {
            val cookies = response.headers("Set-Cookie")
            StorageUtils.setCookies(cookies)
        }
        return chain.proceed(builder.build())
    }
}
