package com.qx.www.shuang_la_master.ui;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.qx.www.shuang_la_master.R;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by pc on 2016/6/29.
 */
public class Dialog_NewGuyFuli_Finish extends Dialog
{
    Button idXinrenfuliBegin;
    private Context context;
    private Dialog alertDialog;

    public Dialog_NewGuyFuli_Finish(Context context)
    {
        super(context);
        this.context = context;
    }

    public void showDialog()
    {
        alertDialog = new Dialog(context);
        alertDialog.setCancelable(false);
        alertDialog.show();
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_fuli_finish, null);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(0, 0, 0, 0)));
        alertDialog.setContentView(view);

        idXinrenfuliBegin = (Button) view.findViewById(R.id.id_xinrenfuli_begin);

        idXinrenfuliBegin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                alertDialog.dismiss();
            }
        });
    }

}
