package com.example.lrx.demohttpwrapper;

import android.app.Activity;
import android.os.Bundle;;
import android.widget.Button;
import android.widget.TextView;
import com.example.lrx.httpwrapper.HttpParams;
import com.example.lrx.httpwrapper.HttpRequset;
import com.example.lrx.httpwrapper.HttpResultListener;
import com.example.lrx.httpwrapper.httpexecute.DefaultDownResultListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liurunxiong on 2017/3/6.
 */

public class FileDownActivity extends Activity {

    private Button mDown;
    private TextView mRequest;
    private TextView mResult;
    private TextView mProgress;

    String url = "http://server.jeasonlzy.com/OkHttpUtils/download";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filedown);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

        mDown = (Button) findViewById(R.id.filedown);
        mRequest = (TextView) findViewById(R.id.request);
        mResult = (TextView) findViewById(R.id.result);
        mProgress = (TextView) findViewById(R.id.progress);

        HttpParams params = HttpRequset.getInstance().setHttpMethod(HttpParams.Method.DOWN_GET);
        params.setUrl(url);
        params.setTag(this);
        Map<String,String> paramsMap = new HashMap<>();
        paramsMap.put("param1","param1");
        params.setParamsMap(paramsMap);
        mRequest.setText(url);

        HttpRequset.getInstance().execute(params, new DefaultDownResultListener() {
            @Override
            public void onSuccess(byte[] response) {
                mResult.setText("下载完成");
            }

            @Override
            public void onFailure(String failMessage) {
                mResult.setText("下载失败");
            }

            @Override
            public void downloadProgress(long currentSize, long totalSize, float progress) {
                super.downloadProgress(currentSize, totalSize, progress);
                mProgress.setText("总量=" + totalSize + "\n" + "目前下载量=" + currentSize + "\n" +
                "百分比=" + progress * 100 + "%");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        HttpRequset.getInstance().cancelRequset(this);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

}
