package com.error.grrravity.mynews.controllers.activities;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.error.grrravity.mynews.models.apiReturns.APIResult;
import com.error.grrravity.mynews.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;

public class ArticleActivity extends AppCompatActivity  {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.webView) WebView webView;


    private Disposable disposable;
    private String url;


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        ButterKnife.bind(this);

        url = getIntent().getStringExtra(APIResult.TOPSTORIES_EXTRA);

        updateUI(url);

        ConfigureToolbar();

        onConfigureWebView();

        onPageFinished();

    }

    //Set toolbar
    private void ConfigureToolbar(){
        setSupportActionBar(toolbar);
        // Get support ActionBar for the toolbar
        ActionBar ab = getSupportActionBar();
        // Enable "Up" button
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);
    }

    // Configure WebView
    @SuppressLint("SetJavaScriptEnabled")
    private void onConfigureWebView() {
        //Configure webView with javascript
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        // Allowing windows opening
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        //Allow storage of Document Object Model
        webSettings.setDomStorageEnabled(true);
    }

    // Display items when all is loaded
    public void onPageFinished() {
        webView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    //
    // UPDATE UI
    //

    //Show WebView
    protected void updateUI(String url){
        webView.loadUrl(url);
    }
}