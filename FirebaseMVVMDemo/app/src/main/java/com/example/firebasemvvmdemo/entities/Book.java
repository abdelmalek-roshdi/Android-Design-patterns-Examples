package com.example.firebasemvvmdemo.entities;

public class Book {
    private String id;
    private String name;
    private String bookOwnerid;

    public Book() {
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBookOwnerid() {
        return bookOwnerid;
    }

    public void setBookOwnerid(String bookOwnerid) {
        this.bookOwnerid = bookOwnerid;
    }
}
