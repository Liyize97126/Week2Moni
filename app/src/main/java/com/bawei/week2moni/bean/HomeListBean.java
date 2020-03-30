package com.bawei.week2moni.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 首页列表Bean类
 */
public class HomeListBean implements Serializable {
    private List<GoodsBean> commodityList;
    private int id;
    private String name;
    public List<GoodsBean> getCommodityList() {
        return commodityList;
    }
    public void setCommodityList(List<GoodsBean> commodityList) {
        this.commodityList = commodityList;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
