package com.qx.www.shuang_la_master.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.qx.www.shuang_la_master.BaseWebView;
import com.qx.www.shuang_la_master.R;

public class DuiHuanActivity extends BaseWebView
{

    private String url = "https://www.baidu.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        showWebView(url, "兑换");
    }
}
