package com.example.wun.fivecrowdsourcing_runner.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wun.fivecrowdsourcing_runner.DataConfig;
import com.example.wun.fivecrowdsourcing_runner.R;
import com.example.wun.fivecrowdsourcing_runner.Utils.RegisterThread;
import com.example.wun.fivecrowdsourcing_runner.View.RegisterView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  商户注册：接收用户的手机号和密码，并传递给服务器
 */
public class RegisterActivity extends AppCompatActivity implements RegisterView {
    //ServletIP
    /**
     * 这里要添加runnerRegister.do
     */
    private String servletIP= DataConfig.URL+"runnerRegister.do";

    private EditText phone;
    private EditText pwd;
    private EditText repwd;
    private TextView register;
   private TextView gotologin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        phone = findViewById(R.id.input_phone);
        pwd = findViewById(R.id.input_password);
        repwd = findViewById(R.id.input_repassword);

        gotologin = findViewById(R.id.link_login);
        gotologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //判断输入有效性
                boolean result = validate(phone.getText().toString(), pwd.getText().toString(), repwd.getText().toString());
                if (result) {
                    new RegisterThread(servletIP, phone.getText().toString(), pwd.getText().toString(),RegisterActivity.this).start();
                }
            }
        });

    }

    private boolean validate(String s, String s1, String s2) {
        /**
         * 移动号段正则表达式
         */
        String pat1 = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern pattern1 = Pattern.compile(pat1);
        Matcher match1 = pattern1.matcher(s);
        boolean isMatch1 = match1.matches();
        if(s.length()!= 11||!isMatch1)
        {
            phone.setError("请输入有效的手机号码!");
            return false;
        }
        if (s1.equals("")) {
            pwd.setError("密码不能为空!");
            return false;
        }
        if (s2.equals("")) {
            repwd.setError("重复密码不能为空!");
            return false;
        }
        if (!s1.equals(s2)) {
            repwd.setError("两次密码不一致!");
            return false;
        }
        return true;
    }

    @Override
    public void onSuccess() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
