package com.invisibleticker;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

import java.io.IOException;
import java.util.HashMap;

public class MyJobIntentService extends JobIntentService {
    public static final String CUSTOM_INTENT = "com.test.intent.action.ALARM";

    private static Context ctx;
    private static final int JOB_ID = 1200;

    public static void enqueueWork(Context context, Intent intent) {
        enqueueWork(context, MyJobIntentService.class, JOB_ID, intent);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        Log.w("MyJobIntentService", "alarm manager setup");

        HashMap<String, String> hashMap = new HashMap<>();
//        hashMap.put("is_device_on","true");
        hashMap.put("name", "rishab");
        hashMap.put("job", "developer");

        String payload = hashMap.toString();
        /* send data to server call */
        try {
            //TODO change your URL here
          makePostRequest("https://reqres.in/api/users", payload);

        } catch (IOException e) {
            e.printStackTrace();
        }
        MyJobIntentService.setAlarm(false, ctx);
        stopSelf();
    }

    public static void cancelAlarm() {
        if (ctx == null) {
            return;
        }

        AlarmManager alarm = (AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pIntent = getPendingIntent();
        if (pIntent != null) {
            alarm.cancel(pIntent);
        }
    }

    public static void setAlarm(boolean force, Context context) {
        ctx = context;
        cancelAlarm();
        if (ctx == null) {
            return;
        }
        AlarmManager alarm = (AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);
        // EVERY X MINUTES
        long delay = (1000 * 60);
        long when = System.currentTimeMillis();
        if (!force) {
            when += delay;
        }
        Log.w("delay", String.valueOf(delay));

        PendingIntent pendingIntent = getPendingIntent();
        if (pendingIntent != null) {

            int SDK_INT = Build.VERSION.SDK_INT;
            if (SDK_INT < Build.VERSION_CODES.KITKAT) {
                alarm.set(AlarmManager.RTC_WAKEUP, when, pendingIntent);
            } else if (SDK_INT < Build.VERSION_CODES.M) {
                alarm.setExact(AlarmManager.RTC_WAKEUP, when, pendingIntent);
            } else {
                alarm.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, when, pendingIntent);
            }
        }
    }


    private static PendingIntent getPendingIntent() {
        if (ctx == null) {
            return null;
        }
        Intent alarmIntent = new Intent(ctx, AlarmReceiver.class);
        alarmIntent.setAction(CUSTOM_INTENT);

        return PendingIntent.getBroadcast(ctx, 0, alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT);
    }

    public static void makePostRequest(String stringUrl, String payload) throws IOException {
//        URL url = new URL(stringUrl);
//        HttpURLConnection uc = (HttpURLConnection) url.openConnection();
//        String line;
//        StringBuffer jsonString = new StringBuffer();
//
//        uc.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
//        uc.setRequestMethod("POST");
//        uc.setDoInput(true);
//        uc.setInstanceFollowRedirects(false);
////        uc.connect();
//        OutputStreamWriter writer = new OutputStreamWriter(uc.getOutputStream(), "UTF-8");
//        writer.write(payload);
//        writer.close();
//        try {
//            BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream()));
//            while ((line = br.readLine()) != null) {
//                jsonString.append(line);
//            }
//            br.close();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        uc.disconnect();
//          String response =  jsonString.toString();
//        Log.w("server response", response);

    }
}