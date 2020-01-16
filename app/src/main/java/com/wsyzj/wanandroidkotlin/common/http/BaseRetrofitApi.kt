package com.wsyzj.wanandroidkotlin.common.http

import com.wsyzj.wanandroidkotlin.business.bean.Article
import com.wsyzj.wanandroidkotlin.business.bean.HomdeBanner
import com.wsyzj.wanandroidkotlin.business.bean.Login
import io.reactivex.Flowable
import retrofit2.http.*

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : wsyzj_92@163.com
 *     time   : 2019/12/23
 *     desc   :
 *     version: 1.0
 * </pre>
 */
interface BaseRetrofitApi {

    /**
     * 首页列表
     */
    @GET("article/list/{pageNumber}/json")
    fun getHomeList(@Path("pageNumber") int: Int): Flowable<BaseEntity<Article>>

    /**
     * 首页banner
     */
    @GET("banner/json")
    fun getBannerList(): Flowable<HomdeBanner>


    /**
     * 登录
     */
    @POST("user/login")
    fun login(@Query("username") username: String, @Query("password") password: String): Flowable<BaseEntity<Login>>

    /**
     * 注册
     */
    @POST("user/register")
    fun register(@Query("username") username: String, @Query("password") password: String, @Query("repassword") repassword: String): Flowable<BaseEntity<Login>>
}