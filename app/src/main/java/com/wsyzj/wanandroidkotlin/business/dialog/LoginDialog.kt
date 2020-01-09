package com.wsyzj.wanandroidkotlin.business.dialog

import android.annotation.SuppressLint
import android.content.Context
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.blankj.utilcode.util.ToastUtils
import com.lxj.xpopup.core.CenterPopupView
import com.lxj.xpopup.impl.PartShadowPopupView
import com.wsyzj.wanandroidkotlin.R
import com.wsyzj.wanandroidkotlin.common.constant.Constant
import com.wsyzj.wanandroidkotlin.common.http.BaseRequest
import com.wsyzj.wanandroidkotlin.common.http.BaseSchedulers
import kotlinx.android.synthetic.main.fragment_home.view.*

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : wsyzj_92@163.com
 *     time   : 2020/01/09
 *     desc   : 登录
 *     version: 1.0
 * </pre>
 */
class LoginDialog(context: Context) : CenterPopupView(context) {

    @BindView(R.id.et_username)
    lateinit var et_username: EditText

    @BindView(R.id.et_password)
    lateinit var et_password: EditText

    override fun onCreate() {
        super.onCreate()
        ButterKnife.bind(this)
    }

    override fun getImplLayoutId(): Int {
        return R.layout.dialog_login
    }

    @OnClick(R.id.card_login)
    fun onClick(view: View) {
        when (view.id) {
            R.id.card_login -> {
                login()
            }
        }
    }

    /**
     * 校验参数
     */
    fun checkLoginParams(): Boolean {
        var username = et_username.text
        var password = et_password.text

        if (TextUtils.isEmpty(username)) {
            ToastUtils.showShort("请输入用户名")
            return false
        } else if (username.length < 6) {
            ToastUtils.showShort("请输入6位数以上的用户名")
            return false
        } else if (TextUtils.isEmpty(password)) {
            ToastUtils.showShort("请输入密码")
            return false
        } else if (password.length < 6) {
            ToastUtils.showShort("请输入6位数以上的密码")
            return false
        }
        return true
    }

    /**
     * 登录
     */
    @SuppressLint("CheckResult")
    private fun login() {
        if (!checkLoginParams()) {
            return
        }

//        BaseRequest.instance.service.login().compose(BaseSchedulers.io_main()).subscribe() {
//            if (it.errorCode == Constant.HTTP_CODE) {
//                it.let {
//
//                }
//            }
//        }
    }

}