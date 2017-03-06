package com.example.lrx.httpwrapper;

/**
 * 网络回调接口
 * Created by liurunxiong on 2017/2/28.
 */

public abstract class  HttpResultListener<T> {
    public abstract void onSuccess(T response);
    public abstract void onFailure(String failMessage);
    public void downloadProgress(long currentSize, long totalSize, float progress) {};
    public void upProgress(long currentSize, long totalSize, float progress) {};
}
