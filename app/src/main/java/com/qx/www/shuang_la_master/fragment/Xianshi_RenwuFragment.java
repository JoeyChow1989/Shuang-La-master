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
import com.qx.www.shuang_la_master.common.AutoLoadRecylerView;
import com.qx.www.shuang_la_master.common.DividerItemDecoration;
import com.qx.www.shuang_la_master.domain.XianshiRenwu;
import com.qx.www.shuang_la_master.ui.CustemDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Xianshi_RenwuFragment extends BaseFragment implements AutoLoadRecylerView.loadMoreListener
{


    @Bind(R.id.id_autorecy_xianshirenwu)
    AutoLoadRecylerView idAutorecyxiashishurenwu;
    private LinearLayoutManager layoutManager;
    private List<XianshiRenwu> mList;
    private Xianshi_RenwuAdapter adapter;
    private CustemDialog mDialog;

    @Override
    protected int getLayoutResource()
    {
        return R.layout.fragment_xianshi__renwu;
    }

    @Override
    protected void initData()
    {

        for (int i = 0; i < 10; i++)
        {
            XianshiRenwu xianshiRenwu = new XianshiRenwu();
            xianshiRenwu.setImg(R.mipmap.ic_launcher);
            xianshiRenwu.setTitle("平安天下通");
            xianshiRenwu.setCount("10223");
            xianshiRenwu.setMsg("是时候有一个随心理财顾问");
            xianshiRenwu.setPic(R.mipmap.ic_launcher);
            mList.add(xianshiRenwu);
        }

        adapter.setOnItemClickListener(new Xianshi_RenwuAdapter.OnItemClickListener()
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
        mList = new ArrayList<XianshiRenwu>();
        layoutManager = new LinearLayoutManager(context);
        idAutorecyxiashishurenwu.setLayoutManager(layoutManager);
        idAutorecyxiashishurenwu.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));
        idAutorecyxiashishurenwu.setLoadMoreListener(this);
        adapter = new Xianshi_RenwuAdapter(mList, context);
        idAutorecyxiashishurenwu.setAdapter(adapter);

        mDialog = new CustemDialog(context, "平安通天下", R.mipmap.ic_action_camera, "29M", "1、首次下载并安装2、使用手机号注册并向专家提问一个问题3、回到钱咖，提交审核获取奖励");
    }

    @Override
    public void onLoadMore()
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
