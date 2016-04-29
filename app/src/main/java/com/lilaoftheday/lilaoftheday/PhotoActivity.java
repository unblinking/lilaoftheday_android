package com.lilaoftheday.lilaoftheday;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toolbar;

public class PhotoActivity extends Activity {

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        Toolbar toolbar;
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setActionBar(toolbar);
        if (getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            ImageView imageView = (ImageView) findViewById(R.id.photo);
            int imageResourceId = getIntent().getIntExtra("image_resource_id", R.mipmap.ic_launcher);
            imageView.setImageResource(imageResourceId);
        }

    }

}

