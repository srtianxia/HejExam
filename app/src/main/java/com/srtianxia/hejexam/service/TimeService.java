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
 * 监视时间的service
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
        //注册广播
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.srtianxia.hejexam.view.activity.MainActivity.TimeBroadCastReceiver");
        receiver = new MainActivity.TimeBroadCastReceiver();
        registerReceiver(receiver,intentFilter);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //使用timer定时器，应用启动1秒后每2秒钟判断一次，是9：00-15：00的时间段就发送广播通知刷新数据
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Calendar c = Calendar.getInstance();
                hour =  c.get(Calendar.HOUR_OF_DAY);
                if (hour >= 9 && hour < 15) {
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
