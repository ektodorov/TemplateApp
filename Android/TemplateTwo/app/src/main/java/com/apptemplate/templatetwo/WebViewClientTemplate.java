package com.apptemplate.templatetwo;

import android.graphics.Bitmap;
import android.os.Build;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import java.lang.ref.WeakReference;

public class WebViewClientTemplate extends WebViewClient {

    private WeakReference<ProgressBar> mWeakProgressBar;

    public WebViewClientTemplate(ProgressBar aProgressBar) {
        super();
        mWeakProgressBar = new WeakReference<ProgressBar>(aProgressBar);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, String url) {

        return false;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        ProgressBar progressBar = mWeakProgressBar.get();
        if(progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        ProgressBar progressBar = mWeakProgressBar.get();
        if(progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            view.loadUrl(ConstantsT.kErrorPageUrl);
        }
    }

    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            view.loadUrl(ConstantsT.kErrorPageUrl);
        }
    }
}
