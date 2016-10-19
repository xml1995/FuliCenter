package com.example.lenovobyeoz.fulicenter.net;

import android.content.Context;

import com.example.lenovobyeoz.fulicenter.I;
import com.example.lenovobyeoz.fulicenter.bean.BoutiqueBean;
import com.example.lenovobyeoz.fulicenter.bean.GoodsDetailsBean;
import com.example.lenovobyeoz.fulicenter.bean.NewGoodsBean;

public class NetDao {
    public static void downloadNewGoods(Context context, int pageId, OkHttpUtils.OnCompleteListener<NewGoodsBean[]> listener) {
        OkHttpUtils utils = new OkHttpUtils( context );
        utils.setRequestUrl( I.REQUEST_FIND_NEW_BOUTIQUE_GOODS )
                .addParam( I.NewAndBoutiqueGoods.CAT_ID, String.valueOf( I.CAT_ID ) )
                .addParam( I.PAGE_ID, String.valueOf( pageId ) )
                .addParam( I.PAGE_SIZE, String.valueOf( I.PAGE_SIZE_DEFAULT ) )
                .targetClass( NewGoodsBean[].class)
                .execute( listener );
    }
    public static void downloadGoodsDetail(Context context, int goodsId, OkHttpUtils.OnCompleteListener<GoodsDetailsBean> listener){
              OkHttpUtils utils = new OkHttpUtils(context);
               utils.setRequestUrl(I.REQUEST_FIND_GOOD_DETAILS)
                                .addParam(I.GoodsDetails.KEY_GOODS_ID,String.valueOf(goodsId))
                                .targetClass(GoodsDetailsBean.class)
                                .execute(listener);

            }
    public static void downloadBoutique(Context context, OkHttpUtils.OnCompleteListener<BoutiqueBean[]> listener){
        OkHttpUtils utils=new OkHttpUtils( context );
        utils.setRequestUrl( I.REQUEST_FIND_BOUTIQUES )
                .targetClass( I.Boutique[].class )
                .execute( listener );
    }

}
