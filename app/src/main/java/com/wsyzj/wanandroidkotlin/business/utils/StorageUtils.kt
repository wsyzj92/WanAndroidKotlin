package com.wsyzj.wanandroidkotlin.business.utils

import com.blankj.utilcode.util.SPUtils

/**
 * 简单存储
 */
class StorageUtils {

    companion object {

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
    }

}