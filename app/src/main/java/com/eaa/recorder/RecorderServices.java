package com.eaa.recorder;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 易 on 2016/6/1.
 * it is the service that record the call event
 */
public class RecorderServices extends Service implements MediaRecorder.OnInfoListener,MediaRecorder.OnErrorListener{
    public String AUDIOPATH = Environment.getExternalStorageDirectory().toString()+File.separator+"CallRecord";
    private String COMINGCALL = AUDIOPATH+File.separator+"Coming";
    private String OUTGOING = AUDIOPATH+File.separator+"Outgoing";
    private MediaRecorder mediaRecorder;
    private boolean isRecording = false;

    private String callNumber;
    @Override
    public void onCreate() {
        Log.e("Recorder Services", "starting recording");
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

//        File audioFileDir = new File(COMINGCALL);
//        if(!audioFileDir.exists()){
//            audioFileDir.mkdirs();
//        }
//        audioFileDir = new File(OUTGOING);
//        if(!audioFileDir.exists()){
//            audioFileDir.mkdirs();
//        }

        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(isRecording ==true){return super.onStartCommand(intent, flags, startId);}
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss-SS");
        String prefix = sdf.format(new Date());
        if(intent.hasExtra("outCallNumber")){
            callNumber = intent.getStringExtra("outCallNumber");
            prefix +="_"+callNumber;
            prefix = OUTGOING+File.separator+prefix+".3gp";
        }else if(intent.hasExtra("comingCallNumber")){
            callNumber = intent.getStringExtra("comingCallNumber");
            prefix = callNumber+"_"+prefix;
            prefix = COMINGCALL+File.separator+prefix+".3gp";
        }

        mediaRecorder.setOutputFile(prefix);
        Log.e("Recorder Services", prefix);
        try {
            mediaRecorder.prepare();
            mediaRecorder.start();
            isRecording = true;
        } catch (IOException e) {
            Log.e("MediaRecord","Recording Failed");
        }

        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        mediaRecorder.stop();
//        mediaRecorder.reset();
        mediaRecorder.release();
        isRecording = false;
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onError(MediaRecorder mr, int what, int extra) {
        isRecording = false;
    }

    @Override
    public void onInfo(MediaRecorder mr, int what, int extra) {
        isRecording = false;
    }
}
