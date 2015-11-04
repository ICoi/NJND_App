package com.example.ico.model;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
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

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

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
        TextView tv = (TextView)findViewById(R.id.cateTitle);
        tv.setText(name);
        ImageButton img = (ImageButton)findViewById((R.id.img_menu));
       // TextView tv = (TextView)findViewById(R.id.textURL);
       // tv.setText(testText.toString());

       // ButtonInfo btn = new ButtonInfo(context);
        //img.setBackground(this.getResources().getDrawable(R.mipmap.content_menu_cell_img));
        //btn.setScaleType(ImageView.ScaleType.FIT_XY);
        //img.setURL(testText.toString());
        //btn.setLayoutParams(new MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
       // btn.setMinimumWidth(150);
       // btn.setMaxHeight(120);
       // btn.setMinimumHeight(120);
        img.setTag(cateImgURL);
        new DownloadImage(img).execute();

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent();
                in.setComponent(new ComponentName("com.example.ico.njnd_app", testText.toString()));
                in.putExtra("IDX",cateID);
                in.putExtra("Category",name);
                context.startActivity(in);
            }
        });
        //this.addView(btn);
    }

}
class DownloadImage extends AsyncTask<ImageButton, Void, Bitmap> {

    ImageButton Ib = null;
    ImageButton imgbtn;
    public DownloadImage(ImageButton imgbtn){
        this.imgbtn = imgbtn;
    }

    @Override
    protected Bitmap doInBackground(ImageButton... imageButton) {
        this.Ib = imgbtn;
        return download_Image((String)Ib.getTag());

    }
    @Override
    protected  void onPostExecute(Bitmap result){
        Ib.setImageBitmap(result);
    }

    private Bitmap download_Image(String tag) {
        Bitmap bm = null;
        try{
            URL url = new URL(tag);
            URLConnection conn = url.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
            return bm;
        }catch (Exception e){}
        return bm;
    }
}
