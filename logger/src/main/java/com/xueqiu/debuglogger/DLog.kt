package com.xueqiu.debuglogger

import android.content.Context

object DLog : IDebugLog {

    private var isDebug: Boolean = false
    private val mLogger: MutableList<BaseDebugLogger> = ArrayList()

    override fun init(context: Context, options: DebugLogOptions) {
        isDebug = options.isDebug
        mLogger.clear()
        mLogger.addAll(options.loggers)
        try {
            mLogger.forEach {
                it.isDebug = isDebug
                it.initLogger(context, options.params)
            }
        } catch (e: Throwable) {
        }
    }

    override fun <T : BaseDebugLogger> getLogger(loggerID: String): T? {
        return mLogger.find { it.getLoggerID() == loggerID }?.let { it as T }
    }

    override fun i(msg: String?) {
        mLogger.forEach {
            it.i(msg)
        }
    }

    override fun v(msg: String?) {
        mLogger.forEach {
            it.v(msg)
        }
    }

    override fun d(msg: String?) {
        mLogger.forEach {
            it.d(msg)
        }
    }

    override fun w(msg: String?) {
        mLogger.forEach {
            it.w(msg)
        }
    }

    override fun e(msg: String?, upload: Boolean) {
        mLogger.forEach {
            it.e(msg, upload)
        }
    }

    override fun e(e: Throwable?, upload: Boolean) {
        mLogger.forEach {
            it.e(e, upload)
        }
    }

    override fun e(msg: String?, e: Throwable?, upload: Boolean) {
        mLogger.forEach {
            it.e(msg, e, upload)
        }
    }

    override fun upload() {
        mLogger.forEach {
            it.upload()
        }
    }

}