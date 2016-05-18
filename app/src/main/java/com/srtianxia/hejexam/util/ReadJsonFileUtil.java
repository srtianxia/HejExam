package com.srtianxia.hejexam.util;

import com.srtianxia.hejexam.app.App;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by srtianxia on 2016/5/18.
 * 读取json的工具类
 */
public class ReadJsonFileUtil {
    public static String getFromAssets(String fileName){
        String result = "";
        try {
            InputStream is = App.getContext().getResources().getAssets().open(fileName);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = is.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }
            is.close();
            result = os.toString();
            os.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
