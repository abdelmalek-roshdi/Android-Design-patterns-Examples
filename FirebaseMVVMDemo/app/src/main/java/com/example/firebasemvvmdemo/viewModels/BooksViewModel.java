package com.example.firebasemvvmdemo.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.firebasemvvmdemo.utils.Constants;
import com.example.firebasemvvmdemo.entities.Book;
import com.example.firebasemvvmdemo.data.Repository;

import java.util.List;

import static com.example.firebasemvvmdemo.utils.Constants.booksReference;

public class BooksViewModel extends ViewModel {
    private Repository repository;
    MutableLiveData<List<Book> > booksLiveData;

    public MutableLiveData<List<Book>> getBooksLiveData() {
        return booksLiveData;
    }

    public void setBooksLiveData(MutableLiveData<List<Book>> booksLiveData) {
        this.booksLiveData = booksLiveData;
    }

    public BooksViewModel() {

        repository = new Repository();
        booksLiveData = repository.getAllBooks(Constants.currentUserID);
    }
    public MutableLiveData<List<Book>> getAllBooks(String userId){
        return repository.getAllBooks(userId);
    }
    public boolean addBookToUser(Book book){
        return repository.addBookForUser(book);
    }

    @Override
    protected void onCleared() {
        booksReference.removeEventListener(repository.getOnlineDB().valueEventListener);
        super.onCleared();
    }
}
