package com.example.lenovobyeoz.fulicenter.bean;

/**
 * Created by lenovoByEOZ on 2016/10/14.
 */

public class NewGoodsBean {

    /**
     * id : 1
     * goodsId : 7672
     * catId : 0
     * goodsName : 趣味煮蛋模具
     * goodsEnglishName : Kotobuki
     * goodsBrief : 将煮好的鸡蛋放到模具中，扣好卡扣，把蛋模放冰水，耐心等上10分钟，就可以变化成各种各样的形状，宝宝看了说不定胃口大开！
     * shopPrice : ￥110
     * currencyPrice : ￥140
     * promotePrice : ￥0
     * rankPrice : ￥0
     * isPromote : false
     * goodsThumb : 201509/thumb_img/7672_thumb_G_1442389445719.jpg
     * goodsImg : 201509/goods_img/7672_P_1442389445199.jpg
     */

    private int id;
    private int goodsId;
    private int catId;
    private String goodsName;
    private String goodsEnglishName;
    private String goodsBrief;
    private String shopPrice;
    private String currencyPrice;
    private String promotePrice;
    private String rankPrice;
    private boolean isPromote;
    private String goodsThumb;
    private String goodsImg;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsEnglishName() {
        return goodsEnglishName;
    }

    public void setGoodsEnglishName(String goodsEnglishName) {
        this.goodsEnglishName = goodsEnglishName;
    }

    public String getGoodsBrief() {
        return goodsBrief;
    }

    public void setGoodsBrief(String goodsBrief) {
        this.goodsBrief = goodsBrief;
    }

    public String getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(String shopPrice) {
        this.shopPrice = shopPrice;
    }

    public String getCurrencyPrice() {
        return currencyPrice;
    }

    public void setCurrencyPrice(String currencyPrice) {
        this.currencyPrice = currencyPrice;
    }

    public String getPromotePrice() {
        return promotePrice;
    }

    public void setPromotePrice(String promotePrice) {
        this.promotePrice = promotePrice;
    }

    public String getRankPrice() {
        return rankPrice;
    }

    public void setRankPrice(String rankPrice) {
        this.rankPrice = rankPrice;
    }

    public boolean isIsPromote() {
        return isPromote;
    }

    public void setIsPromote(boolean isPromote) {
        this.isPromote = isPromote;
    }

    public String getGoodsThumb() {
        return goodsThumb;
    }

    public void setGoodsThumb(String goodsThumb) {
        this.goodsThumb = goodsThumb;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public NewGoodsBean() {
    }

    public NewGoodsBean(int id, int goodsId, int catId, String goodsName, String goodsEnglishName, String goodsBrief, String shopPrice, String currencyPrice, String promotePrice, String rankPrice, boolean isPromote, String goodsThumb, String goodsImg) {
        this.id = id;
        this.goodsId = goodsId;
        this.catId = catId;
        this.goodsName = goodsName;
        this.goodsEnglishName = goodsEnglishName;
        this.goodsBrief = goodsBrief;
        this.shopPrice = shopPrice;
        this.currencyPrice = currencyPrice;
        this.promotePrice = promotePrice;
        this.rankPrice = rankPrice;
        this.isPromote = isPromote;
        this.goodsThumb = goodsThumb;
        this.goodsImg = goodsImg;
    }

    @Override
    public String toString() {
        return "NewGoodsBean{" +
                "id=" + id +
                ", goodsId=" + goodsId +
                ", catId=" + catId +
                ", goodsName='" + goodsName + '\'' +
                ", goodsEnglishName='" + goodsEnglishName + '\'' +
                ", goodsBrief='" + goodsBrief + '\'' +
                ", shopPrice='" + shopPrice + '\'' +
                ", currencyPrice='" + currencyPrice + '\'' +
                ", promotePrice='" + promotePrice + '\'' +
                ", rankPrice='" + rankPrice + '\'' +
                ", isPromote=" + isPromote +
                ", goodsThumb='" + goodsThumb + '\'' +
                ", goodsImg='" + goodsImg + '\'' +
                '}';
    }
}
