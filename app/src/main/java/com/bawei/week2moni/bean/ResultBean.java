package com.bawei.week2moni.bean;

import java.io.Serializable;

/**
 * 结果展示Bean类
 */
public class ResultBean implements Serializable {
    private HomeListBean rxxp;
    private HomeListBean pzsh;
    private HomeListBean mlss;
    public HomeListBean getRxxp() {
        return rxxp;
    }
    public void setRxxp(HomeListBean rxxp) {
        this.rxxp = rxxp;
    }
    public HomeListBean getPzsh() {
        return pzsh;
    }
    public void setPzsh(HomeListBean pzsh) {
        this.pzsh = pzsh;
    }
    public HomeListBean getMlss() {
        return mlss;
    }
    public void setMlss(HomeListBean mlss) {
        this.mlss = mlss;
    }
}
