package com.qx.www.shuang_la_master.fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.qx.www.shuang_la_master.BaseFragment;
import com.qx.www.shuang_la_master.CountDownTimerListener;
import com.qx.www.shuang_la_master.R;
import com.qx.www.shuang_la_master.adapter.Zhuanshu_RenwuAdapter;
import com.qx.www.shuang_la_master.common.AutoLoadRecylerView;
import com.qx.www.shuang_la_master.common.DividerItemDecoration;
import com.qx.www.shuang_la_master.domain.ZhanshuRenwu;
import com.qx.www.shuang_la_master.service.CountDownTimerService;
import com.qx.www.shuang_la_master.utils.AppUtils;
import com.qx.www.shuang_la_master.utils.Constants;
import com.qx.www.shuang_la_master.utils.CountDownTimerUtil;
import com.qx.www.shuang_la_master.utils.VolleyInterface;
import com.qx.www.shuang_la_master.utils.VolleyRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private PackageManager pm;

    private boolean mSdCardIsExist = false;
    private String mPackageName;
    private String tid;
    File f;

    private CountDownTimerService countDownTimerService;
    private long timer_unit = 1000;
    private long service_distination_total = timer_unit * 350;

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

                            System.out.println("icon-------------------" + object.getString("icon"));
                            mList.add(zhanshuRenwu);
                        }
                    }
                } catch (JSONException e)
                {
                    e.printStackTrace();
                }

                adapter = new Zhuanshu_RenwuAdapter(mList, context);
                idAutorecyZhuanshurenwu.setAdapter(adapter);

                adapter.setOnItemClickListener(new Zhuanshu_RenwuAdapter.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(View view, int position)
                    {
                        // TODO: 2016/7/15 专属点击
                        mPackageName = mList.get(position).getPacket();
                        tid = mList.get(position).getTid();
                        System.out.println("mPackageName--------------:" + mPackageName);

                        if (!getSDPath().equals(""))
                        {
                            if (fileIsExists(mPackageName) == true)
                            {
                                Uri uri = Uri.fromFile(f);
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                intent.setDataAndType(uri, "application/vnd.android.package-archive");
                                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                context.startActivity(intent);

                                //开启计时器服务
                                countDownTimerService.startCountDown();

                            } else if (AppUtils.isAvilible(context, mPackageName))
                            {
                                Intent LaunchIntent = context.getPackageManager().getLaunchIntentForPackage(mPackageName);
                                LaunchIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                context.startActivity(LaunchIntent);
                                //开启计时器服务
                                countDownTimerService.startCountDown();
                            }
                        }
                    }

                    @Override
                    public void onItemLongClick(View view, int position)
                    {
                    }
                });
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

        pm = context.getPackageManager();//获得包管理器
        //计时器
        countDownTimerService = CountDownTimerService.getInstance(new MyCountDownLisener()
                , service_distination_total);
        initServiceCountDownTimerStatus();

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

    public String getSDPath()
    {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
        if (sdCardExist)
        {
            sdDir = Environment.getExternalStorageDirectory();// 获取跟目录
            mSdCardIsExist = true;
        }
        return sdDir.toString();
    }

    public boolean fileIsExists(String packageName)
    {
        try
        {
            f = new File(getSDPath() + "/shuangla/" + packageName + "_shangla.apk");
            if (!f.exists())
            {
                return false;
            }

        } catch (Exception e)
        {
            // TODO: handle exception
            return false;
        }
        return true;
    }

    private class MyCountDownLisener implements CountDownTimerListener
    {
        @Override
        public void onChange()
        {
            ArrayList<HashMap<String, Object>> list = AppUtils.LoadList(context, pm);
            for (int i = 0; i < list.size(); i++)
            {
//                if (list.get(i).get("name").equals(mList.get()))
//                {
//                    isFinish = true;
//                }
            }
            System.out.println(countDownTimerService.getCountingTime());

            if (countDownTimerService.getCountingTime() == service_distination_total)
            {
                FinishZhuanshuTask();
            }
        }
    }

    private void FinishZhuanshuTask()
    {
        String url = Constants.BaseUrl + "/site/finishZSTask";
        String tokenBefroeMD5_FinishZsTask = GetThePhoneInfo() + Constants.KEY + "/" + Constants.FINISHZSTASKLIST_Url;
        String token_FinishZsTask = AppUtils.getMd5Value(AppUtils.getMd5Value(tokenBefroeMD5_FinishZsTask).substring(AppUtils.getMd5Value(tokenBefroeMD5_FinishZsTask).length() - 4) +
                AppUtils.getMd5Value(tokenBefroeMD5_FinishZsTask).replace(AppUtils.getMd5Value(tokenBefroeMD5_FinishZsTask).substring(AppUtils.getMd5Value(tokenBefroeMD5_FinishZsTask).length() - 4), ""));

        Map<String, String> params = new HashMap<String, String>();
        params.put("uid", uid);
        params.put("tid", tid);
        params.put("packet", mPackageName);
        params.put("token", token_FinishZsTask);

        VolleyRequest.RequestPost(context, url, "finishZStask", params, new VolleyInterface(context,
                VolleyInterface.mSuccessListener, VolleyInterface.mErrorListener)
        {
            @Override
            public void onMySuccess(String result)
            {
                System.out.println("finish--------finishZStask------------:" + result);
                try
                {
                    JSONObject js = new JSONObject(result);
                    String s = js.getString("status");

                    if (s.equals("ok"))
                    {
                        Toast.makeText(context,"专属任务已完成!",Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e)
                {
                    e.printStackTrace();
                }

            }

            @Override
            public void onMyError(VolleyError error)
            {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * init countdowntimer buttons status for servce
     */
    private void initServiceCountDownTimerStatus()
    {
        switch (countDownTimerService.getTimerStatus())
        {
            case CountDownTimerUtil.PREPARE:
                break;
            case CountDownTimerUtil.START:
                break;
            case CountDownTimerUtil.PASUSE:
                break;
        }

        System.out.println("timer---------------:" + formateTimer(countDownTimerService.getCountingTime()));
    }


    private String formateTimer(long time)
    {
        String str = "00:00:00";
        int hour = 0;
        if (time >= 1000 * 3600)
        {
            hour = (int) (time / (1000 * 3600));
            time -= hour * 1000 * 3600;
        }
        int minute = 0;
        if (time >= 1000 * 60)
        {
            minute = (int) (time / (1000 * 60));
            time -= minute * 1000 * 60;
        }
        int sec = (int) (time / 1000);
        str = formateNumber(hour) + ":" + formateNumber(minute) + ":" + formateNumber(sec);
        return str;
    }

    private String formateNumber(int time)
    {
        return String.format("%02d", time);
    }
}
