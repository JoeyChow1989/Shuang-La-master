package com.qx.www.shuang_la_master.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.qx.www.shuang_la_master.BaseActivity;
import com.qx.www.shuang_la_master.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WeiXinTiXianActivity extends BaseActivity
{

    @Bind(R.id.toolbar1)
    Toolbar toolbar1;
    @Bind(R.id.id_weixintixian_edittext)
    EditText idWeixintixianEdittext;
    @Bind(R.id.id_weixintixian_shiyuan)
    TextView idWeixintixianShiyuan;
    @Bind(R.id.id_weixintixian_sanshiyuan)
    TextView idWeixintixianSanshiyuan;
    @Bind(R.id.id_weixintixian_wushiyuan)
    TextView idWeixintixianWushiyuan;
    @Bind(R.id.id_weixintixian_yibaiyuan)
    TextView idWeixintixianYibaiyuan;
    @Bind(R.id.id_weixintixian_radiogroup)
    RadioGroup idWeixintixianRadiogroup;
    @Bind(R.id.id_weixintixian_zhichu)
    TextView idWeixintixianZhichu;
    @Bind(R.id.id_weixintixian_yue)
    TextView idWeixintixianYue;
    @Bind(R.id.id_weixintixian_bt)
    Button idWeixintixianBt;
    @Bind(R.id.id_weixintixian_rb1)
    RadioButton idWeixintixianRb1;
    @Bind(R.id.id_weixintixian_rb2)
    RadioButton idWeixintixianRb2;
    @Bind(R.id.id_weixintixian_rb3)
    RadioButton idWeixintixianRb3;
    @Bind(R.id.id_weixintixian_rb4)
    RadioButton idWeixintixianRb4;

    String money;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wei_xin_ti_xian);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView()
    {

        toolbar1.setTitle("微信提现");
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

        idWeixintixianRb1.setChecked(true);
        idWeixintixianRb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    idWeixintixianZhichu.setText("10");
                    idWeixintixianRb2.setChecked(false);
                    idWeixintixianRb3.setChecked(false);
                    idWeixintixianRb4.setChecked(false);
                }
            }
        });

        idWeixintixianRb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    idWeixintixianZhichu.setText("29");
                    idWeixintixianRb1.setChecked(false);
                    idWeixintixianRb3.setChecked(false);
                    idWeixintixianRb4.setChecked(false);
                }
            }
        });

        idWeixintixianRb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    idWeixintixianZhichu.setText("48");
                    idWeixintixianRb2.setChecked(false);
                    idWeixintixianRb1.setChecked(false);
                    idWeixintixianRb4.setChecked(false);
                }
            }
        });

        idWeixintixianRb4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    idWeixintixianZhichu.setText("95");
                    idWeixintixianRb2.setChecked(false);
                    idWeixintixianRb3.setChecked(false);
                    idWeixintixianRb1.setChecked(false);
                }
            }
        });
    }

    @Override
    public void initData()
    {
        money = getIntent().getStringExtra("money");
        idWeixintixianYue.setText(money);
    }

    @OnClick(R.id.id_weixintixian_bt)
    public void onClick()
    {
    }
}
