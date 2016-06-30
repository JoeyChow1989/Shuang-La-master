package com.qx.www.shuang_la_master.ui;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.qx.www.shuang_la_master.R;

/**
 * Created by pc on 2016/6/29.
 */
public class CustemDialog extends AlertDialog.Builder
{
    ImageView mDialogIcon;
    TextView mDialogTitle;
    TextView mDialogSize;
    TextView mDialogMsg1;

    Button button;

    private Context context;
    private String title;
    private String msg;
    private int pic;

    public CustemDialog(Context context, String title, int pic, String msg)
    {
        super(context);
        this.context = context;
        this.title = title;
        this.msg = msg;
        this.pic = pic;
    }

    public void showDialog()
    {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setCancelable(true);
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setContentView(R.layout.dialog_info);

        mDialogIcon = (ImageView) window.findViewById(R.id.id_dialog_icon);
        mDialogTitle = (TextView) window.findViewById(R.id.id_dialog_title);
        mDialogSize = (TextView) window.findViewById(R.id.id_dialog_size);
        mDialogMsg1 = (TextView) window.findViewById(R.id.id_dialog_msg_1);

        mDialogIcon.setImageResource(pic);
        mDialogTitle.setText(title);
        mDialogSize.setText(msg);
    }
}
