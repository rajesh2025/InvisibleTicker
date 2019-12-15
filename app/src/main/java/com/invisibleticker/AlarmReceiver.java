package com.invisibleticker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        /* enqueue the job */
        MyJobIntentService.enqueueWork(context, intent);
    }

}