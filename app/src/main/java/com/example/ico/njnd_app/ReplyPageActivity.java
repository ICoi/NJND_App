package com.example.ico.njnd_app;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridLayout;

import com.example.ico.model.ScrollableLinearLayout_Reply;


public class ReplyPageActivity extends Activity {
    public int maxReply = 12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_page);
        GridLayout grid = (GridLayout)findViewById(R.id.gridLayout_activity_menu);

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
