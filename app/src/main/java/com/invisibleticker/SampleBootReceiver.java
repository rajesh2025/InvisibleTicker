package com.invisibleticker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class SampleBootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            // Set the alarm here.
            //TODO update shared preferrence to send device boot up data to server
            MyJobIntentService.setAlarm(true,context);
        }
    }
}