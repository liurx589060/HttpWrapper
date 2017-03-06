package com.example.lrx.httpwrapper;

/**
 * Http请求执行虚拟类
 * Created by liurunxiong on 2017/3/1.
 */

public abstract class AbsHttpExecute {
    //执行数据请求，参数在params里（包括get或者post方法），回调接口为listener
    public abstract <T> void execute(HttpParams params, final HttpResultListener<T> listener);
    //取消请求
    public abstract void cancelRequest(Object tag);
}
