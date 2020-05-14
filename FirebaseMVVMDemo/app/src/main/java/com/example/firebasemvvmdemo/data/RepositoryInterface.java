package com.example.firebasemvvmdemo.data;

import androidx.lifecycle.MutableLiveData;

import com.example.firebasemvvmdemo.entities.Book;

import java.util.List;

public interface RepositoryInterface {
     MutableLiveData<List<Book>> getAllBooks(String userId);

     boolean addBookForUser(Book book);



}
