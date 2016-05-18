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

import java.text.DecimalFormat;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_horizon_list, null);
            holder.imgStockRate= (ImageView) convertView.findViewById(R.id.img_stock_rate);
            holder.tvStockName= (TextView) convertView.findViewById(R.id.tv_stock_name);
            holder.tvStockRate= (TextView) convertView.findViewById(R.id.tv_stock_rate);
            convertView.setTag(holder);

        }else {
            holder = (ViewHolder)convertView.getTag();
        }

        //初次加载值为空，直接返回
        if (stockList.get(position).px_change_rate==null){
            return convertView;
        }

        double value = Double.valueOf(stockList.get(position).px_change_rate);
        DecimalFormat df = new DecimalFormat("#.00");
        value = Double.parseDouble(df.format(value));
        if (Double.valueOf(stockList.get(position).px_change_rate) > 0) {
            holder.tvStockRate.setText("+" + value + "%");
            holder.imgStockRate.setImageResource(R.mipmap.ic_stock_up);
            holder.tvStockName.setTextColor(context.getResources().getColor(R.color.stock_up));
            holder.tvStockRate.setTextColor(context.getResources().getColor(R.color.stock_up));
        } else {
            holder.tvStockRate.setText(value + "%");
            holder.imgStockRate.setImageResource(R.mipmap.ic_stock_down);
            holder.tvStockRate.setTextColor(context.getResources().getColor(R.color.stock_down));
            holder.tvStockName.setTextColor(context.getResources().getColor(R.color
                    .stock_down));
        }
        holder.tvStockName.setText(stockList.get(position).Name);
        return convertView;
    }

    public void updata(List<Stock> data){
        stockList.clear();
        stockList.addAll(data);
        notifyDataSetChanged();
    }

    public final class ViewHolder{
        public ImageView imgStockRate;
        public TextView tvStockName;
        public TextView tvStockRate;
    }
}
