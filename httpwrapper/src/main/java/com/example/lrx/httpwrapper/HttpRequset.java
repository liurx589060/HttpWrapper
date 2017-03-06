package com.example.lrx.httpwrapper;

import android.util.Log;

/**
 * 网络请求的借口
 * Created by liurunxiong on 2017/2/28.
 */

public class HttpRequset {

    public static AbsHttpExecute httpExcute;
    public static HttpRequset httpRequset;

    public HttpRequset() {
    }

    public static void init(AbsHttpExecute excute) {
        httpExcute = excute;
    }

    /**
     * 单例获取
     */
    public static HttpRequset getInstance() {
        try{
            if(httpExcute == null) {
                throw new NullPointerException();
            }
        }catch (Exception e) {
            Log.e("HttpRequest","请调用init方法");
        }


        if(httpRequset == null) {
            httpRequset = new HttpRequset();
        }
        return httpRequset;
    }

    /**
     * 创建一个HttpParams请求
     */
    public HttpParams setHttpMethod(HttpParams.Method method) {
        HttpParams params = new HttpParams();
        params.method = method;
        return params;
    }

    /**
     * 执行代码
     */
    public <T> void execute(HttpParams params, final HttpResultListener<T> listener) {
        if(httpExcute != null) {
            httpExcute.execute(params,listener);
        }
    }

    /**
     * 取消请求
     */
    public void cancelRequset(Object tag) {
        if (httpExcute != null) {
            httpExcute.cancelRequest(tag);
        }
    }
}
