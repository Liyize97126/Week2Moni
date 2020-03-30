package com.bawei.week2moni.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * 网络工具封装类
 */
public class VolleyUtil {
    //单例
    private static final VolleyUtil VOLLEY_UTIL = new VolleyUtil();
    private VolleyUtil() {
    }
    public static VolleyUtil getVolleyUtil() {
        return VOLLEY_UTIL;
    }
    //定义队列
    private static RequestQueue queue = Volley.newRequestQueue(MyApplication.getContext());
    //定义通用请求
    public void request(int method, String url, Map<String,String> params,IModel iModel){
        switch (method){
            case Request.Method.GET: {get(url,iModel);}break;
            case Request.Method.POST: {post(url,params,iModel);}break;
        }
    }
    //定义网络判断
    public boolean hasNet(){
        ConnectivityManager connectivityManager = (ConnectivityManager) MyApplication
                .getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if(activeNetworkInfo != null && activeNetworkInfo.isAvailable()){
            return true;
        } else {
            return false;
        }
    }
    //get请求
    private void get(String url, final IModel iModel){
        //发起请求
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("Tag", response);
                        iModel.requestSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Tag", error.getMessage());
                        iModel.requestError(error.getMessage());
                    }
                }) {
            //乱码问题解决
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                try {
                    String string = new String(response.data, "UTF-8");
                    return Response.success(string,
                            HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (Exception je) {
                    return Response.error(new ParseError(je));
                }
            }
        };
        //将请求添加至队列
        queue.add(stringRequest);
    }
    //post请求
    private void post(String url, final Map<String,String> params, final IModel iModel){
        //发起请求
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("Tag", response);
                        iModel.requestSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Tag", error.getMessage());
                        iModel.requestError(error.getMessage());
                    }
                }) {
            //添加请求参数
            @Override
            protected Map<String, String> getParams() {
                return params;
            }
            //乱码问题解决
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                try {
                    String string = new String(response.data, "UTF-8");
                    return Response.success(string,
                            HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (Exception je) {
                    return Response.error(new ParseError(je));
                }
            }
        };
        //将请求添加至队列
        queue.add(stringRequest);
    }
}
