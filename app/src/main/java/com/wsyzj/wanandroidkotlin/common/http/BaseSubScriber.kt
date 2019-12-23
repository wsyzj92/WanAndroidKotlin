package com.wsyzj.wanandroidkotlin.common.http

import com.blankj.utilcode.util.ToastUtils
import com.wsyzj.wanandroidkotlin.common.constant.Constant
import io.reactivex.subscribers.DisposableSubscriber
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : wsyzj_92@163.com
 *     time   : 2019/12/23
 *     desc   :
 *     version: 1.0
 * </pre>
 */
abstract class BaseSubScriber<T> : DisposableSubscriber<BaseEntity<T>>() {

    override fun onNext(baseEntity: BaseEntity<T>?) {
        if (baseEntity?.code == Constant.HTTP_CODE) {
            if (baseEntity.data != null) {
                onSuccess(baseEntity.data, baseEntity.msg)
            } else {
                ToastUtils.showShort(baseEntity.msg)
            }
        } else {
            onFailure(BaseResponseThrowable())
            ToastUtils.showShort(baseEntity?.msg)
        }
    }

    override fun onError(throwable: Throwable?) {
        var errorMsg = ""
        if (throwable is SocketTimeoutException) {
            errorMsg = Constant.SOCKET_TIMEOUT_EXCEPTION
        } else if (throwable is ConnectException) {
            errorMsg = Constant.CONNECT_EXCEPTION
        } else if (throwable is UnknownHostException) {
            errorMsg = Constant.UNKNOWN_HOST_EXCEPTION
        } else {
            errorMsg = Constant.CONNECT_EXCEPTION
        }
        onFailure(throwable)
        ToastUtils.showShort(errorMsg)
    }

    override fun onComplete() {

    }

    abstract fun onFailure(throwable: Throwable?)

    abstract fun onSuccess(t: T?, msg: String)
}