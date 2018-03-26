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
    private SuperTextView userBalance;
    private int type;
    private static final int CHOOSE_IDCARDOPPO = 1;
    private static final int CHOOSE_IDCARDINHAND =2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getIntent().getIntExtra("type", 0);
        switch (type) {
            case 0:
                setContentView(R.layout.user_center);
                break;
            case 1:
                setContentView(R.layout.wallet_layout);
                break;
        }
        runner = (Runner) getIntent().getSerializableExtra("runner");
        initView(type);
        initData(type);

    }

    private void initView(int type) {
        switch(type){
            case 0:
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

                ((SuperTextView)findViewById(R.id.my_wallet)).setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener() {
                    @Override
                    public void onClickListener(SuperTextView superTextView) {
                        String string = "前往我的钱包";
                        Toast.makeText(UserCenterActivity.this, string, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(UserCenterActivity.this,UserCenterActivity.class);
                        intent.putExtra("type",1);
                        intent.putExtra("runner", runner);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

                        finish();
                        startActivity(intent);
                    }
                });
                break;
            case 1:
                title=findViewById(R.id.title);
                title.setText("我的钱包");
                userBalance = (SuperTextView) findViewById(R.id.user_balance);
                nextStep=findViewById(R.id.next_step);
                nextStep.setText("明细");
//                nextStep.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        finish();
//                    }
//                });

        }


    }

    private void initData(int type) {
        switch (type) {
            case 0:
                userInfo.setLeftString(runner.getName());
                userInfo.setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener() {
                    @Override
                    public void onClickListener(SuperTextView superTextView) {
                        String string = "整个item的点击事件";
                        Toast.makeText(UserCenterActivity.this, string, Toast.LENGTH_SHORT).show();
                    }
                }).setRightTvClickListener(new SuperTextView.OnRightTvClickListener() {
                    @Override
                    public void onClickListener() {
                        String string = "前往认证";
                        Toast.makeText(UserCenterActivity.this, string, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(UserCenterActivity.this, RunnerInfoActivity.class);
                        intent.putExtra("runner", runner);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                    }
                });
                break;
            case 1:
                userBalance.setLeftBottomString(runner.getBalance().toString());
                break;
        }
    }
}
