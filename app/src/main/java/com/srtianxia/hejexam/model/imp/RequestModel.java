package com.srtianxia.hejexam.model.imp;

import com.google.gson.Gson;
import com.srtianxia.hejexam.model.IRequestModel;
import com.srtianxia.hejexam.model.bean.Message;
import com.srtianxia.hejexam.model.bean.MessageHolder;
import com.srtianxia.hejexam.util.HSJsonUtil;
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
     * 同上网络请求在io线程，同时使用map操作符进行Observable的转换（string->List<Stock>）
     * @return
     */
    @Override
    //这里参数改为两个list
    public Observable<String> requestFromNet(List<String> en_prod_code,List<String> fields) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

//                try {
////                    subscriber.onNext(OkHttpUtils.getAsString());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
        }).subscribeOn(Schedulers.io());
    }


}
