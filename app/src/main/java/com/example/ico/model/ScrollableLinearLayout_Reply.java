package com.example.ico.model;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ico.njnd_app.R;

/**
 * Created by user on 2015-08-15.
 */
public class ScrollableLinearLayout_Reply extends RelativeLayout{
    LayoutInflater mInflater;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public ScrollableLinearLayout_Reply(final Context context, final String testContent, final String testDate, final String testWriter)
    {
        super(context);
        mInflater = LayoutInflater.from(context);
        mInflater.inflate(R.layout.activity_customgridlayout_content, this, true);

        TextView content = (TextView)findViewById(R.id.textTitle);
        TextView date = (TextView)findViewById(R.id.textDate);
        TextView writer = (TextView)findViewById(R.id.textWriter);

        content.setText(testContent.toString());
        date.setText(testDate.toString());
        writer.setText(testWriter.toString());

        ButtonInfo btn = new ButtonInfo(context);

        btn.setBackground(this.getResources().getDrawable(R.mipmap.trans));
        btn.setScaleType(ImageView.ScaleType.FIT_XY);
        //btn.setURL(testText.toString());
        btn.setLayoutParams(new MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        btn.setMinimumWidth(150);
        btn.setMaxHeight(120);
        btn.setMinimumHeight(120);

        //btn.setOnClickListener(new OnClickListener() {
           // @Override
          //  public void onClick(View v) {
        //        Intent in = new Intent();
        //        in.setComponent(new ComponentName("com.example.ico.njnd_app", testText.toString()));
        //        context.startActivity(in);
        //    }
        //});
        this.addView(btn);
    }

}
