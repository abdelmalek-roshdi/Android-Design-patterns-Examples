package com.iti.myapplication.viewmodel;

import com.iti.myapplication.data.MovieRepository;
import com.iti.myapplication.data.MovieRepositoryImpl;
import com.iti.myapplication.data.model.Movie;

import java.util.HashSet;
import java.util.List;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    MediatorLiveData<List<Movie>> listMediatorLiveData = new MediatorLiveData<>();
    MovieRepository movieRepository;

    public MainViewModel() {
        movieRepository = new MovieRepositoryImpl();
        listMediatorLiveData.postValue(getAllMovies());
        listMediatorLiveData.addSource(movieRepository.getSavedMovies(), new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                listMediatorLiveData.postValue(movies);
            }
        });
    }

    public MediatorLiveData<List<Movie>> getListMediatorLiveData() {
        return listMediatorLiveData;
    }

    private List<Movie> getAllMovies(){
        return movieRepository.getSavedMovies().getValue();
    }
    public void deleteMovie(HashSet<Movie> movies){
        for (Movie movie :movies)
        movieRepository.deleteMovie(movie);
    }
}
