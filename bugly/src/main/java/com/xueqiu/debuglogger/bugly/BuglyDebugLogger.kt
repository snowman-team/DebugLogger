package com.xueqiu.debuglogger.bugly

import android.content.Context
import com.tencent.bugly.crashreport.CrashReport
import com.xueqiu.debuglogger.BaseDebugLogger

class BuglyDebugLogger(val buglyAppID: String) : BaseDebugLogger() {

    companion object {
        const val LOGGER_ID_BUGLY = "bugly"

        private const val DEFAULT_UPLOAD_DELAY: Long = 20 * 1000
    }

    var listener: BuglyListener? = null
    var uploadDelay: Long = DEFAULT_UPLOAD_DELAY

    override fun getLoggerID(): String = LOGGER_ID_BUGLY

    override fun initLogger(context: Context, params: Map<String, Any>?) {
        val strategy = CrashReport.UserStrategy(context)
        strategy.setCrashHandleCallback(object : CrashReport.CrashHandleCallback() {
            override fun onCrashHandleStart(
                    crashType: Int,
                    errorType: String?,
                    errorMessage: String?,
                    errorStack: String?
            ): MutableMap<String, String> {
                return listener?.onCrashHandleStart(crashType, errorType, errorMessage, errorStack)
                        ?: super.onCrashHandleStart(crashType, errorType, errorMessage, errorStack)
            }
        })
        params?.get(KEY_APP_CHANNEL)?.let {
            if (it is String) strategy.appChannel = it
        }
        params?.get(KEY_APP_VERSION)?.let {
            if (it is String) strategy.appVersion = it
        }
        strategy.appReportDelay = if (isDebug) 0 else uploadDelay
        CrashReport.initCrashReport(context, buglyAppID, isDebug)
        CrashReport.setIsDevelopmentDevice(context, isDebug)
    }

    override fun i(msg: String?) {
        // only post error message
    }

    override fun v(msg: String?) {
        // only post error message
    }

    override fun d(msg: String?) {
        // only post error message
    }

    override fun w(msg: String?) {
        // only post error message
    }

    override fun e(msg: String?, upload: Boolean) {
        if (upload) {
            CrashReport.postCatchedException(UnknownError(msg))
        }
    }

    override fun e(msg: String?, e: Throwable?, upload: Boolean) {
        if (upload) {
            CrashReport.postCatchedException(e)
        }
    }

    override fun e(e: Throwable?, upload: Boolean) {
        if (upload) {
            CrashReport.postCatchedException(e)
        }
    }

    fun setUserID(userID: String) {
        CrashReport.setUserId(userID)
    }

    fun triggerCrash() {
        CrashReport.testJavaCrash()
    }

    override fun upload() {
        // no need
    }

    interface BuglyListener {
        fun onCrashHandleStart(
                crashType: Int,
                errorType: String?,
                errorMessage: String?,
                errorStack: String?
        ): MutableMap<String, String>
    }
}