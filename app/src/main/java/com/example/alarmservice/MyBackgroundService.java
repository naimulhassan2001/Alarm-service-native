package com.example.alarmservice;


import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

public class MyBackgroundService extends Service {

    private static final String TAG = "MyBackgroundService";
    private static final int NOTIFICATION_ID = 123; //
    private static final String CHANNEL_ID = "ForegroundServiceChannel";


// Unique ID for the notification


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: Background service created");
    }

    @SuppressLint({"BackgroundServiceType", "ForegroundServiceType"})

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Your background service logic goes here
        Log.d(TAG, "onStartCommand: Background serv" +
                "ice started");
        Intent notificationIntent = new Intent(this, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
//                .setContentTitle("Foreground Service")
//                .setContentText("Running...")
                .setSmallIcon(R.drawable.android)
                .setContentIntent(pendingIntent)
                .build();

        startForeground(NOTIFICATION_ID,  notification);

        // Return START_STICKY or other appropriate return value based on your requirements
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // Return null because this service is not designed to bind to other components
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: Background service destroyed");
    }
}
