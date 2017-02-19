package com.example.antop.recylerview;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class CommonActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button btnRemove;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        context = this;
        btnRemove = (Button) findViewById(R.id.btnRemove);
        imageView = (ImageView) findViewById(R.id.imageView);
        Picasso.with(context).load(getIntent().getStringExtra("url")).into(imageView);
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
