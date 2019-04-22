package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private WebView webView;
    Button httpTestBtn;
    public static final int SHOW_DATA = 0X123;
    private String detail = "";

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == SHOW_DATA) {
                webView.loadDataWithBaseURL("", detail, "text/html", "UTF-8", "");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setView();
    }

    private void initView() {
        httpTestBtn = (Button) findViewById(R.id.btn_http_test);
        webView = (WebView) findViewById(R.id.wv_test);
    }

    private void setView() {
        httpTestBtn.setOnClickListener(this);
        webView.getSettings().setDomStorageEnabled(true);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_http_test) {
            GetByHttpClient();
        }
    }

    private void GetByHttpClient() {
        new Thread() {
            public void run() {
                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpGet httpGet = new HttpGet("http://www.w3cschool.cc/python/python-tutorial.html");
                    HttpResponse httpResponse = httpClient.execute(httpGet);
                    if (httpResponse.getStatusLine().getStatusCode() == 200) {
                        HttpEntity entity = httpResponse.getEntity();
                        detail = EntityUtils.toString(entity, "utf-8");
                        handler.sendEmptyMessage(SHOW_DATA);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }.start();

    }
}
