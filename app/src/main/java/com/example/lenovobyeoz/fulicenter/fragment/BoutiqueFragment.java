package com.example.lenovobyeoz.fulicenter.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.lenovobyeoz.fulicenter.R;
import com.example.lenovobyeoz.fulicenter.activity.MainActivity;
import com.example.lenovobyeoz.fulicenter.adapter.BoutiqueAdapter;
import com.example.lenovobyeoz.fulicenter.bean.BoutiqueBean;
import com.example.lenovobyeoz.fulicenter.net.NetDao;
import com.example.lenovobyeoz.fulicenter.utils.OkHttpUtils;
import com.example.lenovobyeoz.fulicenter.utils.CommonUtils;
import com.example.lenovobyeoz.fulicenter.utils.ConvertUtils;
import com.example.lenovobyeoz.fulicenter.utils.L;
import com.example.lenovobyeoz.fulicenter.view.SpaceItemDecoration;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
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
    int pageId=1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate( R.layout.fragment_newgoods, container, false );
        ButterKnife.bind( this, layout );
        mContext= (MainActivity) getContext();
        mList=new ArrayList<>();
        mAdapter=new BoutiqueAdapter(mContext,mList);
        initView();
        initData();
        setListener();
        return layout;
    }
    private void setListener() {
        setPullDownListener();
    }

    private void setPullDownListener() {
        mSrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSrl.setRefreshing( true );
                mTvRefresh.setVisibility( View.VISIBLE );
                pageId = 1;
                downLoadBoutique();
            }

        });
    }

    private void initData() {
        downLoadBoutique();
    }
    private void downLoadBoutique() {
        NetDao.downloadBoutique( mContext, new OkHttpUtils.OnCompleteListener<BoutiqueBean[]>(){
            @Override
            public void onSuccess(BoutiqueBean[] result) {
                mSrl.setRefreshing( false );
                mTvRefresh.setVisibility( View.GONE );
                L.e( "result=" + result );
                if (result != null && result.length > 0) {
                    ArrayList<BoutiqueBean> list = ConvertUtils.array2List( result );
                        mAdapter.initData( list );
                    }
                }
                @Override
                public void onError (String error){
                    mSrl.setRefreshing( false );
                    mTvRefresh.setVisibility( View.GONE );
                    CommonUtils.showShortToast( error );
                    L.e( "error:" + error );
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
