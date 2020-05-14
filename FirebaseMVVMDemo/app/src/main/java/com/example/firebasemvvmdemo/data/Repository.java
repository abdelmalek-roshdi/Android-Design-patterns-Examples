package com.example.firebasemvvmdemo.data;

import androidx.lifecycle.MutableLiveData;

import com.example.firebasemvvmdemo.entities.Book;

import java.util.List;

public class Repository implements RepositoryInterface {
    private OnlineDB onlineDB;

    public Repository() {
        onlineDB = new OnlineDB();
    }

   /*
    public MutableLiveData<List<Book>> getAllBooks(String userId){
        return onlineDB.getAllBooksBySerId(userId);
    }
    public boolean addBookForUser(Book book){
        return onlineDB.addBookToUser(book);
    }

    */
    public OnlineDB getOnlineDB() {
        return onlineDB;
    }

    @Override
    public MutableLiveData<List<Book>> getAllBooks(String userId) {
        return onlineDB.getAllBooksBySerId(userId);
    }

    @Override
    public boolean addBookForUser(Book book) {
        return onlineDB.addBookToUser(book);
    }
}
