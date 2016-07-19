package com.qx.www.shuang_la_master.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.qx.www.shuang_la_master.BaseFragment;
import com.qx.www.shuang_la_master.R;
import com.qx.www.shuang_la_master.activity.RenwuDetailActivity;
import com.qx.www.shuang_la_master.adapter.Xianshi_RenwuAdapter;
import com.qx.www.shuang_la_master.common.AutoLoadRecylerView;
import com.qx.www.shuang_la_master.common.DividerItemDecoration;
import com.qx.www.shuang_la_master.domain.FuLiCallBack;
import com.qx.www.shuang_la_master.domain.XianshiRenwu;
import com.qx.www.shuang_la_master.ui.CustemDialog;
import com.qx.www.shuang_la_master.utils.AppUtils;
import com.qx.www.shuang_la_master.utils.Constants;
import com.qx.www.shuang_la_master.utils.VolleyInterface;
import com.qx.www.shuang_la_master.utils.VolleyRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Xianshi_RenwuFragment extends BaseFragment implements AutoLoadRecylerView.loadMoreListener
{


    @Bind(R.id.id_autorecy_xianshirenwu)
    AutoLoadRecylerView idAutorecyxiashishurenwu;
    private LinearLayoutManager layoutManager;
    private Xianshi_RenwuAdapter adapter;

    SharedPreferences sp;
    String uid;
    String tokenBeforeMd5;
    String token;

    @Override
    protected int getLayoutResource()
    {
        return R.layout.fragment_xianshi__renwu;
    }

    @Override
    protected void initData()
    {
        GetXianshiData();
    }

    private void GetXianshiData()
    {
        String url = Constants.BaseUrl + "site/getTaskList";
        Map<String, String> params = new HashMap<String, String>();
        params.put("uid", uid);
        params.put("token", token);

        VolleyRequest.RequestPost(context, url, "task", params, new VolleyInterface(context,
                VolleyInterface.mSuccessListener, VolleyInterface.mErrorListener)
        {
            @Override
            public void onMySuccess(String result)
            {
                //Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
                Gson gson = new Gson();
                XianshiRenwu xianshiRenwu = gson.fromJson(result, XianshiRenwu.class);

                if (xianshiRenwu.getStatus().equals("ok"))
                {
                    System.out.println("result------------------------------:" + result);

                    adapter = new Xianshi_RenwuAdapter(xianshiRenwu, context, uid, GetThePhoneInfo());
                    idAutorecyxiashishurenwu.setAdapter(adapter);
                }
            }

            @Override
            public void onMyError(VolleyError error)
            {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void initView()
    {
        layoutManager = new LinearLayoutManager(context);
        idAutorecyxiashishurenwu.setLayoutManager(layoutManager);
        idAutorecyxiashishurenwu.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));
        idAutorecyxiashishurenwu.setLoadMoreListener(this);

        sp = context.getSharedPreferences("LoginInfo", Context.MODE_PRIVATE);
        uid = String.valueOf(sp.getInt("uid", 0));

        tokenBeforeMd5 = GetThePhoneInfo() + Constants.KEY + "/" + Constants.TASKLIST_Url;
        token = AppUtils.getMd5Value(AppUtils.getMd5Value(tokenBeforeMd5).substring(AppUtils.getMd5Value(tokenBeforeMd5).length() - 4) + AppUtils.getMd5Value(tokenBeforeMd5).replace(AppUtils.getMd5Value(tokenBeforeMd5).substring(AppUtils.getMd5Value(tokenBeforeMd5).length() - 4), ""));

        System.out.println("tokenBeforeMd5----------------------" + tokenBeforeMd5);
        System.out.println("token----------------------" + token);
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

    public String GetThePhoneInfo()
    {
        TelephonyManager TelephonyMgr = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        String szImei = TelephonyMgr.getDeviceId();
        return szImei;
    }

    @Override
    public void onResume()
    {
        super.onResume();
    }
}
