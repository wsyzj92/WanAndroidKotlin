package com.wsyzj.wanandroidkotlin.business.utils

import android.text.TextUtils
import com.blankj.utilcode.util.SPUtils
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject

/**
 * 简单存储
 */
object StorageUtils {

    // 登录状态
    val SP_LOGIN_STATUS = "sp_login_status"

    fun isLogin(): Boolean {
        return SPUtils.getInstance().getBoolean(SP_LOGIN_STATUS)
    }

    fun setLoginStatus(isLogin: Boolean) {
        SPUtils.getInstance().put(SP_LOGIN_STATUS, isLogin)
    }

    // 用户名
    // 用户密码
    val SP_LOGIN_USERNAME = "sp_login_username"
    val SP_LOGIN_PASSWORD = "sp_login_password"

    fun setLoginUserName(userName: String) {
        SPUtils.getInstance().put(SP_LOGIN_USERNAME, userName)
    }

    fun getLoginUserName(): String {
        return SPUtils.getInstance().getString(SP_LOGIN_USERNAME)
    }

    fun setLoginPassword(password: String) {
        SPUtils.getInstance().put(SP_LOGIN_PASSWORD, password)
    }

    fun getLoginPassword(): String {
        return SPUtils.getInstance().getString(SP_LOGIN_PASSWORD)
    }

    // 登录Cookie
    val SP_LOGIN_COOKIE = "sp_login_cookie"

    fun setCookies(cookies: MutableList<String>?) {
        if (cookies != null && cookies.size != 0) {

            var array = JSONArray()
            for (index in 0 until cookies.size) {
                array.put(index, cookies[index])
            }

            var obj = JSONObject()
            obj.put(SP_LOGIN_COOKIE, array)

            SPUtils.getInstance().put(SP_LOGIN_COOKIE, obj.toString())
        } else {
            SPUtils.getInstance().put(SP_LOGIN_COOKIE, "")

        }
    }

    fun getCookies(): MutableList<String>? {
        val cookies = SPUtils.getInstance().getString(SP_LOGIN_COOKIE, "")

        if (!TextUtils.isEmpty(cookies)) {
            var obj = JSONObject(cookies)
            val array = obj.optJSONArray(SP_LOGIN_COOKIE)
            var cookies = mutableListOf<String>()
            for (index in 0 until array.length()) {
                cookies.add(index, array[index].toString())
            }
            return cookies
        }
        return null
    }
}