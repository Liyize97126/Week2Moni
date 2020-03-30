package com.bawei.week2moni.util;

/**
 * IModel接口
 */
public interface IModel {
    void requestSuccess(String response);
    void requestError(String error);
}
