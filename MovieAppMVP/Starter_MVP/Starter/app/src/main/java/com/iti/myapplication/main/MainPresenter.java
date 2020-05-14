package com.iti.myapplication.main;


import com.iti.myapplication.model.LocalDataSource;
import com.iti.myapplication.model.Movie;

import java.util.HashSet;
import java.util.List;

public class MainPresenter implements MainContract.PresenterInterface {
    private LocalDataSource localDataSource;
    private MainContract.ViewInterface view;

    public MainPresenter(LocalDataSource dataSource, MainContract.ViewInterface view) {
        localDataSource = dataSource;
        this.view = view;


    }

    @Override
    public void getMyMoviesList() {
        if (view != null) {
            List<Movie> movieList = localDataSource.allMovies();
            if (movieList != null && movieList.size() > 0) {
                view.displayMovies(movieList);
            } else {
                view.displayError("no movies");
            }
        }

    }

    @Override
    public void onDelete(HashSet<Movie> selectedMovies) {
        for (Movie movie : selectedMovies) {
            localDataSource.delete(movie);
        }
        getMyMoviesList();
    }

    @Override
    public void stop() {
        //release resources
    }
}
