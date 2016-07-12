package com.qx.www.shuang_la_master.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.qx.www.shuang_la_master.BaseActivity;
import com.qx.www.shuang_la_master.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ShangwuActivity extends BaseActivity
{

    @Bind(R.id.toolbar1)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shangwu);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView()
    {
        toolbar.setTitle("商务合作");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onBackPressed();
            }
        });
    }

    @Override
    public void initData()
    {

    }
}
