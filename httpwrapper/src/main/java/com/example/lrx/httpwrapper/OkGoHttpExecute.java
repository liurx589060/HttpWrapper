package com.example.lrx.httpwrapper;

import android.app.Application;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.PostRequest;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 使用OKGo进行网络请求
 * Created by liurunxiong on 2017/3/1.
 */

public class OkGoHttpExecute extends AbsHttpExecute {
    public OkGoHttpExecute(Application application) {
        OkGo.init(application);
    }

    @Override
    public void execute(HttpParams params, final HttpResultListener listener) {
        if(params.method == HttpParams.Method.NOMAL_GET) {//get方法
            OkGo.get(params.url)
                    .tag(params.tag)
                    .cacheMode(!params.cache?CacheMode.NO_CACHE:CacheMode.REQUEST_FAILED_READ_CACHE)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {
                            if(listener != null) {
                                listener.onSuccess(s);
                            }
                        }

                        @Override
                        public void onError(Call call, Response response, Exception e) {
                            super.onError(call, response, e);
                            if(listener != null) {
                                listener.onFailure(e.toString());
                            }
                        }
                    });
        }else if (params.method == HttpParams.Method.DOWN_GET) {//get方法下载
            OkGo.get(params.url)
                    .tag(params.tag)
                    .execute(new FileCallback() {
                        @Override
                        public void onSuccess(File file, Call call, Response response) {
                            if(listener != null) {
                                try {
                                    FileInputStream stream = new FileInputStream(file);
                                    ByteArrayOutputStream out = new ByteArrayOutputStream(1000);
                                    byte[] b = new byte[1000];
                                    int n;
                                    while ((n = stream.read(b)) != -1)
                                        out.write(b, 0, n);
                                    stream.close();
                                    out.close();
                                    listener.onSuccess(out.toByteArray());
                                } catch (IOException e){
                                }
                            }
                        }

                        @Override
                        public void onError(Call call, Response response, Exception e) {
                            super.onError(call, response, e);
                            if (listener != null) {
                                listener.onFailure(e.toString());
                            }
                        }

                        @Override
                        public void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                            super.downloadProgress(currentSize, totalSize, progress, networkSpeed);
                            if(listener != null) {
                                listener.downloadProgress(currentSize,totalSize,progress);
                            }
                        }
                    });
        }else if (params.method == HttpParams.Method.POST) {//post方法,主要用于上传
            PostRequest postRequset = OkGo.post(params.url);
            if(params.params == null) {params.params = new HashMap<>();};
            Iterator<String> iter = params.params.keySet().iterator();
            while (iter.hasNext()) {
                String key = iter.next();
                postRequset.params(key,params.params.get(key));
            }

            if(params.fileMap == null) {params.fileMap = new HashMap<>();};
            Iterator<String> iter2 = params.fileMap.keySet().iterator();
            while (iter2.hasNext()) {
                String key = iter2.next();
                postRequset.params(key,params.fileMap.get(key));
            }

            postRequset.tag(params.tag);
            postRequset.isMultipart(true);
            postRequset.execute(new StringCallback() {
                @Override
                public void onSuccess(String s, Call call, Response response) {
                    if (listener != null) {
                        listener.onSuccess(s);
                    }
                }

                @Override
                public void onError(Call call, Response response, Exception e) {
                    super.onError(call, response, e);
                    if(listener != null) {
                        listener.onFailure(e.toString());
                    }
                }

                @Override
                public void upProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                    super.upProgress(currentSize, totalSize, progress, networkSpeed);
                    if(listener != null) {
                        listener.upProgress(currentSize,totalSize,progress);
                    }
                }
            });
        }
    }

    @Override
    public void cancelRequest(Object tag) {
        OkGo.getInstance().cancelTag(tag);
    }
}
