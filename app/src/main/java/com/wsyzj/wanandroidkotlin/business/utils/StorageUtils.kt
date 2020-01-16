package com.wsyzj.wanandroidkotlin.business.utils

import com.blankj.utilcode.util.SPUtils

/**
 * 简单存储
 */
class StorageUtils {

    companion object {

        // 登录状态
        val SP_LOGIN_STATUS: String = "sp_login_status"

        fun isLogin(): Boolean {
            return SPUtils.getInstance().getBoolean(SP_LOGIN_STATUS)
        }

        fun setLoginStatus(isLogin: Boolean) {
            SPUtils.getInstance().put(SP_LOGIN_STATUS, isLogin)
        }
    }

}