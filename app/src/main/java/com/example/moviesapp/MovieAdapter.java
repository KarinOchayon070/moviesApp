package com.example.moviesapp;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    private Context context;
    private List<Movie> movieList;

    public MovieAdapter(Context context, List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }



    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item , parent , false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.movieNameItemPage.setText(movie.getTitle());
        holder.movieRatingItemPage.setText(movie.getRating().toString());
        holder.movieOverviewItemPage.setText(movie.getOverview());

        Log.d("tag" , movie.getTitle() + movie.getRating().toString() + movie.getOverview());


    }

    @Override
    public int getItemCount() {
        int size = movieList.size();
        Log.d("size", "the value" + size);
        return movieList.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder{

        ImageView movieImageItemPage;
        TextView movieNameItemPage , movieRatingItemPage , movieOverviewItemPage;

        public MovieHolder(@NonNull View itemView) {
            super(itemView);

//            movieImageItemPage = itemView.findViewById(R.id.movieImageItemPage);
            movieNameItemPage = itemView.findViewById(R.id.movieNameItemPage);
            movieRatingItemPage = itemView.findViewById(R.id.movieRatingItemPage);
            movieOverviewItemPage = itemView.findViewById(R.id.movieOverviewItemPage);

        }
    }

}

