package com.qx.www.shuang_la_master.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.qx.www.shuang_la_master.BaseActivity;
import com.qx.www.shuang_la_master.R;
import com.qx.www.shuang_la_master.utils.AppUtils;
import com.qx.www.shuang_la_master.utils.Constants;
import com.qx.www.shuang_la_master.utils.VolleyInterface;
import com.qx.www.shuang_la_master.utils.VolleyRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.drakeet.materialdialog.MaterialDialog;

public class PhoneTiXianActivity extends BaseActivity
{

    @Bind(R.id.toolbar1)
    Toolbar toolbar1;
    @Bind(R.id.id_phonetixian_edittext)
    EditText idPhonetixianEdittext;
    @Bind(R.id.id_phonetixian_zhichu)
    TextView idPhonetixianZhichu;
    @Bind(R.id.id_phonetixian_yue)
    TextView idPhonetixianYue;
    @Bind(R.id.id_phonetixian_bt)
    Button idPhonetixianBt;
    @Bind(R.id.id_phonetixian_rb1)
    RadioButton idPhonetixianRb1;
    @Bind(R.id.id_phonetixian_rb2)
    RadioButton idPhonetixianRb2;
    @Bind(R.id.id_phonetixian_rb3)
    RadioButton idPhonetixianRb3;
    @Bind(R.id.id_phonetixian_rb4)
    RadioButton idPhonetixianRb4;

    String money;
    SharedPreferences sp;
    private String uid;
    private String pid;
    private String zh;
    private String name;

    MaterialDialog mMaterialDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_ti_xian);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView()
    {
        toolbar1.setTitle("手机充值");
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
        uid = String.valueOf(sp.getInt("uid", 0));


        idPhonetixianRb1.setChecked(true);
        pid = "1";
        idPhonetixianRb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    idPhonetixianZhichu.setText("10");
                    pid = "1";
                    idPhonetixianRb2.setChecked(false);
                    idPhonetixianRb3.setChecked(false);
                    idPhonetixianRb4.setChecked(false);
                }
            }
        });

        idPhonetixianRb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    idPhonetixianZhichu.setText("29");
                    pid = "2";
                    idPhonetixianRb1.setChecked(false);
                    idPhonetixianRb3.setChecked(false);
                    idPhonetixianRb4.setChecked(false);
                }
            }
        });

        idPhonetixianRb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    idPhonetixianZhichu.setText("48");
                    pid = "3";
                    idPhonetixianRb2.setChecked(false);
                    idPhonetixianRb1.setChecked(false);
                    idPhonetixianRb4.setChecked(false);
                }
            }
        });

        idPhonetixianRb4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    idPhonetixianZhichu.setText("95");
                    pid = "4";
                    idPhonetixianRb2.setChecked(false);
                    idPhonetixianRb3.setChecked(false);
                    idPhonetixianRb1.setChecked(false);
                }
            }
        });
    }

    @Override
    public void initData()
    {
        money = getIntent().getStringExtra("money");
        idPhonetixianYue.setText(AppUtils.numZhuanHuan(money));
    }

    @OnClick(R.id.id_phonetixian_bt)
    public void onClick()
    {
        if ("".equals(idPhonetixianEdittext.getText().toString().trim()))
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
        } else if (!AppUtils.isMobileNO(idPhonetixianEdittext.getText().toString().trim())|| idPhonetixianEdittext.getText().toString().trim().length() != 11)
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
            name = idPhonetixianEdittext.getText().toString().trim();
            GetTixianData();
        }
    }

    private void GetTixianData()
    {
        String url = Constants.BaseUrl + "/tixian/index";
        // TODO: 2016/7/20 账号
        String tokenBefroeMD5_TiXian = GetThePhoneInfo() + Constants.KEY + "/" + Constants.TIXIANZHONGXI_Url;
        String token_TiXian = AppUtils.getMd5Value(AppUtils.getMd5Value(tokenBefroeMD5_TiXian).substring(AppUtils.getMd5Value(tokenBefroeMD5_TiXian).length() - 4) +
                AppUtils.getMd5Value(tokenBefroeMD5_TiXian).replace(AppUtils.getMd5Value(tokenBefroeMD5_TiXian).substring(AppUtils.getMd5Value(tokenBefroeMD5_TiXian).length() - 4), ""));

        System.out.println("name------:" + name + "uid----------:" + uid + "pid------:" + pid + "" + "token_TiXian------------------------" + token_TiXian);

        Map<String, String> params = new HashMap<String, String>();
        params.put("uid", uid);
        params.put("pid", pid);
        params.put("type", "1");
        //params.put("zh", zh);
        params.put("name", name);
        params.put("token", token_TiXian);

        VolleyRequest.RequestPost(this, url, "TiXian", params, new VolleyInterface(this,
                VolleyInterface.mSuccessListener, VolleyInterface.mErrorListener)
        {
            @Override
            public void onMySuccess(String result)
            {
                System.out.println("-------------TiXian---shouji-----------------:" + result);
                try
                {
                    JSONObject js = new JSONObject(result);
                    String status = js.getString("status");

                    if ("ok".equals(status))
                    {
                        mMaterialDialog = new MaterialDialog(PhoneTiXianActivity.this);
                        mMaterialDialog.setMessage("提现成功！").setPositiveButton("确定", new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View v)
                            {
                                mMaterialDialog.dismiss();
                                PhoneTiXianActivity.this.finish();
                            }
                        })
                                .show();
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

    public String GetThePhoneInfo()
    {
        TelephonyManager TelephonyMgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        String szImei = TelephonyMgr.getDeviceId();
        return szImei;
    }
}
