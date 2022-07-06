package com.apptemplate.templatetwo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String LOG = "MainActivity";

    private WebView mWebViewMain;
    private LinearLayout mLinearLayoutMenuItem1;
    private LinearLayout mLinearLayoutMenuItem2;
    private LinearLayout mLinearLayoutMenuItem3;
    private LinearLayout mLinearLayoutMenuItem4;

    private WebViewClientTemplate mWebViewClient;
    private SharedPreferences mPrefs;
    private ArrayList<MenuItemT> mArrayMenuItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebViewMain = (WebView)findViewById(R.id.webViewMain);
        mLinearLayoutMenuItem1 = (LinearLayout)findViewById(R.id.linearLayoutMenuItem1);
        mLinearLayoutMenuItem2 = (LinearLayout)findViewById(R.id.linearLayoutMenuItem2);
        mLinearLayoutMenuItem3 = (LinearLayout)findViewById(R.id.linearLayoutMenuItem3);
        mLinearLayoutMenuItem4 = (LinearLayout)findViewById(R.id.linearLayoutMenuItem4);
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.progressBar);

        mLinearLayoutMenuItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebViewMain.loadUrl(mArrayMenuItems.get(0).getUrl());
                PrefsHelper.getInstance().setMenuSelectedPosition(mPrefs, 0);
                setSelectedMenuItem(0);
            }
        });

        mLinearLayoutMenuItem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebViewMain.loadUrl(mArrayMenuItems.get(1).getUrl());
                PrefsHelper.getInstance().setMenuSelectedPosition(mPrefs, 1);
                setSelectedMenuItem(1);
            }
        });

        mLinearLayoutMenuItem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebViewMain.loadUrl(mArrayMenuItems.get(2).getUrl());
                PrefsHelper.getInstance().setMenuSelectedPosition(mPrefs, 2);
                setSelectedMenuItem(2);
            }
        });

        mLinearLayoutMenuItem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebViewMain.loadUrl(mArrayMenuItems.get(3).getUrl());
                PrefsHelper.getInstance().setMenuSelectedPosition(mPrefs, 3);
                setSelectedMenuItem(3);
            }
        });

        mPrefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        PrefsHelper prefsHelper = PrefsHelper.getInstance();
        prefsHelper.init(mPrefs, getResources());

        mArrayMenuItems = prefsHelper.getMenuItems(mPrefs);

        mWebViewClient = new WebViewClientTemplate(progressBar);
        mWebViewMain.getSettings().setJavaScriptEnabled(true);
        mWebViewMain.setWebViewClient(mWebViewClient);
        int selectedPosition = prefsHelper.getMenuSelectedPosition(mPrefs);
        mWebViewMain.loadUrl(mArrayMenuItems.get(selectedPosition).getUrl());
        setSelectedMenuItem(selectedPosition);
    }

    private void setSelectedMenuItem(int aPosition) {
        switch(aPosition) {
            case 0:
                mLinearLayoutMenuItem1.setBackgroundColor(PrefsHelper.getInstance().getMenuBackgroundColorSelected(mPrefs));
                mLinearLayoutMenuItem2.setBackgroundColor(PrefsHelper.getInstance().getMenuBackgroundColor(mPrefs));
                mLinearLayoutMenuItem3.setBackgroundColor(PrefsHelper.getInstance().getMenuBackgroundColor(mPrefs));
                mLinearLayoutMenuItem4.setBackgroundColor(PrefsHelper.getInstance().getMenuBackgroundColor(mPrefs));
                break;
            case 1:
                mLinearLayoutMenuItem1.setBackgroundColor(PrefsHelper.getInstance().getMenuBackgroundColor(mPrefs));
                mLinearLayoutMenuItem2.setBackgroundColor(PrefsHelper.getInstance().getMenuBackgroundColorSelected(mPrefs));
                mLinearLayoutMenuItem3.setBackgroundColor(PrefsHelper.getInstance().getMenuBackgroundColor(mPrefs));
                mLinearLayoutMenuItem4.setBackgroundColor(PrefsHelper.getInstance().getMenuBackgroundColor(mPrefs));
                break;
            case 2:
                mLinearLayoutMenuItem1.setBackgroundColor(PrefsHelper.getInstance().getMenuBackgroundColor(mPrefs));
                mLinearLayoutMenuItem2.setBackgroundColor(PrefsHelper.getInstance().getMenuBackgroundColor(mPrefs));
                mLinearLayoutMenuItem3.setBackgroundColor(PrefsHelper.getInstance().getMenuBackgroundColorSelected(mPrefs));
                mLinearLayoutMenuItem4.setBackgroundColor(PrefsHelper.getInstance().getMenuBackgroundColor(mPrefs));
                break;
            case 3:
                mLinearLayoutMenuItem1.setBackgroundColor(PrefsHelper.getInstance().getMenuBackgroundColor(mPrefs));
                mLinearLayoutMenuItem2.setBackgroundColor(PrefsHelper.getInstance().getMenuBackgroundColor(mPrefs));
                mLinearLayoutMenuItem3.setBackgroundColor(PrefsHelper.getInstance().getMenuBackgroundColor(mPrefs));
                mLinearLayoutMenuItem4.setBackgroundColor(PrefsHelper.getInstance().getMenuBackgroundColorSelected(mPrefs));
                break;
        }
    }
}
