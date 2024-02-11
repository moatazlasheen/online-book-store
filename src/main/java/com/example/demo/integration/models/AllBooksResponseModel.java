package com.example.demo.integration.models;

import java.util.List;

public class AllBooksResponseModel {

    private List<BookModel> books;

    public List<BookModel> getBooks() {
        return books;
    }

    public void setBooks(List<BookModel> books) {
        this.books = books;
    }
}
