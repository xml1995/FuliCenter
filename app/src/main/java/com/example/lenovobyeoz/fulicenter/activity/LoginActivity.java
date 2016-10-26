package com.example.lenovobyeoz.fulicenter.activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.lenovobyeoz.fulicenter.FuLiCenterApplication;
import com.example.lenovobyeoz.fulicenter.I;
import com.example.lenovobyeoz.fulicenter.R;
import com.example.lenovobyeoz.fulicenter.bean.Result;
import com.example.lenovobyeoz.fulicenter.bean.User;
import com.example.lenovobyeoz.fulicenter.dao.UserDao;
import com.example.lenovobyeoz.fulicenter.net.NetDao;
import com.example.lenovobyeoz.fulicenter.utils.CommonUtils;
import com.example.lenovobyeoz.fulicenter.utils.L;
import com.example.lenovobyeoz.fulicenter.utils.MFGT;
import com.example.lenovobyeoz.fulicenter.utils.OkHttpUtils;
import com.example.lenovobyeoz.fulicenter.utils.ResultUtils;
import com.example.lenovobyeoz.fulicenter.utils.SharePrefrenceUtils;
import com.example.lenovobyeoz.fulicenter.view.DisplayUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();



    @BindView(R.id.username)

    EditText mUsername;

    @BindView(R.id.password)

    EditText mPassword;



    String username;

    String password;

    LoginActivity mContext;



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        mContext = this;

        super.onCreate(savedInstanceState);

    }



    @Override

    protected void initView() {

        DisplayUtils.initBackWithTitle(mContext,getResources().getString(R.string.login));

    }



    @Override

    protected void initData() {



    }



    @Override

    protected void setListener() {



    }



    @OnClick({R.id.btn_login, R.id.btn_register})

    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btn_login:

                checkedInput();

                break;

            case R.id.btn_register:

                MFGT.gotoRegister(this);

                break;

        }

    }



    private void checkedInput() {

        username = mUsername.getText().toString().trim();

        password = mPassword.getText().toString().trim();

        if(TextUtils.isEmpty(username)){

            CommonUtils.showLongToast(R.string.user_name_connot_be_empty);

            mUsername.requestFocus();

            return;

        }else if(TextUtils.isEmpty(password)){

            CommonUtils.showLongToast(R.string.password_connot_be_empty);

            mPassword.requestFocus();

            return;

        }



        login();

    }



    private void login() {

        final ProgressDialog pd = new ProgressDialog(mContext);

        pd.setMessage(getResources().getString(R.string.logining));

        pd.show();

        L.e(TAG,"username="+username+",password="+password);

        NetDao.login(mContext, username, password, new OkHttpUtils.OnCompleteListener<String>() {

            @Override

            public void onSuccess(String s) {

                Result result = ResultUtils.getResultFromJson(s,User.class);

                L.e(TAG,"result="+result);

                if(result==null){

                    CommonUtils.showLongToast(R.string.login_fail);

                }else{

                    if(result.isRetMsg()){

                        User user = (User) result.getRetData();

                        L.e(TAG,"user="+user);

                        UserDao dao = new UserDao(mContext);

                        boolean isSuccess = dao.saveUser(user);

                        if(isSuccess){

                            SharePrefrenceUtils.getInstence(mContext).saveUser(user.getMuserName());

                            FuLiCenterApplication.setUser(user);

                            MFGT.finish(mContext);

                        }else{

                            CommonUtils.showLongToast(R.string.user_database_error);

                        }

                    }else{

                        if(result.getRetCode()==I.MSG_LOGIN_UNKNOW_USER){

                            CommonUtils.showLongToast(R.string.login_fail_unknow_user);

                        }else if(result.getRetCode()==I.MSG_LOGIN_ERROR_PASSWORD){

                            CommonUtils.showLongToast(R.string.login_fail_error_password);

                        }else{

                            CommonUtils.showLongToast(R.string.login_fail);

                        }

                    }

                }

                pd.dismiss();

            }



            @Override

            public void onError(String error) {

                pd.dismiss();

                CommonUtils.showLongToast(error);

                L.e(TAG,"error="+error);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == I.REQUEST_CODE_REGISTER){
            String name = data.getStringExtra(I.User.USER_NAME);
            mUsername.setText(name);
        }
    }
}