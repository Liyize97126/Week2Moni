package com.bawei.week2moni.util;

import com.bawei.week2moni.bean.DataBean;

/**
 * 反馈接口
 */
public interface DataCall<T> {
    void success(DataBean<T> dataBean);
    void error(String error);
}
