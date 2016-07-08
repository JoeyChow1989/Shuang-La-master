package com.qx.www.shuang_la_master.ui;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qx.www.shuang_la_master.R;
import com.qx.www.shuang_la_master.service.UpdateService;
import com.qx.www.shuang_la_master.utils.AppUtils;
import com.rey.material.widget.ProgressView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by pc on 2016/6/29.
 */
public class CustemDialog extends AlertDialog.Builder
{
    ImageView mDialogIcon;
    TextView mDialogTitle;
    TextView mDialogSize;
    TextView mDialogMsg1, mDialogMsg2, mDialogMsg3;
    LinearLayout mDialogLinear;
    TextView mDialogContent;

    Button mBtStart, mBtShenhe, mBtCancel;
    ProgressView mProgressView;

    private String mPackageName;

    private Context context;
    private String title;
    private String msg;
    private String size;
    private int pic;

    public CustemDialog(Context context, String title, int pic, String size, String msg)
    {
        super(context);
        this.context = context;
        this.title = title;
        this.msg = msg;
        this.pic = pic;
        this.size = size;
    }

    public void showDialog()
    {
        final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setCancelable(false);
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setContentView(R.layout.dialog_info);

        mDialogIcon = (ImageView) window.findViewById(R.id.id_dialog_icon);
        mDialogTitle = (TextView) window.findViewById(R.id.id_dialog_title);
        mDialogSize = (TextView) window.findViewById(R.id.id_dialog_size);
        mDialogMsg1 = (TextView) window.findViewById(R.id.id_dialog_msg_1);
        mDialogMsg2 = (TextView) window.findViewById(R.id.id_dialog_msg_2);
        mDialogMsg3 = (TextView) window.findViewById(R.id.id_dialog_msg_3);
        mBtStart = (Button) window.findViewById(R.id.id_dialog_start);
        mBtShenhe = (Button) window.findViewById(R.id.id_dialog_shenhe);
        mBtCancel = (Button) window.findViewById(R.id.id_dialog_cancel);
        mDialogLinear = (LinearLayout) window.findViewById(R.id.id_dialog_linear);
        mDialogContent = (TextView) window.findViewById(R.id.id_dialog_centent);
        mProgressView = (ProgressView) window.findViewById(R.id.id_dialog_prog);

        mBtStart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mBtStart.setVisibility(View.GONE);
                mDialogLinear.setVisibility(View.VISIBLE);

                if (AppUtils.isAvilible(context, "com.hb.qx"))
                {
                    Uri uri = Uri.fromFile(downFile);
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(uri, "application/vnd.android.package-archive");
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
                //未安装，跳转至market下载该程序
                else
                {
                    DownLoadApk();
                }
            }
        });

        mBtCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                alertDialog.dismiss();

            }
        });

        mDialogIcon.setImageResource(pic);
        mDialogTitle.setText(title);
        mDialogSize.setText(size);
        mDialogMsg1.setText(msg.substring(0, msg.indexOf("2")));
        mDialogMsg2.setText(msg.substring(msg.indexOf("2"), msg.indexOf("3")));
        mDialogMsg3.setText(msg.substring(msg.indexOf("3")));
    }

    private String url = "http://hb.kuaihuala.com/wxhb.apk";
    private static final int TIMEOUT = 10 * 1000;// 超时
    private File filedir;
    private File downFile;
    private static final int DOWN_OK = 1;
    private static final int DOWN_ERROR = 0;
    int totalSize;// 文件总大小

    /**
     * 使用Handler更新UI界面信息
     */
    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            float temp = msg.getData().getInt("size")
                    / (float) totalSize;
            mProgressView.setProgress(temp);
            System.out.println("ssssssssssssssssssssssss" + temp);
            mDialogContent.setText(String.valueOf(temp * 100).substring(0, 4) + "%");
            // int progress = (int) (temp * 100);
            if (temp == 1.0)
            {
                Toast.makeText(context, "下载完成！", Toast.LENGTH_LONG).show();
                mDialogLinear.setVisibility(View.GONE);
                mBtStart.setVisibility(View.VISIBLE);
                mBtStart.setText("安装");
                mBtStart.setBackgroundColor(context.getResources().getColor(R.color.instll_color));
            }
        }
    };


    public void DownLoadApk()
    {
        // 判断文件夹是否存在
        String filepath = getSDPath() + "/shuangla/";
        filedir = new File(filepath);
        if (!filedir.exists())
        {
            filedir.mkdir();
        }
        // 判断文件是否存在
        String filename = getSDPath() + "/shuangla/" + mPackageName + ".apk";
        downFile = new File(filename);
        if (!downFile.exists())
        {
            try
            {
                downFile.createNewFile();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        createThread();
    }

    public String getSDPath()
    {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
        if (sdCardExist)
        {
            sdDir = Environment.getExternalStorageDirectory();// 获取跟目录
        }
        return sdDir.toString();
    }

    public void createThread()
    {
        /***
         * 更新UI
         */
        final Handler handler = new Handler()
        {
            @Override
            public void handleMessage(Message msg)
            {
                switch (msg.what)
                {
                    case DOWN_OK:
                        // 下载完成，点击安装
                        Uri uri = Uri.fromFile(downFile);
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setDataAndType(uri, "application/vnd.android.package-archive");
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        break;
                    case DOWN_ERROR:
                        break;
                    default:
                        break;
                }
            }
        };

        final Message message = new Message();

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    long downloadSize = downloadUpdateFile(url, downFile.toString());
                    if (downloadSize > 0)
                    {
                        // 下载成功
                        message.what = DOWN_OK;
                        handler.sendMessage(message);
                    }
                } catch (Exception e)
                {
                    e.printStackTrace();
                    message.what = DOWN_ERROR;
                    handler.sendMessage(message);
                }
            }
        }).start();
    }

    /***
     * 下载文件
     *
     * @return
     * @throws MalformedURLException
     */
    public long downloadUpdateFile(String down_url, String file) throws Exception
    {
        int down_step = 5;// 提示step
        int downloadCount = 0;// 已经下载好的大小
        int updateCount = 0;// 已经上传的文件大小
        InputStream inputStream;
        OutputStream outputStream;

        URL url = new URL(down_url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setConnectTimeout(TIMEOUT);
        httpURLConnection.setReadTimeout(TIMEOUT);
        // 获取下载文件的size
        totalSize = httpURLConnection.getContentLength();
        if (httpURLConnection.getResponseCode() == 404)
        {
            throw new Exception("fail!");
        }
        inputStream = httpURLConnection.getInputStream();
        outputStream = new FileOutputStream(file, false);// 文件存在则覆盖掉
        byte buffer[] = new byte[1024];
        int readsize = 0;
        while ((readsize = inputStream.read(buffer)) != -1)
        {
            outputStream.write(buffer, 0, readsize);
            downloadCount += readsize;// 时时获取下载到的大小

            Message msg = new Message();
            msg.getData().putInt("size", downloadCount);
            mHandler.sendMessage(msg);

            /**
             * 每次增张5%
             */
//            if (updateCount == 0 || (downloadCount * 100 / totalSize - down_step) >= updateCount)
//            {
//                updateCount += down_step;
//                // 改变通知栏
//                // notification.setLatestEventInfo(this, "正在下载...", updateCount
//                // + "%" + "", pendingIntent);
////                contentView.setTextViewText(R.id.notificationPercent, updateCount + "%");
//                  contentView.setProgressBar(R.id.notificationProgress, 100, updateCount, false);
//
//                System.out.println("ssssssssssss" + updateCount);
//            }
        }
        if (httpURLConnection != null)
        {
            httpURLConnection.disconnect();
        }
        inputStream.close();
        outputStream.close();
        return downloadCount;
    }
}
