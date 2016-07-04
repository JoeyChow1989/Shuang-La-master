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

public class MakeMonneyActivity extends BaseActivity
{

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.id_makemonney_renwu)
    LinearLayout idMakemonneyRenwu;
    @Bind(R.id.id_makemonney_xianshi)
    LinearLayout idMakemonneyXianshi;
    @Bind(R.id.id_makemonney_youchang)
    LinearLayout idMakemonneyYouchang;
    @Bind(R.id.id_makemonney_newguy)
    LinearLayout idMakemonneyNewguy;
    @Bind(R.id.id_makemonney_line3)
    View idMakemonneyLine3;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_monney);
        ButterKnife.bind(this);
        initView();
    }

    @OnClick({R.id.id_makemonney_renwu, R.id.id_makemonney_xianshi, R.id.id_makemonney_youchang, R.id.id_makemonney_newguy})
    public void onClick(View view)
    {
        Intent intent = new Intent();
        switch (view.getId())
        {
            case R.id.id_makemonney_renwu:
                intent.setClass(this, AllianceActivity.class);
                break;
            case R.id.id_makemonney_xianshi:
                intent.setClass(this, XianshiActivity.class);
                break;
            case R.id.id_makemonney_newguy:
                intent.setClass(this, AllianceActivity.class);
                break;
            case R.id.id_makemonney_youchang:
                intent.setClass(this, AllianceActivity.class);
                break;
        }
        startActivity(intent);
    }

    @Override
    public void initView()
    {
        toolbar.setTitle("赚钱");
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
}
