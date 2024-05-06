package com.example.alarmservice;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private static final int NOTIFICATION_ID = 1234;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    Button alarm_start, alarm_stop;
    private static final int ALARM_REQUEST_CODE = 123;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alarm_start = findViewById(R.id.alarm_start);
        alarm_stop = findViewById(R.id.alarm_stop);


        // Initialize AlarmManager
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmBroadCast.class);
        pendingIntent = PendingIntent.getBroadcast(this, ALARM_REQUEST_CODE, intent, PendingIntent.FLAG_MUTABLE);

        alarm_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (10 * 1000),
                        AlarmManager.INTERVAL_DAY, pendingIntent);

                // Start foreground service
                startForegroundService(new Intent(MainActivity.this, MyForegroundService.class));
                Toast.makeText(MainActivity.this, "Alarm set", Toast.LENGTH_SHORT).show();
                Intent serviceIntent = new Intent(MainActivity.this, MyBackgroundService.class);
                startService(serviceIntent);
            }
        });


        alarm_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AlarmBroadCast.mediaPlayer.isPlaying()) {
                    AlarmBroadCast.mediaPlayer.stop();
                }
                Intent serviceIntent = new Intent(MainActivity.this, MyBackgroundService.class);
                stopService(serviceIntent);
                startForegroundService(new Intent(MainActivity.this, MyForegroundService.class));


            }
        });


    }

    // Broadcast Receiver to handle alarm events

    // Foreground Service
}
