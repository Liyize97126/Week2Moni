package com.bawei.week2moni.presenter;

import com.bawei.week2moni.base.BasePresenter;
import com.bawei.week2moni.bean.DataBean;
import com.bawei.week2moni.bean.ResultBean;
import com.bawei.week2moni.util.DataCall;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * 商品Presenter
 */
public class GoodsPresenter extends BasePresenter {
    //方法实现
    public GoodsPresenter(DataCall dataCall) {
        super(dataCall);
    }
    @Override
    protected String getUrl(String... args) {
        return "http://mobile.bwstudent.com/small/commodity/v1/commodityList";
    }
    @Override
    protected DataBean getData(String response) {
        //泛型处理
        Type type = new TypeToken<DataBean<ResultBean>>() {
        }.getType();
        return getGSON().fromJson(response, type);
    }
}
