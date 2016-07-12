package com.qx.www.shuang_la_master.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.qx.www.shuang_la_master.BaseActivity;
import com.qx.www.shuang_la_master.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PhoneBindActivity extends BaseActivity
{

    @Bind(R.id.toolbar1)
    Toolbar toolbar;
    @Bind(R.id.id_phone_bt_getyanzheng)
    Button idPhoneBtGetyanzheng;
    @Bind(R.id.id_phone_sendup)
    Button idPhoneSendup;
    @Bind(R.id.id_phone_edit_phone)
    AppCompatEditText idPhoneEditPhone;
    @Bind(R.id.id_phone_edit_yanzheng)
    AppCompatEditText idPhoneEditYanzheng;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_bind);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView()
    {
        toolbar.setTitle("绑定手机");
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

    @OnClick({R.id.id_phone_bt_getyanzheng, R.id.id_phone_sendup})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.id_phone_bt_getyanzheng:
                break;
            case R.id.id_phone_sendup:
                break;
        }
    }
}
