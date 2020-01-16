package com.wsyzj.wanandroidkotlin.business.manager

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.enums.PopupAnimation
import com.wsyzj.wanandroidkotlin.business.activity.WebviewActivity
import com.wsyzj.wanandroidkotlin.business.dialog.LoginDialog

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : wsyzj_92@163.com
 *     time   : 2020/01/08
 *     desc   : app内跳转管理
 *     version: 1.0
 * </pre>
 */
class IntentManager {

    companion object {

        private fun startActivity(activity: Activity?, clazz: Class<*>) {
            activity?.startActivity(Intent(activity, clazz))
        }

        private fun startActivity(activity: Activity?, intent: Intent) {
            activity?.startActivity(intent)
        }

        /**
         * 登录
         */
        fun login(context: Context?) {
            var loginDialog = LoginDialog(context!!)
            XPopup
                .Builder(context)
                .popupAnimation(PopupAnimation.TranslateAlphaFromTop)
                .asCustom(loginDialog)
                .show()
        }

        /**
         * 到网页显示的界面
         */
        fun webview(activity: Activity?, url: String?, id: Int?) {
            var intent = Intent(activity, WebviewActivity::class.java)
            intent.putExtra("url", url)
            intent.putExtra("id", id)
            startActivity(activity, intent)
        }
    }
}