package com.srtianxia.hejexam.service;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.srtianxia.hejexam.view.activity.MainActivity;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by srtianxia on 2016/5/18.
 */
public class TimeService extends Service {

    private MainActivity.TimeBroadCastReceiver receiver;
    private int hour;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.srtianxia.hejexam.view.activity.MainActivity.TimeBroadCastReceiver");
        receiver = new MainActivity.TimeBroadCastReceiver();
        registerReceiver(receiver,intentFilter);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Calendar c = Calendar.getInstance();
                hour =  c.get(Calendar.HOUR_OF_DAY);
                if (hour >= 22 && hour < 24) {
                    Intent intent = new Intent("com.srtianxia.hejexam.view.activity.MainActivity.TimeBroadCastReceiver");
                    sendBroadcast(intent);
                }
            }
        },1000,2000);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
