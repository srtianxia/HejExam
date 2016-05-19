package com.srtianxia.hejexam.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.srtianxia.hejexam.R;
import com.srtianxia.hejexam.app.Config;
import com.srtianxia.hejexam.model.bean.Message;
import com.srtianxia.hejexam.model.bean.Stock;
import com.srtianxia.hejexam.util.HSJsonUtil;
import com.srtianxia.hejexam.util.OkHttpUtils;
import com.srtianxia.hejexam.view.widget.HorizontalListView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by srtianxia on 2016/5/18.
 */
public class StockAdapter extends RecyclerView.Adapter<StockAdapter.StockRvHolder> {
    private List<Message> items;
    private Context context;

    public StockAdapter(Context context) {
        this.context = context;
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
    public void onBindViewHolder(StockRvHolder holder, final int position) {
        holder.tvTitle.setText(items.get(position).getTitle());
        holder.tvSummary.setText(items.get(position).getSummary());
        holder.tvLikeCount.setText(" "+items.get(position).getLikeCount());
        holder.tvSource.setText(items.get(position).getSource());
        final HorizonListViewAdapter adapter = new HorizonListViewAdapter(context,items.get(position).getStocks());
        holder.horizontalListView.setAdapter(adapter);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        String date = sdf.format(new Date(items.get(position).getCreatedAt() * 1000));
        holder.tvTime.setText(date);

        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                String url = OkHttpUtils.appendParams(items.get(position).getStocks());
                try {
                    Response response = OkHttpUtils.getAsResponse(url);
                    if (response.code()== Config.HTTP_SUCCESS) {
                        subscriber.onNext(response.body().string());
                    }else {
                        subscriber.onError(new Exception("error"));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).map(new Func1<String, List<Stock>>() {
            @Override
            public List<Stock> call(String s) {
                return HSJsonUtil.getRealStockList(s,"snapshot");
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Stock>>() {
                    @Override
                    public void call(List<Stock> stocks) {
                        Log.d("stock info : ",stocks.toString());
                        adapter.updata(stocks);
                    }
                });
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
        @Bind(R.id.horizontal_list_view)
        HorizontalListView horizontalListView;
        @Bind(R.id.img_share)
        ImageView imgShare;
        @Bind(R.id.tv_like_count)
        TextView tvLikeCount;
        @Bind(R.id.tv_time)
        TextView tvTime;
        @Bind(R.id.tv_source)
        TextView tvSource;

        public StockRvHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
