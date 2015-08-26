package com.example.ico.model;

import android.content.Context;
import android.widget.Button;

import org.w3c.dom.Text;

/**
 * Created by user on 2015-08-15.
 */
public class ButtonInfo extends Button {

    private String URL;

    public ButtonInfo(Context context) {
        super(context);

    }
    public ButtonInfo(Context context, String text) {
        super(context);
        URL = text;
    }

    public void setURL(String text)
    {
        URL = text;
    }

    public String getURL() {
        return URL;
    }
}
