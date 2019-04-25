package com.example.astronout.cataloguemovie;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<MovieItems>>, AdapterView.OnItemClickListener {

    ListView listView;
    MovieAdapter adapter;
    EditText edtMovie;
    Button buttonCari;
    ImageView imgPoster;

    static final String EXTRAS_MOVIE = "EXTRAS_MOVIE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new MovieAdapter(this);
        adapter.notifyDataSetChanged();
        listView = findViewById(R.id.listView);
        imgPoster = findViewById(R.id.imgMovie);

        listView.setAdapter(adapter);

        edtMovie = findViewById(R.id.edt_movie_title);
        buttonCari = findViewById(R.id.btn_search);

        listView.setOnItemClickListener(this);

        buttonCari.setOnClickListener(myListener);

        String movieTitle = edtMovie.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRAS_MOVIE, movieTitle);

        getLoaderManager().initLoader(0, bundle, this);

    }

    @Override
    public Loader<ArrayList<MovieItems>> onCreateLoader(int id, Bundle args) {
        String MovieTitle = "";
        if (args != null) {
            MovieTitle = args.getString(EXTRAS_MOVIE);
        }

        return new MyAsyncTaskLoader(this, MovieTitle);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<MovieItems>> loader, ArrayList<MovieItems> data) {
        adapter.setData(data);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<MovieItems>> loader) {
        adapter.setData(null);
    }

    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String movieTitle = edtMovie.getText().toString();

            if (TextUtils.isEmpty(movieTitle)) return;

            Bundle bundle = new Bundle();
            bundle.putString(EXTRAS_MOVIE, movieTitle);
            getLoaderManager().restartLoader(0, bundle, MainActivity.this);
        }
    };

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        MovieItems item = (MovieItems)parent.getItemAtPosition(position);

        Intent intent = new Intent(MainActivity.this, DetailActivity.class);

//        intent.putExtra("EXTRA_TITLE", item.getMovieTitle());
//        intent.putExtra("EXTRA_RELEASE_DATE", item.getReleaseDate());
//        intent.putExtra("EXTRA_IMAGE", item.getImageMovie());
//        intent.putExtra("EXTRA_RATE", item.getRate());
//        intent.putExtra("EXTRA_OVERVIEW", item.getDescription());

        intent.putExtra("EXTRA_ITEM", item);

        startActivity(intent);
    }
}
