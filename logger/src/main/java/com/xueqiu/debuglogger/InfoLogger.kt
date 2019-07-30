package com.xueqiu.debuglogger

import android.content.Context
import android.util.Log

class InfoLogger : BaseDebugLogger() {

    companion object {
        const val LOGGER_ID_INFO = "info"
    }

    private val callerName: String
        get() {
            val element = Throwable().stackTrace[3]
            return element.className
        }

    private val fileName: String
        get() {
            val element = Throwable().stackTrace[3]
            return "${element.fileName}:${element.lineNumber}"
        }

    override fun initLogger(context: Context, params: Map<String, Any>?) {
        // do not need
    }

    override fun getLoggerID(): String = LOGGER_ID_INFO

    override fun i(msg: String?) {
        if (isDebug) {
            Log.i(callerName, "($fileName) - $msg")
        }
    }

    override fun v(msg: String?) {
        if (isDebug) {
            Log.v(callerName, "($fileName) - $msg")
        }
    }

    override fun d(msg: String?) {
        if (isDebug) {
            Log.d(callerName, "($fileName) - $msg")
        }
    }

    override fun w(msg: String?) {
        if (isDebug) {
            Log.w(callerName, "($fileName) - $msg")
        }
    }

    override fun e(msg: String?, upload: Boolean) {
        if (isDebug) {
            Log.e(callerName, "($fileName) - $msg")
        }
    }

    override fun e(e: Throwable?, upload: Boolean) {
        if (isDebug) {
            Log.e(callerName, "($fileName) - error", e)
        }
    }

    override fun e(msg: String?, e: Throwable?, upload: Boolean) {
        if (isDebug) {
            Log.e(callerName, "($fileName) - $msg", e)
        }
    }

    override fun upload() {
        // won't upload
    }
}