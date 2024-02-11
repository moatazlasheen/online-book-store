package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.entities.Book;
import com.example.demo.entities.Category;
import com.example.demo.integration.models.BookModel;
import com.example.demo.repos.BookRepo;
import com.example.demo.repos.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepo bookRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public List<BookModel> listAllBooks() {
		Iterable<Book> bookIt = bookRepo.findAll();
		List<BookModel> bookModelList = new ArrayList<>();
		bookIt.forEach(bookEntity -> {
			BookModel bookModel = new BookModel();
			bookModel.setId(bookEntity.getId());
			bookModel.setName(bookEntity.getName());
			bookModel.setCategory(bookEntity.getCategory() == null? "" : bookEntity.getCategory().getName());
			bookModel.setAvailable(bookEntity.isAvailable());
			bookModelList.add(bookModel);
		});
		return bookModelList;
	}

	@Override
	public BookModel addBook(BookModel bookModel) {
		Book book = new Book();
		book.setAvailable(true);
		book.setName(bookModel.getName());
		Category category = categoryRepo.findCategoryByNameIgnoringCase(bookModel.getCategory().toLowerCase());
		if (category == null) {
			category = new Category();
			category.setName(bookModel.getCategory());
			category = categoryRepo.save(category);
		}
		book.setCategory(category);
		this.bookRepo.save(book);
		return bookModel;
	}

	@Override
	public void udatebook(BookModel bookModel, Integer bookId) {
		Optional<Book> bookEntityOptional = bookRepo.findById(bookId);
		if (bookEntityOptional.isPresent()) {

		}
		Book bookEntity = bookEntityOptional.get();
		bookEntity.setAvailable(true);
		bookEntity.setName(bookModel.getName());
		Category category = categoryRepo.findCategoryByNameIgnoringCase(bookModel.getCategory().toLowerCase());
		if (category == null) {
			category = new Category();
			category.setName(bookModel.getCategory());
			category = categoryRepo.save(category);
		}
		bookEntity.setCategory(category);
		this.bookRepo.save(bookEntity);
	}

	@Override
	public void deleteBook(Integer bookId) {
		this.bookRepo.deleteById(bookId);
	}
}
