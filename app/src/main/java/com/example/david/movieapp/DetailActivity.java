package com.example.david.movieapp;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.david.movieapp.model.MovieModel;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    private ImageView mPoster;
    private ImageView mBackDrop;
    private TextView tvOriginalTitle;
    private TextView tvReleaseDate;
    private TextView tvAveragevote;
    private TextView tvOverview;



    MovieModel mModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mPoster = findViewById(R.id.img_poster);
        mBackDrop = findViewById(R.id.img_back_drop);
        tvOriginalTitle = findViewById(R.id.tv_orinal_title);
        tvAveragevote = findViewById(R.id.tv_vote);
        tvReleaseDate = findViewById(R.id.tv_release_date);
        tvOverview = findViewById(R.id.tv_overview);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        mModel = getIntent().getParcelableExtra("detail");

        if (mModel == null){
            finish();
        }
        tvOriginalTitle.setText(mModel.getOriginalTitle());
        tvOverview.setText(mModel.getOverview());
        tvAveragevote.setText(String.valueOf(mModel.getVoteAverage()));
        tvReleaseDate.setText(mModel.getReleaseDate());
        Picasso.get().load(mModel.posterImgUrl()).into(mPoster);
        Picasso.get().load(mModel.backDropImgUrl()).into(mBackDrop);
    }
}
