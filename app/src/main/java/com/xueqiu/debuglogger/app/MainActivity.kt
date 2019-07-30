package com.xueqiu.debuglogger.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.xueqiu.debuglogger.BaseDebugLogger
import com.xueqiu.debuglogger.DLog
import com.xueqiu.debuglogger.DebugLogOptions
import com.xueqiu.debuglogger.InfoLogger
import com.xueqiu.debuglogger.bugly.BuglyDebugLogger
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val map = HashMap<String, String>()
        map[BaseDebugLogger.KEY_APP_CHANNEL] = "app"
        map[BaseDebugLogger.KEY_APP_VERSION] = BuildConfig.VERSION_NAME

        val options = DebugLogOptions().isDebug(true)
                .withLogger(InfoLogger())
                .withLogger(BuglyDebugLogger("746b3e9b70")) // replace to your bugly app id
                .withParams(map)

        DLog.init(AppContext.INSTANCE, options)

        DLog.i("test")
        DLog.v("test")
        DLog.d("test")
        DLog.w("test")
        DLog.e("test", true)
        DLog.e(NullPointerException(), true)

        DLog.getLogger<BuglyDebugLogger>(BuglyDebugLogger.LOGGER_ID_BUGLY)?.uploadDelay = 100

        btn_test.setOnClickListener {
            DLog.getLogger<BuglyDebugLogger>(BuglyDebugLogger.LOGGER_ID_BUGLY)?.triggerCrash()
        }
    }
}
