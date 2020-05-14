package com.iti.myapplication.data.net;

import androidx.lifecycle.LiveData;

import com.iti.myapplication.data.model.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviesApi {
  @GET("search/movie")
  Call<MoviesResponse> searchMovie(@Query("api_key") String apiKey, @Query("query") String query);
}
