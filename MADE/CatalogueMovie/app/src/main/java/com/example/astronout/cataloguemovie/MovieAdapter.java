package com.example.astronout.cataloguemovie;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MovieAdapter extends BaseAdapter {

    private ArrayList<MovieItems> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context context;
    private String mOverview;

    public MovieAdapter(Context context) {
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(ArrayList<MovieItems> items) {
        mData = items;
        notifyDataSetChanged();
    }

    public void addItem(final MovieItems item) {
        mData.add(item);
        notifyDataSetChanged();
    }

    public void clearData() {
        mData.clear();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getCount() {
        if (mData == null) return 0;
        return mData.size();
    }


    @Override
    public MovieItems getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.movie_items, null);
            holder.Title = convertView.findViewById(R.id.tvTitle);
            holder.Description = convertView.findViewById(R.id.tvDescription);
            holder.ReleaseDate = convertView.findViewById(R.id.tvReleaseDate);
            holder.imgPoster = convertView.findViewById(R.id.imgMovie);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.Title.setText(mData.get(position).getMovieTitle());
        String overview = mData.get(position).getDescription();

        if(TextUtils.isEmpty(overview)){
            mOverview = "No data";
        }else{
            mOverview = overview;
        }
        holder.Description.setText(mOverview);

        String retrievedDate = mData.get(position).getReleaseDate();
        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = date_format.parse(retrievedDate);

            SimpleDateFormat mDateFormat = new SimpleDateFormat("EEEE, MMM dd, yyyy");
            String ReleaseDate = mDateFormat.format(date);
            holder.ReleaseDate.setText(ReleaseDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        Glide.with(context).load("http://image.tmdb.org/t/p/w92/"+mData.get(position).getImageMovie()).into(holder.imgPoster);

        return convertView;
    }

    private static class ViewHolder {
        TextView Title;
        TextView Description;
        TextView ReleaseDate;
        ImageView imgPoster;
    }
}