package com.qx.www.shuang_la_master.utils;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.qx.www.shuang_la_master.application.BaseApp;
import java.util.Map;

/**
 * Created by Administrator on 2015/11/25.
 */
public class VolleyRequest
{

    public static StringRequest mRequest;
    public static Context context;

    public static void RequestGet(Context context, String url, String tag, VolleyInterface volleyInterface)
    {

        BaseApp.getHttpQueues().cancelAll(tag);
        mRequest = new StringRequest(Request.Method.GET, url, volleyInterface.loadingListener(),
                volleyInterface.errorListener());
        BaseApp.getHttpQueues().add(mRequest);
        BaseApp.getHttpQueues().start();
    }

    public static void RequestPost(Context context, String url, String tag, final Map<String, String> params,
                                   VolleyInterface volleyInterface)
    {
        BaseApp.getHttpQueues().cancelAll(tag);
        mRequest = new StringRequest(Request.Method.POST, url, volleyInterface.loadingListener(),
                volleyInterface.errorListener())
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                return params;
            }
        };
        BaseApp.getHttpQueues().add(mRequest);
        BaseApp.getHttpQueues().start();
    }

}
