package com.example.ico.njnd_app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;


public class ContentPageActivity extends Activity {
    private int pageCnt = 1;
    private int nowPage = 0;
    private JSONArray pageDatas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_page);

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://namjungnaedle123.cafe24.com:3000/app/board/view?contentID=2", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                try {
                    JSONObject object = new JSONObject(new String(bytes));
                    String checkSuccess = object.getString("status");
                    if(checkSuccess.equals("s")){
                        // if get data successfully
                        String textTitle = object.getString("title");
                        pageCnt = object.getInt("pagesNum");

                        pageDatas = new JSONArray(object.getString("datas"));
                       // Toast.makeText(getApplicationContext(), object.getString("datas"), Toast.LENGTH_SHORT).show();

                        /// set datas into ui
                        TextView tv = (TextView)findViewById(R.id.text_content_title);
                        tv.setText(textTitle);

                        tv = (TextView)findViewById(R.id.content_page_text);
                        tv.setText(pageDatas.getJSONObject(nowPage).getString("content"));

                        ImageView imgView = (ImageView)findViewById(R.id.img_content_image);
                        imgView.setImageBitmap(download_image(pageDatas.getJSONObject(nowPage).getString("img")));


                    }else{
                        Toast.makeText(getApplicationContext(), "Server Connection Error", Toast.LENGTH_SHORT).show();
                    }
                } catch(JSONException e){
                    e.printStackTrace();
                }
//                Toast.makeText(getApplicationContext(), new String(bytes), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            }
        });

        setOnClickListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_content_page, menu);
        return true;
    }

    public void setOnClickListener()
    {

        Button beforeBtn = (Button)findViewById(R.id.btn_content_page_goBefore);
        beforeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // TODO
                if(nowPage == 0){
                    Toast.makeText(getApplicationContext(),"Here is first page", Toast.LENGTH_SHORT).show();
                    finish();

                }else{
                    try {
                        TextView tv = (TextView) findViewById(R.id.content_page_text);
                        tv.setText(pageDatas.getJSONObject(--nowPage).getString("content"));
                    }catch(JSONException e){
                        e.printStackTrace();
                    }
                }
            }
        });
        Button nextBtn = (Button)findViewById(R.id.btn_content_page_goNext);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO
                if (nowPage == pageCnt - 1) {
                    Toast.makeText(getApplicationContext(), "Here is Last page", Toast.LENGTH_SHORT).show();
                    /*
                Intent i = new Intent(ContentPageActivity.this,ReplyPageActivity.class);
                startActivity(i);

                     */

                } else {
                    try {
                        TextView tv = (TextView) findViewById(R.id.content_page_text);
                        tv.setText(pageDatas.getJSONObject(++nowPage).getString("content"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private Bitmap download_image(String urlStr){
        try{
            URL url = new URL(urlStr);
            URLConnection conn = url.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            Bitmap bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
            return bm;

        }catch(Exception e){
            return null;
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
