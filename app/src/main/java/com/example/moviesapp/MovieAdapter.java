package com.example.moviesapp;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    private Context context;
    private List<Movie> movieList = new ArrayList<>();
    private RecyclerViewInterface recyclerViewInterface;

    public MovieAdapter(Context context, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.recyclerViewInterface = recyclerViewInterface;
    }


    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item , parent , false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        Movie movie = this.movieList.get(position);
        Glide.with(context).load(movie.getImage()).error(R.drawable.ic_baseline_heart_broken_24).into(holder.movieImageItemPage);
        holder.movieNameItemPage.setText(movie.getTitle());
        holder.movieDateItemPage.setText(String.valueOf(movie.getReleaseYear()));
        holder.movieIdItemPage.setText(movie.getId());
    }

    @Override
    public int getItemCount() {
        return this.movieList.size();
    }

    public void addMovies(List<Movie> movies){
        this.movieList.addAll(movies);
    }

    public void clearMovies(){
        this.movieList.clear();
    }

    public class MovieHolder extends RecyclerView.ViewHolder{
        ImageView movieImageItemPage;
        TextView movieNameItemPage , movieDateItemPage , movieIdItemPage;

        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            movieImageItemPage = itemView.findViewById(R.id.movieImageItemPage);
            movieNameItemPage = itemView.findViewById(R.id.movieNameItemPage);
            movieDateItemPage = itemView.findViewById(R.id.movieDateItemPage);
            movieIdItemPage = itemView.findViewById(R.id.movieIdItemPage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recyclerViewInterface != null){
                        int position = getAdapterPosition();
                        // Checking if the position is valid
                        if(position != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(position);
                        }
                    }
                }
            });

        }
    }

}

