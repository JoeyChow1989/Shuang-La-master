package com.qx.www.shuang_la_master.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by pc on 2016/7/6.
 */
public class AppUtils
{
    public static final int REG_SUSSES = 1;
    public static final int LOGIN_SUSSES = 2;

    //判断app是否正在运行

    /**
     * 判断app是否正在运行
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean appI3sRunning(Context context, String packageName)
    {
        ActivityManager am = (ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);

        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = am.getRunningAppProcesses();

        if (runningAppProcesses != null)
        {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses)
            {

                if (runningAppProcessInfo.processName.startsWith(packageName))
                {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * app 是否在后台运行
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean appIsBackgroundRunning(Context context, String packageName)
    {
        ActivityManager am = (ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);

        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = am.getRunningAppProcesses();

        if (runningAppProcesses != null)
        {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses)
            {

                if (runningAppProcessInfo.processName.startsWith(packageName))
                {
                    return runningAppProcessInfo.importance != ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND && runningAppProcessInfo.importance != ActivityManager.RunningAppProcessInfo.IMPORTANCE_VISIBLE; //排除无界面的app
                }
            }
        }
        return false;
    }


    //判断app是否安装
    public static boolean isAvilible(Context context, String packageName)
    {
        final PackageManager packageManager = context.getPackageManager();
        // 获取所有已安装程序的包信息
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        for (int i = 0; i < pinfo.size(); i++)
        {
            if (pinfo.get(i).packageName.equalsIgnoreCase(packageName))
                return true;
        }
        return false;
    }

    //md5加密
    public static String getMd5Value(String sSecret)
    {
        try
        {
            MessageDigest bmd5 = MessageDigest.getInstance("MD5");
            bmd5.update(sSecret.getBytes());
            int i;
            StringBuffer buf = new StringBuffer();
            byte[] b = bmd5.digest();
            for (int offset = 0; offset < b.length; offset++)
            {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return "";
    }

    // 过滤特殊字符
    public static String StringFilter(String str) throws PatternSyntaxException
    {
        // 只允许字母和数字
        // String   regEx  =  "[^a-zA-Z0-9]";
        // 清除掉所有特殊字符
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    //base64位加密图片
    public static String bitmaptoString(Bitmap bitmap) {
        // 将Bitmap转换成字符串
        String string = null;
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bStream);
        byte[] bytes = bStream.toByteArray();
        string = Base64.encodeToString(bytes, Base64.DEFAULT);
        return string;
    }
}
