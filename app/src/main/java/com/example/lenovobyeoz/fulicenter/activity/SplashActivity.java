package com.example.lenovobyeoz.fulicenter.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.lenovobyeoz.fulicenter.R;
import com.example.lenovobyeoz.fulicenter.utils.MFGT;
public class SplashActivity extends AppCompatActivity {

    private final long sleepTime = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_splash);
    }
    @Override
    protected void onStart() {
        super.onStart();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                MFGT.gotoMainActivity(SplashActivity.this);
                finish();
            }
        },sleepTime);
    }
}
