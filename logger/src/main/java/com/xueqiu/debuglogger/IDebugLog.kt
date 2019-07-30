package com.xueqiu.debuglogger

import android.content.Context

interface IDebugLog {

    fun init(context: Context, options: DebugLogOptions)

    fun <T : BaseDebugLogger> getLogger(loggerID: String): T?

    fun i(msg: String?)

    fun v(msg: String?)

    fun d(msg: String?)

    fun w(msg: String?)

    fun e(msg: String?, upload: Boolean = false)

    fun e(e: Throwable?, upload: Boolean = false)

    fun e(msg: String?, e: Throwable?, upload: Boolean = false)

    fun upload()

}