package com.apptemplate.templateone;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String LOG = "MainActivity";

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ListView mListViewMenu;
    private WebView mWebViewMain;

    private ListAdapterMenu mAdapterMenu;
    private WebViewClientTemplate mWebViewClient;
    private SharedPreferences mPrefs;
    private ArrayList<MenuItemT> mArrayMenuItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar)findViewById(R.id.toolbarMain);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawerLayoutMain);
        mListViewMenu = (ListView)findViewById(R.id.listViewMenuMain);
        mWebViewMain = (WebView)findViewById(R.id.webViewMain);
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.progressBar);

        mToolbar.setTitle(R.string.app_name);

        mPrefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        PrefsHelper prefsHelper = PrefsHelper.getInstance();
        prefsHelper.init(mPrefs, getResources());

        mArrayMenuItems = prefsHelper.getMenuItems(mPrefs);
        mAdapterMenu = new ListAdapterMenu(MainActivity.this, mArrayMenuItems);
        mListViewMenu.setAdapter(mAdapterMenu);
        mListViewMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mWebViewMain.loadUrl(mArrayMenuItems.get(position).getUrl());
                mAdapterMenu.setSelectedItem(position);
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                PrefsHelper.getInstance().setMenuSelectedPosition(mPrefs, position);
            }
        });
        mListViewMenu.setBackgroundColor(prefsHelper.getMenuBackgroundColor(mPrefs));

        mWebViewClient = new WebViewClientTemplate(progressBar);
        mWebViewMain.getSettings().setJavaScriptEnabled(true);
        mWebViewMain.setWebViewClient(mWebViewClient);
        int selectedPosition = prefsHelper.getMenuSelectedPosition(mPrefs);
        mWebViewMain.loadUrl(mArrayMenuItems.get(selectedPosition).getUrl());
        mAdapterMenu.setSelectedItem(selectedPosition);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.app_name, R.string.app_name);
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if(mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
            mDrawerLayout.closeDrawer(Gravity.LEFT);
        } else {
            super.onBackPressed();
        }
    }
}
