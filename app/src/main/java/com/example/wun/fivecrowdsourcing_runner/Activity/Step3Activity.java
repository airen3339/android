package com.example.wun.fivecrowdsourcing_runner.Activity;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyachi.stepview.HorizontalStepView;
import com.baoyachi.stepview.bean.StepBean;
import com.example.wun.fivecrowdsourcing_runner.Bean.Runner;
import com.example.wun.fivecrowdsourcing_runner.DataConfig;
import com.example.wun.fivecrowdsourcing_runner.Presenter.Step3Presenter;
import com.example.wun.fivecrowdsourcing_runner.R;
import com.example.wun.fivecrowdsourcing_runner.Utils.UploadUtil;
import com.example.wun.fivecrowdsourcing_runner.View.Step3View;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Step3Activity extends AppCompatActivity implements Step3View {
    private Runner runner = new Runner();
    TextView title;
    ImageView healthcert;
    Button click_healthcert;
    //EditText name_edit;
    EditText idcardnumber_edit;

    String healthcertphoto;
    private TextView nextStep;
    List<StepBean> stepsBeanList = new ArrayList<>();
    Step3Presenter step3Presenter = new Step3Presenter(Step3Activity.this);

    private static final int CHOOSE_HEALTHCERT =3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step3);
        initData();
        initView();
    }
    private void initData() {
        StepBean stepBean0 = new StepBean("????????????",1);
        StepBean stepBean1 = new StepBean("????????????",1);
        StepBean stepBean2 = new StepBean("????????????",0);
        StepBean stepBean3 = new StepBean("?????????",-1);
        stepsBeanList.add(stepBean0);
        stepsBeanList.add(stepBean1);
        stepsBeanList.add(stepBean2);
        stepsBeanList.add(stepBean3);

    }

    private void initView() {
        //??????????????????
        runner = (Runner) getIntent().getSerializableExtra("runner");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        title=findViewById(R.id.title);
        title.setText("?????????");
        healthcert = findViewById(R.id.healthcert);
        click_healthcert = findViewById(R.id.click_idcard);
        //???????????????
        click_healthcert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAlbum(CHOOSE_HEALTHCERT);
            }
        });
       // name_edit = findViewById(R.id.name_edit);
        idcardnumber_edit = findViewById(R.id.idcardnumber_edit);
        nextStep = findViewById(R.id.next_step);
        nextStep.setText("?????????");
        nextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String requestURL = DataConfig.URL + DataConfig.UploadImage;
                //???????????????
                File healthcertfile= new File(healthcertphoto);
                UploadUtil.uploadFile(healthcertfile, requestURL, runner);
                //?????????????????????
                step3Presenter.sendImage(String.valueOf(idcardnumber_edit.getText()), runner,healthcertfile.getName());
            }
        });
        HorizontalStepView setpview = (HorizontalStepView) findViewById(R.id.step_view1);
        setpview
                .setStepViewTexts(stepsBeanList)//?????????
                .setTextSize(12)//set textSize
                .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(this, R.color.colorPrimary))//?????? StepsViewIndicator ??????????????????
                .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(this, R.color.colorAccent))//?????? StepsViewIndicator ?????????????????????
                .setStepViewComplectedTextColor(ContextCompat.getColor(this, R.color.darkorange))//?????? StepsView text ??????????????????
                .setStepViewUnComplectedTextColor(ContextCompat.getColor(this, R.color.colorPrimary))//?????? StepsView text ?????????????????????
                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(this, R.drawable.complted))//?????? StepsViewIndicator CompleteIcon
                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(this, R.drawable.default_icon))//?????? StepsViewIndicator DefaultIcon
                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(this, R.drawable.attention));//?????? StepsViewIndicator

    }

    private void openAlbum(int FLAG) {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,FLAG);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CHOOSE_HEALTHCERT:
                if (resultCode == RESULT_OK) {
                    //???????????????????????????
                    if (Build.VERSION.SDK_INT >= 19) {
                        handleImageOnKitKat(data, CHOOSE_HEALTHCERT);
                    }
                }
                break;
            default:
                break;
        }
    }
    @TargetApi(19)
    private void handleImageOnKitKat(Intent data, int FLAG) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this, uri)) {
            //?????????document?????????Uri,?????????doucument id??????
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];//???????????????id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            //?????????content?????????Uri,???????????????????????????
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            //?????????file?????????Uri???????????????????????????
            imagePath = uri.getPath();
        }
        displayImge(imagePath,FLAG);//??????????????????????????????
    }

    private void displayImge(String imagePath, int FLAG) {
        Bitmap bitmap;
        if (imagePath != null) {
            bitmap=zoomIn(imagePath);//????????????
            switch (FLAG) {
                case CHOOSE_HEALTHCERT:
                   healthcertphoto = imagePath;
                    healthcert.setImageBitmap(bitmap);
                    break;
                default:
                    break;
            }
        } else {
            Toast.makeText(this, "??????????????????!", Toast.LENGTH_SHORT).show();
        }

    }

    private Bitmap zoomIn(String imagePath) {
        int iw, ih, vw, vh;//???????????????????????? imageView???????????????
        Bitmap bitmap;
        BitmapFactory.Options option = new BitmapFactory.Options();
        option.inJustDecodeBounds = true;//??????????????????????????????????????????????????????????????????
        BitmapFactory.decodeFile(imagePath, option);

        iw = option.outWidth;
        ih = option.outHeight;
        vw = healthcert.getWidth();
        vh = healthcert.getHeight();//???????????????????????????

        int scaleFactor = Math.min(iw / vw, ih / vh);//??????????????????

        option.inJustDecodeBounds = false;//????????????
        option.inSampleSize = scaleFactor;//??????????????????

        bitmap = BitmapFactory.decodeFile(imagePath, option);
        return bitmap;
    }

    private String getImagePath(Uri externalContentUri, String selection) {
        String path = null;
        //??????Uri???selection??????????????????????????????
        Cursor cursor = getContentResolver().query(externalContentUri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    @Override
    public void finishStep3(Runner runner) {
        //Toast.makeText(Step3Activity.this, "???????????????????????????????????????????????????!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Step3Activity.this, MainActivity.class);
        runner.setStatus("1");
        intent.putExtra("runner",runner);
        startActivity(intent);
    }
}
