package com.example.wun.fivecrowdsourcing_runner.Presenter;

import com.example.wun.fivecrowdsourcing_runner.Bean.OrderBean;
import com.example.wun.fivecrowdsourcing_runner.Bean.Runner;
import com.example.wun.fivecrowdsourcing_runner.DataConfig;
import com.example.wun.fivecrowdsourcing_runner.Fragment.CompletedFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by WUN、 on 2018/3/23.
 */

public class CompletedPresenter {
    private CompletedFragment completedFragment;
    private List<OrderBean> list = new ArrayList<OrderBean>();
    private String URL = DataConfig.URL;
    private String initservletName = "RunnerInitCompleted";
    private String servletIP;
    public CompletedPresenter(CompletedFragment completedFragment) {
        this.completedFragment = completedFragment;
    }

    public void displayInitOrder(Runner runner) {
        servletIP=URL+initservletName;
        sendRequestWithOkHttpInit(servletIP,runner);
    }

    private void sendRequestWithOkHttpInit(String servletIP, Runner runner) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //转换成JSON格式
                    Gson gson = new Gson();
                    String merchantdata = gson.toJson(runner);

                    RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8")
                            , merchantdata);
                    Request request = new Request.Builder().
                            url(servletIP).
                            post(requestBody).
                            build();

                    OkHttpClient client = new OkHttpClient();
                    Response response = client.newCall(request).execute();

                    String jsonData= response.body().string().toString();
                    parseJSONWithJONObject(jsonData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseJSONWithJONObject(String jsonData) {
        Gson gson = new Gson();
        list= gson.fromJson( jsonData, new TypeToken<List<OrderBean>>(){}.getType());
        completedFragment.dispalyOrder(list);
    }
}
