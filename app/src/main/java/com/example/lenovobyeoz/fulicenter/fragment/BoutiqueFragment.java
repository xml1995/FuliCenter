package com.example.lenovobyeoz.fulicenter.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovobyeoz.fulicenter.I;
import com.example.lenovobyeoz.fulicenter.R;
import com.example.lenovobyeoz.fulicenter.activity.MainActivity;
import com.example.lenovobyeoz.fulicenter.adapter.BoutiqueAdapter;
import com.example.lenovobyeoz.fulicenter.bean.BoutiqueBean;
import com.example.lenovobyeoz.fulicenter.net.NetDao;
import com.example.lenovobyeoz.fulicenter.net.OkHttpUtils;
import com.example.lenovobyeoz.fulicenter.utils.CommonUtils;
import com.example.lenovobyeoz.fulicenter.utils.ConvertUtils;
import com.example.lenovobyeoz.fulicenter.utils.L;
import com.example.lenovobyeoz.fulicenter.view.SpaceItemDecoration;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.lenovobyeoz.fulicenter.I.PAGE_SIZE_DEFAULT;

public class BoutiqueFragment extends Fragment {
    @BindView(R.id.tv_refresh)
    TextView mTvRefresh;
    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.srl)
    SwipeRefreshLayout mSrl;
    LinearLayoutManager llm;
    MainActivity mContext;
    BoutiqueAdapter mAdapter;
    ArrayList<BoutiqueBean>mList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate( R.layout.fragment_newgoods, container, false );
        ButterKnife.bind( this, layout );
        mContext= (MainActivity) getContext();
        mAdapter=new BoutiqueAdapter(mContext,mList);
        initView();
        initData();
        return layout;
    }

    private void initData() {
        downLoadBoutique(I.ACTION_DOWNLOAD);
    }

    private void downLoadBoutique(final int action) {
        NetDao.downloadBoutique( mContext, new OkHttpUtils.OnCompleteListener<BoutiqueBean[]>() {
            @Override
            public void onSuccess(BoutiqueBean[] result) {
                mSrl.setRefreshing( false );
                mTvRefresh.setVisibility( View.GONE );
                mAdapter.setMore( true );
                L.e( "result=" + result );
                if (result != null && result.length > 0) {
                    ArrayList<BoutiqueBean> list=ConvertUtils.array2List( result );
                    if (action == I.ACTION_DOWNLOAD || action == I.ACTION_PULL_DOWN) {
                        mAdapter.initData( list );
                    } else {
                        mAdapter.addData(list);
                    }
                    if (list.size() >= PAGE_SIZE_DEFAULT) {
                        mAdapter.setMore( false );
                    } else {
                        mAdapter.setMore( false );
                    }
                }
            }

            @Override
            public void onError(String error) {
                mSrl.setRefreshing(false);
                mAdapter.setMore(false);
                mTvRefresh.setVisibility(View.GONE);
                CommonUtils.showShortToast(error);
                L.e("error: "+error);
            }

        });
    }

    private void initView() {
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
        mRv.addItemDecoration( new SpaceItemDecoration(12));
    }
}
