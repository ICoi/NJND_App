package com.example.ico.njnd_app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        setOnClickListener();
    }



    public void setOnClickListener(){
        Button btn = (Button)findViewById(R.id.btn_activity_main_goNext);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(MainActivity.this, MenuListActivity.class);
                startActivity(i);
            }
        });

    }

}
