package com.example.lrx.demohttpwrapper;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lrx.httpwrapper.HttpParams;
import com.example.lrx.httpwrapper.HttpRequset;
import com.example.lrx.httpwrapper.HttpResultListener;
import com.example.lrx.httpwrapper.httpexecute.DefaultGetResultListener;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义回调接口
 * Created by liurunxiong on 2017/3/6.
 */

public class CustomCallBackActivity extends Activity {
    private Button mGet;
    private TextView mRequest;
    private TextView mResult;

    private String url = "http://www.weather.com.cn/adat/sk/101010100.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normalget);

        mGet = (Button) findViewById(R.id.get);
        mRequest = (TextView) findViewById(R.id.request);
        mResult = (TextView) findViewById(R.id.result);

        mGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpParams params = HttpRequset.getInstance().setHttpMethod(HttpParams.Method.NOMAL_GET);
                params.setUrl(url);
                params.setTag(this);
                Map<String, String> paramsMap = new HashMap<>();
                paramsMap.put("param1", "param1");
                params.setParamsMap(paramsMap);
                mRequest.setText(url);
                HttpRequset.getInstance().execute(params, new HttpResultListener<String>() {
                    @Override
                    public void onSuccess(String response) {
                        mResult.setText(response);
                    }

                    @Override
                    public void onFailure(String failMessage) {
                        mResult.setText(failMessage);
                    }
                });
            }
        });
    }
}
