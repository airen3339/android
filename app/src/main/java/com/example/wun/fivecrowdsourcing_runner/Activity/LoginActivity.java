package com.example.wun.fivecrowdsourcing_runner.Activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wun.fivecrowdsourcing_runner.Bean.Runner;
import com.example.wun.fivecrowdsourcing_runner.DataConfig;
import com.example.wun.fivecrowdsourcing_runner.Presenter.LoginPresenter;
import com.example.wun.fivecrowdsourcing_runner.R;
import com.example.wun.fivecrowdsourcing_runner.View.JellyInterpolator;
import com.example.wun.fivecrowdsourcing_runner.View.LoginView;

import java.net.SocketTimeoutException;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import cn.smssdk.EventHandler;
//import cn.smssdk.SMSSDK;
//import cn.smssdk.gui.RegisterPage;

//import com.example.wun.fivecrowdsourcing_runner.Activity.MainActivity;


public class LoginActivity extends AppCompatActivity implements LoginView {
    public static String URL = DataConfig.URL;
    private TextView login;
    private LoginPresenter loginPresenter = new LoginPresenter(this);
    private EditText phone;
    private EditText password;
    private View progress;
    private TextView gotoRegister;
    private View mInputLayout;

    private float mWidth, mHeight;

    private LinearLayout mPhone, mPsw;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        MobSDK.init(this);
//        sendCode(getApplicationContext());
        initView();
    }

    /**mob??????
     */
//    public void sendCode(Context context) {
//        RegisterPage page = new RegisterPage();
//        page.setRegisterCallback(new EventHandler() {
//            public void afterEvent(int event, int result, Object data) {
//                if (result == SMSSDK.RESULT_COMPLETE) {
//                    // ?????????????????????
//                    HashMap<String,Object> phoneMap = (HashMap<String, Object>) data;
//                    String country = (String) phoneMap.get("country"); // ?????????????????????86???
//                    String phone = (String) phoneMap.get("phone"); // ?????????????????????13800138000???
//                    // TODO ??????????????????????????????????????????????????????
//                    Toast.makeText(context,country+' '+phone,Toast.LENGTH_SHORT).show();
//
//                } else{
//                    // TODO ?????????????????????
//                    Toast.makeText(context,"none",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//        page.show(context);
//    }

    /**
     * ????????????????????????
     *
     * @param view ??????
     * @param w    ???
     * @param h    ???
     */
    private void inputAnimator(final View view, float w, float h) {

        AnimatorSet set = new AnimatorSet();

        ValueAnimator animator = ValueAnimator.ofFloat(0, w);
        animator.addUpdateListener(animation -> {
            float value = (Float) animation.getAnimatedValue();
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view
                    .getLayoutParams();
            params.leftMargin = (int) value;
            params.rightMargin = (int) value;
            view.setLayoutParams(params);
        });

        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mInputLayout,
                "scaleX", 1f, 0.5f);
        set.setDuration(1000);
        set.setInterpolator(new AccelerateDecelerateInterpolator());
        set.playTogether(animator, animator2);
        set.start();
        set.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                /**
                 * ?????????????????????????????????????????????????????????????????????
                 */
                progress.setVisibility(View.VISIBLE);
                progressAnimator(progress);
                mInputLayout.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }
        });

    }

    /**
     * ??????????????????
     *
     * @param view
     */
    private void progressAnimator(final View view) {
        PropertyValuesHolder animator = PropertyValuesHolder.ofFloat("scaleX",
                0.5f, 1f);
        PropertyValuesHolder animator2 = PropertyValuesHolder.ofFloat("scaleY",
                0.5f, 1f);
        ObjectAnimator animator3 = ObjectAnimator.ofPropertyValuesHolder(view,
                animator, animator2);
        animator3.setDuration(1000);
        animator3.setInterpolator(new JellyInterpolator());
        animator3.start();

    }


    private void initView() {

        progress = findViewById(R.id.layout_progress);
        mInputLayout = findViewById(R.id.input_layout);
        mPhone = findViewById(R.id.input_layout_phone);
        mPsw = findViewById(R.id.input_layout_psw);
        login = findViewById(R.id.login);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);

        login.setOnClickListener(view -> {
            /**???????????????*/
            if (TextUtils.isEmpty(phone.getText()) || TextUtils.isEmpty(password.getText())) {
                Toast.makeText(this, "?????????????????????????????????", Toast.LENGTH_SHORT).show();
                return;
            }
            // ???????????????????????????
            mWidth = login.getMeasuredWidth();
            mHeight = login.getMeasuredHeight();
            // ???????????????
            mPhone.setVisibility(View.INVISIBLE);
            mPsw.setVisibility(View.INVISIBLE);
            /**????????????*/
            login.setEnabled(false);
            findViewById(R.id.main_title).setVisibility(View.INVISIBLE);
            login.setText("????????????...");
            inputAnimator(mInputLayout, mWidth, mHeight);
            Executors.newSingleThreadExecutor().submit(() -> {
                try {
                    Thread.sleep(3000);//??????3???
                    loginPresenter.Login(String.valueOf(phone.getText()), password.getText().toString(), URL);
                } catch (Exception e) {
                    if (e instanceof SocketTimeoutException) {
                        runOnUiThread(() -> {
                            Toast.makeText(this, "????????????!", Toast.LENGTH_SHORT).show();
                            progress.setVisibility(View.GONE);
                            mInputLayout.setVisibility(View.VISIBLE);
                            mPhone.setVisibility(View.VISIBLE);
                            mPsw.setVisibility(View.VISIBLE);
                            login.setEnabled(true);
                            login.setText("??????");
                            findViewById(R.id.main_title).setVisibility(View.VISIBLE);

                            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) mInputLayout.getLayoutParams();
                            params.leftMargin = 0;
                            params.rightMargin = 0;
                            mInputLayout.setLayoutParams(params);


                            ObjectAnimator animator2 = ObjectAnimator.ofFloat(mInputLayout, "scaleX", 0.5f, 1f);
                            animator2.setDuration(500);
                            animator2.setInterpolator(new AccelerateDecelerateInterpolator());
                            animator2.start();
                        });
                    }
                    e.printStackTrace();
                }
            });

        });
        gotoRegister = findViewById(R.id.gotoRegister);
        gotoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
    }

    private boolean validate(String s, String s1) {
        /**
         * ???????????????????????????
         */
        String pat1 = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern pattern1 = Pattern.compile(pat1);
        Matcher match1 = pattern1.matcher(s);
        boolean isMatch1 = match1.matches();
        if (s.length() != 11 || !isMatch1) {
            phone.setError("??????????????????????????????!");
            return false;
        }
        if (s1.equals("")) {
            password.setError("??????????????????!");
            return false;
        }

        return true;
    }

    @Override
    public void onSuccess(Runner runner) {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        Log.v("runner phone", runner.getPhone());
        intent.putExtra("runner", runner);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    @Override
    public void onFailed() {
        runOnUiThread(() -> {
            Toast.makeText(this, "???????????????", Toast.LENGTH_SHORT).show();

            progress.setVisibility(View.GONE);
            mInputLayout.setVisibility(View.VISIBLE);
            mPhone.setVisibility(View.VISIBLE);
            mPsw.setVisibility(View.VISIBLE);
            login.setEnabled(true);
            login.setText("??????");
            findViewById(R.id.main_title).setVisibility(View.VISIBLE);

            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) mInputLayout.getLayoutParams();
            params.leftMargin = 0;
            params.rightMargin = 0;
            mInputLayout.setLayoutParams(params);


            ObjectAnimator animator2 = ObjectAnimator.ofFloat(mInputLayout, "scaleX", 0.5f, 1f);
            animator2.setDuration(500);
            animator2.setInterpolator(new AccelerateDecelerateInterpolator());
            animator2.start();
        });
    }
}
