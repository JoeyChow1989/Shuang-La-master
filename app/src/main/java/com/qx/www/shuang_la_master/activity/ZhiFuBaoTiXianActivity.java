package com.qx.www.shuang_la_master.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class ZhiFuBaoTiXianActivity extends BaseActivity
{

    @Bind(R.id.toolbar1)
    Toolbar toolbar1;
    @Bind(R.id.id_zhifubaotixian_edittext_account)
    EditText idZhifubaotixianEdittextAccount;
    @Bind(R.id.id_zhifubaotixian_edittext_name)
    EditText idZhifubaotixianEdittextName;
    @Bind(R.id.id_zhifubaotixian_rb1)
    RadioButton idZhifubaotixianRb1;
    @Bind(R.id.id_zhifubaotixian_rb2)
    RadioButton idZhifubaotixianRb2;
    @Bind(R.id.id_zhifubaotixian_rb3)
    RadioButton idZhifubaotixianRb3;
    @Bind(R.id.id_zhifubaotixian_rb4)
    RadioButton idZhifubaotixianRb4;
    @Bind(R.id.id_zhifubaotixian_zhichu)
    TextView idZhifubaotixianZhichu;
    @Bind(R.id.id_zhifubaotixian_yue)
    TextView idZhifubaotixianYue;
    @Bind(R.id.id_zhifubaotixian_bt)
    Button idZhifubaotixianBt;

    SharedPreferences sp;
    String uid;
    String pid;
    String zh;
    String name;
    String money;

    MaterialDialog mMaterialDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhi_fu_bao_ti_xian);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView()
    {
        toolbar1.setTitle("支付宝提现");
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

        idZhifubaotixianRb1.setChecked(true);
        idZhifubaotixianZhichu.setText("10.5");
        pid = "1";
        idZhifubaotixianRb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    idZhifubaotixianZhichu.setText("10.5");
                    pid = "1";
                    idZhifubaotixianRb2.setChecked(false);
                    idZhifubaotixianRb3.setChecked(false);
                    idZhifubaotixianRb4.setChecked(false);
                }
            }
        });

        idZhifubaotixianRb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    idZhifubaotixianZhichu.setText("30.5");
                    pid = "2";
                    idZhifubaotixianRb1.setChecked(false);
                    idZhifubaotixianRb3.setChecked(false);
                    idZhifubaotixianRb4.setChecked(false);
                }
            }
        });

        idZhifubaotixianRb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    idZhifubaotixianZhichu.setText("50.5");
                    pid = "3";
                    idZhifubaotixianRb2.setChecked(false);
                    idZhifubaotixianRb1.setChecked(false);
                    idZhifubaotixianRb4.setChecked(false);
                }
            }
        });

        idZhifubaotixianRb4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    idZhifubaotixianZhichu.setText("100.5");
                    pid = "4";
                    idZhifubaotixianRb2.setChecked(false);
                    idZhifubaotixianRb3.setChecked(false);
                    idZhifubaotixianRb1.setChecked(false);
                }
            }
        });
    }

    @Override
    public void initData()
    {
        money = getIntent().getStringExtra("money");
        idZhifubaotixianYue.setText(AppUtils.numZhuanHuan(money));
    }

    @OnClick(R.id.id_zhifubaotixian_bt)
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.id_zhifubaotixian_bt:

                if ("".equals(idZhifubaotixianEdittextName.getText().toString().trim()))
                {
                    Toast.makeText(this, "用户姓名为空!", Toast.LENGTH_LONG).show();
                } else if ("".equals(idZhifubaotixianEdittextAccount.getText().toString().trim()))
                {
                    Toast.makeText(this, "支付宝账号为空!", Toast.LENGTH_LONG).show();
                } else
                {
                    name = idZhifubaotixianEdittextName.getText().toString().trim();
                    zh = idZhifubaotixianEdittextAccount.getText().toString().trim();
                    GetTixianData();
                }
                break;
        }
    }

    private void GetTixianData()
    {
        String url = Constants.BaseUrl + "/tixian/index";
        // TODO: 2016/7/20 账号
        String tokenBefroeMD5_TiXian = GetThePhoneInfo() + Constants.KEY + "/" + Constants.TIXIANZHONGXI_Url;
        String token_TiXian = AppUtils.getMd5Value(AppUtils.getMd5Value(tokenBefroeMD5_TiXian).substring(AppUtils.getMd5Value(tokenBefroeMD5_TiXian).length() - 4) +
                AppUtils.getMd5Value(tokenBefroeMD5_TiXian).replace(AppUtils.getMd5Value(tokenBefroeMD5_TiXian).substring(AppUtils.getMd5Value(tokenBefroeMD5_TiXian).length() - 4), ""));

        System.out.println("name------:" + name + "zh------------:" + zh + "uid----------:" + uid + "pid------:" + pid + "" + "token_TiXian------------------------" + token_TiXian);

        Map<String, String> params = new HashMap<String, String>();
        params.put("uid", uid);
        params.put("pid", pid);
        params.put("type", "3");
        params.put("zh", zh);
        params.put("name", name);
        params.put("token", token_TiXian);

        VolleyRequest.RequestPost(this, url, "TiXian", params, new VolleyInterface(this,
                VolleyInterface.mSuccessListener, VolleyInterface.mErrorListener)
        {
            @Override
            public void onMySuccess(String result)
            {
                System.out.println("-------------TiXian---zhifubao-----------------:" + result);
                try
                {
                    JSONObject js = new JSONObject(result);
                    String status = js.getString("status");
                    if ("ok".equals(status))
                    {
                        mMaterialDialog = new MaterialDialog(ZhiFuBaoTiXianActivity.this);
                        mMaterialDialog.setMessage("提现成功！").setPositiveButton("确定", new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View v)
                            {
                                mMaterialDialog.dismiss();
                                ZhiFuBaoTiXianActivity.this.finish();
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
