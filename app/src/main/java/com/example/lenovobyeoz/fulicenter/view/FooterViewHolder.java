package com.example.lenovobyeoz.fulicenter.view;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.TextView;

import com.example.lenovobyeoz.fulicenter.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FooterViewHolder extends ViewHolder {
    @BindView(R.id.tvFooter)
    public TextView mTvFooter;
    public FooterViewHolder(View view){
        super(view);
        ButterKnife.bind(this,view);
    }

}
