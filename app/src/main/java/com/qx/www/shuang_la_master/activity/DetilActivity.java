package com.qx.www.shuang_la_master.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.qx.www.shuang_la_master.BaseActivity;
import com.qx.www.shuang_la_master.R;
import com.qx.www.shuang_la_master.fragment.AllItemFragment;
import com.qx.www.shuang_la_master.fragment.DuiHuanFragment;
import com.qx.www.shuang_la_master.fragment.RenwuFragment;
import com.qx.www.shuang_la_master.fragment.XueTuFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetilActivity extends BaseActivity
{

    @Bind(R.id.toolbar1)
    Toolbar toolbar;
    @Bind(R.id.tab_layout)
    TabLayout tabLayout;
    @Bind(R.id.main_vp_container)
    ViewPager mainVpContainer;

    MainPagerAdapter mMainPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detil);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView()
    {
        toolbar.setTitle("明细");
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
        mainVpContainer.setAdapter(mMainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(mainVpContainer);
    }

    public class MainPagerAdapter extends FragmentStatePagerAdapter
    {
        public MainPagerAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {
            switch (position)
            {
                case 0:
                    return new AllItemFragment();
                case 1:
                    return new RenwuFragment();
                case 2:
                    return new XueTuFragment();
                default:
                    return new DuiHuanFragment();
            }
        }

        @Override
        public CharSequence getPageTitle(int position)
        {
            switch (position)
            {
                case 0:
                    return "全部";
                case 1:
                    return "任务";
                case 2:
                    return "学徒";
                default:
                    return "提现";
            }
        }

        @Override
        public int getCount()
        {
            return 4;
        }
    }
}
