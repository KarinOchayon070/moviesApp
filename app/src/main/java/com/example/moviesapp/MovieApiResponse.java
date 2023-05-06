package com.example.moviesapp;

import java.util.List;

public class MovieApiResponse {
    private List<Movie> results;
    private String page;
    private String next;
    private int entries;


    public List<Movie> getMovies() {
        return results;
    }

    public String getPage() {
        return page;
    }
}
