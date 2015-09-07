package com.example.ico.njnd_app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;



public class HotFashionActivity extends Activity {
    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_fashion);
        webview = (WebView)findViewById(R.id.webview);

        webview.setWebViewClient(new WebClient()); // 응용프로그램에서 직접 url 처리
        WebSettings set = webview.getSettings();
        set.setJavaScriptEnabled(true);
        webview.loadUrl("http://namjungnaedle123.cafe24.com:3000/web/hot_fashion_list");



    }

    class WebClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
