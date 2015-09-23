package com.example.ico.njnd_app;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.ico.model.ScrollableLinearLayout;
import com.example.ico.model.ScrollableLinearLayout_Reply;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestHandle;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ReplyPageActivity extends Activity{
    public int maxReply = 12;
    AsyncHttpClient client;

    private String nick[];
    private String comment[];
    private int commentNum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_page);
        GridLayout grid = (GridLayout)findViewById(R.id.gridLayout_activity_menu);

        final RequestHandle requestHandle = client.get("http://namjungnaedle123.cafe24.com:3000/app/board?cateID=1&pageNum=1", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                String resStr = new String(response);
                try {
                    JSONObject object = new JSONObject(resStr);
                    commentNum = object.getInt("commentNum");
                    JSONArray jArry = new JSONArray(object.getString("datas"));
                    for (int i = 0; i < jArry.length(); i++) {
                        JSONObject inObject = jArry.getJSONObject(i);
                        nick[i] = inObject.getString("nick");
                        comment[i] = inObject.getString("comment");

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

        for(int num = 0;num<maxReply; num++) {
            ScrollableLinearLayout_Reply ll = new ScrollableLinearLayout_Reply(ReplyPageActivity.this, "GetContent", "Date", "Writer");

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

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reply_page, menu);
        return true;
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
