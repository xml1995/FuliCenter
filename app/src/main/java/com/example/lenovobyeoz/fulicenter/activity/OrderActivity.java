package com.example.lenovobyeoz.fulicenter.activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.lenovobyeoz.fulicenter.I;
import com.example.lenovobyeoz.fulicenter.R;
import com.example.lenovobyeoz.fulicenter.utils.L;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderActivity extends BaseActivity {

    private static final String TAG = OrderActivity.class.getSimpleName();



    @BindView(R.id.ed_order_name)

    EditText mEdOrderName;

    @BindView(R.id.ed_order_phone)

    EditText mEdOrderPhone;

    @BindView(R.id.spin_order_province)

    Spinner mSpinOrderProvince;

    @BindView(R.id.ed_order_street)

    EditText mEdOrderStreet;

    @BindView(R.id.tv_order_price)

    TextView mTvOrderPrice;



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_order);

        ButterKnife.bind(this);

        super.onCreate(savedInstanceState);

    }



    @Override

    protected void initView() {



    }



    @Override

    protected void initData() {

        String cartIds = getIntent().getStringExtra( I.Cart.ID);

        L.e(TAG,"cartIds="+cartIds);

    }



    @Override

    protected void setListener() {



    }



    @OnClick(R.id.tv_order_buy)

    public void onClick() {

    }

}