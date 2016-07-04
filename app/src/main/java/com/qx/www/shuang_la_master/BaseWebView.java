package com.qx.www.shuang_la_master;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.qx.www.shuang_la_master.ui.ProgressWebView;
import com.rey.material.widget.ProgressView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pc on 2016/7/1.
 */
public class BaseWebView extends AppCompatActivity
{

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.progress_pv_linear_determinate)
    ProgressView progressPvLinearDeterminate;
    @Bind(R.id.id_progwebview)
    ProgressWebView idProgwebview;
    private Activity mActivity = null;
    private WebView mWebView = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mActivity = this;
        setContentView(R.layout.activity_basewebview);
        ButterKnife.bind(this);
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void showWebView(String url, String title)
    {        // webView与js交互代码

        toolbar.setTitle(title);
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

        try
        {
            idProgwebview.requestFocus();
            idProgwebview.setWebChromeClient(new WebChromeClient()
            {
                @Override
                public void onProgressChanged(WebView view, int progress)
                {

                    BaseWebView.this.setTitle("Loading...");
                    progressPvLinearDeterminate.setVisibility(View.VISIBLE);
                    progressPvLinearDeterminate.setProgress((float) progress / 100);

                    if (progress >= 100)
                    {
                        BaseWebView.this.setTitle("JsAndroid Test");
                        progressPvLinearDeterminate.setVisibility(View.GONE);
                    }
                }
            });

            idProgwebview.setOnKeyListener(new View.OnKeyListener()
            {        // webview can go back
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event)
                {
                    if (keyCode == KeyEvent.KEYCODE_BACK && idProgwebview.canGoBack())
                    {
                        idProgwebview.goBack();
                        return true;
                    }
                    return false;
                }
            });

            WebSettings webSettings = idProgwebview.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setDefaultTextEncodingName("utf-8");

            idProgwebview.loadUrl(url);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
