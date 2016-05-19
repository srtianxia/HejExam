package com.srtianxia.hejexam.model.imp;

import com.google.gson.Gson;
import com.srtianxia.hejexam.model.IRequestModel;
import com.srtianxia.hejexam.model.bean.Message;
import com.srtianxia.hejexam.model.bean.MessageHolder;
import com.srtianxia.hejexam.util.OkHttpUtils;
import com.srtianxia.hejexam.util.ReadJsonFileUtil;

import java.io.IOException;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
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
     * 网络请求在io线程，同时使用map操作符进行Observable的转换（string->List<Stock>）
     * 这个方法没有用到，本来想在外面请求完数据传到adapter中去，刷新数据有问题，就
     * 在adapter里面请求的（目测是多并发的问题），但是这样破坏了解耦。
     * @return
     */
    @Override
    public Observable<String> requestFromNet(final Message message) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                String url = OkHttpUtils.appendParams(message.getStocks());
                try {
                    subscriber.onNext(OkHttpUtils.getAsString(url));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).subscribeOn(Schedulers.io());
    }


}
