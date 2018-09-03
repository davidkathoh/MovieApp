package com.example.david.movieapp;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.david.movieapp.model.MovieModel;
import com.example.david.movieapp.model.MovieResponse;
import com.example.david.movieapp.model.MovieService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by david on 8/16/18.
 */

public class MovieVIewModel  extends ViewModel{

    private MutableLiveData<List<MovieModel>> moviesList;

    public LiveData<List<MovieModel>> getMovies(){
        if (moviesList == null){
            moviesList = new MutableLiveData<>();
            sendRequest();
        }
        return moviesList;
    }


    public  void  sendRequest(){
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://api.themoviedb.org/3/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        MovieService service = retrofit.create(MovieService.class);
//      //  Call<MovieResponse> call = service.getMovies(R.string.API_KEY);
//        call.enqueue(new Callback<MovieResponse>() {
//            @Override
//            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
//
//                moviesList.setValue(response.body().getMovies());
//
//            }
//
//            @Override
//            public void onFailure(Call<MovieResponse> call, Throwable t) {
//
//            }
//        });


    }


    }

