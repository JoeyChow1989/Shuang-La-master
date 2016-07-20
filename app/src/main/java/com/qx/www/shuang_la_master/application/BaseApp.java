package com.qx.www.shuang_la_master.application;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.umeng.socialize.PlatformConfig;

/**
 * Created by pc on 2016/6/29.
 */
public class BaseApp extends Application
{
    //请求队列
    public static RequestQueue mRequestQueue;

    public static BaseApp instance;
    public int vsercode;
    public String vsername;

    @Override
    public void onCreate()
    {
        super.onCreate();
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        instance = this;
        PackageInfo info;
        try {
            info = this.getPackageManager().getPackageInfo(this.getPackageName(), 0);
            vsercode = info.versionCode;//
            vsername = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    {
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }

    public static RequestQueue getHttpQueues()
    {
        return mRequestQueue;
    }

    public static synchronized BaseApp getInstance() {
        return instance;
    }
}
