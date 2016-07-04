package com.qx.www.shuang_la_master.activity;

import android.os.Bundle;

import com.qx.www.shuang_la_master.BaseWebView;

public class YouHuiJuanActivity extends BaseWebView
{

    private String url = "https://www.baidu.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        showWebView(url, "优惠劵");
    }
}
