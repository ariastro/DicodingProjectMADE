package com.example.astronout.cataloguemovie;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;



public class MovieItems implements Parcelable {
    private int id;
    private String movieTitle;
    private String description;
    private String releaseDate;
    private String rate;
    private String imageMovie;

    public MovieItems(JSONObject object) {
        try {
            int id = object.getInt("id");
            String movie = object.getString("original_title");
            String mDescription = object.getString("overview");
            String mReleaseDate = object.getString("release_date");
            String mRate = object.getString("vote_average");
            String mImageMovie = object.getString("poster_path");

            this.id = id;
            this.movieTitle = movie;
            this.description = mDescription;
            this.releaseDate = mReleaseDate;
            this.rate = mRate;
            this.imageMovie = mImageMovie;

        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public String getImageMovie() {
        return imageMovie;
    }

    public void setImageMovie(String imageMovie) {
        this.imageMovie = imageMovie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.movieTitle);
        dest.writeString(this.description);
        dest.writeString(this.releaseDate);
        dest.writeString(this.rate);
        dest.writeString(this.imageMovie);
    }

    protected MovieItems(Parcel in) {
        this.id = in.readInt();
        this.movieTitle = in.readString();
        this.description = in.readString();
        this.releaseDate = in.readString();
        this.rate = in.readString();
        this.imageMovie = in.readString();
    }

    public static final Parcelable.Creator<MovieItems> CREATOR = new Parcelable.Creator<MovieItems>() {
        @Override
        public MovieItems createFromParcel(Parcel source) {
            return new MovieItems(source);
        }

        @Override
        public MovieItems[] newArray(int size) {
            return new MovieItems[size];
        }
    };
}
