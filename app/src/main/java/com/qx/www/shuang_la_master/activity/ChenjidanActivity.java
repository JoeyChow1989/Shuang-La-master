package com.qx.www.shuang_la_master.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.qx.www.shuang_la_master.BaseActivity;
import com.qx.www.shuang_la_master.R;
import com.qx.www.shuang_la_master.utils.AppUtils;

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ChenjidanActivity extends BaseActivity
{

    @Bind(R.id.toolbar1)
    Toolbar toolbar1;
    @Bind(R.id.id_chengjidan_days)
    TextView idChengjidanDays;
    @Bind(R.id.id_chengjidan_shouru)
    TextView idChengjidanShouru;
    @Bind(R.id.id_chengjidan_jiangli)
    TextView idChengjidanJiangli;
    @Bind(R.id.id_chengjidan_tudinum)
    TextView idChengjidanTudinum;
    @Bind(R.id.id_chengjidan_allmoney)
    TextView idChengjidanAllmoney;

    SharedPreferences sp;
    long timecurrentTimeMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chenjidan);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView()
    {
        toolbar1.setTitle("成绩单");
        setSupportActionBar(toolbar1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar1.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onBackPressed();
            }
        });

        sp = getSharedPreferences("UserInfo", MODE_PRIVATE);
    }

    @Override
    public void initData()
    {

        idChengjidanDays.setText(UseDaysNum() + "天");

        if ("0".equals(sp.getString("total", "")))
        {
            idChengjidanShouru.setText("0元");
        } else
        {
            idChengjidanShouru.setText(AppUtils.numZhuanHuan(sp.getString("total", "")) + "元");
        }

        if ("0".equals(sp.getString("sy", "")))
        {
            idChengjidanJiangli.setText("0元");
        } else
        {
            idChengjidanJiangli.setText(AppUtils.numZhuanHuan(sp.getString("sy", "")) + "元");
        }

        idChengjidanTudinum.setText(sp.getString("num", "") + "人");

        if ("0".equals(sp.getString("total", "")))
        {
            idChengjidanAllmoney.setText("0元");
        } else
        {
            idChengjidanAllmoney.setText(AppUtils.numZhuanHuan(sp.getString("total", "")) + "元");
        }
    }

    public String UseDaysNum()
    {

        String userDays = "";
        timecurrentTimeMillis = Long.decode(String.valueOf(new Date().getTime()).substring(0, 10));
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaa:" + (timecurrentTimeMillis - Long.decode(sp.getString("logintime", ""))) / (3 * 24 * 60 * 60000));

        userDays = String.valueOf((timecurrentTimeMillis - Long.decode(sp.getString("logintime", ""))) / (3 * 24 * 60 * 60000));
        return userDays;
    }

}
