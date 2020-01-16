package com.wsyzj.wanandroidkotlin.business.dialog

import android.annotation.SuppressLint
import android.content.Context
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
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
 *     desc   : 登录/注册dialog
 *     version: 1.0
 * </pre>
 */
class LoginDialog(context: Context) : CenterPopupView(context) {

    @BindView(R.id.et_username)
    lateinit var et_username: EditText

    @BindView(R.id.et_password)
    lateinit var et_password: EditText

    @BindView(R.id.ll_repassword)
    lateinit var ll_repassword: LinearLayout

    @BindView(R.id.et_repassword)
    lateinit var et_repassword: EditText

    @BindView(R.id.tv_login_register_text)
    lateinit var tv_login_register_text: TextView

    @BindView(R.id.tv_login_register)
    lateinit var tv_login_register: TextView

    var isLoginStatus: Boolean = true

    override fun onCreate() {
        super.onCreate()
        ButterKnife.bind(this)
    }

    override fun getImplLayoutId(): Int {
        return R.layout.dialog_login
    }

    @OnClick(R.id.card_login_register, R.id.tv_login_register)
    fun onClick(view: View) {
        when (view.id) {
            R.id.card_login_register -> {
                if (isLoginStatus) {
                    login()
                } else {
                    register()
                }
            }
            R.id.tv_login_register -> {
                if (isLoginStatus) {
                    isLoginStatus = false
                    tv_login_register_text.text = "注册"
                    tv_login_register.text = "去登录"
                    ll_repassword.visibility = View.VISIBLE
                } else {
                    isLoginStatus = true
                    tv_login_register_text.text = "登录"
                    tv_login_register.text = "去注册"
                    ll_repassword.visibility = View.GONE
                }
            }
        }
    }

    /**
     * 校验参数
     */
    fun checkLoginParams(): Boolean {
        var username = et_username.text.trim().toString()
        var password = et_password.text.trim().toString()

        if (TextUtils.isEmpty(username)) {
            ToastUtils.showShort("请输入用户名")
            return false
        } else if (username.length < 6) {
            ToastUtils.showShort("请输入6位数或以上的用户名")
            return false
        } else if (TextUtils.isEmpty(password)) {
            ToastUtils.showShort("请输入密码")
            return false
        } else if (password.length < 6) {
            ToastUtils.showShort("请输入6位数或以上的密码")
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

        var username = et_username.text.trim().toString()
        var password = et_password.text.trim().toString()

        BaseRequest.instance.service
            .login(username, password)
            .compose(BaseSchedulers.io_main())
            .subscribe() {
                if (it.errorCode == Constant.HTTP_CODE) {
                    it.let {
                        ToastUtils.showShort("登录成功")
                        dismiss()
                    }
                } else {
                    ToastUtils.showShort(it.errorMsg)
                }
            }
    }

    /**
     * 校验注册参数
     */
    fun checkRegisterParams(): Boolean {
        var username = et_username.text.trim().toString()
        var password = et_password.text.trim().toString()
        var repassword = et_repassword.text.trim().toString()

        if (TextUtils.isEmpty(username)) {
            ToastUtils.showShort("请输入用户名")
            return false
        } else if (username.length < 6) {
            ToastUtils.showShort("请输入6位数或以上的用户名")
            return false
        } else if (TextUtils.isEmpty(password)) {
            ToastUtils.showShort("请输入密码")
            return false
        } else if (password.length < 6) {
            ToastUtils.showShort("请输入6位数或以上的密码")
            return false
        } else if (TextUtils.isEmpty(repassword)) {
            ToastUtils.showShort("请再次输入密码")
            return false
        } else if (repassword.length < 6) {
            ToastUtils.showShort("请输入6位数或以上的密码")
            return false
        } else if (!TextUtils.equals(password, repassword)) {
            ToastUtils.showShort("两次密码输入不一致")
            return false
        }
        return true
    }

    /**
     * 注册
     */
    @SuppressLint("CheckResult")
    private fun register() {
        if (!checkRegisterParams()) {
            return
        }

        var username = et_username.text.trim().toString()
        var password = et_password.text.trim().toString()
        var repassword = et_repassword.text.trim().toString()

        BaseRequest.instance.service
            .register(username, password, repassword)
            .compose(BaseSchedulers.io_main())
            .subscribe() {
                if (it.errorCode == Constant.HTTP_CODE) {
                    it.let {
                        dismiss()
                        ToastUtils.showShort("注册成功，已为您跳转登录")
                    }
                } else {
                    ToastUtils.showShort(it.errorMsg)
                }
            }
    }

}