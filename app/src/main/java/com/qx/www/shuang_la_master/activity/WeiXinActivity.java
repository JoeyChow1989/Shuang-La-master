package com.qx.www.shuang_la_master.activity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.qx.www.shuang_la_master.BaseActivity;
import com.qx.www.shuang_la_master.R;
import com.qx.www.shuang_la_master.domain.RegCallBack;
import com.qx.www.shuang_la_master.utils.AppUtils;
import com.qx.www.shuang_la_master.utils.Constants;
import com.qx.www.shuang_la_master.utils.SaveImageUtils;
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

public class WeiXinActivity extends BaseActivity
{

    @Bind(R.id.toolbar1)
    Toolbar toolbar;
    @Bind(R.id.id_weinxin_edit)
    TextInputEditText idWeinxinEdit;
    @Bind(R.id.id_weinxin_button)
    Button idWeinxinButton;
    @Bind(R.id.id_weinxin_img)
    ImageView idWeinxinImg;

    MaterialDialog materialDialog;

    String token_Vaild_weixinBeforMD5;
    String token_Vaild_weixin;
    String uid;
    SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wei_xin);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @OnClick(R.id.id_weinxin_button)
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.id_weinxin_button:
                if ("".equals(idWeinxinEdit.getText().toString().trim()))
                {
                    materialDialog = new MaterialDialog(this);
                    materialDialog.setMessage("验证码为空！").setPositiveButton("ok", new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            materialDialog.dismiss();
                        }
                    }).show();
                } else if (idWeinxinEdit.getText().toString().trim().length() != 6)
                {
                    materialDialog = new MaterialDialog(this);
                    materialDialog.setMessage("验证码格式不正确！").setPositiveButton("ok", new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            materialDialog.dismiss();
                        }
                    }).show();
                } else
                {
                    BindWeiXinAccount(idWeinxinEdit.getText().toString().trim());
                }
                break;
        }
    }

    @Override
    public void initView()
    {
        toolbar.setTitle("微信");
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

        token_Vaild_weixinBeforMD5 = GetThePhoneInfo() + Constants.KEY + "/" + Constants.WEIXINAUTHCODE;
        token_Vaild_weixin = AppUtils.getMd5Value(AppUtils.getMd5Value(token_Vaild_weixinBeforMD5).substring(AppUtils.getMd5Value(token_Vaild_weixinBeforMD5).length() - 4) + AppUtils.getMd5Value(token_Vaild_weixinBeforMD5).
                replace(AppUtils.getMd5Value(token_Vaild_weixinBeforMD5).substring(AppUtils.getMd5Value(token_Vaild_weixinBeforMD5).length() - 4), ""));

        System.out.println("---------token_Vaild_weixinBeforMD5-----------" + token_Vaild_weixinBeforMD5);
        System.out.println("---------token_Vaild_weixin-----------" + token_Vaild_weixin);

    }

    @Override
    public void initData()
    {
        idWeinxinImg.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View v)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(WeiXinActivity.this);
                builder.setItems(new String[]{getResources().getString(R.string.save_picture)}, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        idWeinxinImg.setDrawingCacheEnabled(true);
                        Bitmap imageBitmap = idWeinxinImg.getDrawingCache();
                        if (imageBitmap != null)
                        {
                            new SaveImageUtils(WeiXinActivity.this, idWeinxinImg).execute(imageBitmap);
                        }
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    private void BindWeiXinAccount(String code)
    {
        System.out.println("uid-----------:" + uid + "code------------:" + code + "token_AuthCode---------:" + token_Vaild_weixin);

        String url = Constants.BaseUrl + "/user/validWXAuthCode";
        Map<String, String> params = new HashMap<String, String>();
        params.put("uid", uid);
        params.put("code", code);
        params.put("token", token_Vaild_weixin);

        VolleyRequest.RequestPost(this, url, "vaildWX", params, new VolleyInterface(this,
                VolleyInterface.mSuccessListener, VolleyInterface.mErrorListener)
        {
            @Override
            public void onMySuccess(String result)
            {
                System.out.println("-----------result----------" + result);

                try
                {
                    JSONObject js = new JSONObject(result);
                    String status = js.getString("status");

                    if ("ok".equals(status))
                    {
                        materialDialog = new MaterialDialog(WeiXinActivity.this);
                        materialDialog.setMessage("绑定成功!").setPositiveButton("ok", new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View v)
                            {
                                materialDialog.dismiss();
                                WeiXinActivity.this.finish();
                            }
                        }).show();
                    } else
                    {
                        materialDialog = new MaterialDialog(WeiXinActivity.this);
                        materialDialog.setMessage("绑定失败!").setPositiveButton("ok", new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View v)
                            {
                                materialDialog.dismiss();
                            }
                        }).show();
                    }

                } catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }

            @Override
            public void onMyError(VolleyError error)
            {
                Toast.makeText(WeiXinActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
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
