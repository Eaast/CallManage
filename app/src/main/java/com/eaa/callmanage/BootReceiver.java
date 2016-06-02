package com.eaa.callmanage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by 易 on 2016/6/2.
 */
public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Intent intent1 = new Intent(context,CallLinstnerServices.class);
        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startService(intent1);
    }
}
