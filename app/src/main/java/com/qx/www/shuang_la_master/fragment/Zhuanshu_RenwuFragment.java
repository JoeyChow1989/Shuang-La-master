package com.qx.www.shuang_la_master.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qx.www.shuang_la_master.BaseFragment;
import com.qx.www.shuang_la_master.R;
import com.qx.www.shuang_la_master.activity.RenwuDetailActivity;
import com.qx.www.shuang_la_master.adapter.Xianshi_RenwuAdapter;
import com.qx.www.shuang_la_master.adapter.Zhuanshu_RenwuAdapter;
import com.qx.www.shuang_la_master.common.AutoLoadRecylerView;
import com.qx.www.shuang_la_master.common.DividerItemDecoration;
import com.qx.www.shuang_la_master.domain.XianshiRenwu;
import com.qx.www.shuang_la_master.domain.ZhanshuRenwu;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class Zhuanshu_RenwuFragment extends BaseFragment implements AutoLoadRecylerView.loadMoreListener
{


    @Bind(R.id.id_autorecy_zhuanshurenwu)
    AutoLoadRecylerView idAutorecyZhuanshurenwu;
    private LinearLayoutManager layoutManager;
    private List<ZhanshuRenwu> mList;
    private Zhuanshu_RenwuAdapter adapter;

    @Override
    protected int getLayoutResource()
    {
        return R.layout.fragment_zhuanshu__renwu;
    }

    @Override
    protected void initData()
    {
        for (int i = 0; i < 10; i++)
        {
            ZhanshuRenwu zhanshuRenwu = new ZhanshuRenwu();
            zhanshuRenwu.setImg(R.mipmap.ic_launcher);
            zhanshuRenwu.setTitle("平安天下通");
            zhanshuRenwu.setMsg("是时候有一个随心理财顾问");
            zhanshuRenwu.setPic(R.mipmap.ic_launcher);
            mList.add(zhanshuRenwu);
        }

        adapter.setOnItemClickListener(new Zhuanshu_RenwuAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(View view, int position)
            {
                Intent intent = new Intent();
                intent.setClass(context, RenwuDetailActivity.class);
                context.startActivity(intent);
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
        mList = new ArrayList<ZhanshuRenwu>();
        layoutManager = new LinearLayoutManager(context);
        idAutorecyZhuanshurenwu.setLayoutManager(layoutManager);
        idAutorecyZhuanshurenwu.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));
        idAutorecyZhuanshurenwu.setLoadMoreListener(this);
        adapter = new Zhuanshu_RenwuAdapter(mList,context);
        idAutorecyZhuanshurenwu.setAdapter(adapter);
    }

    @Override
    public void onLoadMore()
    {

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
}
