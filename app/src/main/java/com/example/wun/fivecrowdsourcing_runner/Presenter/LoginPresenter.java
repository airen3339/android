package com.example.wun.fivecrowdsourcing_runner.Presenter;


import android.util.Log;

import com.example.wun.fivecrowdsourcing_runner.Activity.LoginActivity;
import com.example.wun.fivecrowdsourcing_runner.Bean.Runner;
import com.example.wun.fivecrowdsourcing_runner.DataConfig;
import com.example.wun.fivecrowdsourcing_runner.View.LoginView;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/2/13.
 */

public class LoginPresenter {
    private String servletName= DataConfig.URL_Login;
    private String servletIP;
    private String result;
    private String jsonData;
    private LoginView loginView;//loginView接口

    private Runner runner =new Runner();

    public LoginPresenter(LoginActivity loginView) {
        this.loginView = loginView;
    }


    public void Login(String phone,String password,String url)throws Exception{
//        //特殊通道，当服务器不行时直接登陆
//        if(DataConfig.debugFlag==true) {
//            Long lng=Long.parseLong("11");
//            runner.setName("wch");
//            runner.setPhone("177740");
//            runner.setRunnerId(lng);
//
//            loginView.onSuccess(runner);
//
//        }
       servletIP=url+servletName;
       Log.v("servlet",servletIP);
       Log.v("phone",phone);
        Log.v("ppassword",password);
        try {
            sendRequestWithOkHttp(servletIP,phone,password);
        } catch ( SocketTimeoutException e) {
            e.printStackTrace();
            throw new SocketTimeoutException();
        }
    }

    private void sendRequestWithOkHttp(final String servletIP, final String phone,final String password) throws Exception {
        try {
            RequestBody requestBody = new FormBody.Builder().
                    add("phone", phone).add("password", password).build();

            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)//设置写的超时时间
                    .build();

            Request request = new Request.Builder().
                    url(servletIP).
                    post(requestBody).
                    build();
            Log.v("request",request.toString());
            Response response = client.newCall(request).execute();
            jsonData = response.body().string().toString();
            Log.v("jsonData",jsonData);
            parseJSONWithJONObject(jsonData);
        } catch ( SocketTimeoutException e) {
            e.printStackTrace();
            if(e instanceof  SocketTimeoutException ) {
                throw new SocketTimeoutException();
            }

        }
    }


    private void parseJSONWithJONObject(String jsonData) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            result=jsonObject.getString("result");
            Log.v("result",result);
            if(result.equals("success")){
                runner.setPhone(jsonObject.getString("phone"));
                runner.setRunnerid(jsonObject.getLong("runnerid"));
                runner.setStatus(jsonObject.getString("status"));
                if (!jsonObject.getString("status").equals("0")) {
                    runner.setName(jsonObject.getString("name"));
                    runner.setBalance(jsonObject.getDouble("balance"));
                    runner.setIntegral(jsonObject.getInt("integral"));
                }
                loginView.onSuccess(runner);
            }else
                loginView.onFailed();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
