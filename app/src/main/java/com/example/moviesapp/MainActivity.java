package com.example.moviesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerview;
    private List<Movie> movieList;
    private MovieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        movieList = new ArrayList<>();
        Movie movie = new Movie("DDD",  "dDD", 7.5);
        Movie movie2 = new Movie("karin", "overview", 100.6);
        movieList.add(movie);
        movieList.add(movie2);


        adapter = new MovieAdapter(this, movieList);
        recyclerview.setAdapter(adapter);


        if (movieList.isEmpty()) {
            Log.d("MainActivity", "Movie list is empty");
        } else {
            Log.d("MainActivity", "Movie list contains " + movieList.size() + " movies");
        }
    }
}