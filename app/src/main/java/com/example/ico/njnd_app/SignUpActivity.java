package com.example.ico.njnd_app;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;


public class SignUpActivity extends Activity {
    private AsyncHttpClient client;

    private ImageView img;

    private String key;
    private String nickName;
    private String appID;

    private String getStatus;

    private EditText NickText;
    private Button Confirm;

    public RequestParams requestParams = new RequestParams();
    public JSONObject postBodyMsg = new JSONObject();

    StringEntity entity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_up);

        client = new AsyncHttpClient();

        NickText = (EditText)findViewById(R.id.NickNameEditText);
        Confirm = (Button)findViewById(R.id.ConfirmButton);


        //img = (ImageView)findViewById(R.id.image);
        setOnClickListener();
    }
    void setOnClickListener()
    {
        Confirm.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                key = new Date().toString();

                    requestParams.put("key",key);
                    requestParams.put("appID","");
                    requestParams.put("nickName",NickText.getText().toString());

                    //postBodyMsg.put("key",key);
                    //postBodyMsg.put("nickName",NickText.getText().toString());
                    //entity = new StringEntity(postBodyMsg.toString());

                    client.post("http://namjungnaedle123.cafe24.com:3000/app/login", requestParams, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int i, Header[] headers, byte[] bytes) {
                            String resStr = new String(bytes);

                            try {
                                JSONObject object = new JSONObject(resStr);
                                getStatus = object.getString("status");
                                if(getStatus.charAt(0) =='s')
                                {
                                    appID = object.getString("appID");
                                    PersistentCookieStore myCookieStore = new PersistentCookieStore(getApplicationContext());
                                    List<Cookie> cookies = myCookieStore.getCookies();

                                    // if Login status 's' than make cookie
                                    client.setCookieStore(myCookieStore);
                                    BasicClientCookie newCookie = new BasicClientCookie("AppID", appID);
                                    newCookie.setVersion(1);
                                    newCookie.setDomain("namjungnaedle123.cafe24.com:3000");
                                    newCookie.setPath("/");
                                    myCookieStore.addCookie(newCookie);

                                            Intent in = new Intent(SignUpActivity.this, MenuListActivity.class);
                                            startActivity(in);

                                }
                                else
                                {
                                    getStatus = "s";
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

                        }
                    });
                    /*client.post(getApplicationContext(), "http://namjungnaedle123.cafe24.com:3000/app/login", entity, "application/json", new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                            String resStr = new String(responseBody);

                            try {
                                JSONObject object = new JSONObject(resStr);
                                getStatus = object.getString("status");
                                if("s" == getStatus)
                                {
                                    appID = object.getString("appID");
                                    PersistentCookieStore myCookieStore = new PersistentCookieStore(getApplicationContext());
                                    List<Cookie> cookies = myCookieStore.getCookies();

                                    // if Login status 's' than make cookie
                                    client.setCookieStore(myCookieStore);
                                    BasicClientCookie newCookie = new BasicClientCookie("AppID", appID);
                                    newCookie.setVersion(1);
                                    newCookie.setDomain("namjungnaedle123.cafe24.com:3000");
                                    newCookie.setPath("/");
                                    myCookieStore.addCookie(newCookie);


                                    final Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            // Do something after 3s = 3000ms
                                            Intent i = new Intent(SignUpActivity.this, MenuListActivity.class);
                                            startActivity(i);
                                        }
                                    }, 3000);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

                        }
                    });*/

            }
        });

    }
}
