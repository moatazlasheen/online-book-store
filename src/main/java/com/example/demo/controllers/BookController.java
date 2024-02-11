package com.example.demo.controllers;

import com.example.demo.integration.models.AllBooksResponseModel;
import com.example.demo.integration.models.BookModel;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class BookController {
	
	
	@Autowired
	private BookService bookService;

	@GetMapping("/books")
	public AllBooksResponseModel listAllBooks () {
		AllBooksResponseModel response = new AllBooksResponseModel();
		response.setBooks(bookService.listAllBooks());
		return response;
	}
	
	
	@PostMapping("/admin/books")
	public BookModel addBook (@RequestBody BookModel bookModel ) {
		return bookService.addBook(bookModel);
	}
	
	@PutMapping("/admin/books/{bookId}")
	public void updateBook ( @RequestBody BookModel book, @PathVariable Integer bookId ) {
		bookService.udatebook(book, bookId);
	}
	
	@DeleteMapping("/books/{bookId}")
	public void deleteBook (  @PathVariable Integer bookId ) {
		bookService.deleteBook(bookId);
	}
	
}
