package com.example.demo.controllers;

import com.example.demo.integration.models.BooksResponseModel;
import com.example.demo.integration.models.BookModel;
import com.example.demo.integration.models.BorrowigRequestModel;
import com.example.demo.service.BookService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/")
@Data
public class BookController {
	
	
	@Autowired
	private BookService bookService;

	// for admins
	@GetMapping("/admin/books")
	public BooksResponseModel listAllBooks () {
		return (new BooksResponseModel(bookService.listBooks(false)));
	}

	// for users
	@GetMapping("/books/category/{categoryId}")
	public BooksResponseModel listAvailableBooks (@PathVariable int categoryId) {
		BooksResponseModel response = new BooksResponseModel();
		response.setBooks(bookService.listBooksByCategory(categoryId));
		return response;
	}

	@GetMapping("/books/{bookId}")
	public BookModel getBookDetails (@PathVariable final Integer bookId) {
		return this.bookService.getBookById(bookId);
	}
	
	
	@PostMapping("/admin/books")
	public BookModel addBook (@Valid @RequestBody BookModel bookModel) {
		return bookService.addBook(bookModel);
	}
	
	@PutMapping("/admin/books/{bookId}")
	public void updateBook (@RequestBody BookModel book, @PathVariable Integer bookId) {
		bookService.udatebook(book, bookId);
	}
	
	@DeleteMapping("/admin/books/{bookId}")
	public void deleteBook (@PathVariable Integer bookId ) {
		bookService.deleteBook(bookId);
	}

	@PostMapping("/books/borrowing/request")
	public void requestBookBorrowing(@Valid @RequestBody BorrowigRequestModel borrowigRequestModel) {
		bookService.requestBorrowing(borrowigRequestModel);
	}
	
}
