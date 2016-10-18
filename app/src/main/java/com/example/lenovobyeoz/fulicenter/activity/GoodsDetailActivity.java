package com.example.lenovobyeoz.fulicenter.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lenovobyeoz.fulicenter.I;
import com.example.lenovobyeoz.fulicenter.R;
import com.example.lenovobyeoz.fulicenter.utils.L;

public class GoodsDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_goods_detail );
        int goodId=getIntent().getIntExtra( I.GoodsDetails.KEY_GOODS_ID,0);
        L.e("details","goodsid="+goodId);
    }
}
