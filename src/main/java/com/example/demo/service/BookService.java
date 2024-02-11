package com.example.demo.service;


import com.example.demo.integration.models.BookModel;

import java.util.List;

public interface BookService {


    List<BookModel> listAllBooks();

    BookModel addBook(BookModel bookModel);

    void udatebook(BookModel book, Integer bookId);

    void deleteBook(Integer bookId);
}