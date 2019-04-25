package com.example.astronout.cataloguemovieuiux.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.astronout.cataloguemovieuiux.DetailActivity;
import com.example.astronout.cataloguemovieuiux.R;
import com.example.astronout.cataloguemovieuiux.model.Movie;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private Context mContext;
    private List<Movie> movieList;

    public MoviesAdapter(Context mContext, List<Movie> movieList) {
        this.mContext = mContext;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MoviesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_movie, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapter.MyViewHolder holder, int position) {
        holder.item_title.setText(movieList.get(position).getmOriginalTitle());
        holder.item_overview.setText(movieList.get(position).getmOverview());
        String vote = Double.toString(movieList.get(position).getmVoteAverage());
        holder.item_rating .setText(vote);

        Glide.with(mContext)
                .load(movieList.get(position).getmPosterPath())
                .apply(new RequestOptions().placeholder(R.drawable.ic_refresh_black_24dp))
                .into(holder.item_poster);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.img_poster) ImageView item_poster;
        @BindView(R.id.mov_title) TextView item_title;
        @BindView(R.id.overview) TextView item_overview;
        @BindView(R.id.rating) TextView item_rating;

        public MyViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION){
                        Intent intent = new Intent(mContext, DetailActivity.class);
                        intent.putExtra("original_title", movieList.get(pos).getmOriginalTitle());
                        intent.putExtra("poster_path", movieList.get(pos).getmPosterPath());
                        intent.putExtra("overview", movieList.get(pos).getmOverview());
                        intent.putExtra("vote_average", Double.toString(movieList.get(pos).getmVoteAverage()));
                        intent.putExtra("release_date", movieList.get(pos).getmReleaseDate());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                    }
                }
            });
        }
    }

}
