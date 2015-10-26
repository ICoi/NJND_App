package com.example.ico.model;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ico.njnd_app.R;

/**
 * Created by user on 2015-08-15.
 */
public class ScrollableLinearLayout extends RelativeLayout{
    LayoutInflater mInflater;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public ScrollableLinearLayout(final Context context, final String testURL,final String testTitle,final String testDate,final String testWriter,final String contentIDX,final String titleImgURL)
    {
        super(context);
        mInflater = LayoutInflater.from(context);
        mInflater.inflate(R.layout.activity_customgridlayout_content, this, true);

        TextView title = (TextView)findViewById(R.id.textTitle);
        TextView date = (TextView)findViewById(R.id.textDate);
        TextView writer = (TextView)findViewById(R.id.textWriter);
        ImageButton img = (ImageButton)findViewById((R.id.img_content_list));
        title.setText(testTitle.toString());
        date.setText(testDate.toString());
        writer.setText(testWriter.toString());

        //ButtonInfo btn = new ButtonInfo(context);

        //btn.setBackground(this.getResources().getDrawable(R.mipmap.trans));
        img.setScaleType(ImageView.ScaleType.FIT_XY);
        //img.setURL(titleImgURL);
        //btn.setLayoutParams(new MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        //btn.setMinimumWidth(150);
        //btn.setMaxHeight(80);
        //btn.setMaxWidth(80);
        //btn.setMinimumHeight(120);
        img.setTag(titleImgURL);
        new DownloadImage(img).execute();

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent();
                in.setComponent(new ComponentName("com.example.ico.njnd_app", testURL.toString()));
                in.putExtra("IDX", contentIDX);
                //in.putExtra("Category",name);
                context.startActivity(in);
            }
        });
        //this.addView(img);
    }

}
