package com.example.wun.fivecrowdsourcing_runner.Presenter;

import android.util.Log;

import com.example.wun.fivecrowdsourcing_runner.Bean.OrderBean;
import com.example.wun.fivecrowdsourcing_runner.Bean.Runner;
import com.example.wun.fivecrowdsourcing_runner.DataConfig;
import com.example.wun.fivecrowdsourcing_runner.Fragment.DeliveryFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by WUN、 on 2018/3/22.
 */

public class DeliveryPresenter {
    private DeliveryFragment deliveryFragment;
    private List<OrderBean> list = new ArrayList<OrderBean>();
    public static String  URL=  DataConfig.URL;
    private String initservletName = DataConfig.SENDING_INIT;
    private String servletIP;

    public DeliveryPresenter(DeliveryFragment deliveryFragment) {
        this.deliveryFragment = deliveryFragment;
    }

//    public void sendMyLocation(Runner runner) throws IOException {
//        servletIP=URL+initservletName;
//        sendRequestWithOkHttp(servletIP,runner);
//    }

    private void sendRequestWithOkHttp(String servletIP, Runner runner) throws IOException {
        //转换成JSON格式
        RequestBody requestBody = new FormBody.Builder().add("runnerId", String.valueOf(runner.getRunnerid())).
                add("runnerLat", String.valueOf(runner.getLaititude())).add("runnerLong", String.valueOf(runner.getLongtitude())).build();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().
                url(servletIP).
                post(requestBody).
                build();
        Log.v("request",request.toString());
        Response response = client.newCall(request).execute();
//        jsonData = response.body().string().toString();
//        Log.v("jsonData",jsonData);
//        parseJSONWithJONObject(jsonData);
    }

    public void dispalyInitOrder(Runner runner) {
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
        deliveryFragment.dispalyOrder(list);
    }
}
