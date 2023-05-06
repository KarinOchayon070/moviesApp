package com.example.moviesapp;

import android.net.Uri;

public class Movie {
        private String id;
        private PrimaryImage primaryImage;
        private TitleType titleType;
        private TitleText titleText;
        private ReleaseYear releaseYear;
        private ReleaseDate releaseDate;


    public static class PrimaryImage {
        public String id;
        public int width;
        public int height;
        public String url;
        public Caption caption;
    }

    public static class Caption {
        public String plainText;
        public String __typename;
    }

    public static class TitleType {
        public String text;
        public String id;
        public boolean isSeries;
        public boolean isEpisode;
    }

    public static class TitleText {
        public String text;
    }

    public static class ReleaseYear {
        public int year;
        public Integer endYear;
    }

    public static class ReleaseDate {
        public Integer day;
        public Integer month;
        public int year;
    }

    public String getId(){
        return this.id;
    }

    public String getTitle(){
        return this.titleText.text;
    }

    public int getReleaseYear(){
        if(this.releaseYear != null){
            return this.releaseYear.year;
        }
        return -1;
    }

    public String getImage(){
        if(this.primaryImage != null){
            return this.primaryImage.url;
        }
        return null;
    }

}
