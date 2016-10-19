package com.example.lenovobyeoz.fulicenter.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.lenovobyeoz.fulicenter.utils.MFGT;


public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        setListener();
    }

    protected abstract void initView();
    protected abstract void initData();
    protected abstract void setListener();

    public void onBackPressed(){
        MFGT.finish(this);
    }
}
