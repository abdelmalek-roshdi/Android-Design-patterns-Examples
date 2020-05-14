package com.example.firebasemvvmdemo.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.firebasemvvmdemo.entities.Book;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.firebasemvvmdemo.utils.Constants.booksReference;

public class OnlineDB {
    final MutableLiveData<List<Book>> listLiveData = new MutableLiveData<>();
    public ValueEventListener valueEventListener;


    public MutableLiveData<List<Book>> getAllBooksBySerId(String userId){
        final List<Book> books = new ArrayList<>();

        valueEventListener = booksReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                    Book Book = snapshot.getValue(Book.class);
                    books.add(Book);
                    // books[0] = (List<Book>) dataSnapshot.getValue(Book.class);

                   /*
                    Book book = new Book();
                    book.setBookOwnerid(String.valueOf(snapshot.child(BOOKS_OWNER_CHILD).getValue()));
                    book.setId(String.valueOf(snapshot.child(ID_CHILD).getValue()));
                    book.setName(String.valueOf(snapshot.child(NAME_CHILD).getValue()));
                    bookList.add(book);
                    */
                }
                listLiveData.postValue(books);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return listLiveData;
    }

    public boolean addBookToUser(Book book){
        try {
            DatabaseReference databaseReference = booksReference.push();
            book.setId(databaseReference.getKey());
            booksReference.child(databaseReference.getKey()).setValue(book);
            return true;
        }catch (Exception e){e.printStackTrace();}
        return true;
    }
}
