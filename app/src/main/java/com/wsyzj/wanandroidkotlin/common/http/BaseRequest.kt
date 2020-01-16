package com.wsyzj.wanandroidkotlin.common.http

import com.wsyzj.wanandroidkotlin.common.constant.Constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : wsyzj_92@163.com
 *     time   : 2019/12/23
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class BaseRequest {

    var service: BaseRetrofitApi

    private object Holder {
        val INSTANCE = BaseRequest()
    }

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val builder = OkHttpClient.Builder()
            .connectTimeout(12, TimeUnit.SECONDS)
            .connectTimeout(12, TimeUnit.SECONDS)
            .writeTimeout(12, TimeUnit.SECONDS)
            .readTimeout(12, TimeUnit.SECONDS)
            .addInterceptor(interceptor)

        service = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(builder.build())
            .baseUrl(Constant.HTTP_URL)
            .build()
            .create(BaseRetrofitApi::class.java)
    }

    companion object {
        val instance by lazy { Holder.INSTANCE }
    }
}