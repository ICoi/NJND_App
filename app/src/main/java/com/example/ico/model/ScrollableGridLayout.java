package com.example.ico.model;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ico.njnd_app.ContentListActivity;
import com.example.ico.njnd_app.R;

/**
 * Created by user on 2015-08-15.
 */
public class ScrollableGridLayout extends RelativeLayout{
    LayoutInflater mInflater;

    public ScrollableGridLayout(final Context context, final String testText)
    {
        super(context);
        mInflater = LayoutInflater.from(context);
        mInflater.inflate(R.layout.activity_customgridlayout_test, this, true);

        TextView tv = (TextView)findViewById(R.id.textURL);
        tv.setText(testText.toString());

        ButtonInfo btn = new ButtonInfo(context);
        btn.setWidth(700);
        btn.setHeight(500);
        btn.setURL(testText.toString());
        btn.setGravity(Gravity.CENTER);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent in = new Intent();
                    in.setComponent(new ComponentName("com.example.ico.njnd_app", testText.toString()));
                    context.startActivity(in);
            }
        });
        this.addView(btn);
    }

}
