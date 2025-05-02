package com.example.myapplication1.utils;

import com.example.myapplication1.models.Book;
import java.util.ArrayList;
import java.util.List;

public class BookStore {
    private static BookStore instance;
    private final List<Book> bookList = new ArrayList<>();

    private BookStore() {}

    public static synchronized BookStore getInstance() {
        if (instance == null) {
            instance = new BookStore();
        }
        return instance;
    }

    public void addBook(Book book) {
        bookList.add(book);
    }

    public List<Book> getBooks() {
        return new ArrayList<>(bookList);
    }
}

