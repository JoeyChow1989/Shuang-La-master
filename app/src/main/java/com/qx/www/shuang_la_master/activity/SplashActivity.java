package com.qx.www.shuang_la_master.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.qx.www.shuang_la_master.BaseActivity;
import com.qx.www.shuang_la_master.MainActivity;
import com.qx.www.shuang_la_master.R;
import com.qx.www.shuang_la_master.application.BaseApp;
import com.qx.www.shuang_la_master.domain.RegCallBack;
import com.qx.www.shuang_la_master.utils.AppUtils;
import com.qx.www.shuang_la_master.utils.Constants;
import com.qx.www.shuang_la_master.utils.VolleyInterface;
import com.qx.www.shuang_la_master.utils.VolleyRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pc on 2016/6/28.
 */
public class SplashActivity extends BaseActivity
{
    String szImei = null;
    String token_reg = null;
    String token_login = null;


    String tokenBeforeMD5_Reg = null;
    String tokenBeforeMD5_Login = null;

    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    public void initData()
    {
        GetRegData();
    }

    @Override
    public void initView()
    {
        tokenBeforeMD5_Reg = GetThePhoneInfo() + Constants.KEY + "/" + Constants.Reg_Url;
        tokenBeforeMD5_Login = GetThePhoneInfo() + Constants.KEY + "/" + Constants.Login_Url;

        sp = getSharedPreferences("LoginInfo", MODE_PRIVATE);
        editor = sp.edit();

        try
        {
            token_reg = AppUtils.getMd5Value(tokenBeforeMD5_Reg);
            token_login = AppUtils.getMd5Value(AppUtils.getMd5Value(tokenBeforeMD5_Login).substring(AppUtils.getMd5Value(tokenBeforeMD5_Login).length() - 4) +
                    AppUtils.getMd5Value(tokenBeforeMD5_Login).replace(AppUtils.getMd5Value(tokenBeforeMD5_Login).substring(AppUtils.getMd5Value(tokenBeforeMD5_Login).length() - 4), ""));
            System.out.println("后四位:" + token_login);

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        initData();
        //JumpToMain();
    }

    public String GetThePhoneInfo()
    {
        TelephonyManager TelephonyMgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        szImei = TelephonyMgr.getDeviceId();
        return szImei;
    }

    private void Login()
    {
        String url = Constants.BaseUrl + "/user/login";

        Map<String, String> params = new HashMap<String, String>();
        params.put("semi", GetThePhoneInfo());
        params.put("token", token_login);

        VolleyRequest.RequestPost(this, url, "login", params, new VolleyInterface(this,
                VolleyInterface.mSuccessListener, VolleyInterface.mErrorListener)
        {
            @Override
            public void onMySuccess(String result)
            {
                Gson gson = new Gson();
                RegCallBack regCallBack = gson.fromJson(result, RegCallBack.class);
                if (regCallBack.getStatus().equals("ok"))
                {
                    System.out.println("-------------login--success-----------");

                    editor.putInt("uid", regCallBack.getMsg());
                    editor.commit();
                    JumpToMain(AppUtils.LOGIN_SUSSES);
                }
            }

            @Override
            public void onMyError(VolleyError error)
            {
                System.out.println(error.toString());
            }
        });
    }


    private void GetRegData()
    {
        String url = Constants.BaseUrl + "/user/reg";

        Map<String, String> params = new HashMap<String, String>();
        params.put("semi", GetThePhoneInfo());
        params.put("token", token_reg);

        VolleyRequest.RequestPost(this, url, "reg", params, new VolleyInterface(this,
                VolleyInterface.mSuccessListener, VolleyInterface.mErrorListener)
        {
            @Override
            public void onMySuccess(String result)
            {
                Gson gson = new Gson();
                RegCallBack regCallBack = gson.fromJson(result, RegCallBack.class);
                if (regCallBack.getStatus().equals("ok"))
                {
                    JumpToMain(AppUtils.REG_SUSSES);
                } else
                {
                    Login();
                }

            }

            @Override
            public void onMyError(VolleyError error)
            {
                System.out.println(error.toString());
            }
        });


    }


    private void JumpToMain(final int i)
    {
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                intent.putExtra("status", i);
                startActivity(intent);
                SplashActivity.this.finish();
            }
        }, 2500);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        BaseApp.getHttpQueues().cancelAll("login");
        BaseApp.getHttpQueues().cancelAll("reg");
    }
}
