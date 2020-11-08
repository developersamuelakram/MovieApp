package com.example.tmdb.Service;

import com.example.tmdb.Model.Info;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetMoviesData {



    @GET("movie/popular")
    Call<Info> getPopularMovies(@Query("api_key") String apikey);




}
