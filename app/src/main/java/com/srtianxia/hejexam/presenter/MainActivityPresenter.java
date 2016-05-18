package com.srtianxia.hejexam.presenter;

import android.util.Log;

import com.srtianxia.hejexam.model.IRequestModel;
import com.srtianxia.hejexam.model.bean.Message;
import com.srtianxia.hejexam.model.bean.Stock;
import com.srtianxia.hejexam.model.imp.RequestModel;
import com.srtianxia.hejexam.util.HSJsonUtil;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

/**
 * Created by srtianxia on 2016/5/18.
 */
public class MainActivityPresenter {
    private static final String TAG = "MainActivityPresenter";
    private IMainActivity iMainActivity;
    private IRequestModel iRequestModel;

    public MainActivityPresenter(IMainActivity iMainActivity){
        this.iMainActivity = iMainActivity;
        iRequestModel = RequestModel.getRequestModel();
    }

    /**
     * Rxjava的使用，主线程更新ui在onNext方法中，避免了回调
     */
    public void requestFromJsonFile(){
        iRequestModel.requestFromJsonFile().observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Message>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Message> messages) {
                        iMainActivity.initJsonFileData(messages);
                    }
                });
    }

    public void requestFromNet(){
        iRequestModel.requestFromNet().map(new Func1<String, List<Stock>>() {
            @Override
            public List<Stock> call(String s) {
                return HSJsonUtil.getRealStockList(s,"snapshot");
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Stock>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Stock> stocks) {
                        Log.d(TAG, stocks.get(0).name);
                    }
                });
    }


    public interface IMainActivity{
        void initJsonFileData(List<Message> messages);
    }
}
