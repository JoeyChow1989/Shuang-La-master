package com.qx.www.shuang_la_master.activity;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.qx.www.shuang_la_master.BaseActivity;
import com.qx.www.shuang_la_master.R;
import com.qx.www.shuang_la_master.utils.SaveImageUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WeiXinKeFuActivity extends BaseActivity
{

    @Bind(R.id.toolbar1)
    Toolbar toolbar;
    @Bind(R.id.id_weixinkefu_img)
    ImageView idWeixinkefuImg;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wei_xin_ke_fu);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView()
    {
        toolbar.setTitle("微信客服");
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
    }

    @Override
    public void initData()
    {
        idWeixinkefuImg.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View v)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(WeiXinKeFuActivity.this);
                builder.setItems(new String[]{getResources().getString(R.string.save_picture)}, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        idWeixinkefuImg.setDrawingCacheEnabled(true);
                        Bitmap imageBitmap = idWeixinkefuImg.getDrawingCache();
                        if (imageBitmap != null)
                        {
                            new SaveImageUtils(WeiXinKeFuActivity.this, idWeixinkefuImg).execute(imageBitmap);
                        }
                    }
                });
                builder.show();
                return true;
            }
        });
    }
}
