package com.qx.www.shuang_la_master.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.qx.www.shuang_la_master.BaseActivity;
import com.qx.www.shuang_la_master.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HuaFeiActivity extends BaseActivity
{

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.id_edittext_huafei_phonenum)
    TextInputEditText idEdittextHuafeiPhonenum;
    @Bind(R.id.id_edittext_huafei_chongzhimoney)
    TextView idEdittextHuafeiChongzhimoney;
    @Bind(R.id.huanfei_sendup)
    Button huanfeiSendup;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hua_fei);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView()
    {
        toolbar.setTitle("话费充值");
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

    @OnClick({R.id.id_edittext_huafei_chongzhimoney, R.id.huanfei_sendup})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.id_edittext_huafei_chongzhimoney:
                break;
            case R.id.huanfei_sendup:
                break;
        }
    }
}
