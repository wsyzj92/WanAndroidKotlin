package com.wsyzj.wanandroidkotlin.common.http

import com.wsyzj.wanandroidkotlin.business.bean.Article
import com.wsyzj.wanandroidkotlin.business.bean.HomdeBanner
import com.wsyzj.wanandroidkotlin.business.bean.Login
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

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
    fun getHomeList(@Path("pageNumber") pageNumber: Int): Flowable<BaseEntity<Article>>

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

    /**
     * 收藏站内文章
     */
    @POST("lg/collect/{id}/json")
    fun collect(@Path("id") id: Int): Flowable<HomdeBanner>

    /**
     * 项目列表数据
     */
    @GET("project/list/{pageNumber}/json")
    fun getProjectList(@Path("pageNumber") pageNumber: Int, @Query("cid") cid: Int): Flowable<BaseEntity<Article>>
}