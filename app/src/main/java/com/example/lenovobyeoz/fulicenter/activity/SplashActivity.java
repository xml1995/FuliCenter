package com.example.lenovobyeoz.fulicenter.activity;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lenovobyeoz.fulicenter.R;

public class SplashActivity extends AppCompatActivity {

    private static final long splashTime=2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }
    @Override
    protected  void onStart(){
        super.onStart();
        new Thread(new Runnable() {
            @Override
            public void run() {
                long start= System.currentTimeMillis();
                long costTime=System.currentTimeMillis() - start;
                if(splashTime-costTime>0){
                    try {
                        Thread.sleep(splashTime-costTime);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
            }
        }).start();
    }
}
