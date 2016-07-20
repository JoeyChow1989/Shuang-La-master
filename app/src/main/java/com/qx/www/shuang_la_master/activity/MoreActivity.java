package com.qx.www.shuang_la_master.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.qx.www.shuang_la_master.BaseActivity;
import com.qx.www.shuang_la_master.R;
import com.qx.www.shuang_la_master.application.BaseApp;
import com.qx.www.shuang_la_master.domain.UserInfo;
import com.qx.www.shuang_la_master.ui.RoundImageView;
import com.qx.www.shuang_la_master.utils.AppUtils;
import com.qx.www.shuang_la_master.utils.Constants;
import com.qx.www.shuang_la_master.utils.VolleyInterface;
import com.qx.www.shuang_la_master.utils.VolleyRequest;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MoreActivity extends BaseActivity
{

    @Bind(R.id.toolbar1)
    Toolbar toolbar;
    @Bind(R.id.id_more_id)
    LinearLayout idMoreId;
    @Bind(R.id.id_more_ziliao)
    LinearLayout idMoreZiliao;
    @Bind(R.id.id_more_weixin)
    LinearLayout idMoreWeixin;
    @Bind(R.id.id_more_shouji)
    LinearLayout idMoreShouji;
    @Bind(R.id.id_more_chenjidan)
    LinearLayout idMoreChenjidan;
    @Bind(R.id.id_more_kefu)
    LinearLayout idMoreKefu;
    @Bind(R.id.id_more_shangwu)
    LinearLayout idMoreShangwu;
    @Bind(R.id.id_more_check)
    LinearLayout idMoreCheck;
    @Bind(R.id.id_more_changeid)
    LinearLayout idMoreChangeid;
    @Bind(R.id.id_more_text_id)
    TextView idMoreTextId;
    @Bind(R.id.id_more_ziliao_nickname)
    TextView idMoreZiliaoNickname;
    @Bind(R.id.id_more_ziliao_headpic)
    RoundImageView idMoreZiliaoHeadpic;
    @Bind(R.id.id_more_ziliao_shouji)
    TextView idMoreZiliaoShouji;
    @Bind(R.id.id_more_vname)
    TextView idMoreVname;

    SharedPreferences sp;
    String uid;
    String url_userinfo;
    String tokenBeforeMD5_info, token_info;
    SharedPreferences info;
    SharedPreferences.Editor editor;
    UserInfo userinfo;


    @Override
    public void initData()
    {
        url_userinfo = Constants.BaseUrl + "/site/getInfo";
        GetUserInfo(url_userinfo, token_info);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView()
    {
        toolbar.setTitle("资料");
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
        info = getSharedPreferences("UserInfo", MODE_PRIVATE);
        editor = info.edit();

        tokenBeforeMD5_info = GetThePhoneInfo() + Constants.KEY + "/" + Constants.USERINFO_Url;
        token_info = AppUtils.getMd5Value(AppUtils.getMd5Value(tokenBeforeMD5_info).substring(AppUtils.getMd5Value(tokenBeforeMD5_info).length() - 4) + AppUtils.getMd5Value(tokenBeforeMD5_info).replace(AppUtils.getMd5Value(tokenBeforeMD5_info).substring(AppUtils.getMd5Value(tokenBeforeMD5_info).length() - 4), ""));

        System.out.println("---tokenBeforeMD5_info---:" + tokenBeforeMD5_info);
        System.out.println("---token_info---:" + token_info);

        idMoreVname.setText("版本:v" + BaseApp.instance.vsername);
    }

    @OnClick({R.id.id_more_ziliao, R.id.id_more_weixin, R.id.id_more_shouji, R.id.id_more_chenjidan, R.id.id_more_kefu, R.id.id_more_shangwu, R.id.id_more_check, R.id.id_more_changeid})
    public void onClick(View view)
    {
        Intent intent = new Intent();
        switch (view.getId())
        {
            case R.id.id_more_ziliao:
                intent.setClass(this, ZiLiaoActivity.class);
                startActivity(intent);
                break;
            case R.id.id_more_weixin:
                intent.setClass(this, WeiXinActivity.class);
                startActivity(intent);
                break;
            case R.id.id_more_shouji:
                intent.setClass(this, PhoneBindActivity.class);
                startActivity(intent);
                break;
            case R.id.id_more_chenjidan:
                intent.setClass(this, ZiLiaoActivity.class);
                startActivity(intent);
                break;
            case R.id.id_more_kefu:
                intent.setClass(this, KeFuCenterActivity.class);
                startActivity(intent);
                break;
            case R.id.id_more_shangwu:
                intent.setClass(this, ShangwuActivity.class);
                startActivity(intent);
                break;
            case R.id.id_more_check:
                CheckUpdata();
                break;
            case R.id.id_more_changeid:
                intent.setClass(this, ZiLiaoActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void CheckUpdata()
    {
        String url = Constants.UP_URL;
        VolleyRequest.RequestGet(this, url, "checkupdata", new VolleyInterface(this, VolleyInterface.mSuccessListener,
                VolleyInterface.mErrorListener)
        {
            @Override
            public void onMySuccess(String result)
            {
                Gson gson = new Gson();
            }

            @Override
            public void onMyError(VolleyError error)
            {
                Toast.makeText(MoreActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String GetThePhoneInfo()
    {
        TelephonyManager TelephonyMgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        String szImei = TelephonyMgr.getDeviceId();
        return szImei;
    }

    private void GetUserInfo(String url, String token_info)
    {
        Map<String, String> params = new HashMap<String, String>();
        params.put("uid", uid);
        params.put("token", token_info);

        VolleyRequest.RequestPost(this, url, "info", params, new VolleyInterface(this,
                VolleyInterface.mSuccessListener, VolleyInterface.mErrorListener)
        {
            @Override
            public void onMySuccess(String result)
            {
                System.out.println("sssssssssssssssssss" + result);

                Gson gson = new Gson();
                userinfo = gson.fromJson(result, UserInfo.class);

                editor.putString("avatar", userinfo.getInfos().getAvatar());
                editor.putString("mobile", userinfo.getInfos().getMobile());
                editor.putString("status", userinfo.getInfos().getStatus());
                editor.putString("work", userinfo.getInfos().getWork());
                editor.putString("weixin", userinfo.getInfos().getWeixin());
                editor.putString("nickname", userinfo.getInfos().getNickname());
                editor.putString("sex", userinfo.getInfos().getSex());
                editor.putString("birthday", userinfo.getInfos().getBirthday());
                editor.putString("uid", userinfo.getInfos().getUid());
                editor.putString("semi", userinfo.getInfos().getSemi());
                editor.putString("tnum", userinfo.getInfos().getTnum());
                editor.putString("tsy", userinfo.getInfos().getTsy());
                editor.putString("num", userinfo.getInfos().getNum());
                editor.putString("sy", userinfo.getInfos().getSy());
                editor.putString("total", userinfo.getInfos().getTotal());

                editor.commit();
                System.out.println("mobile-----------------:" + userinfo.getInfos().getMobile());


                idMoreZiliaoNickname.setText(userinfo.getInfos().getNickname());
                Glide.with(MoreActivity.this)
                        .load(Constants.BACKGROUDUrl + userinfo.getInfos().getAvatar())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .error(R.mipmap.ic_launcher)
                        .into(idMoreZiliaoHeadpic);

                idMoreZiliaoShouji.setText(userinfo.getInfos().getMobile());
                idMoreTextId.setText(userinfo.getInfos().getUid());

            }

            @Override
            public void onMyError(VolleyError error)
            {
                Toast.makeText(MoreActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
