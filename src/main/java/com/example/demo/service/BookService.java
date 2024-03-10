package com.example.demo.service;


import com.example.demo.integration.models.BookModel;
import com.example.demo.integration.models.BorrowigRequestModel;

import java.util.List;

public interface BookService {


    List<BookModel> listBooks(boolean considerAvailability);
    List<BookModel> listBooksByCategory(int categoryId);

    BookModel addBook(BookModel bookModel);

    void udatebook(BookModel book, Integer bookId);

    void deleteBook(Integer bookId);

    BookModel getBookById(Integer bookId);

    void requestBorrowing(BorrowigRequestModel borrowigRequestModel);
}