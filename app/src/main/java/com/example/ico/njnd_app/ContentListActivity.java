package com.example.ico.njnd_app;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.AvoidXfermode;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ico.model.ScrollableGridLayout;
import com.example.ico.model.ScrollableLinearLayout;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestHandle;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ContentListActivity extends Activity {

    private AsyncHttpClient client;

    private ScrollableLinearLayout[] layoutWord = new ScrollableLinearLayout[12];
    private String contentID[];
    private String contentIDx[];
    private String likes[];
    private int clothIdxs[][];
    private String dateTime[];
    private String editor[];
    private String titleImgURL[];
    public String titles[];
    private String category;
    public int maxButtonNum = 20;
    private int contentNum;


    private String idx;

    private Button content[];
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        client = new AsyncHttpClient();

        Intent intent = getIntent();
        idx = intent.getExtras().getString("IDX");
        category = intent.getExtras().getString("Category");

        content = new Button[maxButtonNum];
        Drawable top = getResources().getDrawable(R.mipmap.ic_launcher);
        setContentView(R.layout.activity_content_list);
        TextView cateLabel = (TextView)findViewById(R.id.categoryLabel);
        cateLabel.setText(category);


        //How to script request
        final RequestHandle requestHandle = client.get("http://namjungnaedle123.cafe24.com:3000/app/board?cateID=" + idx + "&pageNum=0", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                String resStr = new String(response);
                try {
                    JSONObject object = new JSONObject(resStr);
                        contentNum = object.getInt("contentsNum");

                    JSONArray jArry = new JSONArray(object.getString("datas"));
                    likes = new String[jArry.length()];
                    titles  = new String[jArry.length()];
                    titleImgURL  = new String[jArry.length()];
                    editor  = new String[jArry.length()];
                    dateTime  = new String[jArry.length()];
                    contentIDx  = new String[jArry.length()];
                    for (int i = 0; i < jArry.length(); i++) {
                        JSONObject inObject = jArry.getJSONObject(i);
                        likes[i] = inObject.getString("likes");
                        titles[i] = inObject.getString("title");
                        titleImgURL[i] = inObject.getString("titleImg");
                        editor[i] = "test"; //inObject.getString("editor");
                        dateTime[i] = inObject.getString("dateTime");
                        contentIDx[i] = inObject.getString("contentIdx");

                    }

                    Toast.makeText(getApplicationContext(), object.getString("status"), Toast.LENGTH_SHORT).show();
                    LinearLayout grid = (LinearLayout)findViewById(R.id.linearLayout_activity_content_list);
                    for(int num = 0;num<jArry.length(); num++) {
                        layoutWord[num] = new ScrollableLinearLayout(ContentListActivity.this, "com.example.ico.njnd_app.ContentPageActivity",titles[num],dateTime[num],editor[num],contentIDx[num],titleImgURL[num]);
                        //LinearLayout.LayoutParams param =  new LinearLayout.LayoutParams(LinearLayout.spec(num / 2, LinearLayout.CENTER),GridLayout.spec(num % 2, GridLayout.CENTER));
                        //param.width = 500;
                        //param.leftMargin = 20;
                        //param.height = 400;
                        //param.bottomMargin = 10;

                        grid.addView(layoutWord[num]/*, param*/);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            }
        });





//        setOnClickListener();

        testToGoNext();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_content_list, menu);
        return true;
    }

    public void setOnClickListener() {
        for(int i = 0; i<contentNum;i++) {
            content[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(ContentListActivity.this, ContentPageActivity.class);
                    startActivity(i);
                }
            });
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

    public void testToGoNext(){
        Button btn = (Button)findViewById(R.id.btn_content_list_goNext);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(ContentListActivity.this, ContentPageActivity.class);
                startActivity(i);

            }
        });
    }
}
