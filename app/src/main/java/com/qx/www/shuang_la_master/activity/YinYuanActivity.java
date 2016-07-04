package com.qx.www.shuang_la_master.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.qx.www.shuang_la_master.BaseWebView;
import com.qx.www.shuang_la_master.R;
import com.qx.www.shuang_la_master.ui.ProgressWebView;
import com.rey.material.widget.ProgressView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class YinYuanActivity extends BaseWebView
{

    private String url = "https://www.baidu.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        showWebView(url, "一元夺宝");
    }
}
