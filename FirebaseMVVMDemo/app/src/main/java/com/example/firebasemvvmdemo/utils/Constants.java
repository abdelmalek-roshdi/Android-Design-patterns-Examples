package com.example.firebasemvvmdemo.utils;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Constants {
    public static final String DB_Base_URL ="https://fir-mvvmdemo.firebaseio.com/";

    private static final String USERS_CHILD = "users";
    private static final String Books_CHILD = "books";

    public static final String ID_CHILD = "id";
    public static final String BOOKS_OWNER_CHILD = "bookOwnerid";
    public static final String NAME_CHILD = "bookOwnerid";


    public static final String currentUserID = "1001";

    public static final DatabaseReference baseReference = FirebaseDatabase.getInstance().getReference();
    public static final DatabaseReference usersReference = FirebaseDatabase.getInstance().getReference().child(USERS_CHILD);
    public static final DatabaseReference booksReference = FirebaseDatabase.getInstance().getReference().child(Books_CHILD);
}
