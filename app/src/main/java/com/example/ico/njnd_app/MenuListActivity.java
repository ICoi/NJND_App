package com.example.ico.njnd_app;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TableRow;


public class MenuListActivity extends ActionBarActivity {

    public int maxButtonNum = 12;
    private Button content[];
    private String text[] = {"123","345","456"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);
        content = new Button[maxButtonNum];

        GridLayout grid = (GridLayout)findViewById(R.id.gridLayout_activity_menu);

        for(int num = 0;num<maxButtonNum; num++) {
                content[num] = new Button(this);
                content[num].setText(text[num % 2]);
                content[num].setId(num);
                content[num].setTextSize(20f);
                content[num].setHeight(350);
                content[num].setWidth(600);

                /*GridLayout.Spec row = GridLayout.spec(num%2,1);
                GridLayout.Spec col = GridLayout.spec(num%6,1);*/

                grid.addView(content[num],new GridLayout.LayoutParams(GridLayout.spec(num/2,GridLayout.CENTER),GridLayout.spec(num%2,GridLayout.CENTER)));
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
        for(int i = 0 ; i < maxButtonNum ; i++) {
            final String name = text[i%2];
            content[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(MenuListActivity.this, ContentListActivity.class);
                    in.putExtra("clickedContentName", name);
                    startActivity(in);
                }
            });
        }
        Button btn2 = (Button)findViewById(R.id.btn_menu_list_goClothList);
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
