package com.example.lenovobyeoz.fulicenter.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.lenovobyeoz.fulicenter.FuLiCenterApplication;
import com.example.lenovobyeoz.fulicenter.I;
import com.example.lenovobyeoz.fulicenter.R;
import com.example.lenovobyeoz.fulicenter.adapter.CollectsAdapter;
import com.example.lenovobyeoz.fulicenter.bean.CollectBean;
import com.example.lenovobyeoz.fulicenter.bean.User;
import com.example.lenovobyeoz.fulicenter.net.NetDao;
import com.example.lenovobyeoz.fulicenter.utils.CommonUtils;
import com.example.lenovobyeoz.fulicenter.utils.ConvertUtils;
import com.example.lenovobyeoz.fulicenter.utils.L;
import com.example.lenovobyeoz.fulicenter.utils.OkHttpUtils;
import com.example.lenovobyeoz.fulicenter.view.DisplayUtils;
import com.example.lenovobyeoz.fulicenter.view.SpaceItemDecoration;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CollectsActivity extends BaseActivity {



    CollectsActivity mContext;

    @BindView(R.id.tv_refresh)

    TextView mTvRefresh;

    @BindView(R.id.rv)

    RecyclerView mRv;

    @BindView(R.id.srl)

    SwipeRefreshLayout mSrl;

    CollectsAdapter mAdapter;

    ArrayList<CollectBean> mList;

    int pageId = 1;

    GridLayoutManager glm;

    User user = null;



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_collects);

        ButterKnife.bind(this);

        mContext = this;

        mList = new ArrayList<>();

        mAdapter = new CollectsAdapter(mContext,mList);

        super.onCreate(savedInstanceState);

    }



    @Override

    protected void initView() {

        DisplayUtils.initBackWithTitle(mContext, getResources().getString(R.string.collect_title));

        mSrl.setColorSchemeColors(

                getResources().getColor( R.color.google_blue),

                getResources().getColor(R.color.google_green),

                getResources().getColor(R.color.google_red),

                getResources().getColor(R.color.google_yellow)

        );

        glm = new GridLayoutManager(mContext, I.COLUM_NUM);

        mRv.setLayoutManager(glm);

        mRv.setHasFixedSize(true);

        mRv.setAdapter(mAdapter);

        mRv.addItemDecoration(new SpaceItemDecoration(12));

    }



    @Override

    protected void setListener() {

        setPullUpListener();

        setPullDownListener();

    }



    private void setPullDownListener() {

        mSrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override

            public void onRefresh() {

                mSrl.setRefreshing(true);

                mTvRefresh.setVisibility( View.VISIBLE);

                pageId = 1;

                downloadCollects(I.ACTION_PULL_DOWN);

            }

        });

    }



    private void downloadCollects(final int action) {

        NetDao.downloadCollects(mContext, user.getMuserName(), pageId, new OkHttpUtils.OnCompleteListener<CollectBean[]>() {

            @Override

            public void onSuccess(CollectBean[] result) {

                mSrl.setRefreshing(false);

                mTvRefresh.setVisibility(View.GONE);

                mAdapter.setMore(true);

                if (result != null && result.length > 0) {

                    ArrayList<CollectBean> list = ConvertUtils.array2List(result);

                    if (action == I.ACTION_DOWNLOAD || action == I.ACTION_PULL_DOWN) {

                        mAdapter.initData(list);

                    } else {

                        mAdapter.addData(list);

                    }

                    if (list.size() < I.PAGE_SIZE_DEFAULT) {

                        mAdapter.setMore(false);

                    }

                } else {

                    mAdapter.setMore(false);

                }

            }



            @Override

            public void onError(String error) {

                mSrl.setRefreshing(false);

                mTvRefresh.setVisibility(View.GONE);

                mAdapter.setMore(false);

                CommonUtils.showShortToast(error);

                L.e("error:" + error);

            }

        });

    }



    private void setPullUpListener() {

        mRv.setOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override

            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                super.onScrollStateChanged(recyclerView, newState);

                int lastPosition = glm.findLastVisibleItemPosition();

                if (newState == RecyclerView.SCROLL_STATE_IDLE

                        && lastPosition == mAdapter.getItemCount() - 1

                        && mAdapter.isMore()) {

                    pageId++;

                    downloadCollects(I.ACTION_PULL_UP);

                }

            }



            @Override

            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                super.onScrolled(recyclerView, dx, dy);

                int firstPosition = glm.findFirstVisibleItemPosition();

                mSrl.setEnabled(firstPosition == 0);

            }

        });

    }



    @Override

    protected void initData() {

        user = FuLiCenterApplication.getUser();

        if(user==null){

            finish();

        }

        downloadCollects( I.ACTION_DOWNLOAD);

    }
}
