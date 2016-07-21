package com.qx.www.shuang_la_master.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.qx.www.shuang_la_master.BaseActivity;
import com.qx.www.shuang_la_master.R;

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
    @Bind(R.id.id_chengjidan_tudijiangli)
    TextView idChengjidanTudijiangli;
    @Bind(R.id.id_chengjidan_allmoney)
    TextView idChengjidanAllmoney;

    SharedPreferences sp;

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
        idChengjidanShouru.setText(sp.getString("total", ""));
        idChengjidanJiangli.setText(sp.getString("sy", ""));
        idChengjidanTudinum.setText(sp.getString("num", ""));
        idChengjidanTudijiangli.setText(sp.getString("tnum", ""));
        idChengjidanAllmoney.setText(sp.getString("total", ""));
    }

}
