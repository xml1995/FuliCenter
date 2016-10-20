package com.example.lenovobyeoz.fulicenter.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.lenovobyeoz.fulicenter.R;
import com.example.lenovobyeoz.fulicenter.activity.MainActivity;
import com.example.lenovobyeoz.fulicenter.adapter.CategoryAdapter;
import com.example.lenovobyeoz.fulicenter.bean.CategoryChildBean;
import com.example.lenovobyeoz.fulicenter.bean.CategoryGroupBean;
import com.example.lenovobyeoz.fulicenter.net.NetDao;
import com.example.lenovobyeoz.fulicenter.utils.ConvertUtils;
import com.example.lenovobyeoz.fulicenter.utils.L;
import com.example.lenovobyeoz.fulicenter.utils.OkHttpUtils;

import java.util.ArrayList;
import butterknife.BindView;

import butterknife.ButterKnife;

public class CategoryFragment extends BaseFragment {
    @BindView(R.id.elv_category)
    ExpandableListView mElvCategory;
    CategoryAdapter mAdapter;

    MainActivity mContext;

    ArrayList<CategoryGroupBean> mGroupList;

    ArrayList<ArrayList<CategoryChildBean>> mChildList;



    int groupCount;



    @Nullable

    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_category, container, false);

        ButterKnife.bind(this, layout);

        mContext = (MainActivity) getContext();

        mGroupList = new ArrayList<>();

        mChildList = new ArrayList<>();

        mAdapter = new CategoryAdapter(mContext,mGroupList,mChildList);

        super.onCreateView(inflater, container, savedInstanceState);

        return layout;

    }



    @Override

    protected void initView() {

        mElvCategory.setGroupIndicator(null);

        mElvCategory.setAdapter(mAdapter);

    }
    @Override

    protected void initData() {

        downloadGroup();

    }



    private void downloadGroup() {

        NetDao.downloadCategoryGroup(mContext, new OkHttpUtils.OnCompleteListener<CategoryGroupBean[]>() {

            @Override

            public void onSuccess(CategoryGroupBean[] result) {

                L.e("downloadGroup,result="+result);

                if(result!=null && result.length>0){

                    ArrayList<CategoryGroupBean> groupList = ConvertUtils.array2List(result);

                    L.e("groupList="+groupList.size());

                    mGroupList.addAll(groupList);

                    for (CategoryGroupBean g:groupList){

                        downloadChild(g.getId());

                    }

                }

            }



            @Override

            public void onError(String error) {

                L.e("error="+error);

            }

        });

    }



    private void downloadChild(int id) {

        NetDao.downloadCategoryChild(mContext, id, new OkHttpUtils.OnCompleteListener<CategoryChildBean[]>() {

            @Override

            public void onSuccess(CategoryChildBean[] result) {

                groupCount++;

                L.e("downloadChild,result="+result);

                if(result!=null && result.length>0) {

                    ArrayList<CategoryChildBean> childList = ConvertUtils.array2List(result);

                    L.e("childList="+childList.size());

                    mChildList.add(childList);

                }

                if(groupCount==mGroupList.size()){

                    mAdapter.initData(mGroupList,mChildList);

                }



            }



            @Override

            public void onError(String error) {

                L.e("error="+error);

            }

        });
    }
    @Override

    protected void setListener() {



    }

}
