package com.qx.www.shuang_la_master.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.qx.www.shuang_la_master.BaseActivity;
import com.qx.www.shuang_la_master.R;
import com.qx.www.shuang_la_master.application.BaseApp;
import com.qx.www.shuang_la_master.domain.RegCallBack;
import com.qx.www.shuang_la_master.utils.AppUtils;
import com.qx.www.shuang_la_master.utils.Constants;
import com.qx.www.shuang_la_master.utils.VolleyInterface;
import com.qx.www.shuang_la_master.utils.VolleyRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.drakeet.materialdialog.MaterialDialog;

public class PhoneBindActivity extends BaseActivity
{

    @Bind(R.id.toolbar1)
    Toolbar toolbar;
    @Bind(R.id.id_phone_bt_getyanzheng)
    Button idPhoneBtGetyanzheng;
    @Bind(R.id.id_phone_sendup)
    Button idPhoneSendup;
    @Bind(R.id.id_phone_edit_phone)
    AppCompatEditText idPhoneEditPhone;
    @Bind(R.id.id_phone_edit_yanzheng)
    AppCompatEditText idPhoneEditYanzheng;

    private String uid;
    SharedPreferences sp;
    private String tokenBeforeMD5_AuthCode;
    private String token_AuthCode;
    private String tokenBeforeMD5_Vaild;
    private String token_Vaild;

    private int recLen = 60;
    Timer timer = new Timer();

    MaterialDialog mMaterialDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_bind);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView()
    {
        toolbar.setTitle("绑定手机");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onBackPressed();
            }
        });
        sp = getSharedPreferences("LoginInfo", MODE_PRIVATE);
        uid = String.valueOf(sp.getInt("uid", 0));
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
        tokenBeforeMD5_AuthCode = GetThePhoneInfo() + Constants.KEY + "/" + Constants.AUTHCODE_Url;
        tokenBeforeMD5_Vaild = GetThePhoneInfo() + Constants.KEY + "/" + Constants.VALID_Url;
        token_AuthCode = AppUtils.getMd5Value(AppUtils.getMd5Value(tokenBeforeMD5_AuthCode).substring(AppUtils.getMd5Value(tokenBeforeMD5_AuthCode).length() - 4) +
                AppUtils.getMd5Value(tokenBeforeMD5_AuthCode).replace(AppUtils.getMd5Value(tokenBeforeMD5_AuthCode).substring(AppUtils.getMd5Value(tokenBeforeMD5_AuthCode).length() - 4), ""));

        token_Vaild = AppUtils.getMd5Value(AppUtils.getMd5Value(tokenBeforeMD5_Vaild).substring(AppUtils.getMd5Value(tokenBeforeMD5_Vaild).length() - 4) +
                AppUtils.getMd5Value(tokenBeforeMD5_Vaild).replace(AppUtils.getMd5Value(tokenBeforeMD5_Vaild).substring(AppUtils.getMd5Value(tokenBeforeMD5_Vaild).length() - 4), ""));
        System.out.println("token_Vaild------------------:" + token_Vaild);
        System.out.println("token_AuthCode-----------------:" + token_AuthCode);
    }

    @OnClick({R.id.id_phone_bt_getyanzheng, R.id.id_phone_sendup})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.id_phone_bt_getyanzheng:
                if ("".equals(idPhoneEditPhone.getText().toString().trim()))
                {
                    mMaterialDialog = new MaterialDialog(this);
                    mMaterialDialog.setMessage("手机号码为空!").setPositiveButton("ok", new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            mMaterialDialog.dismiss();
                        }
                    }).show();
                } else if (AppUtils.isMobileNO(idPhoneEditPhone.getText().toString().trim()))
                {
                    mMaterialDialog = new MaterialDialog(this);
                    mMaterialDialog.setMessage("手机号码格式不正确!").setPositiveButton("ok", new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            mMaterialDialog.dismiss();
                        }
                    }).show();
                } else
                {
                    GetAuthCodeNum(idPhoneEditPhone.getText().toString().trim());
                }
                break;
            case R.id.id_phone_sendup:

                if ("".equals(idPhoneEditPhone.getText().toString().trim()))
                {
                    mMaterialDialog = new MaterialDialog(this);
                    mMaterialDialog.setMessage("手机号码为空!").setPositiveButton("ok", new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            mMaterialDialog.dismiss();
                        }
                    }).show();
                } else if (AppUtils.isMobileNO(idPhoneEditPhone.getText().toString().trim()))
                {
                    mMaterialDialog = new MaterialDialog(this);
                    mMaterialDialog.setMessage("手机号码格式不正确!").setPositiveButton("ok", new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            mMaterialDialog.dismiss();
                        }
                    }).show();
                } else if (!equals(idPhoneEditYanzheng.getText().toString().trim()))
                {

                    mMaterialDialog = new MaterialDialog(this);
                    mMaterialDialog.setMessage("验证码为空!").setPositiveButton("ok", new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            mMaterialDialog.dismiss();
                        }
                    }).show();
                } else if (idPhoneEditYanzheng.getText().toString().trim().length() != 4)
                {

                    mMaterialDialog = new MaterialDialog(this);
                    mMaterialDialog.setMessage("验证码格式不正确!").setPositiveButton("ok", new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            mMaterialDialog.dismiss();
                        }
                    }).show();
                } else
                {
                    BindPhoneNum(idPhoneEditPhone.getText().toString().trim(), idPhoneEditYanzheng.getText().toString().trim());
                }
                break;
        }
    }

    private void BindPhoneNum(String phone, String code)
    {
        System.out.println("uid-----------:" + uid + "phone------------:" + phone + "token_AuthCode---------:" + token_AuthCode);

        String url = Constants.BaseUrl + "/user/validAuthCode";
        Map<String, String> params = new HashMap<String, String>();
        params.put("uid", uid);
        params.put("phone", phone);
        params.put("code", code);
        params.put("token", token_Vaild);

        VolleyRequest.RequestPost(this, url, "vaild", params, new VolleyInterface(this,
                VolleyInterface.mSuccessListener, VolleyInterface.mErrorListener)
        {
            @Override
            public void onMySuccess(String result)
            {
                Gson gson = new Gson();
                RegCallBack regCallBack = gson.fromJson(result, RegCallBack.class);

                if (regCallBack.getStatus() == "ok")
                {
                    Toast.makeText(PhoneBindActivity.this, "绑定成功!", Toast.LENGTH_SHORT).show();
                } else
                {
                    Toast.makeText(PhoneBindActivity.this, "绑定失败！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMyError(VolleyError error)
            {
                Toast.makeText(PhoneBindActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void GetAuthCodeNum(String phone)
    {
        System.out.println("uid-----------:" + uid + "phone------------:" + phone + "token_AuthCode---------:" + token_AuthCode);

        String url = Constants.BaseUrl + "/user/getAuthCode";
        Map<String, String> params = new HashMap<String, String>();
        params.put("uid", uid);
        params.put("phone", phone);
        params.put("token", token_AuthCode);

        VolleyRequest.RequestPost(this, url, "getAuthCode", params, new VolleyInterface(this,
                VolleyInterface.mSuccessListener, VolleyInterface.mErrorListener)
        {
            @Override
            public void onMySuccess(String result)
            {
                System.out.println("-------------result-getAuthCode-------------:" + result);

                Gson gson = new Gson();
                RegCallBack regCallBack = gson.fromJson(result, RegCallBack.class);

                if (regCallBack.getStatus().equals("ok"))
                {
                    System.out.println("-------------getAuthCode-------------:" + regCallBack.getStatus());
                    Toast.makeText(PhoneBindActivity.this, "正在获取验证码", Toast.LENGTH_SHORT).show();
                    timer.schedule(task, 1000, 1000);
                }
            }

            @Override
            public void onMyError(VolleyError error)
            {
                Toast.makeText(PhoneBindActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    final Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {
                case 1:
                    idPhoneBtGetyanzheng.setText(recLen + " s");
                    if (recLen < 0)
                    {
                        timer.cancel();
                        idPhoneBtGetyanzheng.setText("发送验证码");
                    }
            }
        }
    };

    //倒计时
    TimerTask task = new TimerTask()
    {
        @Override
        public void run()
        {
            recLen--;
            Message message = new Message();
            message.what = 1;
            handler.sendMessage(message);
        }
    };


    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        BaseApp.getHttpQueues().cancelAll("getAuthCode");
        BaseApp.getHttpQueues().cancelAll("vaild");
    }
}
