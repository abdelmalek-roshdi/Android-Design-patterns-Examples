package com.iti.myapplication.data;

import android.util.Log;

import com.iti.myapplication.App;
import com.iti.myapplication.data.db.MovieDao;
import com.iti.myapplication.data.model.Movie;
import com.iti.myapplication.data.model.MoviesResponse;
import com.iti.myapplication.data.net.RetrofitClient;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.iti.myapplication.App.db;

public class MovieRepositoryImpl implements MovieRepository {
  public static final String API_KEY = "9ba2a10e20c15ef6fb276c33692ba9e8";
  private RetrofitClient retrofitClient;
  private MutableLiveData<List<Movie>> allMovies = new MutableLiveData<>();

  public MovieRepositoryImpl() {
    retrofitClient = RetrofitClient.getInstance();
  }

  @Override
  public LiveData<List<Movie>> getSavedMovies() {
    return db.movieDao().getAll();
  }

  @Override
  public void saveMovie(Movie movie) {
    db.movieDao().insert(movie);
  }

  @Override
  public void deleteMovie(Movie movie) {
    db.movieDao().delete(movie.getId());
  }

  @Override
  public LiveData<List<Movie>> searchMovies(String query) {

    retrofitClient.getMoviesApi().searchMovie(API_KEY,query).enqueue(new Callback<MoviesResponse>() {
      @Override
      public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
         allMovies.postValue(response.body().getResults());
      }

      @Override
      public void onFailure(Call<MoviesResponse> call, Throwable t) {

      }
    });
    return allMovies;
  }
}
