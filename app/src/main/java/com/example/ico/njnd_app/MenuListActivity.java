package com.example.ico.njnd_app;

import android.app.ActionBar;
import android.app.Activity;
import android.bluetooth.BluetoothClass;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.preference.PreferenceScreen;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ico.model.ScrollableGridLayout;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestHandle;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;


public class MenuListActivity extends Activity {
    private AsyncHttpClient client;

    public int maxButtonNum = 12;
    private ScrollableGridLayout[] layoutWord = new ScrollableGridLayout[maxButtonNum];
    GridLayout linear;
    private String text[] = {"123","345","456"};
    private String[] getname ;
    private String[] getcateID ;
    private String[] getcateURL ;
    private int contentNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);

        client = new AsyncHttpClient();
        Log.d("test","123");
        final RequestHandle requestHandle = client.get("http://namjungnaedle123.cafe24.com:3000/app/menu", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                String resStr = new String(response);
                try {
                    JSONObject object = new JSONObject(resStr);
                    contentNum = 2;//object.getInt("contentNum");
                    JSONArray jArry = new JSONArray(object.getString("categorys"));
                    getname = new String[jArry.length()];
                    getcateID  = new String[jArry.length()];
                    getcateURL = new String[jArry.length()];
                    for (int i = 0; i < jArry.length(); i++) {
                        JSONObject inObject = jArry.getJSONObject(i);
                        Toast.makeText(getApplicationContext(), inObject.getString("cate_name"), Toast.LENGTH_SHORT).show();
                        getname[i] = inObject.getString("cate_name");
                        getcateID[i] = inObject.getString("cate_idx");
                        getcateURL[i] = inObject.getString("cate_url");
                    }

                    linear = (GridLayout)findViewById(R.id.gridLayout_activity_menu);
                    for(int num = 0;num<jArry.length(); num++) {
                        layoutWord[num] = new ScrollableGridLayout(MenuListActivity.this, "com.example.ico.njnd_app.ContentListActivity",getname[num],getcateID[num],getcateURL[num]);
                        GridLayout.LayoutParams param =  new GridLayout.LayoutParams(GridLayout.spec(num / 2, GridLayout.CENTER),GridLayout.spec(num % 2, GridLayout.CENTER));
                        param.width = linear.getWidth()/2;
                        //param.leftMargin = 20;
                        param.height = 500;
                        param.bottomMargin = 20;

                        linear.addView(layoutWord[num], param);
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
        setOnClickListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu_list, menu);
        return true;
    }

    public void setOnClickListener()
    {
        ImageButton btn2 = (ImageButton)findViewById(R.id.btn_menu_list_goClothList);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuListActivity.this,ClothListActivity.class);
                startActivity(i);
            }

        });

        ImageButton btn3 = (ImageButton)findViewById(R.id.btn_menu_list_goHotFashion);
        btn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(MenuListActivity.this, HotFashionActivity.class);
                startActivity(i);
            }
        });
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
