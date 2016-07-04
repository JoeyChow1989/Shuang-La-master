package com.qx.www.shuang_la_master.activity;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.actionsheet.ActionSheet;
import com.bigkoo.pickerview.TimePickerView;
import com.qx.www.shuang_la_master.BaseActivity;
import com.qx.www.shuang_la_master.R;
import com.qx.www.shuang_la_master.galleryfinal.listener.GlidePauseOnScrollListener;
import com.qx.www.shuang_la_master.galleryfinal.loader.GlideImageLoader;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.PauseOnScrollListener;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.finalteam.galleryfinal.model.PhotoInfo;

public class ZiLiaoActivity extends BaseActivity
{

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.id_ziliao_edit)
    AppCompatEditText idZiliaoEdit;
    @Bind(R.id.id_ziliao_nickname)
    LinearLayout idZiliaoNickname;
    @Bind(R.id.id_ziliao_imgs)
    ImageView idZiliaoImgs;
    @Bind(R.id.id_ziliao_linear_imgs)
    LinearLayout idZiliaoLinearImgs;
    @Bind(R.id.id_ziliao_headimg)
    LinearLayout idZiliaoHeadimg;
    @Bind(R.id.id_ziliao_sexup)
    TextView idZiliaoSexup;
    @Bind(R.id.id_ziliao_sex)
    LinearLayout idZiliaoSex;
    @Bind(R.id.id_ziliao_brithup)
    TextView idZiliaoBrithup;
    @Bind(R.id.id_ziliao_brith)
    LinearLayout idZiliaoBrith;
    @Bind(R.id.id_ziliao_jobup)
    TextView idZiliaoJobup;
    @Bind(R.id.id_ziliao_job)
    LinearLayout idZiliaoJob;
    @Bind(R.id.id_ziliao_sendup)
    Button idZiliaoSendup;


    //图片选择
    private List<PhotoInfo> mPhotoList;
    private boolean muti;
    ThemeConfig themeConfig = null;
    private final int REQUEST_CODE_CAMERA = 1000;
    private final int REQUEST_CODE_GALLERY = 1001;

    //选择日期
    private TimePickerView pvTime;

    //sex
    RadioButton idSexRtNan;
    RadioButton idSexRtNv;

    //职业
    RadioButton idZhiyeRtStudent;
    RadioButton idZhiyeRtTeacher;
    RadioButton idZhiyeRtWorker;
    RadioButton idZhiyeRtBoss;
    RadioButton idZhiyeRtGongwuyuan;
    RadioButton idZhiyeRtFree;
    RadioButton idZhiyeRtBackup;
    RadioButton idZhiyeRtOther;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zi_liao);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView()
    {
        toolbar.setTitle("资料");
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

    }

    @Override
    public void initData()
    {
        mPhotoList = new ArrayList<>();
        //时间选择器
        pvTime = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
        //设置标题
        pvTime.setTitle("选择日期");
        //控制时间范围
        Calendar calendar = Calendar.getInstance();
        pvTime.setRange(calendar.get(Calendar.YEAR) - 100, calendar.get(Calendar.YEAR));
        pvTime.setTime(new Date());
        //设置是否循环
        pvTime.setCyclic(false);
        //设置是否可以关闭
        pvTime.setCancelable(true);
        //设置选择监听
        pvTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener()
        {
            @Override
            public void onTimeSelect(Date date)
            {
                idZiliaoBrithup.setText(getTime(date));
                //Toast.makeText(ZiLiaoActivity.this, getTime(date), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick({R.id.id_ziliao_linear_imgs, R.id.id_ziliao_sex, R.id.id_ziliao_brith, R.id.id_ziliao_job})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.id_ziliao_linear_imgs:
                showPopupWinUpLoadPic();
                break;
            case R.id.id_ziliao_sex:
                showSexChoicePopup();
                break;
            case R.id.id_ziliao_brith:
                showPickBrith();
                break;
            case R.id.id_ziliao_job:
                showZhiyePopup();
                break;
        }
    }

    private void showZhiyePopup()
    {
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setCancelable(false);
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setContentView(R.layout.dialog_zhiye_choice);

        idZhiyeRtStudent = (RadioButton) window.findViewById(R.id.id_zhiye_rt_student);
        idZhiyeRtTeacher = (RadioButton) window.findViewById(R.id.id_zhiye_rt_teacher);
        idZhiyeRtWorker = (RadioButton) window.findViewById(R.id.id_zhiye_rt_worker);
        idZhiyeRtBoss = (RadioButton) window.findViewById(R.id.id_zhiye_rt_boss);
        idZhiyeRtGongwuyuan = (RadioButton) window.findViewById(R.id.id_zhiye_rt_gongwuyuan);
        idZhiyeRtFree = (RadioButton) window.findViewById(R.id.id_zhiye_rt_free);
        idZhiyeRtBackup = (RadioButton) window.findViewById(R.id.id_zhiye_rt_backup);
        idZhiyeRtOther = (RadioButton) window.findViewById(R.id.id_zhiye_rt_other);

        if (idZhiyeRtStudent.getText().equals("学生"))
        {
            idZhiyeRtStudent.setChecked(true);
        }

        if (idZhiyeRtTeacher.getText().equals("教师"))
        {
            idZhiyeRtTeacher.setChecked(true);
        }

        if (idZhiyeRtWorker.getText().equals("上班族"))
        {
            idZhiyeRtWorker.setChecked(true);
        }

        if (idZhiyeRtBoss.getText().equals("老板"))
        {
            idZhiyeRtBoss.setChecked(true);
        }

        if (idZhiyeRtGongwuyuan.getText().equals("公务员"))
        {
            idZhiyeRtGongwuyuan.setChecked(true);
        }

        if (idZhiyeRtFree.getText().equals("自由"))
        {
            idZhiyeRtFree.setChecked(true);
        }

        if (idZhiyeRtBackup.getText().equals("退休"))
        {
            idZhiyeRtBackup.setChecked(true);
        }

        if (idZhiyeRtOther.getText().equals("其他"))
        {
            idZhiyeRtOther.setChecked(true);
        }

        idZhiyeRtStudent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    idZiliaoJobup.setText("学生");
                    alertDialog.dismiss();
                }
            }
        });

        idZhiyeRtTeacher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    idZiliaoJobup.setText("教师");
                    alertDialog.dismiss();
                }
            }
        });

        idZhiyeRtWorker.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    idZiliaoJobup.setText("上班族");
                    alertDialog.dismiss();
                }
            }
        });

        idZhiyeRtBoss.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    idZiliaoJobup.setText("老板");
                    alertDialog.dismiss();
                }
            }
        });


        idZhiyeRtGongwuyuan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    idZiliaoJobup.setText("公务员");
                    alertDialog.dismiss();
                }
            }
        });

        idZhiyeRtFree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    idZiliaoJobup.setText("自由");
                    alertDialog.dismiss();
                }
            }
        });

        idZhiyeRtBackup.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    idZiliaoJobup.setText("退休");
                    alertDialog.dismiss();
                }
            }
        });

        idZhiyeRtOther.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    idZiliaoJobup.setText("其他");
                    alertDialog.dismiss();
                }
            }
        });
    }

    private void showSexChoicePopup()
    {
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setCancelable(false);
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setContentView(R.layout.dialog_sex_choice);

        idSexRtNan = (RadioButton) window.findViewById(R.id.id_sex_rt_nan);
        idSexRtNv = (RadioButton) window.findViewById(R.id.id_sex_rt_nv);
        if (idZiliaoSexup.getText().equals("男"))
        {
            idSexRtNan.setChecked(true);
        }

        if (idZiliaoSexup.getText().equals("女"))
        {
            idSexRtNv.setChecked(true);
        }


        idSexRtNan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    idZiliaoSexup.setText("男");
                    alertDialog.dismiss();
                }
            }
        });

        idSexRtNv.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    idZiliaoSexup.setText("女");
                    alertDialog.dismiss();
                }
            }
        });
    }

    private void showPickBrith()
    {
        //显示
        pvTime.show();
    }

    private void showPopupWinUpLoadPic()
    {
        FunctionConfig.Builder functionConfigBuilder = new FunctionConfig.Builder();
        ImageLoader imageLoader;
        PauseOnScrollListener pauseOnScrollListener = null;
        imageLoader = new GlideImageLoader();
        pauseOnScrollListener = new GlidePauseOnScrollListener(false, true);

        muti = false;
        int maxSize = 1;
        functionConfigBuilder.setMutiSelectMaxSize(maxSize);

        themeConfig = ThemeConfig.DEFAULT;

        final boolean mutiSelect = muti;

        functionConfigBuilder.setEnableEdit(true);
        functionConfigBuilder.setRotateReplaceSource(true);
        functionConfigBuilder.setEnableCrop(true);
        functionConfigBuilder.setCropSquare(true);
        functionConfigBuilder.setCropReplaceSource(true);
        functionConfigBuilder.setForceCrop(true);
        functionConfigBuilder.setForceCropEdit(true);
        functionConfigBuilder.setEnableCamera(true);
        functionConfigBuilder.setEnablePreview(true);
        functionConfigBuilder.setSelected(mPhotoList);//添加过滤集合
        final FunctionConfig functionConfig = functionConfigBuilder.build();

        CoreConfig coreConfig = new CoreConfig.Builder(ZiLiaoActivity.this, imageLoader, themeConfig)
                .setFunctionConfig(functionConfig)
                .setPauseOnScrollListener(pauseOnScrollListener)
                .setNoAnimcation(true)
                .build();
        GalleryFinal.init(coreConfig);

        ActionSheet.createBuilder(ZiLiaoActivity.this, getSupportFragmentManager())
                .setCancelButtonTitle("取消(Cancel)").setOtherButtonTitles("打开相册(Open Gallery)", "拍照(Camera)")
                .setCancelableOnTouchOutside(true)
                .setListener(new ActionSheet.ActionSheetListener()
                             {
                                 @Override
                                 public void onDismiss(ActionSheet actionSheet, boolean isCancel)
                                 {

                                 }

                                 @Override
                                 public void onOtherButtonClick(ActionSheet actionSheet, int index)
                                 {
                                     String path = "/sdcard/pk1-2.jpg";
                                     switch (index)
                                     {
                                         case 0:
                                             System.out.println("----------------------------" + mutiSelect);
                                             if (mutiSelect)
                                             {
                                                 GalleryFinal.openGalleryMuti(REQUEST_CODE_GALLERY, functionConfig, mOnHanlderResultCallback);
                                             } else
                                             {
                                                 GalleryFinal.openGallerySingle(REQUEST_CODE_GALLERY, functionConfig, mOnHanlderResultCallback);
                                             }
                                             break;
                                         case 1:
                                             GalleryFinal.openCamera(REQUEST_CODE_CAMERA, functionConfig, mOnHanlderResultCallback);
                                             break;
                                         default:
                                             break;
                                     }
                                 }
                             }
                ).show();
    }

    private GalleryFinal.OnHanlderResultCallback mOnHanlderResultCallback = new GalleryFinal.OnHanlderResultCallback()
    {
        @Override
        public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList)
        {
            if (resultList != null)
            {
                mPhotoList.addAll(resultList);
            }

            // TODO: 2016/6/30  图片上传

            System.out.println("-------------path-------------" + mPhotoList.get(0).getPhotoPath());
        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg)
        {
            Toast.makeText(ZiLiaoActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
        }
    };

    public static String getTime(Date date)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

}
