package com.qx.www.shuang_la_master.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qx.www.shuang_la_master.BaseActivity;
import com.qx.www.shuang_la_master.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZiLiaoActivity extends BaseActivity
{

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.id_ziliao_edit)
    AppCompatEditText idZiliaoEdit;
    @Bind(R.id.id_ziliao_nickname)
    LinearLayout idZiliaoNickname;
    @Bind(R.id.id_ziliao_imgs)
    ImageView idZiliaoImgs;
    @Bind(R.id.id_ziliao_linear_imgs)
    LinearLayout idZiliaoLinearImgs;
    @Bind(R.id.id_ziliao_headimg)
    LinearLayout idZiliaoHeadimg;
    @Bind(R.id.id_ziliao_sexup)
    TextView idZiliaoSexup;
    @Bind(R.id.id_ziliao_sex)
    LinearLayout idZiliaoSex;
    @Bind(R.id.id_ziliao_brithup)
    TextView idZiliaoBrithup;
    @Bind(R.id.id_ziliao_brith)
    LinearLayout idZiliaoBrith;
    @Bind(R.id.id_ziliao_jobup)
    TextView idZiliaoJobup;
    @Bind(R.id.id_ziliao_job)
    LinearLayout idZiliaoJob;
    @Bind(R.id.id_ziliao_sendup)
    Button idZiliaoSendup;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zi_liao);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView()
    {
        toolbar.setTitle("资料");
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

    @OnClick({R.id.id_ziliao_linear_imgs, R.id.id_ziliao_sex, R.id.id_ziliao_brith, R.id.id_ziliao_job})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.id_ziliao_linear_imgs:
                break;
            case R.id.id_ziliao_sex:
                break;
            case R.id.id_ziliao_brith:
                break;
            case R.id.id_ziliao_job:
                break;
        }
    }
}
