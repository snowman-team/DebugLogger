package com.xueqiu.debuglogger

interface IDebugLogOptions {

    fun withLogger(logger: BaseDebugLogger): IDebugLogOptions

    fun withParams(params: Map<String, Any>): IDebugLogOptions

    fun isDebug(boolean: Boolean): IDebugLogOptions
}