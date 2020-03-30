package com.bawei.week2moni.bean;

import com.stx.xhb.androidx.entity.SimpleBannerInfo;

import java.io.Serializable;

/**
 * XBanner数据
 */
public class XBannerListBean extends SimpleBannerInfo implements Serializable {
    private String imageUrl;
    private String jumpUrl;
    private int rank;
    private String title;
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getJumpUrl() {
        return jumpUrl;
    }
    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl;
    }
    public int getRank() {
        return rank;
    }
    public void setRank(int rank) {
        this.rank = rank;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    @Override
    public Object getXBannerUrl() {
        return null;
    }
}
