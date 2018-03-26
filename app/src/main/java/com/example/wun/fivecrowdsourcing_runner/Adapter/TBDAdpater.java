package com.example.wun.fivecrowdsourcing_runner.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wun.fivecrowdsourcing_runner.Bean.OrderBean;
import com.example.wun.fivecrowdsourcing_runner.Bean.Runner;
import com.example.wun.fivecrowdsourcing_runner.Fragment.TBDFragment;
import com.example.wun.fivecrowdsourcing_runner.Presenter.TBDPresenter;
import com.example.wun.fivecrowdsourcing_runner.R;

import java.util.List;

/**
 * Created by WUN on 2018/3/21.
 */

public class TBDAdpater extends RecyclerView.Adapter<TBDAdpater.ViewHolder>{
    private List<OrderBean> orderList;
    private TBDPresenter tbdPresenter = new TBDPresenter();
    private Runner runner;
    TBDFragment tbdFragment;
    public TBDAdpater(List<OrderBean> orderList, Runner runner, TBDFragment tbdFragment) {
        this.orderList = orderList;
        this.runner = runner;
        this.tbdFragment = tbdFragment;
    }

    public List<OrderBean> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderBean> orderList) {
        this.orderList = orderList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView storeName;
        TextView storeAddress;
        TextView cusAddress;
        TextView estimatedPrice;
        TextView estimatedTime;
        TextView getOrder;
        TextView cusName;

        public ViewHolder(View itemView) {
            super(itemView);
            storeName= itemView.findViewById(R.id.shopName);
            storeAddress = itemView.findViewById(R.id.storeAddress);
            cusAddress = itemView.findViewById(R.id.cusAddress);
            estimatedPrice = itemView.findViewById(R.id.estimatedPrice);
            estimatedTime = itemView.findViewById(R.id.estimatedTime);
            cusName = itemView.findViewById(R.id.cusName);
            getOrder = itemView.findViewById(R.id.get_order);

        }
    }

    @Override
    public TBDAdpater.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_layout ,parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(TBDAdpater.ViewHolder holder, int position) {
        OrderBean deliveryOrder = orderList.get(position);
        holder.storeName.setText(deliveryOrder.getStoreName());
        holder.storeAddress.setText(deliveryOrder.getStoreAddress());
        holder.cusAddress.setText(deliveryOrder.getCusAddress());
        holder.estimatedTime.setText(deliveryOrder.getEstimatedtime()+"分钟");
        holder.estimatedPrice.setText(deliveryOrder.getEstimatedtotalprice()+"元");
        holder.getOrder.setText("抢单");
        holder.getOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tbdPresenter.grap(deliveryOrder,runner,tbdFragment);
            }
        });

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }
}
