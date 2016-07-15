package com.qx.www.shuang_la_master;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.qx.www.shuang_la_master.activity.AllianceActivity;
import com.qx.www.shuang_la_master.activity.DetilActivity;
import com.qx.www.shuang_la_master.activity.GaoeRenwuActivity;
import com.qx.www.shuang_la_master.activity.MakeMoneyCenterActivity;
import com.qx.www.shuang_la_master.activity.MoreActivity;
import com.qx.www.shuang_la_master.activity.NewGuyActivity;
import com.qx.www.shuang_la_master.activity.ShouTuActivity;
import com.qx.www.shuang_la_master.activity.TixianActivity;
import com.qx.www.shuang_la_master.application.BaseApp;
import com.qx.www.shuang_la_master.domain.FuLiCallBack;
import com.qx.www.shuang_la_master.domain.MoneyInfo;
import com.qx.www.shuang_la_master.domain.UserInfo;
import com.qx.www.shuang_la_master.ui.Dialog_NewGuyFuli;
import com.qx.www.shuang_la_master.utils.AppUtils;
import com.qx.www.shuang_la_master.utils.Constants;
import com.qx.www.shuang_la_master.utils.VolleyInterface;
import com.qx.www.shuang_la_master.utils.VolleyRequest;
import com.rey.material.widget.Button;
import com.rey.material.widget.LinearLayout;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements View.OnClickListener
{
    @Bind(R.id.id_detil)
    LinearLayout idDetil;
    @Bind(R.id.id_more)
    LinearLayout idMore;
    @Bind(R.id.id_linear_zhuanqian)
    LinearLayout idLinearZhuanqian;
    @Bind(R.id.id_linear_shoutu)
    LinearLayout idLinearShoutu;
    @Bind(R.id.id_linear_yiyuan)
    LinearLayout idLinearYiyuan;
    @Bind(R.id.id_linear_qiankafanli)
    LinearLayout idLinearQiankafanli;
    @Bind(R.id.id_linear_youhuijuan)
    LinearLayout idLinearYouhuijuan;
    @Bind(R.id.id_toolbar_img)
    ImageView idToolbarImg;
    @Bind(R.id.id_toolbar_title)
    TextView idToolbarTitle;
    @Bind(R.id.id_toolbar_menu)
    ImageView idToolbarMenu;
    @Bind(R.id.id_nestedscrollView_main)
    NestedScrollView idNestedscrollViewMain;
    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;
    @Bind(R.id.id_main_yue)
    android.widget.LinearLayout idMainYue;
    @Bind(R.id.id_main_tixian)
    Button idMainTixian;
    @Bind(R.id.id_main_yue_textview)
    TextView idMainYueTextview;
    @Bind(R.id.id_main_todaymoney)
    TextView idMainTodaymoney;
    @Bind(R.id.id_main_totalmoney)
    TextView idMainTotalmoney;
    private long exitTime = 0;

    private int status;

    Dialog_NewGuyFuli dialog;
    String token_fuli;
    String tokenBeforeMD5_Fuli;
    String tokenBeforeMD5_remmond;
    String token_remmond;

    String tokenBeforMD5_moneyinfo;
    String token_moneyinfo;

    String uid;
    SharedPreferences sp;
    SharedPreferences info;
    SharedPreferences.Editor editor;
    String tokenBeforeMD5_info;
    String token_info;

    //得到的钱
    float money;
    DecimalFormat decimalFormat;
    String url;
    String url_userinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView()
    {
        idToolbarMenu.setImageResource(R.drawable.ic_more_vert);
        sp = getSharedPreferences("LoginInfo", MODE_PRIVATE);
        uid = String.valueOf(sp.getInt("uid", 0));
        info = getSharedPreferences("UserInfo", MODE_PRIVATE);
        editor = info.edit();

        decimalFormat = new DecimalFormat(".00");
        idToolbarMenu.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                showPopupMenu(idToolbarMenu);
            }
        });
        tokenBeforeMD5_Fuli = GetThePhoneInfo() + Constants.KEY + "/" + Constants.FULI_Url;
        token_fuli = AppUtils.getMd5Value(AppUtils.getMd5Value(tokenBeforeMD5_Fuli).substring(AppUtils.getMd5Value(tokenBeforeMD5_Fuli).length() - 4) + AppUtils.getMd5Value(tokenBeforeMD5_Fuli).replace(AppUtils.getMd5Value(tokenBeforeMD5_Fuli).substring(AppUtils.getMd5Value(tokenBeforeMD5_Fuli).length() - 4), ""));

        tokenBeforeMD5_remmond = GetThePhoneInfo() + Constants.KEY + "/" + Constants.RECOMMEND_Url;
        token_remmond = AppUtils.getMd5Value(AppUtils.getMd5Value(tokenBeforeMD5_remmond).substring(AppUtils.getMd5Value(tokenBeforeMD5_remmond).length() - 4) + AppUtils.getMd5Value(tokenBeforeMD5_remmond).replace(AppUtils.getMd5Value(tokenBeforeMD5_remmond).substring(AppUtils.getMd5Value(tokenBeforeMD5_remmond).length() - 4), ""));

        tokenBeforMD5_moneyinfo = GetThePhoneInfo() + Constants.KEY + "/" + Constants.MONEYINFO_Url;
        token_moneyinfo = AppUtils.getMd5Value(AppUtils.getMd5Value(tokenBeforMD5_moneyinfo).substring(AppUtils.getMd5Value(tokenBeforMD5_moneyinfo).length() - 4) + AppUtils.getMd5Value(tokenBeforMD5_moneyinfo).replace(AppUtils.getMd5Value(tokenBeforMD5_moneyinfo).substring(AppUtils.getMd5Value(tokenBeforMD5_moneyinfo).length() - 4), ""));

        tokenBeforeMD5_info = GetThePhoneInfo() + Constants.KEY + "/" + Constants.USERINFO_Url;
        token_info = AppUtils.getMd5Value(AppUtils.getMd5Value(tokenBeforeMD5_info).substring(AppUtils.getMd5Value(tokenBeforeMD5_info).length() - 4) + AppUtils.getMd5Value(tokenBeforeMD5_info).replace(AppUtils.getMd5Value(tokenBeforeMD5_info).substring(AppUtils.getMd5Value(tokenBeforeMD5_info).length() - 4), ""));


        System.out.println("---tokenBeforeMD5_info---:" + tokenBeforeMD5_info);
        System.out.println("---token_info---:" + token_info);
        System.out.println("---tokenBeforeMD5_Fuli---:" + tokenBeforeMD5_Fuli);
        System.out.println("---token_Fuli---:" + token_fuli);
        System.out.println("---tokenBeforeMD5_remmond---:" + tokenBeforeMD5_remmond);
        System.out.println("---token_remmond---:" + token_remmond);
        System.out.println("---tokenBeforMD5_moneyinfo---:" + tokenBeforMD5_moneyinfo);
        System.out.println("---token_moneyinfo---:" + token_moneyinfo);

        dialog = new Dialog_NewGuyFuli(this, uid, token_remmond);
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
                UserInfo userinfo = gson.fromJson(result, UserInfo.class);

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


                Glide.with(MainActivity.this)
                        .load(info.getString("avatar", ""))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(idToolbarImg);

            }

            @Override
            public void onMyError(VolleyError error)
            {
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void initData()
    {
        status = getIntent().getIntExtra("status", 2);
        if (status == AppUtils.LOGIN_SUSSES)
        {
            String url = Constants.BaseUrl + "/user/xrfl";
            GetFuLi(url, token_fuli);
        }

        url = Constants.BaseUrl + "/user/getInfos";
        GetMoneyInfo(url, token_moneyinfo);
        url_userinfo = Constants.BaseUrl + "/site/getInfo";
        GetUserInfo(url_userinfo, token_info);
    }


    public void GetMoneyInfo(String url, String token)
    {
        Map<String, String> params = new HashMap<String, String>();
        params.put("uid", uid);
        params.put("token", token);

        VolleyRequest.RequestPost(this, url, "money", params, new VolleyInterface(this,
                VolleyInterface.mSuccessListener, VolleyInterface.mErrorListener)
        {
            @Override
            public void onMySuccess(String result)
            {
                Gson gson = new Gson();
                MoneyInfo moneyinfo = gson.fromJson(result, MoneyInfo.class);

                if (moneyinfo.getStatus().equals("ok"))
                {
                    //Toast.makeText(MainActivity.this,moneyinfo.getInfos().getTotalmoney(),Toast.LENGTH_SHORT).show();

                    System.out.println("------------today------------" + moneyinfo.getInfos().getTodaymoney());

                    if (moneyinfo.getInfos().getMoney().equals("0"))
                    {
                        idMainYueTextview.setText("0");
                    } else
                    {
                        idMainYueTextview.setText(decimalFormat.format(Float.parseFloat(moneyinfo.getInfos().getMoney()) / 100));

                    }
                    if (moneyinfo.getInfos().getTodaymoney().equals("0"))
                    {
                        idMainTodaymoney.setText("0");
                    } else
                    {
                        idMainTodaymoney.setText(decimalFormat.format(Float.parseFloat(moneyinfo.getInfos().getTodaymoney()) / 100));
                    }
                    if (moneyinfo.getInfos().getTotalmoney().equals("0"))
                    {
                        idMainTotalmoney.setText("0");
                    } else
                    {
                        idMainTotalmoney.setText(decimalFormat.format(Float.parseFloat(moneyinfo.getInfos().getTotalmoney()) / 100));
                    }
                }
            }

            @Override
            public void onMyError(VolleyError error)
            {
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showPopupMenu(View view)
    {
        // View当前PopupMenu显示的相对View的位置
        PopupMenu popupMenu = new PopupMenu(this, view);
        // menu布局
        popupMenu.getMenuInflater().inflate(R.menu.menu_main, popupMenu.getMenu());
        // menu的item点击事件
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {

                switch (item.getItemId())
                {
                    case R.id.action_main:
                        // TODO: 2016/7/15 刷新
                        GetMoneyInfo(url, token_moneyinfo);
                        GetUserInfo(url_userinfo, token_info);
                        break;
                    case R.id.action_more:
                        Intent intent = new Intent();
                        intent.setClass(MainActivity.this, MoreActivity.class);
                        startActivity(intent);
                        break;
                }
                return false;
            }
        });
        // PopupMenu关闭事件
        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener()
        {
            @Override
            public void onDismiss(PopupMenu menu)
            {
                // Toast.makeText(getApplicationContext(), "关闭PopupMenu", Toast.LENGTH_SHORT).show();
            }
        });
        popupMenu.show();
    }

    private void GetFuLi(String url, String token)
    {
        Map<String, String> params = new HashMap<String, String>();
        params.put("uid", uid);
        params.put("token", token);

        VolleyRequest.RequestPost(this, url, "fuli", params, new VolleyInterface(this,
                VolleyInterface.mSuccessListener, VolleyInterface.mErrorListener)
        {
            @Override
            public void onMySuccess(String result)
            {
                Gson gson = new Gson();
                FuLiCallBack fuLiCallBack = gson.fromJson(result, FuLiCallBack.class);

                System.out.println("stuts----:" + fuLiCallBack.getStatus() + "msg-----:" + fuLiCallBack.getMsg());

                if ("ok".equals(fuLiCallBack.getStatus()))
                {
                    Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                    money = (Float.parseFloat(fuLiCallBack.getMoney()) / 100);
                    System.out.println("money-----------------------------" + money + "sssssssssssssssss" + decimalFormat.format(money));

                    idMainYueTextview.setText(decimalFormat.format(money));
                    dialog.showDialog();
                }
            }

            @Override
            public void onMyError(VolleyError error)
            {
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN)
        {
            if ((System.currentTimeMillis() - exitTime) > 2000)
            {
                Toast.makeText(getApplicationContext(), "再按一次退出应用",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else
            {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick({R.id.id_main_yue, R.id.id_main_tixian, R.id.id_linear_zhuanqian, R.id.id_linear_shoutu, R.id.id_linear_yiyuan, R.id.id_linear_qiankafanli,
            R.id.id_linear_youhuijuan})
    public void onClick(View view)
    {

        Intent intent = new Intent();
        switch (view.getId())
        {
            case R.id.id_main_yue:
                intent.setClass(this, DetilActivity.class);
                break;
            case R.id.id_main_tixian:
                intent.setClass(this, TixianActivity.class);
                break;
            case R.id.id_linear_zhuanqian:
                intent.setClass(this, MakeMoneyCenterActivity.class);
                break;
            case R.id.id_linear_shoutu:
                intent.setClass(this, GaoeRenwuActivity.class);
                break;
            case R.id.id_linear_yiyuan:
                intent.setClass(this, AllianceActivity.class);
                break;
            case R.id.id_linear_qiankafanli:
                intent.setClass(this, NewGuyActivity.class);
                break;
            case R.id.id_linear_youhuijuan:
                intent.setClass(this, ShouTuActivity.class);
                break;
        }
        startActivity(intent);
    }

    public String GetThePhoneInfo()
    {
        TelephonyManager TelephonyMgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        String szImei = TelephonyMgr.getDeviceId();
        return szImei;
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        BaseApp.getHttpQueues().cancelAll("fuli");
        BaseApp.getHttpQueues().cancelAll("money");
    }
}
