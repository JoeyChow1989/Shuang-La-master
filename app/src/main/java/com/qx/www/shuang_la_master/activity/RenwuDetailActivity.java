package com.qx.www.shuang_la_master.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.qx.www.shuang_la_master.BaseActivity;
import com.qx.www.shuang_la_master.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RenwuDetailActivity extends BaseActivity
{

    @Bind(R.id.id_toolbar_img)
    ImageView idToolbarImg;
    @Bind(R.id.id_toolbar_title)
    TextView idToolbarTitle;
    @Bind(R.id.id_renwudetail_img)
    ImageView idRenwudetailImg;
    @Bind(R.id.id_renwudetail_appname)
    TextView idRenwudetailAppname;
    @Bind(R.id.id_renwudetail_appsize)
    TextView idRenwudetailAppsize;
    @Bind(R.id.id_renwudetail_yuan)
    TextView idRenwudetailYuan;
    @Bind(R.id.id_renwudetail_times)
    TextView idRenwudetailTimes;
    @Bind(R.id.id_renwudetail_tianshu)
    TextView idRenwudetailTianshu;
    @Bind(R.id.id_renwudetail_count)
    TextView idRenwudetailCount;
    @Bind(R.id.id_renwudetail_detailtitle)
    TextView idRenwudetailDetailtitle;
    @Bind(R.id.id_renwudetail_detail1)
    TextView idRenwudetailDetail1;
    @Bind(R.id.id_renwudetail_detail2)
    TextView idRenwudetailDetail2;
    @Bind(R.id.id_renwudetail_detail3)
    TextView idRenwudetailDetail3;
    @Bind(R.id.id_renwudetail_detail4)
    TextView idRenwudetailDetail4;
    @Bind(R.id.id_renwudetail_detail5)
    TextView idRenwudetailDetail5;
    @Bind(R.id.id_renwudetail_detail6)
    TextView idRenwudetailDetail6;
    @Bind(R.id.id_renwudetail_detail7)
    TextView idRenwudetailDetail7;
    @Bind(R.id.id_start_download)
    Button idStartDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renwu_detail);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView()
    {
        idToolbarTitle.setText("任务详情");
    }

    @Override
    public void initData()
    {
    }

    @OnClick({R.id.id_toolbar_img, R.id.id_start_download})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.id_toolbar_img:
                finish();
                break;
            case R.id.id_start_download:
                break;
        }
    }
}
