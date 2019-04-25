package com.example.astronout.cataloguemovie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView title, releaseDate, rating, overview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageView = findViewById(R.id.poster);
        title = findViewById(R.id.movie_title);
        releaseDate = findViewById(R.id.releaseDate);
        rating = findViewById(R.id.rating);
        overview = findViewById(R.id.overview);

        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity.hasExtra("EXTRA_ITEM")){

            MovieItems mItems = getIntent().getParcelableExtra("EXTRA_ITEM");

//            String mTitle = getIntent().getExtras().getString("EXTRA_TITLE");
//            String mImageView = getIntent().getExtras().getString("EXTRA_IMAGE");
//            String mDate  = getIntent().getExtras().getString("EXTRA_RELEASE_DATE");
//            String mRate = getIntent().getExtras().getString("EXTRA_RATE");
//            String mOverview = getIntent().getExtras().getString("EXTRA_OVERVIEW");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = dateFormat.parse(mItems.getReleaseDate());

                SimpleDateFormat mDateformat = new SimpleDateFormat("EEEE, MMM dd, yyyy");
                String release_date = mDateformat.format(date);
                releaseDate.setText(release_date);

            } catch (ParseException e) {
                e.printStackTrace();
            }

            Glide.with(this)
                    .load("http://image.tmdb.org/t/p/w500/"+mItems.getImageMovie())
                    .into(imageView);
            title.setText(mItems.getMovieTitle());
            rating.setText(mItems.getRate());
            overview.setText(mItems.getDescription());
        }else {
            Toast.makeText(this, "No API Data", Toast.LENGTH_SHORT).show();
        }

    }
}
