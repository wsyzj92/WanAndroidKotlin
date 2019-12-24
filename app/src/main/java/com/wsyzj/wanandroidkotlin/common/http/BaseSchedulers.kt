package com.wsyzj.wanandroidkotlin.common.http

import android.app.ProgressDialog
import android.content.Context

import org.reactivestreams.Publisher
import org.reactivestreams.Subscription

import java.util.concurrent.TimeUnit

import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

/**
 * 创建时间 : 2017/10/26
 * 编写人 : 焦洋
 * 功能描述 :
 */

object BaseSchedulers {

    /**
     * 基本请求
     */
    fun <T> io_main(): FlowableTransformer<T, T> {
        return FlowableTransformer { upstream ->
            upstream
                .subscribeOn(Schedulers.io())
                .doOnSubscribe {
                    //                                if (!NetworkUtils.isConnected()) {
                    //                                    subscription.cancel();
                    //                                }
                }
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

    /**
     * 带进度条的请求
     */
    fun <T> io_main(context: Context, dialog: ProgressDialog?): FlowableTransformer<T, T> {
        return FlowableTransformer { upstream ->
            upstream
                .delay(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe {
                    //                                if (!NetworkUtils.isConnected()) {
                    //                                    subscription.cancel();
                    //                                } else {
                    //
                    //                                }

                    //启动进度显示，取消进度时会取消请求
                    dialog?.show()
                }
                .observeOn(AndroidSchedulers.mainThread())
        }
    }
}
