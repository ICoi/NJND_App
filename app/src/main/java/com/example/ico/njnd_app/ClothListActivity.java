package com.example.ico.njnd_app;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;


public class ClothListActivity extends Activity {
    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloth_list);
        webview = (WebView)findViewById(R.id.webview);

        webview.setWebViewClient(new WebClient()); // 응용프로그램에서 직접 url 처리
        WebSettings set = webview.getSettings();
        set.setJavaScriptEnabled(true);
        webview.loadUrl("http://namjungnaedle123.cafe24.com:3000/web/fitting_room_list");
    }

    class WebClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
            }
        }

}
