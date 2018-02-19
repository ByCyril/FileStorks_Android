package com.bycyril.filestorks_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class Web extends AppCompatActivity {

    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        showFile();
    }

    private void showFile() {
        String link = getIntent().getStringExtra("link");
        System.out.println("Passed link here: " + link);
        webview = (WebView)findViewById(R.id.webview);

        webview.loadUrl(link);
    }
}
