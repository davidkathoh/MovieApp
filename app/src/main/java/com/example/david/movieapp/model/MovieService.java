package com.example.david.movieapp.model;

import com.example.david.movieapp.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by david on 8/14/18.
 */

public interface MovieService {
    @GET("discover/movie")
    Call<MovieResponse> getMovies(@Query("api_key") String apiKey);
    @GET("discover/movie")
    Call<MovieResponse>getSortedMOvies(@Query("api_key") String apikey,@Query("sort_by") String sortby);
}
