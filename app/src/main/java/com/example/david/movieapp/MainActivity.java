package com.example.david.movieapp;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.david.movieapp.model.MovieModel;
import com.example.david.movieapp.model.MovieResponse;
import com.example.david.movieapp.model.MovieService;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private final String PATH_POPULARITY = "popularity.asc";
    private final String PATH_HIGH_RATED = "vote_average.asc";
    private MovieAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private LinearLayout mInternetInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycle_view);
        mInternetInfo = findViewById(R.id.ll_internet);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
        mRecyclerView.setHasFixedSize(true);

        if (isConnected()){
            mInternetInfo.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
            makeRequest(false,null);

        }else {
            mInternetInfo.setVisibility(View.VISIBLE);
        }

    }

    public  void  makeRequest( boolean sorted, String sortBy){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MovieService service = retrofit.create(MovieService.class);

        if (!sorted) {

            Call<MovieResponse> call = service.getMovies(getString(R.string.API_KEY));
            call.enqueue(new Callback<MovieResponse>() {
                @Override
                public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                    mAdapter = new MovieAdapter(response.body().getMovies());
                    mRecyclerView.setAdapter(mAdapter);


                }

                @Override
                public void onFailure(Call<MovieResponse> call, Throwable t) {
                    Log.e("Failure", t.getMessage());

                }
            });
        }else {
            Call<MovieResponse> call = service.getSortedMOvies(getString(R.string.API_KEY),sortBy);
            call.enqueue(new Callback<MovieResponse>() {
                @Override
                public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                    mAdapter = new MovieAdapter(response.body().getMovies());
                    mRecyclerView.setAdapter(mAdapter);

                }

                @Override
                public void onFailure(Call<MovieResponse> call, Throwable t) {

                }
            });

        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sorting_menu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        ActionBar ab = getSupportActionBar();
        switch (item.getItemId()){
            case R.id.action_popular:
                ab.setTitle(R.string.ab_title_poupular_movie);
                makeRequest(true,PATH_POPULARITY);
                return(true);
            case R.id.action_top_rated:
                ab.setTitle(R.string.ab_title_top_rate_movie);
                makeRequest(true,PATH_HIGH_RATED);
                return(true);
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean isConnected() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }



    private class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{
        List<MovieModel> mList;

        public MovieAdapter(List<MovieModel> list) {
            mList = list;
        }

        @Override
        public MovieAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.recycle_view,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MovieAdapter.ViewHolder holder, int position) {
            holder.mTitle.setText(mList.get(position).getTitle());
            holder.mRating.setText(String.valueOf(mList.get(position).getVoteAverage()));
            Picasso.get().load(mList.get(position).posterImgUrl()).into(holder.mPoster);



        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            TextView mTitle;
            TextView mRating;
            ImageView mPoster;
            ViewGroup root;
            public ViewHolder(View itemView) {
                super(itemView);
                mTitle = itemView.findViewById(R.id.tv_title);
                mRating = itemView.findViewById(R.id.tv_rating);
                mPoster = itemView.findViewById(R.id.im_poster);
                root = itemView.findViewById(R.id.root_layout);
                root.setOnClickListener(this);


            }

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),DetailActivity.class);
                intent.putExtra("detail",mList.get(getAdapterPosition()));

                startActivity(intent);

            }
        }
    }
}
