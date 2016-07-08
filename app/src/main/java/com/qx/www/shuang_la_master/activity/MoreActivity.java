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

public class MoreActivity extends BaseActivity
{

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.id_more_id)
    LinearLayout idMoreId;
    @Bind(R.id.id_more_ziliao)
    LinearLayout idMoreZiliao;
    @Bind(R.id.id_more_weixin)
    LinearLayout idMoreWeixin;
    @Bind(R.id.id_more_shouji)
    LinearLayout idMoreShouji;
    @Bind(R.id.id_more_chenjidan)
    LinearLayout idMoreChenjidan;
    @Bind(R.id.id_more_kefu)
    LinearLayout idMoreKefu;
    @Bind(R.id.id_more_xinwen)
    LinearLayout idMoreXinwen;
    @Bind(R.id.id_more_shangwu)
    LinearLayout idMoreShangwu;
    @Bind(R.id.id_more_check)
    LinearLayout idMoreCheck;
    @Bind(R.id.id_more_changeid)
    LinearLayout idMoreChangeid;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void initView()
    {
        toolbar.setTitle("更多");
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

    @OnClick({R.id.id_more_ziliao, R.id.id_more_weixin, R.id.id_more_shouji, R.id.id_more_chenjidan, R.id.id_more_kefu, R.id.id_more_xinwen, R.id.id_more_shangwu, R.id.id_more_check, R.id.id_more_changeid})
    public void onClick(View view)
    {
        Intent intent = new Intent();
        switch (view.getId())
        {
            case R.id.id_more_ziliao:
                intent.setClass(this, ZiLiaoActivity.class);
                break;
            case R.id.id_more_weixin:
                intent.setClass(this, WeiXinActivity.class);
                break;
            case R.id.id_more_shouji:
                intent.setClass(this, PhoneBindActivity.class);
                break;
            case R.id.id_more_chenjidan:
                intent.setClass(this, ZiLiaoActivity.class);
                break;
            case R.id.id_more_kefu:
                intent.setClass(this, KeFuCenterActivity.class);
                break;
            case R.id.id_more_xinwen:
                intent.setClass(this, NewsNoticeActivity.class);
                break;
            case R.id.id_more_shangwu:
                intent.setClass(this, ShangwuActivity.class);
                break;
            case R.id.id_more_check:
                intent.setClass(this, ZiLiaoActivity.class);
                break;
            case R.id.id_more_changeid:
                intent.setClass(this, ZiLiaoActivity.class);
                break;
        }
        startActivity(intent);
    }
}
