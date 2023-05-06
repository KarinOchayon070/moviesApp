package com.example.moviesapp;

public class Movie {

    private String title, overview;
    private Double rating;

    public Movie(String title, String overview, Double rating){
        this.title = title;
//        this.poster = poster;
        this.overview = overview;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

//    public String getPoster() {
//        return poster;
//    }

    public String getOverview() {
        return overview;
    }

    public Double getRating() {
        return rating;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//    public void setPoster(String poster) {
//        this.poster = poster;
//    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
