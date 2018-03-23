package com.example.wun.fivecrowdsourcing_runner.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wun.fivecrowdsourcing_runner.Adapter.CompletetdAdpater;
import com.example.wun.fivecrowdsourcing_runner.Adapter.DeliveryAdpater;
import com.example.wun.fivecrowdsourcing_runner.Bean.OrderBean;
import com.example.wun.fivecrowdsourcing_runner.Bean.Runner;
import com.example.wun.fivecrowdsourcing_runner.Presenter.CompletedPresenter;
import com.example.wun.fivecrowdsourcing_runner.R;

import java.util.List;

/**
 * Created by WUN on 2018/3/8.
 */
@SuppressLint("ValidFragment")
public class CompletedFragment extends Fragment{
    protected SwipeRefreshLayout mRefreshLayout;
    protected RecyclerView mRecyclerView;
    private Runner runner;
    private CompletedPresenter completedPresenter = new CompletedPresenter(this);

    public CompletedFragment(Runner runner) {
        this.runner = runner;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_completed_order, container, false);
        mRecyclerView = view.findViewById(R.id.recycler_view_completed);
        mRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipe_Layout_completed);
        completedPresenter.displayInitOrder(runner);
        return view;
    }

    public void dispalyOrder(List<OrderBean> list) {
        getActivity().runOnUiThread(() -> {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            mRecyclerView.setLayoutManager(layoutManager);
            CompletetdAdpater adpater = new CompletetdAdpater(list);
            mRecyclerView.setAdapter(adpater);
        });
    }
}
