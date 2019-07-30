package com.xueqiu.debuglogger

class DebugLogOptions : IDebugLogOptions {

    var isDebug: Boolean = false
        private set

    var params: Map<String, Any>? = null

    var loggers: MutableList<BaseDebugLogger> = ArrayList()

    override fun isDebug(boolean: Boolean): DebugLogOptions {
        isDebug = boolean
        return this
    }

    override fun withParams(params: Map<String, Any>): DebugLogOptions {
        this.params = params
        return this
    }

    override fun withLogger(logger: BaseDebugLogger): DebugLogOptions {
        var isExist = false
        for (it in loggers) {
            if (it.getLoggerID() == logger.getLoggerID()) {
                isExist = true
                break
            }
        }
        if (!isExist) loggers.add(logger)
        return this
    }
}