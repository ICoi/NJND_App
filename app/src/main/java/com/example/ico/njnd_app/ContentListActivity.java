package com.example.ico.njnd_app;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
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
    private String contentID[];
    private String contentIDx[];
    private String likes[];
    private int clothIdxs[][];
    private String dateTime[];

    public String titles[];
    private String category;
    public int maxButtonNum = 20;
    private int contentNum;

    private Button content[];
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        client = new AsyncHttpClient();

        Intent intent = getIntent();
      //  category = intent.getExtras().getString("category");
        //TextView cateLabel = (TextView)findViewById(R.id.categoryLabel);
        //cateLabel.setText(category);

        content = new Button[maxButtonNum];
        Drawable top = getResources().getDrawable(R.mipmap.ic_launcher);
        setContentView(R.layout.activity_content_list);

        LinearLayout grid = (LinearLayout)findViewById(R.id.linearLayout_activity_content_list);
        //How to script request
        final RequestHandle requestHandle = client.get("http://namjungnaedle123.cafe24.com:3000/app/board?cateID=1&pageNum=1", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                String resStr = new String(response);
                try {
                    JSONObject object = new JSONObject(resStr);
                    contentNum = object.getInt("contentNum");
                    JSONArray jArry = new JSONArray(object.getString("rankedDatas"));
                    for (int i = 0; i < jArry.length(); i++) {
                        JSONObject inObject = jArry.getJSONObject(i);
                        likes[i] = inObject.getString("likes");

                        JSONArray clothIdArry = new JSONArray(inObject.getString("clothIdxs"));
                        for(int k = 0;k<clothIdArry.length();k++) {
                            clothIdxs[i][k]  = clothIdArry.getInt(k);

                        }
                            Toast.makeText(getApplicationContext(), inObject.getString("name"), Toast.LENGTH_SHORT).show();

                    }

                    Toast.makeText(getApplicationContext(), object.getString("status"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            }
        });

        for(int num = 0;num<contentNum; num++) {
            ScrollableLinearLayout ll = new ScrollableLinearLayout(ContentListActivity.this,"com.example.ico.njnd_app.ContentPageActivity", "GetContentTitle", "Date", "Writer");

            /*content[num] = new Button(this);
            content[num].setCompoundDrawablesWithIntrinsicBounds(null,top,null,null);
            content[num].setText("" + num);
            content[num].setId(num);
            content[num].setBackground(this.getResources().getDrawable(R.mipmap.ic_launcher));
            content[num].setTextSize(20f);
            content[num].setHeight(500);
            content[num].setWidth(700);
            content[num].setGravity(Gravity.CENTER);
                GridLayout.Spec row = GridLayout.spec(num%2,1);
                GridLayout.Spec col = GridLayout.spec(num%6,1);

*/
            grid.addView(ll);

        }



//        setOnClickListener();
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
}
