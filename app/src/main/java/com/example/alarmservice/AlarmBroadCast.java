package com.example.alarmservice;


import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class AlarmBroadCast extends BroadcastReceiver {

    public static MediaPlayer mediaPlayer = new MediaPlayer();

    @Override
    public void onReceive(Context context, Intent intent) {

        mediaPlayer = MediaPlayer.create(context, Settings.System.DEFAULT_RINGTONE_URI);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        Log.d("hited","hited") ;
        Toast.makeText(context, "Alarm Time", Toast.LENGTH_SHORT).show();


    }
}


