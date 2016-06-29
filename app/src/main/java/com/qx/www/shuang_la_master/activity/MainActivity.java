package com.qx.www.shuang_la_master.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.qx.www.shuang_la_master.BaseActivity;
import com.qx.www.shuang_la_master.R;
import com.qx.www.shuang_la_master.View.ProgressWebView;
import com.rey.material.widget.ProgressView;

public class MainActivity extends BaseActivity
{
    private ProgressWebView mWebView;
    private ProgressView mProgressView;
    private long exitTime = 0;
    // private CustemDialog mCustemDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        showWebView();
        //mCustemDialog = new CustemDialog(this, "京东金融", R.mipmap.ic_launcher, "8M");
        // mCustemDialog.showDialog();
    }

    @Override
    public void initView()
    {
        super.initView();
        mWebView = (ProgressWebView) findViewById(R.id.id_webview_main);
        mProgressView = (ProgressView) findViewById(R.id.id_progressView_main);
    }

    @Override
    public void showWebView()
    {
        super.showWebView();
        mWebView.requestFocus();
        mWebView.setWebChromeClient(new WebChromeClient()
        {
            @Override
            public void onProgressChanged(WebView view, int progress)
            {
                MainActivity.this.setTitle("Loading...");
                if (progress != 0)
                {
                    mProgressView.setVisibility(View.VISIBLE);
                    //System.out.println("progress-------------" + (float) progress / 100);
                    mProgressView.setProgress((float) progress / 100);
                }

                if (progress == 100)
                {
                    MainActivity.this.setTitle("爽啦");
                    mProgressView.setVisibility(View.GONE);
                }
            }
        });

        mWebView.setOnKeyListener(new View.OnKeyListener()
        {
            // webview can go back
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack())
                {
                    mWebView.goBack();
                    return true;
                }
                return false;
            }
        });
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDefaultTextEncodingName("utf-8");
        if (Build.VERSION.SDK_INT >= 19)
        {
            webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }
        mWebView.loadUrl("http://blog.csdn.net/lmj623565791");
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
}
