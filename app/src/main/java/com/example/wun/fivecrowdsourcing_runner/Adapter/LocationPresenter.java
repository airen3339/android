package com.example.wun.fivecrowdsourcing_runner.Adapter;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.wun.fivecrowdsourcing_runner.Bean.Runner;
import com.example.wun.fivecrowdsourcing_runner.Fragment.TBDFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WUN on 2018/3/21.
 */

public class LocationPresenter {
    public LocationClient mLocationClient;
    private boolean isFirstLocate = true;
    private TBDFragment tbdFragment;
    private Runner runner;



    public LocationPresenter(TBDFragment tbdFragment,Runner runner) {
        this.runner = runner;
        this.tbdFragment = tbdFragment;
        mLocationClient = new LocationClient(tbdFragment.getContext());
        mLocationClient.registerLocationListener(new MyLocationListener());
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        //option.setLocationMode(LocationClientOption.LocationMode.Device_Sensors);//使用GPS定位
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
        requestPermisson();
        mLocationClient.start();
    }

    private void requestPermisson() {
        //权限申请
        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(tbdFragment.getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(tbdFragment.getActivity(),
                Manifest.permission.READ_PHONE_STATE)!= PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(tbdFragment.getActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (!permissionList.isEmpty()) {
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(tbdFragment.getActivity(), permissions, 1);
        }
    }


    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            //得到位置信息
            runner.setLaititude(bdLocation.getLatitude());
            runner.setLongtitude(bdLocation.getLongitude());
        }
    }

    public Runner getRunner() {
        return runner;
    }

    public void setRunner(Runner runner) {
        this.runner = runner;
    }
}
