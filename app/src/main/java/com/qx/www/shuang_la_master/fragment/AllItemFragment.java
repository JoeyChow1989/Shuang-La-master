package com.qx.www.shuang_la_master.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qx.www.shuang_la_master.BaseFragment;
import com.qx.www.shuang_la_master.R;
import com.qx.www.shuang_la_master.adapter.DetailAdatper;
import com.qx.www.shuang_la_master.common.AutoLoadRecylerView;
import com.qx.www.shuang_la_master.domain.Detail;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AllItemFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener
{


    @Bind(R.id.id_all_item_autorecy)
    AutoLoadRecylerView idAllItemAutorecy;
    @Bind(R.id.common_error_txt)
    TextView commonErrorTxt;
    @Bind(R.id.retry_btn)
    Button retryBtn;
    @Bind(R.id.common_error)
    RelativeLayout commonError;

    private List<Detail> mList;
    private DetailAdatper adatper;

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
        mList = new ArrayList<>();
        adatper = new DetailAdatper(mList, context);
        idAllItemAutorecy.setAdapter(adatper);
    }

    @Override
    public void onRefresh()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
