package com.example.ico.njnd_app;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;

import org.apache.http.Header;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.cookie.BasicClientCookie;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


public class MainActivity extends Activity {
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //AsyncHttpClient client = new AsyncHttpClient();
        PersistentCookieStore myCookieStore = new PersistentCookieStore(getApplicationContext());
        List<Cookie> cookies = myCookieStore.getCookies();

        if(cookies.isEmpty()){
            //Toast.makeText(getApplicationContext(), "no cookies", Toast.LENGTH_SHORT).show();

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Do something after 3s = 3000ms
                    Intent i = new Intent(MainActivity.this, SignUpActivity.class);
                    startActivity(i);
                    finish();
                }
            },1000);
            // move intent to Sign up page
/*
            client.setCookieStore(myCookieStore);
            BasicClientCookie newCookie = new BasicClientCookie("AppID", "awesome");
            newCookie.setVersion(1);
            newCookie.setDomain("namjungnaedle123.cafe24.com:3000");
            newCookie.setPath("/");
            myCookieStore.addCookie(newCookie);
            */

        }else{
           // Toast.makeText(getApplicationContext(),"HAS cookies // " + cookies.get(0).getName() + " - " + cookies.get(0).getValue(),Toast.LENGTH_SHORT).show();
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Do something after 3s = 3000ms
                    Intent i = new Intent(MainActivity.this, MenuListActivity.class);
                    startActivity(i);
                    finish();
                }
            }, 3000);
        }
      //  img = (ImageView)findViewById(R.id.image);
    }
}
