package com.eaa.callmanage;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by 易 on 2016/5/30.
 */
public class CallLinstnerServices extends Service{
    public  static final String ACTION = "com.android.broadcast.RECEIVER_ACTION";
    private CallBroadcastReceiver cbr;

    @Override
    public void onCreate() {
        cbr= new CallBroadcastReceiver();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.NEW_OUTGOING_CALL");
        intentFilter.addAction("android.intent.action.PHONE_STATE");
        registerReceiver(cbr,intentFilter);

        super.onCreate();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        flags = START_STICKY;
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(cbr);
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }


}
