package com.example.lenovobyeoz.fulicenter.bean;

/**
 * Created by lenovoByEOZ on 2016/10/14.
 */

public class Albums {

    /**
     * pid : 7677
     * imgId : 28296
     * imgUrl : 201509/goods_img/7677_P_1442391216432.png
     * thumbUrl : no_picture.gif
     */

    private int pid;
    private int imgId;
    private String imgUrl;
    private String thumbUrl;

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public Albums() {
    }

    public Albums(int pid, int imgId, String imgUrl, String thumbUrl) {
        this.pid = pid;
        this.imgId = imgId;
        this.imgUrl = imgUrl;
        this.thumbUrl = thumbUrl;
    }

    @Override
    public String toString() {
        return "Albums{" +
                "pid=" + pid +
                ", imgId=" + imgId +
                ", imgUrl='" + imgUrl + '\'' +
                ", thumbUrl='" + thumbUrl + '\'' +
                '}';
    }
}
