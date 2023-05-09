package com.example.moviesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ItemDescription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_description);
        String value = getIntent().getStringExtra("Name");
        TextView movieNameItemDescriptionPage = findViewById(R.id.movieNameItemDescriptionPage);
        movieNameItemDescriptionPage.setText(value);
    }
}