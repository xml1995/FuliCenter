package com.example.lenovobyeoz.fulicenter.activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lenovobyeoz.fulicenter.I;
import com.example.lenovobyeoz.fulicenter.R;
import com.example.lenovobyeoz.fulicenter.adapter.GoodsAdapter;
import com.example.lenovobyeoz.fulicenter.bean.NewGoodsBean;
import com.example.lenovobyeoz.fulicenter.net.NetDao;
import com.example.lenovobyeoz.fulicenter.utils.CommonUtils;
import com.example.lenovobyeoz.fulicenter.utils.ConvertUtils;
import com.example.lenovobyeoz.fulicenter.utils.L;
import com.example.lenovobyeoz.fulicenter.utils.MFGT;
import com.example.lenovobyeoz.fulicenter.utils.OkHttpUtils;
import com.example.lenovobyeoz.fulicenter.view.SpaceItemDecoration;

import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CategoryChildActivity extends BaseActivity {



    @BindView(R.id.tv_refresh)

    TextView mTvRefresh;

    @BindView(R.id.rv)

    RecyclerView mRv;

    @BindView(R.id.srl)

    SwipeRefreshLayout mSrl;



    CategoryChildActivity mContext;

    GoodsAdapter mAdapter;

    ArrayList<NewGoodsBean> mList;

    int pageId = 1;

    GridLayoutManager glm;

    int catId;

    @BindView(R.id.btn_sort_price)

    Button mBtnSortPrice;

    @BindView(R.id.btn_sort_addtime)

    Button mBtnSortAddtime;

    boolean addTimeAsc = false;

    boolean priceAsc = false;

    int sortBy = I.SORT_BY_ADDTIME_DESC;



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_category_child);

        ButterKnife.bind(this);

        mContext = this;

        mList = new ArrayList<>();

        mAdapter = new GoodsAdapter(mContext, mList);

        catId = getIntent().getIntExtra(I.CategoryChild.CAT_ID, 0);

        if (catId == 0) {

            finish();

        }

        super.onCreate(savedInstanceState);

    }



    @Override

    protected void initView() {

        mSrl.setColorSchemeColors(

                getResources().getColor(R.color.google_blue),

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

                mTvRefresh.setVisibility(View.VISIBLE);

                pageId = 1;

                downloadCategoryGoods(I.ACTION_PULL_DOWN);

            }

        });

    }



    private void downloadCategoryGoods(final int action) {

        NetDao.downloadCategoryGoods(mContext, catId, pageId, new OkHttpUtils.OnCompleteListener<NewGoodsBean[]>() {

            @Override

            public void onSuccess(NewGoodsBean[] result) {

                mSrl.setRefreshing(false);

                mTvRefresh.setVisibility(View.GONE);

                mAdapter.setMore(true);

                L.e("result=" + result);

                if (result != null && result.length > 0) {

                    ArrayList<NewGoodsBean> list = ConvertUtils.array2List(result);

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

                    downloadCategoryGoods(I.ACTION_PULL_UP);

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

        downloadCategoryGoods(I.ACTION_DOWNLOAD);

    }



    @OnClick(R.id.backClickArea)

    public void onClick() {

        MFGT.finish(this);

    }



    @OnClick({R.id.btn_sort_price, R.id.btn_sort_addtime})

    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btn_sort_price:

                if(priceAsc){

                    sortBy = I.SORT_BY_PRICE_ASC;

                }else{

                    sortBy = I.SORT_BY_PRICE_DESC;

                }

                priceAsc = !priceAsc;

                break;

            case R.id.btn_sort_addtime:

                if(addTimeAsc){

                    sortBy = I.SORT_BY_ADDTIME_ASC;

                }else{

                    sortBy = I.SORT_BY_ADDTIME_DESC;

                }
                addTimeAsc = !addTimeAsc;
                break;
        }
        mAdapter.setSoryBy(sortBy);

    }

}