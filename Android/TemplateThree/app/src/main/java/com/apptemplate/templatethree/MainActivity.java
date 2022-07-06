package com.apptemplate.templatethree;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String LOG = "MainActivity";

    private ListView mListViewMenu;
    private WebView mWebViewMain;
    private LinearLayout mLinearLayoutMenu;
    private Button mButtonMenu;

    private ListAdapterMenu mAdapterMenu;
    private WebViewClientTemplate mWebViewClient;
    private SharedPreferences mPrefs;
    private ArrayList<MenuItemT> mArrayMenuItems;
    private int mMenuOpenTopMargin;
    private int mMenuClosedTopMargin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListViewMenu = (ListView)findViewById(R.id.listViewMenu);
        mWebViewMain = (WebView)findViewById(R.id.webViewMain);
        mLinearLayoutMenu = (LinearLayout)findViewById(R.id.linearLayoutMenu);
        mButtonMenu = (Button)findViewById(R.id.buttonMenu);
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.progressBar);

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
                PrefsHelper.getInstance().setMenuSelectedPosition(mPrefs, position);
                toggleMenu();
            }
        });
        mListViewMenu.setBackgroundColor(prefsHelper.getMenuBackgroundColor(mPrefs));

        mMenuClosedTopMargin = (int)getResources().getDimension(R.dimen.menu_closed_margin_top);
        mWebViewMain.post(new Runnable() {
            @Override
            public void run() {
                int bottom = mWebViewMain.getBottom();
                mMenuOpenTopMargin = -bottom + mMenuClosedTopMargin;
            }
        });

        mButtonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleMenu();
            }
        });

        mWebViewMain.getSettings().setJavaScriptEnabled(true);
        mWebViewClient = new WebViewClientTemplate(progressBar);
        mWebViewMain.setWebViewClient(mWebViewClient);
        int selectedPosition = prefsHelper.getMenuSelectedPosition(mPrefs);
        mWebViewMain.loadUrl(mArrayMenuItems.get(selectedPosition).getUrl());
        mAdapterMenu.setSelectedItem(selectedPosition);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        if(isMenuOpen()) {
            toggleMenu();
        } else {
            super.onBackPressed();
        }
    }

    private void toggleMenu() {
        final ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams)mLinearLayoutMenu.getLayoutParams();
        if(params.topMargin > mMenuOpenTopMargin) {
            ValueAnimator animation = ValueAnimator.ofInt(params.topMargin, mMenuOpenTopMargin);
            animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator aValueAnimator) {
                    params.topMargin = (Integer)aValueAnimator.getAnimatedValue();
                    mLinearLayoutMenu.setLayoutParams(params);
                }
            });
            animation.setDuration(ConstantsT.kMenuOpenAnimationDuration);
            animation.start();
            animation.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {}

                @Override
                public void onAnimationEnd(Animator animation) {
                    mButtonMenu.setBackgroundResource(R.drawable.button_menu_open_xml);
                }

                @Override
                public void onAnimationCancel(Animator animation) {}

                @Override
                public void onAnimationRepeat(Animator animation) {}
            });
        } else {
            ValueAnimator animation = ValueAnimator.ofInt(params.topMargin, mMenuClosedTopMargin);
            animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator aValueAnimator) {
                    params.topMargin = (Integer)aValueAnimator.getAnimatedValue();
                    mLinearLayoutMenu.setLayoutParams(params);
                }
            });
            animation.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {}

                @Override
                public void onAnimationEnd(Animator animation) {
                    mButtonMenu.setBackgroundResource(R.drawable.button_menu_closed_xml);
                }

                @Override
                public void onAnimationCancel(Animator animation) {}

                @Override
                public void onAnimationRepeat(Animator animation) {}
            });
            animation.setDuration(ConstantsT.kMenuCloseAnimationDuration);
            animation.start();
        }
    }

    private boolean isMenuOpen() {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams)mLinearLayoutMenu.getLayoutParams();
        if(params.topMargin > mMenuOpenTopMargin) {
            return false;
        }
        return true;
    }
}
