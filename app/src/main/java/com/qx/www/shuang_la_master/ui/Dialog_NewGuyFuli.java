package com.qx.www.shuang_la_master.ui;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.qx.www.shuang_la_master.R;
import com.qx.www.shuang_la_master.domain.FuLiCallBack;
import com.qx.www.shuang_la_master.utils.Constants;
import com.qx.www.shuang_la_master.utils.VolleyInterface;
import com.qx.www.shuang_la_master.utils.VolleyRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pc on 2016/6/29.
 */
public class Dialog_NewGuyFuli extends Dialog
{
    Button idXinrenfuliHaveinvent;
    EditText idXinrenfuliLinerEdittext;
    Button idXinrenfuliLinerSure;
    LinearLayout idXinrenfuliLiner;
    Button idXinrenfuliNoinvent;
    TextView idXinrenfuliUserdeil;
    private Context context;
    Dialog alertDialog = null;
    private String uid;
    private String token_remmend;

    public Dialog_NewGuyFuli(Context context,String uid,String token_remmend)
    {
        super(context);
        this.context = context;
        this.uid = uid;
        this.token_remmend = token_remmend;
    }

    public void showDialog()
    {
        alertDialog = new Dialog(context);
        alertDialog.setCancelable(false);
        alertDialog.show();
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_fuli, null);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(0, 0, 0, 0)));
        alertDialog.setContentView(view);

        idXinrenfuliHaveinvent = (Button) view.findViewById(R.id.id_xinrenfuli_haveinvent);
        idXinrenfuliLinerEdittext = (EditText) view.findViewById(R.id.id_xinrenfuli_liner_edittext);
        idXinrenfuliLinerSure = (Button) view.findViewById(R.id.id_xinrenfuli_liner_sure);
        idXinrenfuliLiner = (LinearLayout) view.findViewById(R.id.id_xinrenfuli_liner);
        idXinrenfuliNoinvent = (Button) view.findViewById(R.id.id_xinrenfuli_noinvent);
        idXinrenfuliUserdeil = (TextView) view.findViewById(R.id.id_xinrenfuli_userdeil);

        idXinrenfuliHaveinvent.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                idXinrenfuliHaveinvent.setVisibility(View.GONE);
                idXinrenfuliLiner.setVisibility(View.VISIBLE);
            }
        });

        idXinrenfuliNoinvent.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                alertDialog.dismiss();
            }
        });

        idXinrenfuliLinerSure.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (!"".equals(idXinrenfuliLinerEdittext.getText().toString().trim()))
                {
                    String inventID = idXinrenfuliLinerEdittext.getText().toString().trim();
                    SendInventID(inventID);
                }
            }
        });
    }

    private void SendInventID(String recommend_id)
    {
        String url = Constants.BaseUrl + "/user/recommend";
        Map<String, String> params = new HashMap<String, String>();
        params.put("uid", uid);
        params.put("recommend_id",recommend_id);
        params.put("token", token_remmend);

        VolleyRequest.RequestPost(context, url, "recommend", params, new VolleyInterface(context,
                VolleyInterface.mSuccessListener, VolleyInterface.mErrorListener)
        {
            @Override
            public void onMySuccess(String result)
            {
                Gson gson = new Gson();
                FuLiCallBack fuLiCallBack = gson.fromJson(result, FuLiCallBack.class);

                System.out.println("stuts----:" + fuLiCallBack.getStatus() + "msg-----:" + fuLiCallBack.getMsg());

                if ("ok".equals(fuLiCallBack.getStatus()))
                {
                    Toast.makeText(context, "success", Toast.LENGTH_SHORT).show();

                }
                alertDialog.dismiss();
            }

            @Override
            public void onMyError(VolleyError error)
            {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
