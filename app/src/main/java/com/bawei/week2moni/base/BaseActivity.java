package com.bawei.week2moni.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 界面基类
 */
public abstract class BaseActivity extends AppCompatActivity {
    //定义
    private ActionBar actionBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        actionBar = getSupportActionBar();
        actionBar.hide();
        initView();
    }
    //方法封装
    protected abstract int getLayoutId();
    protected abstract void initView();
}
