package com.example.lenovobyeoz.fulicenter.net;

import android.content.Context;

import com.example.lenovobyeoz.fulicenter.I;
import com.example.lenovobyeoz.fulicenter.bean.BoutiqueBean;
import com.example.lenovobyeoz.fulicenter.bean.CategoryChildBean;
import com.example.lenovobyeoz.fulicenter.bean.CategoryGroupBean;
import com.example.lenovobyeoz.fulicenter.bean.GoodsDetailsBean;
import com.example.lenovobyeoz.fulicenter.bean.NewGoodsBean;
import com.example.lenovobyeoz.fulicenter.bean.Result;
import com.example.lenovobyeoz.fulicenter.utils.MD5;
import com.example.lenovobyeoz.fulicenter.utils.OkHttpUtils;

public class NetDao {

    public static void downloadNewGoods(Context context,int catId, int pageId, OkHttpUtils.OnCompleteListener<NewGoodsBean[]> listener){

        OkHttpUtils<NewGoodsBean[]> utils = new OkHttpUtils<>(context);

        utils.setRequestUrl(I.REQUEST_FIND_NEW_BOUTIQUE_GOODS)

                .addParam(I.NewAndBoutiqueGoods.CAT_ID,String.valueOf(catId))

                .addParam(I.PAGE_ID,String.valueOf(pageId))

                .addParam(I.PAGE_SIZE,String.valueOf(I.PAGE_SIZE_DEFAULT))

                .targetClass(NewGoodsBean[].class)

                .execute(listener);

    }



    public static void downloadGoodsDetail(Context context, int goodsId, OkHttpUtils.OnCompleteListener<GoodsDetailsBean> listener){

        OkHttpUtils<GoodsDetailsBean> utils = new OkHttpUtils<>(context);

        utils.setRequestUrl(I.REQUEST_FIND_GOOD_DETAILS)

                .addParam(I.GoodsDetails.KEY_GOODS_ID,String.valueOf(goodsId))

                .targetClass(GoodsDetailsBean.class)

                .execute(listener);

    }



    public static void downloadBuotique(Context context, OkHttpUtils.OnCompleteListener<BoutiqueBean[]> listener){

        OkHttpUtils<BoutiqueBean[]> utils = new OkHttpUtils<>(context);

        utils.setRequestUrl(I.REQUEST_FIND_BOUTIQUES)

                .targetClass(BoutiqueBean[].class)

                .execute(listener);

    }



    public static void downloadCategoryGroup(Context context, OkHttpUtils.OnCompleteListener<CategoryGroupBean[]> listener){

        OkHttpUtils<CategoryGroupBean[]> utils = new OkHttpUtils<>(context);

        utils.setRequestUrl(I.REQUEST_FIND_CATEGORY_GROUP)

                .targetClass(CategoryGroupBean[].class)

                .execute(listener);

    }



    public static void downloadCategoryChild(Context context,int parentId, OkHttpUtils.OnCompleteListener<CategoryChildBean[]> listener){

        OkHttpUtils<CategoryChildBean[]> utils = new OkHttpUtils<>(context);

        utils.setRequestUrl(I.REQUEST_FIND_CATEGORY_CHILDREN)

                .addParam(I.CategoryChild.PARENT_ID,String.valueOf(parentId))

                .targetClass(CategoryChildBean[].class)

                .execute(listener);

    }



    public static void downloadCategoryGoods(Context context,int catId, int pageId, OkHttpUtils.OnCompleteListener<NewGoodsBean[]> listener){

        OkHttpUtils<NewGoodsBean[]> utils = new OkHttpUtils<>(context);

        utils.setRequestUrl(I.REQUEST_FIND_GOODS_DETAILS)

                .addParam(I.NewAndBoutiqueGoods.CAT_ID,String.valueOf(catId))

                .addParam(I.PAGE_ID,String.valueOf(pageId))

                .addParam(I.PAGE_SIZE,String.valueOf(I.PAGE_SIZE_DEFAULT))

                .targetClass(NewGoodsBean[].class)

                .execute(listener);

    }



    public static void register(Context context, String username, String nickname, String password, OkHttpUtils.OnCompleteListener<Result> listener){

        OkHttpUtils<Result> utils = new OkHttpUtils<>(context);

        utils.setRequestUrl(I.REQUEST_REGISTER)

                .addParam(I.User.USER_NAME,username)

                .addParam(I.User.NICK,nickname)

                .addParam(I.User.PASSWORD, MD5.getMessageDigest(password))

                .targetClass(Result.class)

                .post()

                .execute(listener);

    }



    public static void login(Context context, String username, String password, OkHttpUtils.OnCompleteListener<String> listener){

        OkHttpUtils<String> utils = new OkHttpUtils<>(context);

        utils.setRequestUrl(I.REQUEST_LOGIN)

                .addParam(I.User.USER_NAME,username)

                .addParam(I.User.PASSWORD,MD5.getMessageDigest(password))

                .targetClass(String.class)

                .execute(listener);

    }



    public static void updateNick(Context context, String username, String nick, OkHttpUtils.OnCompleteListener<String> listener){

        OkHttpUtils<String> utils = new OkHttpUtils<>(context);

        utils.setRequestUrl(I.REQUEST_UPDATE_USER_NICK)

                .addParam(I.User.USER_NAME,username)

                .addParam(I.User.NICK,nick)

                .targetClass(String.class)

                .execute(listener);

    }

}