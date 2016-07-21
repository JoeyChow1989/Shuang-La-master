package com.qx.www.shuang_la_master.activity;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.qx.www.shuang_la_master.BaseActivity;
import com.qx.www.shuang_la_master.R;
import com.qx.www.shuang_la_master.utils.SaveImageUtils;

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


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wei_xin);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @OnClick({R.id.id_weinxin_edit, R.id.id_weinxin_button})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.id_weinxin_edit:
                break;
            case R.id.id_weinxin_button:
                BindWeiXinAccount();
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
                builder.setItems(new String[]{getResources().getString(R.string.save_picture)}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        idWeinxinImg.setDrawingCacheEnabled(true);
                        Bitmap imageBitmap = idWeinxinImg.getDrawingCache();
                        if (imageBitmap != null) {
                            new SaveImageUtils(WeiXinActivity.this, idWeinxinImg).execute(imageBitmap);
                        }
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    private void BindWeiXinAccount()
    {

    }
}
