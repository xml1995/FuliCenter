package com.example.lenovobyeoz.fulicenter.fragment;
import android.os.Bundle;

import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovobyeoz.fulicenter.R;

public class PersonalCenterFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate( R.layout.fragment_personal_center,container,false);
        super.onCreateView(inflater, container, savedInstanceState);
        return layout;
    }
    @Override
    protected void initView() {
    }
    @Override
    protected void initData() {
    }
    @Override
    protected void setListener() {
    }
}