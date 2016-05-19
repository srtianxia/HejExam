package com.srtianxia.hejexam.util;



import com.srtianxia.hejexam.app.Config;
import com.srtianxia.hejexam.model.bean.Stock;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by srtianxia on 2016/5/18.
 * 简单的封装了okhttp，因为使用了rxjava，就封装了同步方法，异步交给rx去执行
 */
public class OkHttpUtils {
    private static final String TAG = "OkHttpUtils";

    private static OkHttpUtils instance;

    private static OkHttpClient client;

    public static OkHttpUtils getInstance() {
        if (instance == null) {
            synchronized (OkHttpUtils.class) {
                if (instance == null) {
                    instance = new OkHttpUtils();
                }
            }
        }
        return instance;
    }
    private OkHttpUtils(){
        client = new OkHttpClient();
    }

    /**
     * 同步的Get请求
     *
     * @param url
     * @return Response
     */
    private Response _getAsyn(String url) throws IOException {
        final Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        Response execute = call.execute();
        return execute;
    }

    /**
     * 同步的Get请求
     *
     * @param url
     * @return 字符串
     */
    private String _getAsString(String url) throws IOException {
        Response execute = _getAsyn(url);
        return execute.body().string();
    }

    public static String getAsString(String url) throws IOException {
        return getInstance()._getAsString(url);
    }

    public static Response getAsResponse(String url) throws IOException {
        return getInstance()._getAsyn(url);
    }

    /**
     * 根据 List<Stock> stocks拼接url的工具
     * @param stocks
     * @return
     */
    public static String appendParams(List<Stock> stocks){
        StringBuilder stringBuilder = new StringBuilder(Config.BASE_URL+"?en_prod_code=");
        for (Stock stock : stocks){
            stringBuilder.append(stock.Symbol +",");
        }
        stringBuilder.append("&fields=prod_name,px_change,last_px,px_change_rate,trade_status");
        return stringBuilder.toString();
    }
}
