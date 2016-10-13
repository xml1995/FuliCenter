package com.example.lenovobyeoz.fulicenter.bean;

/**
 * Created by lenovoByEOZ on 2016/10/14.
 */

public class ColorlBean {

    /**
     * colorId : 4
     * colorName : 绿色
     * colorCode : #59d85c
     * colorUrl : 1
     * addTime : 1442389445
     * promote : false
     */

    private int colorId;
    private String colorName;
    private String colorCode;
    private String colorUrl;
    private int addTime;
    private boolean promote;

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getColorUrl() {
        return colorUrl;
    }

    public void setColorUrl(String colorUrl) {
        this.colorUrl = colorUrl;
    }

    public int getAddTime() {
        return addTime;
    }

    public void setAddTime(int addTime) {
        this.addTime = addTime;
    }

    public boolean isPromote() {
        return promote;
    }

    public void setPromote(boolean promote) {
        this.promote = promote;
    }

    public ColorlBean() {
    }

    public ColorlBean(int colorId, String colorName, String colorCode, String colorUrl, int addTime, boolean promote) {
        this.colorId = colorId;
        this.colorName = colorName;
        this.colorCode = colorCode;
        this.colorUrl = colorUrl;
        this.addTime = addTime;
        this.promote = promote;
    }

    @Override
    public String toString() {
        return "ColorlBean{" +
                "colorId=" + colorId +
                ", colorName='" + colorName + '\'' +
                ", colorCode='" + colorCode + '\'' +
                ", colorUrl='" + colorUrl + '\'' +
                ", addTime=" + addTime +
                ", promote=" + promote +
                '}';
    }
}
