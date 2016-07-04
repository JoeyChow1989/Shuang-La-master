package com.qx.www.shuang_la_master.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.qx.www.shuang_la_master.BaseActivity;
import com.qx.www.shuang_la_master.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class KeFuCenterActivity extends BaseActivity
{

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.id_kefu_jifenwenti)
    LinearLayout idKefuJifenwenti;
    @Bind(R.id.id_kefu_weixinkefu)
    LinearLayout idKefuWeixinkefu;
    @Bind(R.id.id_kefu_normalqu)
    LinearLayout idKefuNormalqu;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ke_fu_center);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView()
    {
        toolbar.setTitle("客服中心");
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

    @OnClick({R.id.id_kefu_jifenwenti, R.id.id_kefu_weixinkefu, R.id.id_kefu_normalqu})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.id_kefu_jifenwenti:
                break;
            case R.id.id_kefu_weixinkefu:
                break;
            case R.id.id_kefu_normalqu:
                break;
        }
    }
}
