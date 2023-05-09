package com.example.moviesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ItemDescription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_description);

        String movieName = getIntent().getStringExtra("movieName");
        int movieReleaseYear = getIntent().getIntExtra("movieReleaseYear", 0);
        String movieImage = getIntent().getStringExtra("movieImage");

        TextView movieNameItemDescriptionPage = findViewById(R.id.movieNameItemDescriptionPage);
        TextView movieReleaseYearItemDescriptionPage = findViewById(R.id.movieReleaseYearItemDescriptionPage);
        ImageView movieImageItemDescriptionPage = findViewById(R.id.movieImageItemDescriptionPage);


        movieNameItemDescriptionPage.setText(movieName);
        movieReleaseYearItemDescriptionPage.setText(String.valueOf(movieReleaseYear));

        Glide.with(this).load(movieImage).error(R.drawable.ic_baseline_heart_broken_24).into(movieImageItemDescriptionPage);
    }
}
