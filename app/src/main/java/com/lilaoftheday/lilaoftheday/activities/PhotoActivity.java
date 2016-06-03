package com.lilaoftheday.lilaoftheday.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.lilaoftheday.lilaoftheday.R;

public class PhotoActivity extends AppCompatActivity {

    /*String id;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        // Create a toolbar.
        android.support.v7.widget.Toolbar toolbar;
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            ImageView imageView = (ImageView) findViewById(R.id.photo);
            int imageResourceId = getIntent().getIntExtra("image_resource_id", R.mipmap.ic_launcher);
            if (imageView != null) {
                imageView.setImageResource(imageResourceId);
            }
        }

    }

}

