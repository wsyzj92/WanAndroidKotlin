package com.wsyzj.wanandroidkotlin.common.utils

import com.wsyzj.wanandroidkotlin.common.base.BaseEventBus
import org.greenrobot.eventbus.EventBus

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : wsyzj_92@163.com
 *     time   : 2019/12/23
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class EventBusUtils {

    companion object {
        fun register(subscribe: Any) {
            EventBus.getDefault().register(subscribe)
        }

        fun unregister(subscribe: Any) {
            EventBus.getDefault().unregister(subscribe)
        }

        fun post(eventbus: BaseEventBus) {
            EventBus.getDefault().post(eventbus)
        }
    }
}
