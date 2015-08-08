package com.example.ico.njnd_app;

import android.annotation.TargetApi;
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


public class ContentListActivity extends ActionBarActivity {
    private String contentID;
    public String titles[];

    public int maxButtonNum = 20;

    private Button content[];
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        content = new Button[maxButtonNum];
        Drawable top = getResources().getDrawable(R.mipmap.ic_launcher);
        setContentView(R.layout.activity_content_list);

        GridLayout grid = (GridLayout)findViewById(R.id.gridLayout_activity_content_list);

        for(int num = 0;num<maxButtonNum; num++) {
            content[num] = new Button(this);
            content[num].setCompoundDrawablesWithIntrinsicBounds(null,top,null,null);
            content[num].setText("" + num);
            content[num].setId(num);
            content[num].setBackground(this.getResources().getDrawable(R.mipmap.cloth_bg));
            content[num].setTextSize(20f);
            content[num].setHeight(500);
            content[num].setWidth(700);
            content[num].setGravity(Gravity.CENTER);
                /*GridLayout.Spec row = GridLayout.spec(num%2,1);
                GridLayout.Spec col = GridLayout.spec(num%6,1);*/

            grid.addView(content[num], new GridLayout.LayoutParams(GridLayout.spec(num / 2, GridLayout.CENTER), GridLayout.spec(num % 2, GridLayout.CENTER)));
        }



        setOnClickListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_content_list, menu);
        return true;
    }

    public void setOnClickListener() {
        for(int i = 0; i<maxButtonNum;i++) {
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
