package com.iti.myapplication.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.iti.myapplication.R;
import com.iti.myapplication.data.model.Movie;
import com.iti.myapplication.view.adapters.SearchAdapter;
import com.iti.myapplication.viewmodel.SearchViewModel;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class SearchActivity extends AppCompatActivity {
  public static final String SEARCH_QUERY = "searchQuery";
  public static final String EXTRA_TITLE = "SearchActivity.TITLE_REPLY";
  public static final String EXTRA_RELEASE_DATE = "SearchActivity.RELEASE_DATE_REPLY";
  public static final String EXTRA_POSTER_PATH = "SearchActivity.POSTER_PATH_REPLY";

  private final String TAG = "SearchActivity";
  private RecyclerView searchResultsRecyclerView;
  private SearchAdapter adapter;
  private TextView noMoviesTextView;
  private ProgressBar progressBar;
  private String query;
  private SearchViewModel viewModel;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_search_movie);
    Intent intent = getIntent();
    query = intent.getStringExtra(SEARCH_QUERY);
    viewModel = ViewModelProviders.of(this).get(SearchViewModel.class);

    setupViews();
    getSearchResults(query);
    viewModel.getListLiveData().observe(this, new Observer<List<Movie>>() {
      @Override
      public void onChanged(List<Movie> movies) {
        displayResult(movies);
      }
    });

  }


  private void setupViews() {
    searchResultsRecyclerView = findViewById(R.id.search_results_recyclerview);
    noMoviesTextView = findViewById(R.id.no_movies_textview);
    progressBar = findViewById(R.id.progress_bar);
    searchResultsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
  }


  private void getSearchResults(final String query) {

   viewModel.searchMovies(query);
  }

  public void displayResult(List<Movie> tmdbResponse) {
    progressBar.setVisibility(INVISIBLE);

    if (tmdbResponse == null || tmdbResponse.size() == 0) {
      searchResultsRecyclerView.setVisibility(INVISIBLE);
      noMoviesTextView.setVisibility(VISIBLE);
    } else {
      adapter = new SearchAdapter(tmdbResponse, this, itemListener);
      searchResultsRecyclerView.setAdapter(adapter);

      searchResultsRecyclerView.setVisibility(VISIBLE);
      noMoviesTextView.setVisibility(INVISIBLE);
    }
  }




  public RecyclerItemListener itemListener =
          new SearchActivity.RecyclerItemListener() {
            public void onItemClick(View view, int position) {
              Movie movie = adapter.getItemAtPosition(position);
              Intent replyIntent = new Intent();
              replyIntent.putExtra(EXTRA_TITLE, movie.getTitle());
              replyIntent.putExtra(EXTRA_RELEASE_DATE, movie.getReleaseYearFromDate());
              replyIntent.putExtra(EXTRA_POSTER_PATH, movie.getPosterPath());
              setResult(RESULT_OK, replyIntent);

              finish();
            }
          };

  public interface RecyclerItemListener {
    void onItemClick(View v, int position);
  }
}
