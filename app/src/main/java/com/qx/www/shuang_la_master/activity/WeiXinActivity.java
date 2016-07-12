package com.qx.www.shuang_la_master.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.qx.www.shuang_la_master.BaseActivity;
import com.qx.www.shuang_la_master.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WeiXinActivity extends BaseActivity
{

    @Bind(R.id.toolbar1)
    Toolbar toolbar;
    @Bind(R.id.id_weinxin_edit)
    TextInputEditText idWeinxinEdit;
    @Bind(R.id.id_weinxin_button)
    Button idWeinxinButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wei_xin);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @OnClick({R.id.id_weinxin_edit, R.id.id_weinxin_button})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.id_weinxin_edit:
                break;
            case R.id.id_weinxin_button:
                break;
        }
    }

    @Override
    public void initView()
    {
        toolbar.setTitle("微信");
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
