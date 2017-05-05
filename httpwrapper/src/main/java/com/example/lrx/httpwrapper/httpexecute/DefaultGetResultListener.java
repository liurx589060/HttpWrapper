package com.example.lrx.httpwrapper.httpexecute;

import com.example.lrx.httpwrapper.HttpResultListener;

/**
 * 默认的普通请求的回调接口
 * Created by liurunxiong on 2017/5/5.
 */

public abstract  class DefaultGetResultListener extends HttpResultListener<String> {
    public abstract void onSuccess(String response);
    public abstract void onFailure(String failMessage);
}
