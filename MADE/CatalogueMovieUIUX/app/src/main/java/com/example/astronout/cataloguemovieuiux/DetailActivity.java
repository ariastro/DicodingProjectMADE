package com.example.astronout.cataloguemovieuiux;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.image_header)
    ImageView imagePoster;
    @BindView(R.id.tv_movie_title)
    TextView movieTitle;
    @BindView(R.id.tv_movie_overview)
    TextView movieOverview;
    @BindView(R.id.tv_movie_rating)
    TextView movieRating;
    @BindView(R.id.tv_movie_release_date)
    TextView movieReleaseDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        initCollapsingToolbar();

        Intent intentThatStartThisActivity = getIntent();
        if (intentThatStartThisActivity.hasExtra("original_title")){
            String imageMovie = getIntent().getExtras().getString("poster_path");
            String mTitle = getIntent().getExtras().getString("original_title");
            String mOverview = getIntent().getExtras().getString("overview");
            String mRating = getIntent().getExtras().getString("vote_average");
            String mReleaseDate = getIntent().getExtras().getString("release_date");

            Glide.with(this)
                    .load(imageMovie)
                    .apply(new RequestOptions().placeholder(R.drawable.ic_refresh_black_24dp))
                    .into(imagePoster);

            movieTitle.setText(mTitle);
            movieOverview.setText(mOverview);
            movieRating.setText(mRating);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = dateFormat.parse(mReleaseDate);

                SimpleDateFormat mDateformat = new SimpleDateFormat("EEEE, MMM dd, yyyy");
                String release_date = mDateformat.format(date);
                movieReleaseDate.setText(release_date);

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(this, "No Api Data Found", Toast.LENGTH_SHORT).show();
        }

    }

    private void initCollapsingToolbar(){
        final CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("");
        AppBarLayout appBarLayout = findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1){
                    scrollRange = appBarLayout.getTotalScrollRange();
                }if (scrollRange + verticalOffset == 0){
                    collapsingToolbarLayout.setTitle(movieTitle.getText());
                    isShow = true;
                }else if (isShow){
                    collapsingToolbarLayout.setTitle("");
                    isShow = false;
                }
            }
        });
    }

}
