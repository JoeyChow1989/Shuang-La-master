package com.qx.www.shuang_la_master.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qx.www.shuang_la_master.BaseFragment;
import com.qx.www.shuang_la_master.R;
import com.qx.www.shuang_la_master.adapter.DetailAdatper;
import com.qx.www.shuang_la_master.adapter.Detail_TixianAdatper;
import com.qx.www.shuang_la_master.common.AutoLoadRecylerView;
import com.qx.www.shuang_la_master.common.DividerItemDecoration;
import com.qx.www.shuang_la_master.domain.Detail;
import com.qx.www.shuang_la_master.domain.Detail_tixian;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DuiHuanFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,AutoLoadRecylerView.loadMoreListener
{
    @Bind(R.id.id_duihuan_autorecy)
    AutoLoadRecylerView idDuihuanAutorecy;
    @Bind(R.id.common_error_txt)
    TextView commonErrorTxt;
    @Bind(R.id.retry_btn)
    Button retryBtn;
    @Bind(R.id.common_error)
    RelativeLayout commonError;

    private List<Detail_tixian> mList;
    private Detail_TixianAdatper adapter;
    private LinearLayoutManager layoutManager;

    @Override
    protected int getLayoutResource()
    {
        return R.layout.fragment_dui_huan;
    }

    @Override
    protected void initData()
    {
        for (int i = 0; i < 10; i++)
        {
            Detail_tixian detail = new Detail_tixian();
            detail.setImg(R.mipmap.ic_launcher);
            detail.setTitle("你的益达");
            detail.setMsgs("完成任务限时推荐《你的英雄》，赚了1.5元");
            detail.setTime("03月17 15:52");
            detail.setPics(R.mipmap.ic_launcher);
            mList.add(detail);
        }

        adapter.setOnItemClickListener(new Detail_TixianAdatper.OnItemClickListener()
        {
            @Override
            public void onItemClick(View view, int position)
            {
//                Intent intent = new Intent();
//                intent.setClass(context, RenwuDetailActivity.class);
//                context.startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position)
            {

            }
        });
    }

    @Override
    protected void initView()
    {
        mList = new ArrayList<Detail_tixian>();
        layoutManager = new LinearLayoutManager(context);
        idDuihuanAutorecy.setLayoutManager(layoutManager);
        idDuihuanAutorecy.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));
        idDuihuanAutorecy.setLoadMoreListener(this);
        adapter = new Detail_TixianAdatper(mList, context);
        idDuihuanAutorecy.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // TODO: inflate a fragment view
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

    @Override
    public void onRefresh()
    {

    }

    @Override
    public void onLoadMore()
    {

    }
}
