package com.example.firebasemvvmdemo.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.firebasemvvmdemo.utils.FragmentUtils;
import com.example.firebasemvvmdemo.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    BooksFragment booksFragment;
    AddBookFragment addBookFragment;
    FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        if (booksFragment == null){
            booksFragment = new BooksFragment();
        }
        FragmentUtils.showFragment(this, R.id.container_layout, booksFragment, BooksFragment.TAG, true);
        handleActions();
    }

    private void handleActions() {
        if (addBookFragment == null){
            addBookFragment = new AddBookFragment();
        }
        floatingActionButton.setOnClickListener((view -> {
            FragmentUtils.showFragment(this, R.id.container_layout, addBookFragment, AddBookFragment.TAG, true);
        }));
    }

    private void initView() {
        floatingActionButton = findViewById(R.id.floatingActionButton);
    }


}
