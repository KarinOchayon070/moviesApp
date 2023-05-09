package com.example.moviesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Switch;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface {
    private MovieAdapter movieAdapter = new MovieAdapter(MainActivity.this, this);
    private  List<Movie> movieList = new ArrayList<>();
    private RecyclerView recyclerview;

    // This is the variables the i transfer to the request line
    private String page = "1";
    private String year = "year.decr";
    private String genre = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // The "switch button" is used to change the order of the year the movies were released.
        // By default, the movies are arranged from newest to oldest.
        // When the switch is pressed for the first time, the movies will be sorted from oldest to newest.

        Switch switchOrder = findViewById(R.id.switchOrder);
        switchOrder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                // Show movies starting page 1 of the API
                page = "1";
                if (isChecked) {
                    year = "year.incr";
                } else {
                    year = "year.decr";
                }
                // Clean all the movies currently in the array and bring them again
                movieAdapter.clearMovies();
                fetchMovies();
            }
        });


        // When we want to receive movies from a certain genre

        EditText editTextGenre = findViewById(R.id.editTextTSearch);
        editTextGenre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // called before the text is changed
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // called as the text is changed
            }
            @Override
            public void afterTextChanged(Editable editable) {
                // Again - Show movies starting page 1 of the API, clean all the movies currently in the array and bring them again
                    genre = editTextGenre.getText().toString();
                    page = "1";
                    movieAdapter.clearMovies();
                    fetchMovies();
            }
        });

        // This is the default - identifying the recyclerView and
        recyclerview = findViewById(R.id.recyclerViewItemPage);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        fetchMovies();

        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                // Check if the user has scrolled to the bottom of the list
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int totalItemCount = layoutManager.getItemCount();
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                boolean endHasBeenReached = lastVisibleItemPosition + 1 >= totalItemCount;

                // If the user has scrolled to the bottom, fetch more movies
                if (totalItemCount > 0 && endHasBeenReached) {
                    fetchMovies();
                }
            }
        });
    }



    private void fetchMovies(){
        String url = "https://moviesdatabase.p.rapidapi.com/titles";
        url += "?sort=" + this.year;
        url += "&page=" + this.page;
        if(!this.genre.isEmpty()){
            url += "&genre=" + this.genre;
        }

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("X-RapidAPI-Key", "1e5c2595e5mshc18e0b33083353ap195da8jsn6d7b91d03321")
                .addHeader("X-RapidAPI-Host", "moviesdatabase.p.rapidapi.com")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Handle the failure
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // Parse the JSON response and update the adapter with the movie data
                String responseBody = response.body().string();
                movieList = parseMovieData(responseBody);
                if(movieList.isEmpty()){
                    Log.d("LogEmpty", "Movie list is empty");
                }
                else{
                    movieAdapter.addMovies(movieList);
                    runOnUiThread(() -> recyclerview.setAdapter(movieAdapter));
                }
            }
        });

    }

    private List<Movie> parseMovieData(String responseBody) {
        Gson gson = new Gson();
        MovieApiResponse response = gson.fromJson(responseBody, MovieApiResponse.class);
        this.page = String.valueOf(Integer.parseInt(response.getPage()) + 1);
        return response.getMovies();
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(MainActivity.this, ItemDescription.class);
        intent.putExtra("name", movieList.get(position).getTitle());
        intent.putExtra("image", movieList.get(position).getImage());
        intent.putExtra("releaseYear", movieList.get(position).getReleaseYear());
        startActivity(intent);
    }
}