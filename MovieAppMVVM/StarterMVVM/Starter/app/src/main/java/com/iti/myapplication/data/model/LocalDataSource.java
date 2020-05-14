package com.iti.myapplication.data.model;

import android.app.Application;

import com.iti.myapplication.data.db.MovieDao;
import com.iti.myapplication.data.db.MovieDatabase;

import java.util.List;

import androidx.lifecycle.LiveData;

import static com.iti.myapplication.App.db;

public class LocalDataSource {

  private final MovieDao movieDao;

  public LocalDataSource() {

    this.movieDao = db.movieDao();
  }

  public LiveData<List<Movie>> allMovies() {
    return movieDao.getAll();
  }

  public void insert(Movie movie) {
    movieDao.insert(movie);
  }

  public void delete(Movie movie) {
    movieDao.delete(movie.getId());
  }
}
