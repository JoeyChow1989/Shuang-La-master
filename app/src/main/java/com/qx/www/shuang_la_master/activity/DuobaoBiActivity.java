package com.qx.www.shuang_la_master.activity;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.qx.www.shuang_la_master.BaseActivity;
import com.qx.www.shuang_la_master.MainActivity;
import com.qx.www.shuang_la_master.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DuobaoBiActivity extends BaseActivity
{

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.duobaobi_sendup)
    Button duobaobiSendup;
    @Bind(R.id.id_edittext_duobaobi_phonenum)
    TextView idEdittextDuobaobiPhonenum;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duobao_bi);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView()
    {
        toolbar.setTitle("夺宝币兑换");
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
    }

    private void showMonneyPopupWindow()
    {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.popwindowlayout, null);

        // 下面是两种方法得到宽度和高度 getWindow().getDecorView().getWidth()

        final PopupWindow window = new PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);

        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        window.setFocusable(true);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        window.setBackgroundDrawable(dw);

        // 设置popWindow的显示和消失动画
        window.setAnimationStyle(R.style.mypopwindow_anim_style);
        // 在底部显示
        window.showAtLocation(DuobaoBiActivity.this.findViewById(R.id.id_edittext_duobaobi_phonenum),
                Gravity.BOTTOM, 0, 0);

        // 这里检验popWindow里的button是否可以点击
        Button first = (Button) view.findViewById(R.id.first);
        first.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {

                System.out.println("第一个按钮被点击了");
            }
        });

        Button er = (Button) view.findViewById(R.id.second);
        er.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(DuobaoBiActivity.this,"第二个按钮",Toast.LENGTH_SHORT).show();
            }
        });

        Button dismiss = (Button) view.findViewById(R.id.five);
        dismiss.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                window.dismiss();
            }
        });

//       // popWindow消失监听方法
//        window.setOnDismissListener(new PopupWindow.OnDismissListener()
//        {
//
//            @Override
//            public void onDismiss()
//            {
//                System.out.println("popWindow消失");
//            }
//        });
    }

    @OnClick({R.id.id_edittext_duobaobi_phonenum, R.id.duobaobi_sendup})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.id_edittext_duobaobi_phonenum:
                showMonneyPopupWindow();
                break;
            case R.id.duobaobi_sendup:
                break;
        }
    }
}
