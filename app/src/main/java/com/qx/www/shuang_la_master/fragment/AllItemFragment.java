package com.qx.www.shuang_la_master.fragment;

import android.support.v4.widget.SwipeRefreshLayout;

import com.qx.www.shuang_la_master.BaseFragment;
import com.qx.www.shuang_la_master.R;
import com.qx.www.shuang_la_master.common.AutoLoadRecylerView;

public class AllItemFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, AutoLoadRecylerView.loadMoreListener
{
    @Override
    protected int getLayoutResource()
    {
        return R.layout.fragment_all_item;
    }

    @Override
    protected void initData()
    {

    }

    @Override
    protected void initView()
    {

    }

    @Override
    public void onRefresh()
    {

    }

    @Override
    public void onLoadMore()
    {

    }
}
