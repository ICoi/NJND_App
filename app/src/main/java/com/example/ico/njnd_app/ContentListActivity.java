package com.example.ico.njnd_app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;


public class ContentListActivity extends ActionBarActivity {
    private String contentID;
    public String titles[];
    public int maxButtonNum;
    private Button content[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        content = new Button[maxButtonNum];
        setContentView(R.layout.activity_content_list);
        GridLayout grid = (GridLayout)findViewById(R.id.gridLayout_activity_content_list);

        for(int num = 0;num<maxButtonNum; num++) {
            content[num] = new Button(this);
            content[num].setText(num);
            content[num].setId(num);
            content[num].setTextSize(20f);
            content[num].setHeight(350);
            content[num].setWidth(600);

                /*GridLayout.Spec row = GridLayout.spec(num%2,1);
                GridLayout.Spec col = GridLayout.spec(num%6,1);*/

            grid.addView(content[num],new GridLayout.LayoutParams(GridLayout.spec(num/2,GridLayout.CENTER),GridLayout.spec(num%2,GridLayout.CENTER)));
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
        Button btn = (Button)findViewById(R.id.btn_content_list_goNext);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ContentListActivity.this,ContentPageActivity.class);
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
