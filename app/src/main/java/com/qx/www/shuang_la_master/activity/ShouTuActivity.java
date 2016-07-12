package com.qx.www.shuang_la_master.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qx.www.shuang_la_master.BaseActivity;
import com.qx.www.shuang_la_master.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShouTuActivity extends BaseActivity
{

    @Bind(R.id.toolbar1)
    Toolbar toolbar;
    @Bind(R.id.id_shoutu_jiangli_leiji)
    TextView idShoutuJiangliLeiji;
    @Bind(R.id.id_shoutu_tudi_geshu)
    TextView idShoutuTudiGeshu;
    @Bind(R.id.id_shoutu_jiangli_tudi)
    TextView idShoutuJiangliTudi;
    @Bind(R.id.id_shoutu_share)
    Button idShoutuShare;
    @Bind(R.id.id_shoutu_bidu)
    LinearLayout idShoutuBidu;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shou_tu);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void initView()
    {
        toolbar.setTitle("收徒");
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(onMenuItemClick);
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
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_shoutu, menu);
        return true;
    }

    @OnClick({R.id.id_shoutu_share, R.id.id_shoutu_bidu})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.id_shoutu_share:
                break;
            case R.id.id_shoutu_bidu:
                break;
        }
    }

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener()
    {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem)
        {
            switch (menuItem.getItemId())
            {
                case R.id.action_settings1:

                    break;
            }
            return true;
        }
    };
}
