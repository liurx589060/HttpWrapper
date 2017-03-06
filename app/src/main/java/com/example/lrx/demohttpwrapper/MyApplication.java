package com.example.lrx.demohttpwrapper;

import android.app.Application;

import com.example.lrx.httpwrapper.HttpRequset;
import com.example.lrx.httpwrapper.OkGoHttpExecute;

/**
 * Created by liurunxiong on 2017/3/6.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        HttpRequset.init(new OkGoHttpExecute(this));
    }
}
