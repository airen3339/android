package com.example.wun.fivecrowdsourcing_runner.Presenter;

import android.util.Log;

import com.example.wun.fivecrowdsourcing_runner.Bean.OrderBean;
import com.example.wun.fivecrowdsourcing_runner.Bean.Runner;
import com.example.wun.fivecrowdsourcing_runner.DataConfig;
import com.example.wun.fivecrowdsourcing_runner.Fragment.TBDFragment;
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
 * Created by WUN on 2018/3/21.
 */

public class TBDPresenter {
    public static String  URL=  DataConfig.URL;
    private String initservletName=DataConfig.RunnerGetOrder;
    private String servletIP;
    private String jsonData;
    private List<OrderBean> list = new ArrayList<OrderBean>();
    private TBDFragment tbdFragment;
    public TBDPresenter(TBDFragment tbdFragment) {
        this.tbdFragment = tbdFragment;
    }

    public TBDPresenter() {
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
        try {
            list= gson.fromJson( jsonData, new TypeToken<List<OrderBean>>(){}.getType());
        } catch (Exception e) {
        }

        tbdFragment.dispalyOrder(list);
    }

    public void grap(OrderBean deliveryOrder, Runner runner, TBDFragment tbdFragment) {
        this.tbdFragment = tbdFragment;
        String servletName = "GrapOrderServlet";
        servletIP=URL+servletName ;
        sendRequestWithOkHttp(servletIP,runner,deliveryOrder);
    }

    //发送订单号和runnerId
    private void sendRequestWithOkHttp(String servletIP, Runner runner, OrderBean deliveryOrder) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    RequestBody requestBody = new FormBody.Builder().
                            add("delorderid", String.valueOf(deliveryOrder.getDelorderid())).add("runnerid", String.valueOf(runner.getRunnerid())).build();
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().
                            url(servletIP).
                            post(requestBody).
                            build();
                    Log.v("request",request.toString());
                    Response response = client.newCall(request).execute();
                    jsonData = response.body().string().toString();
                    Log.v("jsonData",jsonData);
                    parseJSONWithJONObject(jsonData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
