package com.example.lenovobyeoz.fulicenter.fragment;
import android.os.Bundle;

import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovobyeoz.fulicenter.FuLiCenterApplication;
import com.example.lenovobyeoz.fulicenter.R;
import com.example.lenovobyeoz.fulicenter.activity.MainActivity;
import com.example.lenovobyeoz.fulicenter.bean.User;
import com.example.lenovobyeoz.fulicenter.utils.ImageLoader;
import com.example.lenovobyeoz.fulicenter.utils.L;
import com.example.lenovobyeoz.fulicenter.utils.MFGT;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonalCenterFragment extends BaseFragment {

    private static final String TAG = PersonalCenterFragment.class.getSimpleName();

    @BindView(R.id.iv_user_avatar)

    ImageView mIvUserAvatar;

    @BindView(R.id.tv_user_name)

    TextView mTvUserName;



    MainActivity mContext;



    @Nullable

    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_personal_center, container, false);

        ButterKnife.bind(this, layout);

        mContext = (MainActivity) getActivity();

        super.onCreateView(inflater, container, savedInstanceState);

        return layout;

    }



    @Override

    protected void initView() {



    }



    @Override

    protected void initData() {

        User user = FuLiCenterApplication.getUser();

        L.e(TAG,"user="+user);

        if(user==null){

            MFGT.gotoLogin(mContext);

        }else{

            ImageLoader.setAvatar(ImageLoader.getAvatarUrl(user),mContext,mIvUserAvatar);

            mTvUserName.setText(user.getMuserNick());

        }

    }



    @Override

    protected void setListener() {



    }



    @OnClick(R.id.tv_center_settings)

    public void onClick() {

    }

}