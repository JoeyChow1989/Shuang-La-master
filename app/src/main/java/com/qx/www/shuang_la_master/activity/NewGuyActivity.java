package com.qx.www.shuang_la_master.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.qx.www.shuang_la_master.BaseActivity;
import com.qx.www.shuang_la_master.R;
import com.qx.www.shuang_la_master.application.BaseApp;
import com.qx.www.shuang_la_master.domain.NewGuyCallBack;
import com.qx.www.shuang_la_master.utils.AppUtils;
import com.qx.www.shuang_la_master.utils.Constants;
import com.qx.www.shuang_la_master.utils.VolleyInterface;
import com.qx.www.shuang_la_master.utils.VolleyRequest;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewGuyActivity extends BaseActivity
{

    @Bind(R.id.id_toolbar_img)
    ImageView idToolbarImg;
    @Bind(R.id.id_toolbar_title)
    TextView idToolbarTitle;
    @Bind(R.id.id_toolbar_menu)
    TextView idToolbarMenu;
    @Bind(R.id.id_newpop_wanshan)
    LinearLayout idNewpopWanshan;
    @Bind(R.id.id_newpop_bindphone)
    LinearLayout idNewpopBindphone;
    @Bind(R.id.toolbar2)
    LinearLayout toolbar;

    SharedPreferences sp;
    String tokenBeforeMD5_NewGuy = null;
    String token_NewGuy;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newpople);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView()
    {
        idToolbarMenu.setText("刷新");
        idToolbarTitle.setText("新手任务");
        sp = getSharedPreferences("LoginInfo", MODE_PRIVATE);
        uid = String.valueOf(sp.getInt("uid", 0));

        tokenBeforeMD5_NewGuy = GetThePhoneInfo() + Constants.KEY + "/" + Constants.NEWGUY_Url;
        token_NewGuy = AppUtils.getMd5Value(AppUtils.getMd5Value(tokenBeforeMD5_NewGuy).substring(AppUtils.getMd5Value(tokenBeforeMD5_NewGuy).length() - 4) +
                AppUtils.getMd5Value(tokenBeforeMD5_NewGuy).replace(AppUtils.getMd5Value(tokenBeforeMD5_NewGuy).substring(AppUtils.getMd5Value(tokenBeforeMD5_NewGuy).length() - 4), ""));

        System.out.println("token_NewGuy---------:" + token_NewGuy);
    }

    public String GetThePhoneInfo()
    {
        TelephonyManager TelephonyMgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        String szImei = TelephonyMgr.getDeviceId();
        return szImei;
    }

    @Override
    public void initData()
    {
        GetNewGuyInfo();
    }

    private void GetNewGuyInfo()
    {
        String url = Constants.BaseUrl + "/user/task";
        Map<String, String> params = new HashMap<String, String>();
        params.put("uid", uid);
        params.put("token", token_NewGuy);

        VolleyRequest.RequestPost(this, url, "task", params, new VolleyInterface(this,
                VolleyInterface.mSuccessListener, VolleyInterface.mErrorListener)
        {
            @Override
            public void onMySuccess(String result)
            {
                Gson gson = new Gson();
                NewGuyCallBack newGuyCallBack = gson.fromJson(result, NewGuyCallBack.class);
                if (newGuyCallBack.getStatus().equals("ok"))
                {
                    if (newGuyCallBack.getMsg().getTask1() == 0)
                    {
                        idNewpopWanshan.setVisibility(View.VISIBLE);
                    } else
                    {
                        idNewpopWanshan.setVisibility(View.GONE);
                    }

                    if (newGuyCallBack.getMsg().getTask2() == 0)
                    {
                        idNewpopBindphone.setVisibility(View.VISIBLE);
                    } else
                    {
                        idNewpopBindphone.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onMyError(VolleyError error)
            {
                Toast.makeText(NewGuyActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick({R.id.id_toolbar_img, R.id.id_toolbar_menu, R.id.id_newpop_wanshan, R.id.id_newpop_bindphone})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.id_toolbar_img:
                finish();
                break;
            case R.id.id_toolbar_menu:
                break;
            case R.id.id_newpop_wanshan:
                Intent intent = new Intent();
                intent.setClass(NewGuyActivity.this, ZiLiaoActivity.class);
                startActivity(intent);
                break;
            case R.id.id_newpop_bindphone:
                Intent intent1 = new Intent();
                intent1.setClass(NewGuyActivity.this, PhoneBindActivity.class);
                startActivity(intent1);
                break;
        }
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        BaseApp.getHttpQueues().cancelAll("task");
    }
}
