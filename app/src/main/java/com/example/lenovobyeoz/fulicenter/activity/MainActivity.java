package com.example.lenovobyeoz.fulicenter.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.lenovobyeoz.fulicenter.R;
import com.example.lenovobyeoz.fulicenter.utils.L;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.layout_new_goods)
    RadioButton mLayoutNewGoods;
    @BindView(R.id.layout_boutique)
    RadioButton mLayoutBoutique;
    @BindView(R.id.layout_category)
    RadioButton mLayoutCategory;
    @BindView(R.id.layout_cart)
    RadioButton mLayoutCart;
    @BindView(R.id.tvCartHint)
    TextView mTvCartHint;
    @BindView(R.id.layout_personal_center)
    RadioButton mLayoutPersonalCenter;
    @BindView(R.id.rg_bottom_layout)
    RadioGroup mRgBottomLayout;

    int index;
    RadioButton[] rbs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        L.i("MainActivity onCreate");
        initView();
    }

    private void initView() {
        rbs=new RadioButton[5];
        rbs[0]=mLayoutNewGoods;
        rbs[1]=mLayoutBoutique;
        rbs[2]=mLayoutCategory;
        rbs[3]=mLayoutCart;
        rbs[4]=mLayoutPersonalCenter;
    }

    public void onCheckedChange(View v) {
        switch (v.getId()){
            case R.id.layout_new_goods:
                index=0;
                break;
            case R.id.layout_boutique:
                index=1;
                break;
            case R.id.layout_category:
                index=2;
                break;
            case R.id.layout_cart:
                index=3;
                break;
            case R.id.layout_personal_center:
                index=4;
                break;
        }
        setRadioButtonStatus();

    }

    private void setRadioButtonStatus() {
        for(int i=0;i<rbs.length;i++){
            if(i==index){
                rbs[i].setChecked(true);
            }else{
                rbs[i].setChecked(false);
            }
        }
    }
}
