package com.xueqiu.debuglogger

import android.content.Context

abstract class BaseDebugLogger {

    companion object {
        const val KEY_APP_CHANNEL = "app_channel"
        const val KEY_APP_VERSION = "app_version"
    }

    var isDebug: Boolean = false

    abstract fun getLoggerID(): String

    abstract fun initLogger(context: Context, params: Map<String, Any>?)

    abstract fun i(msg: String?)

    abstract fun d(msg: String?)

    abstract fun v(msg: String?)

    abstract fun w(msg: String?)

    abstract fun e(msg: String?, upload: Boolean)

    abstract fun e(e: Throwable?, upload: Boolean)

    abstract fun e(msg: String?, e: Throwable?, upload: Boolean)

    abstract fun upload()

}