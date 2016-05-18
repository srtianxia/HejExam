package com.srtianxia.hejexam.model.bean;

public class Stock {
    public static final String TRADE = "TRADE";//正常交易
    public static final String HALT = "HALT";//停牌
    public static final String BREAK = "BREAK";//休市
    public static final String ENDTR = "ENDTR";//收盘
    public static final String OCALL = "OCALL";//集合竞价(09:15 --09:30)
    public String id;
    public boolean isFav;//是否收藏
    public String px_change;//涨跌额
    public String last_px;//最新价格 现价
    public String px_change_rate;//涨跌幅
    public String trade_status = HALT;  //交易状态 "TRADE"=>正常交易  "HALT"=>停牌
    public String Name;
    public String Symbol;


    @Override
    public String toString() {
        return "symbol= " + Symbol + " px_change_rate= "+ px_change_rate;
    }
}
