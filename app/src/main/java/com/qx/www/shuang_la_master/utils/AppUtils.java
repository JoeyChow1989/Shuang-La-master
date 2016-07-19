package com.qx.www.shuang_la_master.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
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

    public static ArrayList<HashMap<String, Object>> LoadList(Context context, PackageManager pm)
    {
        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        try
        {
            ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);  //获得ActivityManager对象
            List<ActivityManager.RunningAppProcessInfo> runningTasks = am.getRunningAppProcesses();  //获得所有正在进行的程序存放在一个list中
            for (int i = 0; i < runningTasks.size(); i++)
            {
                PackageInfos pInfo = new PackageInfos(context);//获得PackageInfo对象
                //get application which is not in system and the usually
                //如果是非系统应用程序以及一些常用的应用程序就加到list中
                if ((pInfo.getInfo(runningTasks.get(i).processName).flags & pInfo.getInfo(runningTasks.get(i).processName).FLAG_SYSTEM) == 0
                        || (runningTasks.get(i).processName).equals("com.android.contacts")
                        || (runningTasks.get(i).processName).equals("com.android.email")
                        || (runningTasks.get(i).processName).equals("com.android.settings")
                        || (runningTasks.get(i).processName).equals("com.android.music")
                        || (runningTasks.get(i).processName).equals("com.android.calendar")
                        || (runningTasks.get(i).processName).equals("com.android.calculator2")
                        || (runningTasks.get(i).processName).equals("com.android.browser")
                        || (runningTasks.get(i).processName).equals("com.android.camera")
                        || (runningTasks.get(i).processName).equals("com.cooliris.media")
                        || (runningTasks.get(i).processName).equals("com.android.bluetooth")
                        || (runningTasks.get(i).processName).equals("com.android.mms"))
                {
                    String dir = pInfo.getInfo(runningTasks.get(i).processName).publicSourceDir;

                    Float size = Float.valueOf((float) ((new File(dir).length() * 1.0)));//获得应用程序的大小如果size大于一M就用M为单位，否则用KB
                    //long date = new Date(new File(dir).lastModified()).getTime();
                    //System.out.println(pInfo.getInfo(runningTasks.get(i).processName).loadIcon(pm));
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    map.put("icon", pInfo.getInfo(runningTasks.get(i).processName).loadIcon(pm));
                    map.put("name", pInfo.getInfo(runningTasks.get(i).processName).loadLabel(pm));
                    if (size > 1024 * 1024)
                        map.put("info", size / 1024 / 1024 + " MB");
                    else
                        map.put("info", size / 1024 + " KB");
                    map.put("packagename", runningTasks.get(i).processName.toString());//获得包名给后面用
                    list.add(map);
                }
            }
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return list;
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
    public static String bitmaptoString(Bitmap bitmap)
    {
        // 将Bitmap转换成字符串
        String string = null;
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bStream);
        byte[] bytes = bStream.toByteArray();
        string = Base64.encodeToString(bytes, Base64.DEFAULT);
        return string;
    }

    public static String numZhuanHuan(String s)
    {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return decimalFormat.format(Float.parseFloat(s) / 100);
    }
}
