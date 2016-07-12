package com.qx.www.shuang_la_master.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.qx.www.shuang_la_master.BaseActivity;
import com.qx.www.shuang_la_master.R;
import com.qx.www.shuang_la_master.adapter.NewsNoticeAdapter;
import com.qx.www.shuang_la_master.common.AutoLoadRecylerView;
import com.qx.www.shuang_la_master.common.DividerItemDecoration;
import com.qx.www.shuang_la_master.domain.News;
import com.qx.www.shuang_la_master.domain.XianshiRenwu;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewsNoticeActivity extends BaseActivity implements AutoLoadRecylerView.loadMoreListener
{
    @Bind(R.id.toolbar1)
    Toolbar toolbar;
    @Bind(R.id.id_autorecyler_newsnotice)
    AutoLoadRecylerView idAutorecylerNewsnotice;
    private LinearLayoutManager layoutManager;

    private List<News> mList;
    private NewsNoticeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_notice);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView()
    {
        toolbar.setTitle("新闻公告");
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

        mList = new ArrayList<>();
        layoutManager = new LinearLayoutManager(this);
        idAutorecylerNewsnotice.setLayoutManager(layoutManager);
        idAutorecylerNewsnotice.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        idAutorecylerNewsnotice.setLoadMoreListener(this);
    }

    @Override
    public void initData()
    {
        for (int i = 0; i < 10; i++)
        {
            News news = new News();
            news.setMsg("是时候有一个随心理财顾问");
            news.setDate("6月3日 6:30");
            mList.add(news);
        }

        adapter = new NewsNoticeAdapter(mList, this);
        idAutorecylerNewsnotice.setAdapter(adapter);

        adapter.setOnItemClickListener(new NewsNoticeAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(View view, int position)
            {
                Toast.makeText(NewsNoticeActivity.this, "Onclick" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position)
            {
                Toast.makeText(NewsNoticeActivity.this, "Longclick" + position, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onLoadMore()
    {

    }
}
