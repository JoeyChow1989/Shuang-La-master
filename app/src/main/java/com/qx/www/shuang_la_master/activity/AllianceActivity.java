package com.qx.www.shuang_la_master.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.qx.www.shuang_la_master.BaseActivity;
import com.qx.www.shuang_la_master.R;
import com.qx.www.shuang_la_master.adapter.AllianceAdapter;
import com.qx.www.shuang_la_master.domain.AllianceRenwu;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AllianceActivity extends BaseActivity
{

    @Bind(R.id.toolbar1)
    Toolbar toolbar;
    @Bind(R.id.id_allance_renwu_recy)
    ListView idAllanceRenwuRecy;
    AllianceAdapter mAdapter;
    List<AllianceRenwu> mList;

    private int imgs[] = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,};
    private String titles[] = {"一元夺宝", "钱咖返利", "点乐", "多盟", "有米", "点入", "点财", "中亿"};
    private String msgs[] = {"一元买手机充值卡", "返利高，价格低", "任务多，结算快", "更稳定，口碑好", "更新快，任务好", "均价高，返利快", "均价多，更新快", "赚钱多，返利快"};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alliance);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView()
    {
        toolbar.setTitle("联盟任务");
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

        mList = new ArrayList<AllianceRenwu>();
        View view = LayoutInflater.from(this).inflate(R.layout.addhead_img, null, false);
        idAllanceRenwuRecy.addHeaderView(view);
    }

    @Override
    public void initData()
    {
        for (int i = 0; i < imgs.length; i++)
        {
            AllianceRenwu allianceRenwu = new AllianceRenwu();
            allianceRenwu.setImgs(imgs[i]);
            allianceRenwu.setTitle(titles[i]);
            allianceRenwu.setMsgs(msgs[i]);
            mList.add(allianceRenwu);
        }

        System.out.println("-----------------------" + mList.size());

        mAdapter = new AllianceAdapter(mList, this);
        idAllanceRenwuRecy.setAdapter(mAdapter);
    }
}
