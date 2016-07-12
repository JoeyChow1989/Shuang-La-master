package com.qx.www.shuang_la_master.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qx.www.shuang_la_master.BaseActivity;
import com.qx.www.shuang_la_master.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewGuyActivity extends BaseActivity
{

    @Bind(R.id.id_toolbar_img)
    ImageView idToolbarImg;
    @Bind(R.id.id_toolbar_title)
    TextView idToolbarTitle;
    @Bind(R.id.id_toolbar_menu)
    TextView idToolbarMenu;
    @Bind(R.id.id_newpop_wanshan)
    LinearLayout idNewpopWanshan;
    @Bind(R.id.id_newpop_bindphone)
    LinearLayout idNewpopBindphone;
    @Bind(R.id.toolbar2)
    LinearLayout toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newpople);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView()
    {
        idToolbarMenu.setText("刷新");
        idToolbarTitle.setText("新手任务");
    }

    @Override
    public void initData()
    {
    }

    @OnClick({R.id.id_toolbar_img, R.id.id_toolbar_menu, R.id.id_newpop_wanshan, R.id.id_newpop_bindphone})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.id_toolbar_img:
                finish();
                break;
            case R.id.id_toolbar_menu:
                break;
            case R.id.id_newpop_wanshan:
                break;
            case R.id.id_newpop_bindphone:
                break;
        }
    }
}
