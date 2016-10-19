package com.example.lenovobyeoz.fulicenter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.lenovobyeoz.fulicenter.I;
import com.example.lenovobyeoz.fulicenter.R;
import com.example.lenovobyeoz.fulicenter.bean.BoutiqueBean;

import java.util.ArrayList;

/**
 * Created by lenovoByEOZ on 2016/10/19.
 */

public class BoutiqueAdapter extends RecyclerView.Adapter {
    Context mContext;
    ArrayList<BoutiqueBean>mList;
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder=null;
        if (viewType== I.TYPE_FOOTER){
            holder=new GoodsAdapter.FooterViewHolder( LayoutInflater.from( mContext )
                    .inflate( R.layout.item_footer,parent,false ));
        }else {
            holder=new GoodsAdapter.FooterViewHolder( LayoutInflater.from( mContext )
                    .inflate( R.layout.item_footer,parent,false ));

        }

        return holder
                ;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
