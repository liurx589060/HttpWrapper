package com.example.lrx.demohttpwrapper;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.lrx.httpwrapper.HttpParams;
import com.example.lrx.httpwrapper.HttpRequset;
import com.example.lrx.httpwrapper.HttpResultListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

;

/**
 * Created by liurunxiong on 2017/3/6.
 */

public class UploadActivity extends Activity {

    private Button mDown;
    private TextView mRequest;
    private TextView mResult;
    private TextView mProgress;

    String url = "http://server.jeasonlzy.com/OkHttpUtils/upload";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

        mDown = (Button) findViewById(R.id.filedown);
        mRequest = (TextView) findViewById(R.id.request);
        mResult = (TextView) findViewById(R.id.result);
        mProgress = (TextView) findViewById(R.id.progress);

        HttpParams params = HttpRequset.getInstance().setHttpMethod(HttpParams.Method.POST);
        params.setUrl(url);
        params.setTag(this);
        Map<String,String> paramsMap = new HashMap<>();
        paramsMap.put("param1","param1");
        params.setParamsMap(paramsMap);

        Map<String,File> fileMap = new HashMap<>();
        File file= getRawFile();
        fileMap.put("upfile1",file);
        params.setFileMap(fileMap);
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

            @Override
            public void upProgress(long currentSize, long totalSize, float progress) {
                super.upProgress(currentSize, totalSize, progress);
                mProgress.setText("总量=" + totalSize + "\n" + "目前下载量=" + currentSize + "\n" +
                        "百分比=" + progress * 100 + "%");
            }
        });
    }

    public File getRawFile() {
        File file = new File(getExternalCacheDir(),"temp.jpg");
        try {
            if(file.exists()) {
                file.delete();
            }
            file.createNewFile();

            OutputStream out = new FileOutputStream(file);
            InputStream in = getResources().openRawResource(R.raw.a);
            int status = 0;
            byte[] buffer = new byte[1024];
            while ((status = in.read(buffer)) != -1) {
                out.write(buffer,0,status);
            }
        }catch (Exception e) {
            return null;
        }
        return file;
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
