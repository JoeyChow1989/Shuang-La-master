package com.qx.www.shuang_la_master.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;

import com.qx.www.shuang_la_master.BaseActivity;
import com.qx.www.shuang_la_master.R;
import com.qx.www.shuang_la_master.service.UpdateService;

/**
 * Created by pc on 2016/6/28.
 */
public class SplashActivity extends BaseActivity
{
    String szImei = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        Intent intent = new Intent(this,
//                UpdateService.class);
//        intent.putExtra("dowurl",
//                "http://hb.kuaihuala.com/wxhb.apk");
//        startService(intent);

        GetThePhoneInfo();
        JumpToMain();
    }

    private void GetThePhoneInfo()
    {
        TelephonyManager TelephonyMgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        szImei = TelephonyMgr.getDeviceId();
        System.out.println("szImei-------------" + szImei);
    }

    private void JumpToMain()
    {
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();
            }
        }, 2500);
    }
}
