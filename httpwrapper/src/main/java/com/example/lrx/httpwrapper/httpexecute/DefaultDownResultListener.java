package com.example.lrx.httpwrapper.httpexecute;

import com.example.lrx.httpwrapper.HttpResultListener;

/**
 * 默认的下载请求的回调接口
 * Created by liurunxiong on 2017/5/5.
 */

public abstract  class DefaultDownResultListener extends HttpResultListener<byte[]> {
    public abstract void onSuccess(byte[] response);
    public abstract void onFailure(String failMessage);
}
