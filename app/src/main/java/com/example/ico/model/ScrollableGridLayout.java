package com.example.ico.model;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
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

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public ScrollableGridLayout(final Context context, final String testText,final String name,final String cateID, final String cateImgURL)
    {
        super(context);
        mInflater = LayoutInflater.from(context);
        mInflater.inflate(R.layout.activity_customgridlayout_test, this, true);

        TextView tv = (TextView)findViewById(R.id.textURL);
        tv.setText(testText.toString());

        ButtonInfo btn = new ButtonInfo(context);

        btn.setBackground(this.getResources().getDrawable(R.mipmap.content_menu_cell_img));
        btn.setScaleType(ImageView.ScaleType.FIT_XY);
        btn.setURL(testText.toString());
        btn.setLayoutParams(new MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        btn.setMinimumWidth(150);
        btn.setMaxHeight(120);
        btn.setMinimumHeight(120);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent();
                in.setComponent(new ComponentName("com.example.ico.njnd_app", testText.toString()));
                in.putExtra("category",cateID);
                context.startActivity(in);
            }
        });
        this.addView(btn);
    }

}
