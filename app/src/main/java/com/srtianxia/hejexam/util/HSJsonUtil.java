package com.srtianxia.hejexam.util;

import com.srtianxia.hejexam.model.bean.Stock;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HSJsonUtil {

    /**
     * 获取组合股票的数据
     *
     * @param json
     * @param jsonObjectName --->snapshot
     * @return
     */
    public static ArrayList<Stock> getRealStockList(String json, String jsonObjectName) {
        ArrayList<Stock> stocks = new ArrayList<>();
        try {
            JSONObject jsonObj = new JSONObject(json).getJSONObject("data").getJSONObject(jsonObjectName);
            Iterator keyIter = jsonObj.keys();
            List list = new ArrayList();
            JSONArray fields = jsonObj.getJSONArray("fields");
            for (int i = 0; i < fields.length(); i++) {
                list.add(i, fields.getString(i));
            }
            while (keyIter.hasNext()) {
                String keys = (String) keyIter.next();
                JSONArray jsonArray = jsonObj.getJSONArray(keys);
                Map map = new HashMap();
                if (keys.equals("fields")) {
                    continue;
                } else {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        map.put(list.get(i), jsonArray.get(i));
                    }
                    Stock stock = new Stock();
                    stock.name = (String) map.get("prod_name");//股票名称
                    stock.symbol = keys; //股票编码

                    stock.last_px = map.get("last_px").toString();
                    stock.px_change_rate = map.get("px_change_rate").toString();
                    stock.px_change = map.get("px_change").toString();
                    stock.trade_status = map.get("trade_status").toString();
                    stocks.add(stock);
                }
            }
            return stocks;
        } catch (JSONException e) {
            return stocks;
        }


    }


    /**
     * 获取单个股票的数据
     *
     * @param json
     * @param jsonObjectName --->snapshot
     * @return
     */
    public static Stock getRealInfo(String json, String jsonObjectName) {
        Stock stock = new Stock();
        try {
            JSONObject jsonObj = new JSONObject(json).getJSONObject("data").getJSONObject(jsonObjectName);
            Iterator keyIter = jsonObj.keys();
            List list = new ArrayList();
            JSONArray fields = jsonObj.getJSONArray("fields");
            for (int i = 0; i < fields.length(); i++) {
                list.add(i, fields.getString(i));
            }
            while (keyIter.hasNext()) {
                String keys = (String) keyIter.next();
                JSONArray jsonArray = jsonObj.getJSONArray(keys);
                Map map = new HashMap();
                if (keys.equals("fields")) {
                    continue;
                } else {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        map.put(list.get(i), jsonArray.get(i));
                    }
                    stock.name = (String) map.get("prod_name");//股票名称
                    stock.symbol = keys; //股票编码

                    stock.last_px = map.get("last_px").toString();
                    stock.px_change_rate = map.get("px_change_rate").toString();
                    stock.px_change = map.get("px_change").toString();
                    stock.trade_status = map.get("trade_status").toString();
                }
            }
            return stock;
        } catch (JSONException e) {
            return stock;
        }
    }
}
