package com.example.wun.fivecrowdsourcing_runner.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.allen.library.SuperTextView;
import com.example.wun.fivecrowdsourcing_runner.Bean.Runner;
import com.example.wun.fivecrowdsourcing_runner.R;

/**
 * Created by WUN、 on 2018/3/25.
 */

public class UserCenterActivity extends AppCompatActivity {
    private TextView title;
    private TextView backStep;
    private TextView nextStep;
    private Runner runner = new Runner();
    private SuperTextView userInfo;

    private static final int CHOOSE_IDCARDOPPO = 1;
    private static final int CHOOSE_IDCARDINHAND =2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_center);
        runner = (Runner) getIntent().getSerializableExtra("runner");
        initView();
        initData();

    }

    private void initView() {
        title=findViewById(R.id.title);
        title.setText("个人中心");
        userInfo = (SuperTextView) findViewById(R.id.user_info);
        backStep=findViewById(R.id.back_step);
        backStep.setText("返回");
        backStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });

    }

    private void initData() {
        userInfo.setLeftString(runner.getName());
        userInfo.setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener() {
            @Override
            public void onClickListener(SuperTextView superTextView) {
               String  string = "整个item的点击事件";
                Toast.makeText(UserCenterActivity.this, string, Toast.LENGTH_SHORT).show();
            }
        }).setRightTvClickListener(new SuperTextView.OnRightTvClickListener() {
            @Override
            public void onClickListener() {
               String  string = "前往认证";
                Toast.makeText(UserCenterActivity.this, string, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(UserCenterActivity.this, RunnerInfoActivity.class);
                intent.putExtra("runner",runner);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
    }
}
