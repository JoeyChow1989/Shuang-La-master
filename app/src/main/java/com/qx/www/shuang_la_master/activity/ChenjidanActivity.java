package com.qx.www.shuang_la_master.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.qx.www.shuang_la_master.BaseActivity;
import com.qx.www.shuang_la_master.R;
import com.qx.www.shuang_la_master.utils.AppUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.utils.Log;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChenjidanActivity extends BaseActivity
{

    @Bind(R.id.toolbar1)
    Toolbar toolbar1;
    @Bind(R.id.id_chengjidan_days)
    TextView idChengjidanDays;
    @Bind(R.id.id_chengjidan_shouru)
    TextView idChengjidanShouru;
    @Bind(R.id.id_chengjidan_jiangli)
    TextView idChengjidanJiangli;
    @Bind(R.id.id_chengjidan_tudinum)
    TextView idChengjidanTudinum;
    @Bind(R.id.id_chengjidan_allmoney)
    TextView idChengjidanAllmoney;

    SharedPreferences sp;
    long timecurrentTimeMillis;
    @Bind(R.id.id_chengjidan_saiyisai)
    Button idChengjidanSaiyisai;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chenjidan);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView()
    {
        toolbar1.setTitle("成绩单");
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

        sp = getSharedPreferences("UserInfo", MODE_PRIVATE);
    }

    @Override
    public void initData()
    {

        idChengjidanDays.setText(UseDaysNum() + "天");

        if ("0".equals(sp.getString("total", "")))
        {
            idChengjidanShouru.setText("0元");
        } else
        {
            idChengjidanShouru.setText(AppUtils.numZhuanHuan(sp.getString("total", "")) + "元");
        }

        if ("0".equals(sp.getString("sy", "")))
        {
            idChengjidanJiangli.setText("0元");
        } else
        {
            idChengjidanJiangli.setText(AppUtils.numZhuanHuan(sp.getString("sy", "")) + "元");
        }

        idChengjidanTudinum.setText(sp.getString("num", "") + "人");

        if ("0".equals(sp.getString("total", "")))
        {
            idChengjidanAllmoney.setText("0元");
        } else
        {
            idChengjidanAllmoney.setText(AppUtils.numZhuanHuan(sp.getString("total", "")) + "元");
        }
    }

    public String UseDaysNum()
    {

        String userDays = "";
        timecurrentTimeMillis = Long.decode(String.valueOf(new Date().getTime()).substring(0, 10));

        System.out.println("------------timecurrentTimeMillis--------------:" + timecurrentTimeMillis);
        System.out.println("------------接口--------------:" + Long.decode(sp.getString("logintime", "")));

        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaa:" + (timecurrentTimeMillis - Long.decode(sp.getString("logintime", ""))) / (3 * 24 * 60 * 60000));
        userDays = String.valueOf((timecurrentTimeMillis - Long.decode(sp.getString("logintime", ""))) / (3 * 24 * 60 * 60000));
        return userDays;
    }

    @OnClick(R.id.id_chengjidan_saiyisai)
    public void onClick()
    {
        ShareUmeng();
    }

    private void ShareUmeng()
    {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon);
        UMImage image = new UMImage(ChenjidanActivity.this, bitmap);
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
                Toast.makeText(ChenjidanActivity.this, platform + " 收藏成功啦", Toast.LENGTH_SHORT).show();
            } else
            {
                Toast.makeText(ChenjidanActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t)
        {
            Toast.makeText(ChenjidanActivity.this, platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if (t != null)
            {
                Log.d("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform)
        {
            Toast.makeText(ChenjidanActivity.this, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
