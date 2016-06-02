package com.eaa.callmanage;


import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.eaa.recorder.RecorderServices;

/**
 * Created by 易 on 2016/5/30.
 */
public class CallBroadcastReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)){
//            Boolean stopped = context.stopService(new Intent(context, RecorderServices.class));
//            Log.i("CallRecorder", "stopService for RecordService returned " + stopped);

            String callNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
            Log.i("CallRecorder", " callNumber="+callNumber);
            Intent callIntent = new Intent(context, RecorderServices.class);
            callIntent.putExtra("outCallNumber", callNumber);
            context.startService(callIntent);
        }else{
            PhoneListener phoneListener = new PhoneListener(context);
            TelephonyManager tm = (TelephonyManager)
                    context.getSystemService(Context.TELEPHONY_SERVICE);
            tm.listen(phoneListener,PhoneStateListener.LISTEN_CALL_STATE);
        }

    }

}
