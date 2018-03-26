package com.example.wun.fivecrowdsourcing_runner.Presenter;

import com.example.wun.fivecrowdsourcing_runner.Bean.Runner;
import com.example.wun.fivecrowdsourcing_runner.DataConfig;
import com.example.wun.fivecrowdsourcing_runner.Fragment.EnterFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/3/25.
 */

public class EnterPresenter {
    private EnterFragment enterFragment;
    private String servletName="RunnerStatus";
    private String servletIP;
    private String jsonData;
    private String result;
    private Runner runner;

    public EnterPresenter(EnterFragment enterFragment) {
        this.enterFragment = enterFragment;
    }

    public void getRunnerStatus(Runner runner) throws IOException, JSONException {
        this.runner= runner;
        servletIP = DataConfig.URL + servletName;
        sendRequestWithOkHttp(servletIP, runner);
    }

    private void sendRequestWithOkHttp(String servletIP, Runner runner) throws IOException, JSONException {
        RequestBody requestBody = new FormBody.Builder().add("runnerId",
                String.valueOf(runner.getRunnerid())).build();
        Request request = new Request.Builder().
                url(servletIP).
                post(requestBody).
                build();
        /**超时设置*/
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)//设置写的超时时间
                .build();
        Response response = client.newCall(request).execute();
        jsonData = response.body().string().toString();
        parseJSONWithJONObject(jsonData);
    }

    private void parseJSONWithJONObject(String jsonData) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonData);
        result=jsonObject.getString("result");
        if (result.equals("success")) {
            runner.setStatus(jsonObject.getString("status"));
            enterFragment.updateRunnerStatus(runner);
        }
    }
}
