package com.example.wun.fivecrowdsourcing_runner.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.location.LocationClientOption;
import com.example.wun.fivecrowdsourcing_runner.Adapter.DeliveryAdpater;
import com.example.wun.fivecrowdsourcing_runner.Bean.OrderBean;
import com.example.wun.fivecrowdsourcing_runner.Bean.Runner;
import com.example.wun.fivecrowdsourcing_runner.Presenter.DeliveryPresenter;
import com.example.wun.fivecrowdsourcing_runner.R;

import java.util.List;

/**
 * Created by WUN on 2018/3/8.
 */
@SuppressLint("ValidFragment")
public class DeliveryFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    private Runner runner;
    protected SwipeRefreshLayout mRefreshLayout;
    protected RecyclerView mRecyclerView;
//    public LocationClient mLocationClient;
    private DeliveryPresenter deliveryPresenter=new DeliveryPresenter(this);

    @SuppressLint("ValidFragment")
    public DeliveryFragment(Runner runner) {
        this.runner = runner;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mLocationClient = new LocationClient(getContext());
//        mLocationClient.registerLocationListener(new MyLocationListener());
//        requestLocation();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sending_order, container, false);
        mRecyclerView = view.findViewById(R.id.recycler_view_sending);
        deliveryPresenter.dispalyInitOrder(runner);
        mRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipe_Layout_sending);
        mRefreshLayout .setColorSchemeResources(new int[]{R.color.colorAccent, R.color.colorPrimary});
        mRefreshLayout .setOnRefreshListener(this);
        return view;
    }

    private void requestLocation() {
        initLocation();
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //每隔10s发送跑腿人的位置
//                mLocationClient.start();
            }
        };
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedAddress(true);
//        mLocationClient.setLocOption(option);

    }

    public void dispalyOrder(List<OrderBean> list) {
        getActivity().runOnUiThread(() -> {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            mRecyclerView.setLayoutManager(layoutManager);
            DeliveryAdpater adpater = new  DeliveryAdpater(list);
            mRecyclerView.setAdapter(adpater);
        });
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               deliveryPresenter.dispalyInitOrder(runner);
                // 加载完数据设置为不刷新状态，将下拉进度收起来
                mRefreshLayout.setRefreshing(false);
            }
        }, 2000);
    }

//    private class MyLocationListener implements BDLocationListener {
//        @Override
//        public void onReceiveLocation(BDLocation bdLocation) {
//            runner.setLaititude(bdLocation.getLatitude());
//            runner.setLongtitude(bdLocation.getLongitude());
//            try {
//                deliveryPresenter.sendMyLocation(runner);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        }

}
