package com.iti.myapplication.viewmodel;

import com.iti.myapplication.data.MovieRepository;
import com.iti.myapplication.data.MovieRepositoryImpl;
import com.iti.myapplication.data.model.Movie;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SearchViewModel extends ViewModel {
    MovieRepository movieRepository;

    LiveData<List<Movie>> listLiveData;

    public SearchViewModel() {
        movieRepository = new MovieRepositoryImpl();

    }

    public LiveData<List<Movie>> searchMovies(String query){
        LiveData<List<Movie>> liveData = movieRepository.searchMovies(query);
        listLiveData =liveData;
        return listLiveData ;
    }

    public LiveData<List<Movie>> getListLiveData() {
        return listLiveData;
    }
}
