package com.qx.www.shuang_la_master.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.actionsheet.ActionSheet;
import com.qx.www.shuang_la_master.BaseActivity;
import com.qx.www.shuang_la_master.R;
import com.qx.www.shuang_la_master.galleryfinal.listener.GlidePauseOnScrollListener;
import com.qx.www.shuang_la_master.galleryfinal.loader.GlideImageLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
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

    private List<PhotoInfo> mPhotoList;
    private boolean muti;
    private final int REQUEST_CODE_CAMERA = 1000;
    private final int REQUEST_CODE_GALLERY = 1001;
    private final int REQUEST_CODE_CROP = 1002;
    private final int REQUEST_CODE_EDIT = 1003;

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
    }

    @OnClick({R.id.id_ziliao_linear_imgs, R.id.id_ziliao_sex, R.id.id_ziliao_brith, R.id.id_ziliao_job})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.id_ziliao_linear_imgs:

                showPopupWin();
                break;
            case R.id.id_ziliao_sex:
                break;
            case R.id.id_ziliao_brith:
                break;
            case R.id.id_ziliao_job:
                break;
        }
    }

    ThemeConfig themeConfig = null;

    private void showPopupWin()
    {
        FunctionConfig.Builder functionConfigBuilder = new FunctionConfig.Builder();
        cn.finalteam.galleryfinal.ImageLoader imageLoader;
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

            
            System.out.println("-------------path-------------"+mPhotoList.get(0).getPhotoPath());
        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg)
        {
            Toast.makeText(ZiLiaoActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
        }
    };


}
