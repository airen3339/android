package com.example.wun.fivecrowdsourcing_runner.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.baoyachi.stepview.HorizontalStepView;
import com.baoyachi.stepview.bean.StepBean;
import com.example.wun.fivecrowdsourcing_runner.Bean.Runner;
import com.example.wun.fivecrowdsourcing_runner.Presenter.RunnerInfoPresenter;
import com.example.wun.fivecrowdsourcing_runner.R;
import com.example.wun.fivecrowdsourcing_runner.View.RunnerInfoView;

import java.util.ArrayList;
import java.util.List;

public class RunnerInfoActivity extends AppCompatActivity implements RunnerInfoView {
    private TextView title;
    private EditText name;
    private  EditText phone;;
    //private  EditText typeofgood;
   // private  TextView address;
   // private String addressname;
    private Button firststep;
    private TextView nextStep;
  // private  AddressInfo addressInfo;
    private Runner runner = new Runner();
    List<StepBean> stepsBeanList = new ArrayList<>();
    RunnerInfoPresenter runnerInfoPresenter =new RunnerInfoPresenter(this);

    public RunnerInfoActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runner_info);
        runner = (Runner) getIntent().getSerializableExtra("runner");
        //Log.v("rinfo runner id",runner.getRunnerId().toString());
        initData();
        initView();
    }
    private void initData() {
        StepBean stepBean0 = new StepBean("基本信息",0);
        StepBean stepBean1 = new StepBean("资质证书",-1);
        StepBean stepBean2 = new StepBean("身份信息",-1);
        StepBean stepBean3 = new StepBean("已完成",-1);
        stepsBeanList.add(stepBean0);
        stepsBeanList.add(stepBean1);
        stepsBeanList.add(stepBean2);
        stepsBeanList.add(stepBean3);

    }

    private void initView() {
        //获得pctvrf信息

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //address = findViewById(R.id.address);
        name = findViewById(R.id.runnername);
        phone = findViewById(R.id.runnerphone);
        phone.setText(runner.getPhone());
       // typeofgood = findViewById(R.id.type_good);
//        firststep = findViewById(R.id.first_step);
        title = findViewById(R.id.title);
        title.setText("跑腿人信息认证");
        nextStep=findViewById(R.id.next_step);
        nextStep.setText("下一步");
       /* ImageView iv = findViewById(R.id.right);
       iv.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               //选择地址界面
               gotomap();
           }
       });*/
       nextStep.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               //发送商户基本信息
             runnerInfoPresenter.sendRunnerInfo(String.valueOf(name.getText()),
                     String.valueOf(phone.getText()),  runner);

           }
       });
        HorizontalStepView setpview = (HorizontalStepView) findViewById(R.id.step_view1);
        setpview
                .setStepViewTexts(stepsBeanList)//总步骤
                .setTextSize(12)//set textSize
                .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(this, R.color.colorPrimary))//设置 StepsViewIndicator 完成线的颜色
                .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(this, R.color.colorAccent))//设置 StepsViewIndicator 未完成线的颜色
                .setStepViewComplectedTextColor(ContextCompat.getColor(this, R.color.darkorange))//设置 StepsView text 完成线的颜色
                .setStepViewUnComplectedTextColor(ContextCompat.getColor(this, R.color.colorPrimary))//设置 StepsView text 未完成线的颜色
                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(this, R.drawable.complted))//设置 StepsViewIndicator CompleteIcon
                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(this, R.drawable.default_icon))//设置 StepsViewIndicator DefaultIcon
                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(this, R.drawable.attention));//设置 StepsViewIndicator
    }

    @Override
    public void gotomap() {
        Intent intent = new Intent(RunnerInfoActivity.this, MapsActivity.class);
        startActivityForResult(intent,1);
    }

   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                if (resultCode==RESULT_OK) {
                    addressname =  (String)data.getSerializableExtra("addressInfo");
                    //addressname = addressInfo.getCity() + "-" + addressInfo.getDistrict() + "-" + addressInfo.getStreet();
                    address.setText(addressname);
                }
        }
    }*/

    @Override
    public void finishStep1(Runner runner) {
        Intent intent = new Intent(RunnerInfoActivity.this, Step2Activity.class);
        intent.putExtra("runner",runner);
        startActivity(intent);
    }
}
