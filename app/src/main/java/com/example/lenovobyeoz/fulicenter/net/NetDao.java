package com.example.lenovobyeoz.fulicenter.net;

import android.content.Context;

import com.example.lenovobyeoz.fulicenter.I;
import com.example.lenovobyeoz.fulicenter.activity.MainActivity;
import com.example.lenovobyeoz.fulicenter.bean.BoutiqueBean;
import com.example.lenovobyeoz.fulicenter.bean.CategoryChildBean;
import com.example.lenovobyeoz.fulicenter.bean.CategoryGroupBean;
import com.example.lenovobyeoz.fulicenter.bean.GoodsDetailsBean;
import com.example.lenovobyeoz.fulicenter.bean.NewGoodsBean;
import com.example.lenovobyeoz.fulicenter.utils.OkHttpUtils;
public class NetDao {

    public static void downloadNewGoods(Context context,int catId, int pageId, OkHttpUtils.OnCompleteListener<NewGoodsBean[]> listener){

        OkHttpUtils utils = new OkHttpUtils(context);

        utils.setRequestUrl(I.REQUEST_FIND_NEW_BOUTIQUE_GOODS)

                .addParam(I.NewAndBoutiqueGoods.CAT_ID,String.valueOf(catId))

                .addParam(I.PAGE_ID,String.valueOf(pageId))

                .addParam(I.PAGE_SIZE,String.valueOf(I.PAGE_SIZE_DEFAULT))

                .targetClass(NewGoodsBean[].class)

                .execute(listener);

    }



    public static void downloadGoodsDetail(Context context, int goodsId, OkHttpUtils.OnCompleteListener<GoodsDetailsBean> listener){

        OkHttpUtils utils = new OkHttpUtils(context);

        utils.setRequestUrl(I.REQUEST_FIND_GOOD_DETAILS)

                .addParam(I.GoodsDetails.KEY_GOODS_ID,String.valueOf(goodsId))

                .targetClass(GoodsDetailsBean.class)

                .execute(listener);

    }



    public static void downloadBuotique(Context context, OkHttpUtils.OnCompleteListener<BoutiqueBean[]> listener){

        OkHttpUtils utils = new OkHttpUtils(context);

        utils.setRequestUrl(I.REQUEST_FIND_BOUTIQUES)

                .targetClass(BoutiqueBean[].class)

                .execute(listener);

    }



    public static void downloadCategoryGroup(Context context, OkHttpUtils.OnCompleteListener<CategoryGroupBean[]> listener){

        OkHttpUtils utils = new OkHttpUtils(context);

        utils.setRequestUrl(I.REQUEST_FIND_CATEGORY_GROUP)

                .targetClass(CategoryGroupBean[].class)

                .execute(listener);

    }



    public static void downloadCategoryChild(Context context,int parentId, OkHttpUtils.OnCompleteListener<CategoryChildBean[]> listener){

        OkHttpUtils utils = new OkHttpUtils(context);

        utils.setRequestUrl(I.REQUEST_FIND_CATEGORY_CHILDREN)

                .addParam(I.CategoryChild.PARENT_ID,String.valueOf(parentId))

                .targetClass(CategoryChildBean[].class)

                .execute(listener);

    }
    public static void downloadCategoryGoods(Context context,int catId, int pageId, OkHttpUtils.OnCompleteListener<NewGoodsBean[]> listener){

        OkHttpUtils utils = new OkHttpUtils(context);

        utils.setRequestUrl(I.REQUEST_FIND_GOODS_DETAILS)

                .addParam(I.NewAndBoutiqueGoods.CAT_ID,String.valueOf(catId))

                .addParam(I.PAGE_ID,String.valueOf(pageId))

                .addParam(I.PAGE_SIZE,String.valueOf(I.PAGE_SIZE_DEFAULT))

                .targetClass(NewGoodsBean[].class)

                .execute(listener);

    }

}