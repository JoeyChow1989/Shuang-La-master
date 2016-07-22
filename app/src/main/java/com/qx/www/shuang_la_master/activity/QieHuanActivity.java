package com.qx.www.shuang_la_master.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.qx.www.shuang_la_master.BaseActivity;
import com.qx.www.shuang_la_master.R;
import com.qx.www.shuang_la_master.domain.RegCallBack;
import com.qx.www.shuang_la_master.utils.AppUtils;
import com.qx.www.shuang_la_master.utils.Constants;
import com.qx.www.shuang_la_master.utils.VolleyInterface;
import com.qx.www.shuang_la_master.utils.VolleyRequest;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.drakeet.materialdialog.MaterialDialog;

public class QieHuanActivity extends BaseActivity
{

    @Bind(R.id.toolbar1)
    Toolbar toolbar1;
    @Bind(R.id.id_qiehuan_edit_phone)
    MaterialEditText idQiehuanEditPhone;
    @Bind(R.id.id_qiehuan_edit_yanzheng)
    MaterialEditText idQiehuanEditYanzheng;
    @Bind(R.id.id_qiehuan_bt_getyanzheng)
    Button idQiehuanBtGetyanzheng;
    @Bind(R.id.id_qiehuan_sendup)
    Button idQiehuanSendup;

    private String uid;
    private String token_changeAccAuthCodeBeforeMd5, token_ChangeAccAuthCode;
    private String token_changeBeforeMd5, token_Change;

    SharedPreferences sp;
    SharedPreferences.Editor editor;

    MaterialDialog mMaterialDialog;

    private int recLen = 60;
    Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qie_huan);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView()
    {
        toolbar1.setTitle("切换账号");
        setSupportActionBar(toolbar1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar1.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onBackPressed();
            }
        });

        sp = getSharedPreferences("LoginInfo", MODE_PRIVATE);
        editor = sp.edit();
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
        token_changeAccAuthCodeBeforeMd5 = GetThePhoneInfo() + Constants.KEY + "/" + Constants.QH_AUTHCODE_Url;
        token_ChangeAccAuthCode = AppUtils.getMd5Value(AppUtils.getMd5Value(token_changeAccAuthCodeBeforeMd5).substring(AppUtils.getMd5Value(token_changeAccAuthCodeBeforeMd5).length() - 4) +
                AppUtils.getMd5Value(token_changeAccAuthCodeBeforeMd5).replace(AppUtils.getMd5Value(token_changeAccAuthCodeBeforeMd5).substring(AppUtils.getMd5Value(token_changeAccAuthCodeBeforeMd5).length() - 4), ""));

        token_changeBeforeMd5 = GetThePhoneInfo() + Constants.KEY + "/" + Constants.QH_Url;
        token_Change = AppUtils.getMd5Value(AppUtils.getMd5Value(token_changeBeforeMd5).substring(AppUtils.getMd5Value(token_changeBeforeMd5).length() - 4) +
                AppUtils.getMd5Value(token_changeBeforeMd5).replace(AppUtils.getMd5Value(token_changeBeforeMd5).substring(AppUtils.getMd5Value(token_changeBeforeMd5).length() - 4), ""));

        System.out.println("-------------token_ChangeAccAuthCode------------" + token_ChangeAccAuthCode);
        System.out.println("-------------token_Change------------" + token_Change);

    }

    @OnClick({R.id.id_qiehuan_bt_getyanzheng, R.id.id_qiehuan_sendup})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.id_qiehuan_bt_getyanzheng:
                if ("".equals(idQiehuanEditPhone.getText().toString().trim()))
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
                } else if (!AppUtils.isMobileNO(idQiehuanEditPhone.getText().toString().trim()) || idQiehuanEditPhone.getText().toString().trim().length() != 11)
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
                    GetChangeAccontAuthCodeNum(idQiehuanEditPhone.getText().toString().trim());
                }
                break;
            case R.id.id_qiehuan_sendup:
                if ("".equals(idQiehuanEditPhone.getText().toString().trim()))
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
                } else if (!AppUtils.isMobileNO(idQiehuanEditPhone.getText().toString().trim()) || idQiehuanEditPhone.getText().toString().trim().length() != 11)
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
                } else if (!equals(idQiehuanEditYanzheng.getText().toString().trim()))
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
                } else if (idQiehuanEditYanzheng.getText().toString().trim().length() != 4)
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
                    ChangeAccontPhoneNum(idQiehuanEditPhone.getText().toString().trim(), idQiehuanEditYanzheng.getText().toString().trim());
                }
                break;
        }
    }

    private void GetChangeAccontAuthCodeNum(String phone)
    {
        System.out.println("uid-----------:" + uid + "phone------------:" + phone + "token_AuthCode---------:" + token_ChangeAccAuthCode);

        String url = Constants.BaseUrl + "/user/getQHAuthCode";
        Map<String, String> params = new HashMap<String, String>();
        params.put("uid", uid);
        params.put("phone", phone);
        params.put("token", token_ChangeAccAuthCode);

        VolleyRequest.RequestPost(this, url, "QHAuthCode", params, new VolleyInterface(this,
                VolleyInterface.mSuccessListener, VolleyInterface.mErrorListener)
        {
            @Override
            public void onMySuccess(String result)
            {
                Gson gson = new Gson();
                RegCallBack regCallBack = gson.fromJson(result, RegCallBack.class);

                if (regCallBack.getStatus() == "ok")
                {
                    System.out.println("sssssssssssssssssssss:" + regCallBack.getStatus());
                    Toast.makeText(QieHuanActivity.this, "正在获取验证码", Toast.LENGTH_LONG).show();
                    timer.schedule(task, 1000, 1000);
                }
            }

            @Override
            public void onMyError(VolleyError error)
            {
                Toast.makeText(QieHuanActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ChangeAccontPhoneNum(String phone, String code)
    {
        System.out.println("uid-----------:" + uid + "phone------------:" + phone + "token_AuthCode---------:" + token_Change);

        String url = Constants.BaseUrl + "/user/qiehuan";
        Map<String, String> params = new HashMap<String, String>();
        params.put("uid", uid);
        params.put("phone", phone);
        params.put("code", code);
        params.put("token", token_Change);

        VolleyRequest.RequestPost(this, url, "qiehuan", params, new VolleyInterface(this,
                VolleyInterface.mSuccessListener, VolleyInterface.mErrorListener)
        {
            @Override
            public void onMySuccess(String result)
            {
                Gson gson = new Gson();
                RegCallBack regCallBack = gson.fromJson(result, RegCallBack.class);

                if (regCallBack.getStatus() == "ok")
                {
                    if (!"".equals(regCallBack.getMsg()))
                    {
                        editor.putString("uid", String.valueOf(regCallBack.getMsg()));
                        editor.commit();
                        Toast.makeText(QieHuanActivity.this, "切换成功!", Toast.LENGTH_SHORT).show();
                    }
                } else
                {
                    Toast.makeText(QieHuanActivity.this, "切换失败！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMyError(VolleyError error)
            {
                Toast.makeText(QieHuanActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
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
                    idQiehuanBtGetyanzheng.setText(recLen + " s");
                    if (recLen < 0)
                    {
                        timer.cancel();
                        idQiehuanBtGetyanzheng.setText("发送验证码");
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

}
