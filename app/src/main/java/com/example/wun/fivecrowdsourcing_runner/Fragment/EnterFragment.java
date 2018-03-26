package com.example.wun.fivecrowdsourcing_runner.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wun.fivecrowdsourcing_runner.Activity.MainActivity;
import com.example.wun.fivecrowdsourcing_runner.Activity.RunnerInfoActivity;
import com.example.wun.fivecrowdsourcing_runner.Bean.Runner;
import com.example.wun.fivecrowdsourcing_runner.Presenter.EnterPresenter;
import com.example.wun.fivecrowdsourcing_runner.R;

import org.json.JSONException;

import java.io.IOException;

/**
 * Created by Administrator on 2018/3/25.
 */

@SuppressLint("ValidFragment")
public class EnterFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    TextView enter;//跑腿人入驻
    private SwipeRefreshLayout mSwipeLayout;
    private EnterPresenter enterPresenter=new EnterPresenter(this);
    private Runner runner;
    @SuppressLint("ValidFragment")
    public EnterFragment(Runner runner) {
        this.runner = runner;
    }
    public EnterFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_enter, container, false);
        enter = view.findViewById(R.id.enter);
        mSwipeLayout = view.findViewById(R.id.swipeLayout_enter);
        mSwipeLayout.setColorSchemeResources(new int[]{R.color.colorAccent, R.color.colorPrimary});
        mSwipeLayout.setOnRefreshListener(this);
        if(runner.getStatus().equals("1"))
        { enter.setText("待审核中请稍候");
            enter.setEnabled(false);}
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RunnerInfoActivity.class);
                intent.putExtra("runner", runner);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            enterPresenter.getRunnerStatus(runner);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                // 加载完数据设置为不刷新状态，将下拉进度收起来
                mSwipeLayout.setRefreshing(false);
            }
        }, 2000);
    }

    public void updateRunnerStatus(Runner runner) {
        if(runner.getStatus().equals("2")){
            Intent intent = new Intent(getActivity(), MainActivity.class);
            intent.putExtra("runner", runner);
            startActivity(intent);
        }
    }
}
