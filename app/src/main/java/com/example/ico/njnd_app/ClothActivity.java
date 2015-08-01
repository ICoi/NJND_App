package com.example.ico.njnd_app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;



public class ClothActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloth);

        setOnClickListener();
    }

    private void setOnClickListener(){
        ImageButton headImgBtn = (ImageButton)findViewById(R.id.imgbtn_cloth_head);
        headImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "head Button", Toast.LENGTH_LONG);
                toast.show();
            }
        });
        ImageButton shirtsImgBtn = (ImageButton)findViewById(R.id.imgbtn_cloth_shirts);
        shirtsImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "shirts Button", Toast.LENGTH_LONG);
                toast.show();
            }
        });
        ImageButton coatImgBtn = (ImageButton)findViewById(R.id.imgbtn_cloth_coat);
        coatImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "coat Button", Toast.LENGTH_LONG);
                toast.show();
            }
        });

        ImageButton pantsImgBtn = (ImageButton)findViewById(R.id.imgbtn_cloth_pants);
        pantsImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "pants Button", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cloth, menu);
        return true;
    }

}
