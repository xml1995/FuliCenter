package com.example.lenovobyeoz.fulicenter.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovobyeoz.fulicenter.FuLiCenterApplication;
import com.example.lenovobyeoz.fulicenter.R;
import com.example.lenovobyeoz.fulicenter.activity.MainActivity;
import com.example.lenovobyeoz.fulicenter.adapter.CartAdapter;
import com.example.lenovobyeoz.fulicenter.bean.CartBean;
import com.example.lenovobyeoz.fulicenter.bean.User;
import com.example.lenovobyeoz.fulicenter.net.NetDao;
import com.example.lenovobyeoz.fulicenter.utils.CommonUtils;
import com.example.lenovobyeoz.fulicenter.utils.ConvertUtils;
import com.example.lenovobyeoz.fulicenter.utils.L;
import com.example.lenovobyeoz.fulicenter.utils.OkHttpUtils;
import com.example.lenovobyeoz.fulicenter.utils.ResultUtils;
import com.example.lenovobyeoz.fulicenter.view.SpaceItemDecoration;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartFragment extends BaseFragment {

    private static final String TAG = CartFragment.class.getSimpleName();

    @BindView(R.id.tv_refresh)

    TextView mTvRefresh;

    @BindView(R.id.rv)

    RecyclerView mRv;

    @BindView(R.id.srl)

    SwipeRefreshLayout mSrl;

    LinearLayoutManager llm;

    MainActivity mContext;

    CartAdapter mAdapter;

    ArrayList<CartBean> mList;



    @Nullable

    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_newgoods, container, false);

        ButterKnife.bind(this, layout);

        mContext = (MainActivity) getContext();

        mList = new ArrayList<>();

        mAdapter = new CartAdapter(mContext,mList);

        super.onCreateView(inflater,container,savedInstanceState);

        return layout;

    }



    @Override

    protected void setListener() {

        setPullDownListener();

    }



    private void setPullDownListener() {

        mSrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override

            public void onRefresh() {

                mSrl.setRefreshing(true);

                mTvRefresh.setVisibility(View.VISIBLE);

                downloadCart();

            }

        });

    }



    @Override

    protected  void initData() {

        downloadCart();

    }



    private void downloadCart() {

        User user = FuLiCenterApplication.getUser();

        if(user!=null){

            NetDao.downloadCart(mContext, user.getMuserName(), new OkHttpUtils.OnCompleteListener<String>() {

                @Override

                public void onSuccess(String s) {

                    ArrayList<CartBean> list = ResultUtils.getCartFromJson(s);

                    L.e(TAG,"result="+list);

                    mSrl.setRefreshing(false);

                    mTvRefresh.setVisibility(View.GONE);

                    if(list!=null && list.size()>0){

                        L.e(TAG,"list[0]="+list.get(0));

                        mAdapter.initData(list);

                    }

                }



                @Override

                public void onError(String error) {

                    mSrl.setRefreshing(false);

                    mTvRefresh.setVisibility(View.GONE);

                    CommonUtils.showShortToast(error);

                    L.e("error:"+error);

                }

            });

        }

    }



    @Override

    protected  void initView() {

        mSrl.setColorSchemeColors(

                getResources().getColor(R.color.google_blue),

                getResources().getColor(R.color.google_green),

                getResources().getColor(R.color.google_red),

                getResources().getColor(R.color.google_yellow)

        );

        llm = new LinearLayoutManager(mContext);

        mRv.setLayoutManager(llm);

        mRv.setHasFixedSize(true);

        mRv.setAdapter(mAdapter);

        mRv.addItemDecoration(new SpaceItemDecoration(12));

    }

}