package com.qx.www.shuang_la_master.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import com.qx.www.shuang_la_master.adapter.Zhuanshu_RenwuAdapter;
import com.qx.www.shuang_la_master.common.AutoLoadRecylerView;
import com.qx.www.shuang_la_master.common.DividerItemDecoration;
import com.qx.www.shuang_la_master.domain.XianshiRenwu;
import com.qx.www.shuang_la_master.domain.ZhanshuRenwu;
import com.qx.www.shuang_la_master.utils.AppUtils;
import com.qx.www.shuang_la_master.utils.Constants;
import com.qx.www.shuang_la_master.utils.VolleyInterface;
import com.qx.www.shuang_la_master.utils.VolleyRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    SharedPreferences sp;

    private String uid;
    String tokenBeforeMd5;
    String token;

    @Override
    protected int getLayoutResource()
    {
        return R.layout.fragment_zhuanshu__renwu;
    }

    @Override
    protected void initData()
    {
        GetZhuanshuRenwu();
    }

    private void GetZhuanshuRenwu()
    {
        String url = Constants.BaseUrl + "site/getZSTaskList";
        Map<String, String> params = new HashMap<String, String>();
        params.put("uid", uid);
        params.put("token", token);

        VolleyRequest.RequestPost(context, url, "task_ZS", params, new VolleyInterface(context,
                VolleyInterface.mSuccessListener, VolleyInterface.mErrorListener)
        {
            @Override
            public void onMySuccess(String result)
            {
                System.out.println("--------------result_zhuanshu-------------" + result);

                try
                {
                    JSONObject jsonObject = new JSONObject(result);
                    String status = jsonObject.getString("status");
                    if (status.equals("ok"))
                    {
                        JSONObject infos = jsonObject.getJSONObject("infos");
                        JSONArray array = infos.getJSONArray("taskinfos");

                        for (int i = 0; i < array.length(); i++)
                        {
                            ZhanshuRenwu zhanshuRenwu = new ZhanshuRenwu();
                            JSONObject object = (JSONObject) array.opt(i);
                            zhanshuRenwu.setTitle(object.getString("title"));
                            zhanshuRenwu.setPacket(object.getString("packet"));
                            zhanshuRenwu.setIcon(object.getString("icon"));
                            zhanshuRenwu.setStatus(object.getString("status"));
                            zhanshuRenwu.setZs_reward(object.getString("zs_reward"));
                            zhanshuRenwu.setTid(object.getString("tid"));
                            mList.add(zhanshuRenwu);
                        }
                    }
                } catch (JSONException e)
                {
                    e.printStackTrace();
                }

                adapter = new Zhuanshu_RenwuAdapter(mList, context);
                idAutorecyZhuanshurenwu.setAdapter(adapter);
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
        mList = new ArrayList<>();
        sp = context.getSharedPreferences("LoginInfo", context.MODE_PRIVATE);
        uid = String.valueOf(sp.getInt("uid", 0));

        layoutManager = new LinearLayoutManager(context);
        idAutorecyZhuanshurenwu.setLayoutManager(layoutManager);
        idAutorecyZhuanshurenwu.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));
        idAutorecyZhuanshurenwu.setLoadMoreListener(this);

        tokenBeforeMd5 = GetThePhoneInfo() + Constants.KEY + "/" + Constants.ZSTASKLIST_Url;
        token = AppUtils.getMd5Value(AppUtils.getMd5Value(tokenBeforeMd5).substring(AppUtils.getMd5Value(tokenBeforeMd5).length() - 4) + AppUtils.getMd5Value(tokenBeforeMd5).replace(AppUtils.getMd5Value(tokenBeforeMd5).substring(AppUtils.getMd5Value(tokenBeforeMd5).length() - 4), ""));

        System.out.println("tokenBeforeMd5_专属----------------------" + tokenBeforeMd5);
        System.out.println("token_专属----------------------" + token);

    }

    @Override
    public void onLoadMore()
    {

    }

    public String GetThePhoneInfo()
    {
        TelephonyManager TelephonyMgr = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        String szImei = TelephonyMgr.getDeviceId();
        return szImei;
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
