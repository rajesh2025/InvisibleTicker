package com.invisibleticker

import android.content.ComponentName
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //start background service
        MyJobIntentService.setAlarm(
            true,
            this
        )
        val p = packageManager
        val componentName = ComponentName(
            this,
            MainActivity::class.java
        )
        // code to hide launcher icon
        p.setComponentEnabledSetting(
            componentName,
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            PackageManager.DONT_KILL_APP
        )
      //code to unhide laucher icon for app
//        p.setComponentEnabledSetting(
//            componentName,
//            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
//            PackageManager.DONT_KILL_APP
//        )


        finish()
    }

//    override fun onDestroy() {
//        MyJobIntentService.cancelAlarm()
//        super.onDestroy()
//    }

}
