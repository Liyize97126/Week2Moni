package com.bawei.week2moni.presenter;

import com.bawei.week2moni.base.BasePresenter;
import com.bawei.week2moni.bean.DataBean;
import com.bawei.week2moni.bean.XBannerListBean;
import com.bawei.week2moni.util.DataCall;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * XBanner数据处理
 */
public class XBannerDataPresenter extends BasePresenter {
    //方法处理
    public XBannerDataPresenter(DataCall dataCall) {
        super(dataCall);
    }
    @Override
    protected String getUrl(String... args) {
        return "http://mobile.bwstudent.com/small/commodity/v1/bannerShow";
    }
    @Override
    protected DataBean getData(String response) {
        Type type = new TypeToken<DataBean<List<XBannerListBean>>>() {
        }.getType();
        return getGSON().fromJson(response,type);
    }
}
