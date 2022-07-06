package com.blogspot.techzealous.templatefour;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String LOG = "MainActivity";

    private WebView mWebViewMain;

    private WebViewClientTemplate mWebViewClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebViewMain = (WebView)findViewById(R.id.webViewMain);
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.progressBar);

        mWebViewMain.getSettings().setJavaScriptEnabled(true);
        mWebViewClient = new WebViewClientTemplate(progressBar);
        mWebViewMain.setWebViewClient(mWebViewClient);
        mWebViewMain.loadUrl(ConstantsT.kStartPage);
    }
}
