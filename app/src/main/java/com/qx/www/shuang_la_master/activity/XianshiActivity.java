package com.qx.www.shuang_la_master.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.qx.www.shuang_la_master.BaseActivity;
import com.qx.www.shuang_la_master.R;
import com.qx.www.shuang_la_master.fragment.Xianshi_RenwuFragment;
import com.qx.www.shuang_la_master.fragment.Zhuanshu_RenwuFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class XianshiActivity extends BaseActivity
{
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tab_layout_xianshi)
    TabLayout tabLayoutXianshi;
    @Bind(R.id.main_vp_container_xianshi)
    ViewPager mainVpContainerXianshi;

    PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xianshi);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView()
    {
        toolbar.setTitle("限时推荐");
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
        mainVpContainerXianshi.setAdapter(mPagerAdapter = new PagerAdapter(getSupportFragmentManager()));
        tabLayoutXianshi.setupWithViewPager(mainVpContainerXianshi);
    }

    public class PagerAdapter extends FragmentStatePagerAdapter
    {
        public PagerAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {
            switch (position)
            {
                case 0:
                    return new Xianshi_RenwuFragment();
                default:
                    return new Zhuanshu_RenwuFragment();
            }
        }

        @Override
        public CharSequence getPageTitle(int position)
        {
            switch (position)
            {
                case 0:
                    return "限时任务";
                default:
                    return "专属任务";
            }
        }

        @Override
        public int getCount()
        {
            return 2;
        }
    }
}
