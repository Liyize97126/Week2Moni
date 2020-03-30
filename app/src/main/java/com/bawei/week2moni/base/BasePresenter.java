package com.bawei.week2moni.base;

import com.android.volley.Request;
import com.bawei.week2moni.bean.DataBean;
import com.bawei.week2moni.util.DataCall;
import com.bawei.week2moni.util.IModel;
import com.bawei.week2moni.util.MyApplication;
import com.bawei.week2moni.util.VolleyUtil;
import com.google.gson.Gson;

/**
 * BasePresenter类
 */
public abstract class BasePresenter {
    //定义
    private static final Gson GSON = new Gson();
    public static Gson getGSON() {
        return GSON;
    }
    private DataCall dataCall;
    public BasePresenter(DataCall dataCall) {
        this.dataCall = dataCall;
    }
    public void destroy(){
        dataCall = null;
    }
    //发起请求
    public void request(final String name, String...args){
        //调用方法
        VolleyUtil.getVolleyUtil().request(Request.Method.GET, getUrl(args), null, new IModel() {
            @Override
            public void requestSuccess(String response) {
                //保存数据
                MyApplication.getSharedPreferences().edit().putString(name,response).commit();
                DataBean dataBean = getData(response);
                dataCall.success(dataBean);
            }
            @Override
            public void requestError(String error) {
                dataCall.error(error);
            }
        });
    }
    //方法封装
    protected abstract String getUrl(String...args);
    protected abstract DataBean getData(String response);
}
