package com.example.lenovobyeoz.fulicenter.utils;

import android.app.Activity;

import android.content.Context;

import android.content.Intent;

import android.graphics.Bitmap;

import android.graphics.drawable.BitmapDrawable;

import android.net.Uri;

import android.os.Bundle;

import android.os.Environment;

import android.provider.MediaStore;

import android.util.DisplayMetrics;

import android.util.Log;

import android.view.Display;

import android.view.Gravity;

import android.view.View;

import android.widget.ImageView;

import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.lenovobyeoz.fulicenter.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class OnSetAvatarListener implements View.OnClickListener {
    private static final int REQUEST_TAKE_PICTURE=1;
    private static final int REQUEST_CHOOSE_PHOTO=2;
    public static final int REQUEST_CROP_PHOTO=3;
    private Activity mActivity;
    private View mLayout;
    PopupWindow mPopuWindow;
    String mUserName;

    String mAvatarType;

    public OnSetAvatarListener(Activity mActivity, int parentId, String userName, String avatarType) {
        this.mActivity = mActivity;

        mUserName=userName;

        mAvatarType=avatarType;

        View parentLayout = mActivity.findViewById(parentId);

        mLayout= View.inflate(mActivity, R.layout.popu_show_avatar,null);

        mLayout.findViewById(R.id.btn_take_picture).setOnClickListener(this);

        mLayout.findViewById(R.id.btn_choose_photo).setOnClickListener(this);

        showPopupWindow(parentLayout);

    }

    private void showPopupWindow(View parentLayout) {

        mPopuWindow = new PopupWindow(mLayout, getScreenDisplay().widthPixels, (int)(90*getScreenDisplay().density));

        mPopuWindow.setOutsideTouchable(true);

        mPopuWindow.setTouchable(true);

        mPopuWindow.setFocusable(true);

        mPopuWindow.setBackgroundDrawable(new BitmapDrawable());

        mPopuWindow.setAnimationStyle(R.style.styles_pop_window);

        //设置PopuWindow从屏幕底部进入

        mPopuWindow.showAtLocation(parentLayout, Gravity.BOTTOM, 0, 0);

    }

    private DisplayMetrics getScreenDisplay(){

        //创建用于获取屏幕尺寸、像素密度的对象

        Display defaultDisplay  = mActivity.getWindowManager().getDefaultDisplay();

        //创建用于获取屏幕尺寸、像素密度等信息的对象

        DisplayMetrics outMetrics = new DisplayMetrics();

        defaultDisplay.getMetrics(outMetrics);

        return outMetrics;

    }
    @Override

    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_take_picture:

                takePicture();

                break;

            case R.id.btn_choose_photo:

                choosePhoto();

                break;

        }

    }

    private void choosePhoto() {

        Intent intent = new Intent(Intent.ACTION_PICK);

        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");

        mActivity.startActivityForResult(intent, REQUEST_CHOOSE_PHOTO);

    }

    private void takePicture() {

        File file = FileUtils.getAvatarPath(mActivity,mAvatarType, mUserName + ".jpg");

        Uri uri = Uri.fromFile(file);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);

        mActivity.startActivityForResult(intent,REQUEST_TAKE_PICTURE);

    }

    public void closePopuAvatar() {

        if (mPopuWindow != null) {

            mPopuWindow.dismiss();

        }

    }

    public void setAvatar(int requestCode, Intent data, ImageView ivAvatar) {

        switch (requestCode) {

            case REQUEST_CHOOSE_PHOTO:

                if (data != null) {

                    startCropPhotoActivity(data.getData(), 200, 200,REQUEST_CROP_PHOTO);

                }

                break;

            case REQUEST_TAKE_PICTURE:

                if (data != null) {

                    startCropPhotoActivity(data.getData(), 200, 200,REQUEST_CROP_PHOTO);

                }

                break;

            case REQUEST_CROP_PHOTO:

                saveCropAndShowAvatar(ivAvatar, data);

                closePopuAvatar();

                break;

        }

    }

    private void saveCropAndShowAvatar(ImageView ivAvatar, Intent data) {

        Bundle extras = data.getExtras();

        Bitmap avatar = extras.getParcelable("data");

        if (avatar == null) {

            return;

        }

        ivAvatar.setImageBitmap(avatar);

        File file = FileUtils.getAvatarPath(mActivity,mAvatarType, mUserName + ".jpg");

        if(!file.getParentFile().exists()){

            Toast.makeText(mActivity, "照片保存失败,保存的路径不存在", Toast.LENGTH_LONG).show();

            return ;

        }

        FileOutputStream out = null;

        try {

            out = new FileOutputStream(file);

            avatar.compress(Bitmap.CompressFormat.JPEG,100,out);

        } catch (FileNotFoundException e) {

            e.printStackTrace();

            Log.i("main", "头像保存失败");

        }

    }

    public static File saveCropAndShowAvatar(Intent data, Activity context, String avatarType, String avatarName) {

        Bundle extras = data.getExtras();

        Bitmap avatar = extras.getParcelable("data");

        if (avatar == null) {

            return null;

        }

        File file = FileUtils.getAvatarPath(context,avatarType, avatarName + ".jpg");

        if(!file.getParentFile().exists()){

            Toast.makeText(context, "照片保存失败,保存的路径不存在", Toast.LENGTH_LONG).show();

            return null;

        }

        FileOutputStream out = null;

        try {

            out = new FileOutputStream(file);

            avatar.compress(Bitmap.CompressFormat.JPEG,100,out);

        } catch (FileNotFoundException e) {

            e.printStackTrace();

            Log.i("main", "头像保存失败");

        }

        return file;

    }
    private void startCropPhotoActivity(Uri uri, int outputX, int outputY, int requestCode) {

        Intent intent = new Intent("com.android.camera.action.CROP");

        intent.setDataAndType(uri, "image/*");

        intent.putExtra("outputX", outputX);

        intent.putExtra("outputY", outputY);

        intent.putExtra("return-data", true);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);

        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());

        mActivity.startActivityForResult(intent,requestCode);

    }

    public static File getAvatarFile(Activity activity, String avatar){

        File dir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        File file;

        try {

            file = new File(dir,avatar);

            boolean isExists = file.getParentFile().exists();

            if(!isExists){

                isExists = file.getParentFile().mkdirs();

            }

            if(isExists){

                return file;

            }

        } catch (Exception e) {

            return null;

        }

        return null;

    }

    public static String getAvatarPath(Context context, String path){

        File dir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        File folder = new File(dir,path);

        if(!folder.exists()){

            folder.mkdir();

        }

        return folder.getAbsolutePath();

    }

}