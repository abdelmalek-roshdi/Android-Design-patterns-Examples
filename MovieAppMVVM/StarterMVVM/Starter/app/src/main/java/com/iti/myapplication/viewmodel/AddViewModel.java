package com.iti.myapplication.viewmodel;

import com.iti.myapplication.data.MovieRepository;
import com.iti.myapplication.data.MovieRepositoryImpl;
import com.iti.myapplication.data.model.Movie;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddViewModel extends ViewModel {
   // MutableLiveData<Movie> mutableMovieLiveData = new MutableLiveData<>();

    MovieRepository movieRepository;
    public AddViewModel() {
        movieRepository = new MovieRepositoryImpl();
    }
    public void addMovie(Movie movie){
        movieRepository.saveMovie(movie);
    //    mutableMovieLiveData.postValue(movie);
    }

   // public MutableLiveData<Movie> getMutableMovieLiveData() {
     //   return mutableMovieLiveData;
   // }
}
