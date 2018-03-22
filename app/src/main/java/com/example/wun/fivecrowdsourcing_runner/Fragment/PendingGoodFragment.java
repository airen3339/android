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

import com.example.wun.fivecrowdsourcing_runner.Adapter.PendingGoodAdpater;
import com.example.wun.fivecrowdsourcing_runner.Bean.OrderBean;
import com.example.wun.fivecrowdsourcing_runner.Bean.Runner;
import com.example.wun.fivecrowdsourcing_runner.Presenter.PendingGoodPresenter;
import com.example.wun.fivecrowdsourcing_runner.R;

import java.util.List;

/**
 * Created by WUN、 on 2018/3/22.
 */

@SuppressLint("ValidFragment")
public class PendingGoodFragment extends Fragment {
    protected SwipeRefreshLayout mRefreshLayout;
    protected RecyclerView mRecyclerView;
    private Runner runner;
    private PendingGoodPresenter pendingGoodPresenter = new PendingGoodPresenter(this);
    @SuppressLint("ValidFragment")
    public PendingGoodFragment(Runner runner) {
        this.runner = runner;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pending_good, container, false);
        mRecyclerView = view.findViewById(R.id.recycler_view_pending);
        mRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipe_Layout_pending);
        pendingGoodPresenter.dispalyInitOrder(runner);
        return view;
    }

    public void dispalyOrder(List<OrderBean> list) {
        getActivity().runOnUiThread(() -> {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            mRecyclerView.setLayoutManager(layoutManager);
            PendingGoodAdpater adpater = new  PendingGoodAdpater(list);
            mRecyclerView.setAdapter(adpater);
        });
    }
}
