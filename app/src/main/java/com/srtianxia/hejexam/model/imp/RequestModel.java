package com.srtianxia.hejexam.model.imp;

import com.google.gson.Gson;
import com.srtianxia.hejexam.model.IRequestModel;
import com.srtianxia.hejexam.model.bean.Message;
import com.srtianxia.hejexam.model.bean.MessageHolder;
import com.srtianxia.hejexam.model.bean.Stock;
import com.srtianxia.hejexam.util.OkHttpUtils;
import com.srtianxia.hejexam.util.ReadJsonFileUtil;

import java.io.IOException;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by srtianxia on 2016/5/18.
 * 用于请求本地json数据和网络API的model层 单例
 */
public class RequestModel implements IRequestModel {
    private static final String TAG = "RequestModel";
    private RequestModel(){}

    public static RequestModel getRequestModel(){
        return SingleHolder.model;
    }


    private static class SingleHolder{
        private static final RequestModel model = new RequestModel();
    }

    /**
     * Rxjava的使用，model层读写文件在io线程
     * @return
     */
    @Override
    public Observable<List<Message>> requestFromJsonFile() {
        return Observable.create(new Observable.OnSubscribe<List<Message>>() {
            @Override
            public void call(Subscriber<? super List<Message>> subscriber) {
                Gson gson = new Gson();
                MessageHolder holder = gson.fromJson(ReadJsonFileUtil.getFromAssets("data.json"),MessageHolder.class);
                subscriber.onNext(holder.getMessages());
            }
        }).subscribeOn(Schedulers.io());
    }

    /**
     * 同上网络请求在io线程，同时使用map操作符进行Observable的转换（string->List<Stock>）
     * @return
     */
    @Override
    public Observable<String> requestFromNet() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    subscriber.onNext(OkHttpUtils.getAsString("https://bao.wallstreetcn.com/q/quote/v1/real?en_prod_code=002477.SZ,002714.SZ,300498.SZ,002234.SZ,002299.SZ,002458.SZ,002155.SZ,600547.SS,600988.SS,002161.SZ,600650.SS,600836.SS,&fields=prod_name,px_change,last_px,px_change_rate,trade_status"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).subscribeOn(Schedulers.io());
    }


}
