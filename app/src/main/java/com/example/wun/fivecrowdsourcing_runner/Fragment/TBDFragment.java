package com.example.wun.fivecrowdsourcing_runner.Fragment;

/**
 * 待配送Fragment
 * Created by WUN on 2018/3/8.
 */

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.wun.fivecrowdsourcing_runner.Adapter.TBDAdpater;
import com.example.wun.fivecrowdsourcing_runner.Bean.OrderBean;
import com.example.wun.fivecrowdsourcing_runner.Bean.Runner;
import com.example.wun.fivecrowdsourcing_runner.Presenter.TBDPresenter;
import com.example.wun.fivecrowdsourcing_runner.R;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class TBDFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    private Runner runner;
    private TBDPresenter tbdPresenter = new TBDPresenter(this);
    protected SwipeRefreshLayout mRefreshLayout;
    protected RecyclerView mRecyclerView;
    public LocationClient mLocationClient;
    private boolean isFirstLocate=true;


    @SuppressLint("ValidFragment")
    public TBDFragment(Runner runner) {
        this.runner = runner;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLocationClient = new LocationClient(getContext());
        mLocationClient.registerLocationListener(new MyLocationListener());
        requestPermisson();
    }

    private void requestPermisson() {
        //权限申请
        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_PHONE_STATE)!= PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (!permissionList.isEmpty()) {
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(getActivity(), permissions, 1);
        } else {
            requestLocation();
        }
    }

    private void requestLocation() {
        initLocation();
        mLocationClient.start();
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_item, container, false);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipe_Layout);

        return view;
    }



    public void dispalyOrder(List<OrderBean> deliveryOrderList) {
        getActivity().runOnUiThread(() -> {
//            for(int i=0;i<deliveryOrderList.size();i++) {
//                deliveryOrderList.get(i).setStoreAddress(merchant.getAddress());
//                deliveryOrderList.get(i).setStoreName(merchant.getStorename());
//            }
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            mRecyclerView.setLayoutManager(layoutManager);
            TBDAdpater adpater = new TBDAdpater(deliveryOrderList);
            mRecyclerView.setAdapter(adpater);
        });

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    for (int result : grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(getContext(), "必须统一所有权限才能使用本程序", Toast.LENGTH_SHORT).
                                    show();
                            getActivity().finish();
                            return;
                        }
                    }
                    requestLocation();
                } else {
                    Toast.makeText(getContext(), "发生未知错误", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            //得到位置信息
            if (isFirstLocate) {
                runner.setLaititude(bdLocation.getLatitude());
                runner.setLongtitude(bdLocation.getLongitude());
                isFirstLocate = false;
            }
            //发送跑腿人位置
            if(runner.getLaititude()!=0.0){
                tbdPresenter.dispalyInitOrder(runner);
            }

        }
    }
}
