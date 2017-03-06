package com.example.lrx.httpwrapper;

import android.content.Context;

import java.io.File;
import java.util.Map;

/**
 * Created by liurunxiong on 2017/3/1.
 */

public class HttpParams {

    public static enum Method {
        NOMAL_GET,//普通get方法
        DOWN_GET,//下载get方法
        POST//post方法
    }

    public Context mContext;
    public Object tag;
    public String url;
    public boolean cache;
    public Map<String,String> params;
    public Map<String,File> fileMap;
    public String cacheKey;
    public Method method;

    public void setContext(Context context) {
        this.mContext = context;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCache(boolean cache) {
        this.cache = cache;
    }

    public void setParamsMap(Map<String,String> map) {
        this.params = map;
    }

    public void setFileMap(Map<String,File> map) {
        this.fileMap = map;
    }

    public void setCacheKey(String key) {
        this.cacheKey = key;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
