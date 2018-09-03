package com.example.david.movieapp.model;

import com.example.david.movieapp.model.MovieModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by david on 8/14/18.
 */

public class MovieResponse {

    @SerializedName("results")
    @Expose
    private List<MovieModel> movies = null;

    public List<MovieModel> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieModel> movies) {
        this.movies = movies;
    }
}
