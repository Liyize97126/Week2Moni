package com.bawei.week2moni.bean;

import java.io.Serializable;

/**
 * 最外层数据解析Bean类
 */
public class DataBean<T> implements Serializable {
    private T result;
    private String status;
    private String message;
    public T getResult() {
        return result;
    }
    public void setResult(T result) {
        this.result = result;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
