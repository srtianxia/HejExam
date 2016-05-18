package com.srtianxia.hejexam.model;

import com.srtianxia.hejexam.model.bean.Message;
import com.srtianxia.hejexam.model.bean.Stock;

import java.util.List;

import rx.Observable;

/**
 * Created by srtianxia on 2016/5/18.
 */
public interface IRequestModel {
    Observable<List<Message>> requestFromJsonFile();

    Observable<String> requestFromNet();
}
