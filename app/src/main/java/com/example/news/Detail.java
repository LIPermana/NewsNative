package com.example.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import static com.example.news.MainActivity.NEWS_URL;

public class Detail extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedIntanceState){
        super.onCreate(savedIntanceState);
        setContentView(R.layout.detail);
        Intent i = getIntent();
        String Url = i.getStringExtra(NEWS_URL);
        WebView web = findViewById(R.id.webview);
        web.setWebViewClient(new WebViewClient());
        web.loadUrl(Url);
    }
}
