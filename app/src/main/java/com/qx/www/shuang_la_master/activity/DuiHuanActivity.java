package com.qx.www.shuang_la_master.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.qx.www.shuang_la_master.BaseActivity;
import com.qx.www.shuang_la_master.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DuiHuanActivity extends BaseActivity
{

    @Bind(R.id.id_linear_huafei)
    LinearLayout idLinearHuafei;
    @Bind(R.id.id_linear_tixia)
    LinearLayout idLinearTixia;
    @Bind(R.id.id_linear_duibaobi)
    LinearLayout idLinearDuibaobi;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duihuan);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView()
    {
        toolbar.setTitle("兑换");
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

    @OnClick({R.id.id_linear_huafei, R.id.id_linear_tixia, R.id.id_linear_duibaobi})
    public void onClick(View view)
    {
        Intent intent = new Intent();
        switch (view.getId())
        {
            case R.id.id_linear_huafei:
                intent.setClass(this, HuaFeiActivity.class);
                break;
            case R.id.id_linear_tixia:
                intent.setClass(this, TixianActivity.class);
                break;
            case R.id.id_linear_duibaobi:
                intent.setClass(this, DuobaoBiActivity.class);
                break;
        }
        startActivity(intent);
    }
}