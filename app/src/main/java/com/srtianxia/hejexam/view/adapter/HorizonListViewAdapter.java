package com.srtianxia.hejexam.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.srtianxia.hejexam.R;
import com.srtianxia.hejexam.model.bean.Stock;

import java.util.List;

/**
 * Created by srtianxia on 2016/5/18.
 */
public class HorizonListViewAdapter extends BaseAdapter {
    private Context context;
    private List<Stock> stockList;
    public HorizonListViewAdapter(Context context,List<Stock> stockList){
        this.context=context;
        this.stockList=stockList;
    }
    @Override
    public int getCount() {
        return stockList.size();
    }

    @Override
    public Object getItem(int position) {
        return stockList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder=new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_horizon_list, null);
            holder.imgStockRate= (ImageView) convertView.findViewById(R.id.img_stock_rate);
            holder.tvStockName= (TextView) convertView.findViewById(R.id.tv_stock_name);
            holder.tvStockRate= (TextView) convertView.findViewById(R.id.tv_stock_rate);
            convertView.setTag(holder);

        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.imgStockRate.setImageResource(R.mipmap.ic_stock_down);
        holder.tvStockName.setText(stockList.get(position).getName());
        holder.tvStockRate.setText(stockList.get(position).getSymbol());
        return convertView;
    }
    public final class ViewHolder{
        public ImageView imgStockRate;
        public TextView tvStockName;
        public TextView tvStockRate;
    }
}
