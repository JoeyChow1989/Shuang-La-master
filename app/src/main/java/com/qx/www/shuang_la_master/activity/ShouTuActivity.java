package com.qx.www.shuang_la_master.activity;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.qx.www.shuang_la_master.BaseActivity;
import com.qx.www.shuang_la_master.R;
import com.qx.www.shuang_la_master.utils.AppUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMVideo;
import com.umeng.socialize.media.UMusic;
import com.umeng.socialize.utils.Log;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShouTuActivity extends BaseActivity
{

    SharedPreferences sp;
    @Bind(R.id.toolbar1)
    Toolbar toolbar1;
    @Bind(R.id.id_shoutu_bidu)
    ImageView idShoutuBidu;
    @Bind(R.id.id_shoutu_inventcode)
    TextView idShoutuInventcode;
    @Bind(R.id.id_shoutu_copyinventcode)
    Button idShoutuCopyinventcode;
    @Bind(R.id.appbar)
    AppBarLayout appbar;

    String sy, total, num, tnum, tsy;
    @Bind(R.id.id_shoutu_tudi_geshu)
    TextView idShoutuTudiGeshu;
    @Bind(R.id.id_shoutu_jiangli_tudi)
    TextView idShoutuJiangliTudi;
    @Bind(R.id.id_shoutu_share)
    Button idShoutuShare;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoutu);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView()
    {
        toolbar1.setTitle("");
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
        //总邀请的徒弟收益
        sy = sp.getString("sy", "");
        //总收益
        total = sp.getString("total", "");
        //总邀请的徒弟数量
        num = sp.getString("num", "");
        //今天邀请的徒弟数量
        tnum = sp.getString("tnum", "");
        //今天邀请的徒弟收益
        tsy = sp.getString("tsy", "");

        if (num.equals("0"))
        {
            idShoutuTudiGeshu.setText("0人");
        } else
        {
            idShoutuTudiGeshu.setText(AppUtils.numZhuanHuan(num) + "人");
        }

        if (sy.equals("0"))
        {
            idShoutuJiangliTudi.setText("0");
        } else
        {
            idShoutuJiangliTudi.setText(AppUtils.numZhuanHuan(sy));
        }
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
                AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setMessage("“http://m.shuangla.cc”\n\n" +
                        "1.徒弟完成IOS限时任务，您可以获得100%等额奖励\n" +
                        "2.徒弟完成Android任务，（含限时、专属、联盟）您可获得20%奖励\n" +
                        "3.师傅从每个徒弟获得最高10元奖励，收的越多奖励越多\n" +
                        "4.老徒弟规则保留到2015年12月31日");
                alertDialog.show();
                break;
            case R.id.id_shoutu_copyinventcode:
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 将文本内容放到系统剪贴板里。
                cm.setText(idShoutuInventcode.getText().toString());
                Toast.makeText(this, "复制成功，可以发给朋友们了。", Toast.LENGTH_LONG).show();
                break;
            case R.id.id_shoutu_share:
                //ShareUmeng();
                break;
        }
    }

    private void ShareUmeng()
    {
        //Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.info_icon_1);
        UMImage image = new UMImage(ShouTuActivity.this, "http://www.umeng.com/images/pic/social/integrated_3.png");
        //UMImage image = new UMImage(ShareActivity.this,bitmap);
        //UMImage image = new UMImage(ShareActivity.this,new File("/SDCARD/image_jpg.jpg"));

        UMusic music = new UMusic("http://music.huoxing.com/upload/20130330/1364651263157_1085.mp3");
        //UMusic music = new UMusic("http://y.qq.com/#type=song&mid=002I7CmS01UAIH&tpl=yqq_song_detail");
        music.setTitle("This is music title");
        music.setThumb("http://www.umeng.com/images/pic/social/chart_1.png");
        music.setDescription("my description");
        // share video
        UMVideo video = new UMVideo("http://video.sina.com.cn/p/sports/cba/v/2013-10-22/144463050817.html");
        video.setThumb("http://www.adiumxtras.com/images/thumbs/dango_menu_bar_icon_set_11_19047_6240_thumb.png");
        // share URL
        String url = "http://www.umeng.com";

        new ShareAction(this).setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN_FAVORITE)
                .withTitle("友盟分享")
                .withText("来自友盟分享面板")
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
}
