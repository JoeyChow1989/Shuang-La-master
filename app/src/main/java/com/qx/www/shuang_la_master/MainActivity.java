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
import com.qx.www.shuang_la_master.domain.Tixian;
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
    String tokenBeforeMD5_remmond;
    String token_remmond;

    String tokenBeforMD5_moneyinfo;
    String token_moneyinfo;

    String uid;
    SharedPreferences sp, info;

    //得到的钱
    float money;
    String url;

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
        info = getSharedPreferences("UserInfo", MODE_PRIVATE);
        uid = String.valueOf(sp.getInt("uid", 0));

        idToolbarMenu.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //showPopupMenu(idToolbarMenu);
                Intent intent = new Intent(MainActivity.this,MoreActivity.class);
                startActivity(intent);
            }
        });
        tokenBeforeMD5_remmond = GetThePhoneInfo() + Constants.KEY + "/" + Constants.RECOMMEND_Url;
        token_remmond = AppUtils.getMd5Value(AppUtils.getMd5Value(tokenBeforeMD5_remmond).substring(AppUtils.getMd5Value(tokenBeforeMD5_remmond).length() - 4) + AppUtils.getMd5Value(tokenBeforeMD5_remmond).replace(AppUtils.getMd5Value(tokenBeforeMD5_remmond).substring(AppUtils.getMd5Value(tokenBeforeMD5_remmond).length() - 4), ""));

        tokenBeforMD5_moneyinfo = GetThePhoneInfo() + Constants.KEY + "/" + Constants.MONEYINFO_Url;
        token_moneyinfo = AppUtils.getMd5Value(AppUtils.getMd5Value(tokenBeforMD5_moneyinfo).substring(AppUtils.getMd5Value(tokenBeforMD5_moneyinfo).length() - 4) + AppUtils.getMd5Value(tokenBeforMD5_moneyinfo).replace(AppUtils.getMd5Value(tokenBeforMD5_moneyinfo).substring(AppUtils.getMd5Value(tokenBeforMD5_moneyinfo).length() - 4), ""));

        System.out.println("---tokenBeforeMD5_remmond---:" + tokenBeforeMD5_remmond);
        System.out.println("---token_remmond---:" + token_remmond);
        System.out.println("---tokenBeforMD5_moneyinfo---:" + tokenBeforMD5_moneyinfo);
        System.out.println("---token_moneyinfo---:" + token_moneyinfo);
    }

    @Override
    public void initData()
    {
        status = getIntent().getIntExtra("status", 2);
        if (status == AppUtils.REG_SUSSES)
        {
            dialog = new Dialog_NewGuyFuli(this, GetThePhoneInfo(), uid, token_remmond);
            dialog.showDialog();
        }
    }


    @Override
    protected void onStart()
    {
        super.onStart();
        url = Constants.BaseUrl + "/user/getInfos";
        GetMoneyInfo(url, token_moneyinfo);

        Glide.with(this)
                .load(Constants.BACKGROUDUrl+info.getString("avatar",""))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.mipmap.ic_launcher)
                .into(idToolbarImg);
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
                        idMainYueTextview.setText(AppUtils.numZhuanHuan(moneyinfo.getInfos().getMoney()));

                    }
                    if (moneyinfo.getInfos().getTodaymoney().equals("0"))
                    {
                        idMainTodaymoney.setText("0");
                    } else
                    {
                        idMainTodaymoney.setText(AppUtils.numZhuanHuan(moneyinfo.getInfos().getTodaymoney()));
                    }
                    if (moneyinfo.getInfos().getTotalmoney().equals("0"))
                    {
                        idMainTotalmoney.setText("0");
                    } else
                    {
                        idMainTotalmoney.setText(AppUtils.numZhuanHuan(moneyinfo.getInfos().getTotalmoney()));
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

//    private void showPopupMenu(View view)
//    {
//        // View当前PopupMenu显示的相对View的位置
//        PopupMenu popupMenu = new PopupMenu(this, view);
//        // menu布局
//        popupMenu.getMenuInflater().inflate(R.menu.menu_main, popupMenu.getMenu());
//        // menu的item点击事件
//        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
//        {
//            @Override
//            public boolean onMenuItemClick(MenuItem item)
//            {
//
//                switch (item.getItemId())
//                {
//                    case R.id.action_main:
//                        // TODO: 2016/7/15 刷新
//                        GetMoneyInfo(url, token_moneyinfo);
//                        break;
//                    case R.id.action_more:
//                        Intent intent = new Intent();
//                        intent.setClass(MainActivity.this, MoreActivity.class);
//                        startActivity(intent);
//                        break;
//                }
//                return false;
//            }
//        });
//        // PopupMenu关闭事件
//        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener()
//        {
//            @Override
//            public void onDismiss(PopupMenu menu)
//            {
//                // Toast.makeText(getApplicationContext(), "关闭PopupMenu", Toast.LENGTH_SHORT).show();
//            }
//        });
//        popupMenu.show();
//    }

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
