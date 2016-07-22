package com.qx.www.shuang_la_master.activity;

import android.Manifest;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.qx.www.shuang_la_master.BaseActivity;
import com.qx.www.shuang_la_master.R;
import com.qx.www.shuang_la_master.domain.UserInfo;
import com.qx.www.shuang_la_master.utils.AppUtils;
import com.qx.www.shuang_la_master.utils.Constants;
import com.qx.www.shuang_la_master.utils.SaveImageUtils;
import com.qx.www.shuang_la_master.utils.VolleyInterface;
import com.qx.www.shuang_la_master.utils.VolleyRequest;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.utils.Log;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.drakeet.materialdialog.MaterialDialog;

public class ShouTuActivity extends BaseActivity
{

    @Bind(R.id.toolbar1)
    Toolbar toolbar1;
    @Bind(R.id.id_shoutu_bidu)
    ImageView idShoutuBidu;
    @Bind(R.id.id_shoutu_inventcode)
    TextView idShoutuInventcode;
    @Bind(R.id.id_shoutu_copyinventcode)
    Button idShoutuCopyinventcode;

    @Bind(R.id.id_shoutu_tudi_geshu)
    TextView idShoutuTudiGeshu;
    @Bind(R.id.id_shoutu_jiangli_tudi)
    TextView idShoutuJiangliTudi;
    @Bind(R.id.id_shoutu_share)
    Button idShoutuShare;
    @Bind(R.id.id_shoutu_total)
    TextView idShoutuTotal;
    @Bind(R.id.id_shoutu_erweima)
    ImageView idShoutuErweima;

    SharedPreferences sp;
    String uid;
    String url_userinfo;
    String tokenBeforeMD5_info, token_info;
    SharedPreferences info;
    SharedPreferences.Editor editor;
    UserInfo userinfo;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoutu);
        ButterKnife.bind(this);
        String[] mPermissionList = new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS};
        ActivityCompat.requestPermissions(ShouTuActivity.this, mPermissionList, 100);
        initView();
        initData();
    }

    @Override
    public void initView()
    {
        toolbar1.setTitle("收徒");
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
        info = getSharedPreferences("UserInfo", MODE_PRIVATE);
        editor = info.edit();

        tokenBeforeMD5_info = GetThePhoneInfo() + Constants.KEY + "/" + Constants.USERINFO_Url;
        token_info = AppUtils.getMd5Value(AppUtils.getMd5Value(tokenBeforeMD5_info).substring(AppUtils.getMd5Value(tokenBeforeMD5_info).length() - 4) + AppUtils.getMd5Value(tokenBeforeMD5_info).replace(AppUtils.getMd5Value(tokenBeforeMD5_info).substring(AppUtils.getMd5Value(tokenBeforeMD5_info).length() - 4), ""));

        System.out.println("---tokenBeforeMD5_info---:" + tokenBeforeMD5_info);
        System.out.println("---token_info---:" + token_info);

        idShoutuErweima.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View v)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(ShouTuActivity.this);
                builder.setItems(new String[]{getResources().getString(R.string.save_picture)}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        idShoutuErweima.setDrawingCacheEnabled(true);
                        Bitmap imageBitmap = idShoutuErweima.getDrawingCache();
                        if (imageBitmap != null) {
                            new SaveImageUtils(ShouTuActivity.this, idShoutuErweima).execute(imageBitmap);
                        }
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    @Override
    public void initData()
    {
        url_userinfo = Constants.BaseUrl + "/site/getInfo";
        GetUserInfo(url_userinfo, token_info);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }

    @OnClick({R.id.id_shoutu_bidu, R.id.id_shoutu_copyinventcode, R.id.id_shoutu_share})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.id_shoutu_bidu:
                final MaterialDialog materialDialog = new MaterialDialog(this);
                materialDialog.setMessage("“http://m.shuangla.cc”\n\n" +
                        "1.徒弟完成IOS限时任务，您可以获得100%等额奖励\n" +
                        "2.徒弟完成Android任务，（含限时、专属、联盟）您可获得20%奖励\n" +
                        "3.师傅从每个徒弟获得最高10元奖励，收的越多奖励越多\n" +
                        "4.老徒弟规则保留到2015年12月31日").setPositiveButton("ok", new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        materialDialog.dismiss();
                    }
                }).show();
                break;
            case R.id.id_shoutu_copyinventcode:
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 将文本内容放到系统剪贴板里。
                cm.setText(idShoutuInventcode.getText().toString());
                Toast.makeText(this, "复制成功，可以发给朋友们了。", Toast.LENGTH_LONG).show();
                break;
            case R.id.id_shoutu_share:
                ShareUmeng();
                break;
        }
    }

    private void ShareUmeng()
    {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon);
        UMImage image = new UMImage(ShouTuActivity.this, bitmap);
        //UMImage image = new UMImage(ShouTuActivity.this, "http://www.umeng.com/images/pic/social/integrated_3.png");
        //UMImage image = new UMImage(ShareActivity.this,new File("/SDCARD/image_jpg.jpg"));

        //UMusic music = new UMusic("http://music.huoxing.com/upload/20130330/1364651263157_1085.mp3");
        //UMusic music = new UMusic("http://y.qq.com/#type=song&mid=002I7CmS01UAIH&tpl=yqq_song_detail");
//        music.setTitle("This is music title");
//        music.setThumb("http://www.umeng.com/images/pic/social/chart_1.png");
//        music.setDescription("my description");
        // share video
//        UMVideo video = new UMVideo("http://video.sina.com.cn/p/sports/cba/v/2013-10-22/144463050817.html");
//        video.setThumb("http://www.adiumxtras.com/images/thumbs/dango_menu_bar_icon_set_11_19047_6240_thumb.png");
        // share URL
        String url = AppUtils.SHUANGLA_URL;

        new ShareAction(this).setDisplayList(SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE)
                .withTitle("爽啦分享")
                .withText("分享你的邀请码，邀请更多人吧")
                .withMedia(image)
                .withTargetUrl(url)
                .setCallback(umShareListener)
                //.withShareBoardDirection(view, Gravity.TOP|Gravity.LEFT)
                .open();
    }

    private UMShareListener umShareListener = new UMShareListener()
    {
        @Override
        public void onResult(SHARE_MEDIA platform)
        {
            Log.d("plat", "platform" + platform);
            if (platform.name().equals("WEIXIN_FAVORITE"))
            {
                Toast.makeText(ShouTuActivity.this, platform + " 收藏成功啦", Toast.LENGTH_SHORT).show();
            } else
            {
                Toast.makeText(ShouTuActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t)
        {
            Toast.makeText(ShouTuActivity.this, platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if (t != null)
            {
                Log.d("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform)
        {
            Toast.makeText(ShouTuActivity.this, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
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
                System.out.println("--------------getInfo-------------" + result);
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
                editor.putString("logintime", userinfo.getInfos().getLogintime());
                editor.putString("tnum", userinfo.getInfos().getTnum());
                editor.putString("tsy", userinfo.getInfos().getTsy());
                editor.putString("num", userinfo.getInfos().getNum());
                editor.putString("sy", userinfo.getInfos().getSy());
                editor.putString("total", userinfo.getInfos().getTotal());

                editor.commit();
                System.out.println("mobile-----------------:" + userinfo.getInfos().getMobile());

                if (userinfo.getInfos().getNum().equals("0"))
                {
                    idShoutuTudiGeshu.setText("0人");
                } else
                {
                    idShoutuTudiGeshu.setText(userinfo.getInfos().getNum() + "人");
                }

                if (userinfo.getInfos().getSy().equals("0"))
                {
                    idShoutuJiangliTudi.setText("0");
                } else
                {
                    idShoutuJiangliTudi.setText(userinfo.getInfos().getSy());
                }

                if (userinfo.getInfos().getTsy().equals("0"))
                {
                    idShoutuTotal.setText("00.00");
                } else
                {
                    idShoutuTotal.setText(userinfo.getInfos().getTsy());
                }
            }

            @Override
            public void onMyError(VolleyError error)
            {
                Toast.makeText(ShouTuActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
