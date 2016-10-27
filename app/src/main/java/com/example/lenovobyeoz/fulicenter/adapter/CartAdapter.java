package com.example.lenovobyeoz.fulicenter.adapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovobyeoz.fulicenter.R;
import com.example.lenovobyeoz.fulicenter.bean.CartBean;
import com.example.lenovobyeoz.fulicenter.bean.GoodsDetailsBean;
import com.example.lenovobyeoz.fulicenter.utils.ImageLoader;

import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
public class CartAdapter extends Adapter<CartAdapter.CartViewHolder> {

    Context mContext;

    ArrayList<CartBean> mList;



    public CartAdapter(Context context, ArrayList<CartBean> list) {

        mContext = context;

        mList = new ArrayList<>();

        mList.addAll(list);

    }



    @Override

    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        CartViewHolder holder = new CartViewHolder(LayoutInflater.from(mContext)

                .inflate(R.layout.item_cart, parent, false));

        return holder;

    }



    @Override

    public void onBindViewHolder(CartViewHolder holder, int position) {

        CartBean cartBean = mList.get(position);

        GoodsDetailsBean goods = cartBean.getGoods();

        if(goods!=null) {

            ImageLoader.downloadImg(mContext, holder.mIvCartThumb, goods.getGoodsThumb());

            holder.mTvCartGoodName.setText(goods.getGoodsName());

            holder.mTvCartPrice.setText(goods.getCurrencyPrice());

        }

        holder.mTvCartCount.setText("("+cartBean.getCount()+")");

        holder.mCbCartSelected.setChecked(false);

    }



    @Override

    public int getItemCount() {

        return mList != null ? mList.size() : 0;

    }



    public void initData(ArrayList<CartBean> list) {

        if (mList != null) {

            mList.clear();

        }

        mList.addAll(list);

        notifyDataSetChanged();

    }



    class CartViewHolder extends ViewHolder {

        @BindView(R.id.cb_cart_selected)

        CheckBox mCbCartSelected;

        @BindView(R.id.iv_cart_thumb)

        ImageView mIvCartThumb;

        @BindView(R.id.tv_cart_good_name)

        TextView mTvCartGoodName;

        @BindView(R.id.iv_cart_add)

        ImageView mIvCartAdd;

        @BindView(R.id.tv_cart_count)

        TextView mTvCartCount;

        @BindView(R.id.iv_cart_del)

        ImageView mIvCartDel;

        @BindView(R.id.tv_cart_price)

        TextView mTvCartPrice;



        CartViewHolder(View view) {

            super(view);

            ButterKnife.bind(this, view);

        }

    }

}