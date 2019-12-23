package com.wsyzj.wanandroidkotlin.common.http

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : wsyzj_92@163.com
 *     time   : 2019/12/23
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class BaseRetrofit {

    companion object {
        var requestManager: HashMap<String, CompositeDisposable>? = hashMapOf()

        /**
         * 所有的网络请求添加到集合中，方便管理
         */
        fun add(key: String, disposable: Disposable) {
            if (requestManager == null) {
                return
            }

            if (requestManager?.containsKey(key + disposable.hashCode())!!) {
                requestManager?.get(key)!!.add(disposable)
            } else {
                val cd = CompositeDisposable()
                cd.add(disposable)
                requestManager?.put(key, cd)
            }
        }

        /**
         * 移除某个网络请求
         */
        fun clear(key: String) {
            val disposable = requestManager?.get(key)
            if (disposable != null) {
                disposable.clear()
                requestManager?.remove(key)
            }
        }
    }

}