package com.eaa.callmanage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by 易 on 2016/5/30.
 */
public class MainActivity  extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.main_layout);
        Intent intent = new Intent(this, CallLinstnerServices.class);
        startService(intent);
        super.onCreate(savedInstanceState);
    }
}
