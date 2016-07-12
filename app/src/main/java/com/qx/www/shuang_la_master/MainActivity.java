package com.qx.www.shuang_la_master;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.qx.www.shuang_la_master.activity.AllianceActivity;
import com.qx.www.shuang_la_master.activity.DetilActivity;
import com.qx.www.shuang_la_master.activity.GaoeRenwuActivity;
import com.qx.www.shuang_la_master.activity.MakeMoneyCenterActivity;
import com.qx.www.shuang_la_master.activity.MoreActivity;
import com.qx.www.shuang_la_master.activity.NewGuyActivity;
import com.qx.www.shuang_la_master.activity.ShouTuActivity;
import com.qx.www.shuang_la_master.activity.TixianActivity;
import com.rey.material.widget.Button;
import com.rey.material.widget.LinearLayout;

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
    private long exitTime = 0;

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
        idToolbarImg.setImageResource(R.drawable.aa);
        idToolbarMenu.setImageResource(R.drawable.ic_more_vert);
        idToolbarMenu.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                showPopupMenu(idToolbarMenu);
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
                    case R.id.action_settings1:
                        Toast.makeText(MainActivity.this, "action_settings", Toast.LENGTH_SHORT).show();
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
}
