package com.iti.myapplication.data;

import com.iti.myapplication.data.model.Movie;

import java.util.List;

import androidx.lifecycle.LiveData;

public interface MovieRepository {

  LiveData<List<Movie>> getSavedMovies();

  void saveMovie(Movie movie);

  void deleteMovie(Movie movie);

  LiveData<List<Movie>> searchMovies(String query);
}
