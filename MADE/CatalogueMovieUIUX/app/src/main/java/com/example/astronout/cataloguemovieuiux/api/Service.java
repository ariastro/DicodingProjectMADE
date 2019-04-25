package com.example.astronout.cataloguemovieuiux.api;

import com.example.astronout.cataloguemovieuiux.model.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {

    @GET("movie/now_playing")
    Call<MoviesResponse> getNowPlayingMovie(@Query("api_key") String apiKey);

    @GET("movie/upcoming")
    Call<MoviesResponse> getUpcomingMovie(@Query("api_key") String apiKey);

    @GET("search/movie/")
    Call<MoviesResponse> getMovieBySearch(@Query("query") String q, @Query("api_key") String apiKey);

}
