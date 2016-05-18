package com.srtianxia.hejexam.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.srtianxia.hejexam.R;
import com.srtianxia.hejexam.model.bean.Message;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by srtianxia on 2016/5/18.
 */
public class StockAdapter extends RecyclerView.Adapter<StockAdapter.StockRvHolder> {
    private List<Message> items;

    public StockAdapter() {
        items = new ArrayList<>();
    }

    public void addItems(List<Message> newItems) {
        items.addAll(newItems);
        notifyDataSetChanged();
    }

    @Override
    public StockRvHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stock, parent, false);
        return new StockRvHolder(view);
    }

    @Override
    public void onBindViewHolder(StockRvHolder holder, int position) {
        holder.tvTitle.setText(items.get(position).getTitle());
        holder.tvSummary.setText(items.get(position).getSummary());
        holder.tvStock01.setText(items.get(position).getStocks().get(0).getName());
        holder.tvStock02.setText(items.get(position).getStocks().get(1).getName());
        holder.tvStock03.setText(items.get(position).getStocks().get(2).getName());
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class StockRvHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_title)
        TextView tvTitle;
        @Bind(R.id.tv_summary)
        TextView tvSummary;
        @Bind(R.id.img_stock_01)
        ImageView imgStock01;
        @Bind(R.id.tv_stock_01)
        TextView tvStock01;
        @Bind(R.id.img_stock_02)
        ImageView imgStock02;
        @Bind(R.id.tv_stock_02)
        TextView tvStock02;
        @Bind(R.id.img_stock_03)
        ImageView imgStock03;
        @Bind(R.id.tv_stock_03)
        TextView tvStock03;
        public StockRvHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
