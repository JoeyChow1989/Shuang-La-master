package com.qx.www.shuang_la_master.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import java.util.List;

/**
 * Created by pc on 2016/7/14.
 */
class PackageInfos
{
    private List<ApplicationInfo> appList;

    public PackageInfos(Context context){
    //get all package data
        PackageManager pm = context.getApplicationContext().getPackageManager();
        appList = pm.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
    }

    public ApplicationInfo getInfo(String name){
        if(name == null){
            return null;
        }
        for(ApplicationInfo appInfo : appList){
            if(name.equals(appInfo.processName)){
                return appInfo;
            }
        }
        return null;
    }
}