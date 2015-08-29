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

import com.example.ico.model.ScrollableGridLayout;


public class MenuListActivity extends Activity {

    public int maxButtonNum = 12;
    private ScrollableGridLayout[] layoutWord = new ScrollableGridLayout[maxButtonNum];
    GridLayout linear;
    private String text[] = {"123","345","456"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);


        linear = (GridLayout)findViewById(R.id.gridLayout_activity_menu);
        for(int num = 0;num<maxButtonNum; num++) {
        layoutWord[num] = new ScrollableGridLayout(MenuListActivity.this, "com.example.ico.njnd_app.ContentListActivity");
            GridLayout.LayoutParams param =  new GridLayout.LayoutParams(GridLayout.spec(num / 2, GridLayout.CENTER),GridLayout.spec(num % 2, GridLayout.CENTER));
            param.width = 520;
            param.leftMargin = 20;
            param.height = 400;
            param.bottomMargin = 10;
            linear.addView(layoutWord[num], param);
        }


    /*
        String text[] =  {"123","456","789"};

        for (int i = 0 ; i < text.length; i++) {
              this.text[i] = text[i];
        }
    */



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
