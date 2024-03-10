package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.entities.Book;
import com.example.demo.entities.BorrowingRequest;
import com.example.demo.entities.Category;
import com.example.demo.exceptions.BookNotFoundException;
import com.example.demo.exceptions.CategoryNotFoundException;
import com.example.demo.integration.models.BookModel;
import com.example.demo.integration.models.BorrowigRequestModel;
import com.example.demo.repos.BookRepo;
import com.example.demo.repos.BorrowingRequestRepo;
import com.example.demo.repos.CategoryRepo;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepo bookRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private BorrowingRequestRepo borrowingRequestRepo;


	@Override
	public List<BookModel> listBooks(boolean considerAvailiability) {
		Iterable<Book> bookIt;
		if (considerAvailiability) {
			bookIt = bookRepo.findByAvailable(true);
		} else {
			bookIt = bookRepo.findAll();
		}
		List<BookModel> bookModelList = new ArrayList<>();
		bookIt.forEach(bookEntity -> {
			bookModelList.add(convertBookEntityToBookModel(bookEntity));
		});
		return bookModelList;
	}

	@Override
	public List<BookModel> listBooksByCategory(final int categoryId) {
		final Category category = categoryRepo.findById(categoryId)
				.orElseThrow(()-> new CategoryNotFoundException(String.format("category with id %s was not found", categoryId)));

		Iterable<Book> booksIt = bookRepo.findByCategory(category);
		List<BookModel> bookModelList = new ArrayList<>();
		booksIt.forEach(bookEntity -> {
			bookModelList.add(convertBookEntityToBookModel(bookEntity));
		});

		return bookModelList;
	}

	private BookModel convertBookEntityToBookModel(Book bookEntity){
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.typeMap(Book.class, BookModel.class)
				.addMapping(book -> book.getCategory().getName(),
						BookModel::setCategory);
		return modelMapper.map(bookEntity, BookModel.class);

	}

	@Override
	public BookModel addBook(BookModel bookModel) {
		Book book = new Book();
		book.setAvailable(bookModel.isAvailable());
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
		if (!bookEntityOptional.isPresent()) {
			throw new BookNotFoundException(String.format("Book with id : %d was not found", bookId));
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

	@Override
	public BookModel getBookById(Integer bookId) {
		Optional<Book> bookOptional = this.bookRepo.findById(bookId);
		final Book bookEntity = bookOptional.orElseThrow( ()-> new BookNotFoundException(String.format("Book with id : %d was not found", bookId)));
		return convertBookEntityToBookModel(bookEntity);
	}

	@Override
	public void requestBorrowing(final BorrowigRequestModel borrowigRequestModel) {
		Optional<Book> bookEntityOptional = bookRepo.findById(borrowigRequestModel.getBookId());
		if (!bookEntityOptional.isPresent()) {
			throw new BookNotFoundException(String.format("Book with id : %d was not found", borrowigRequestModel.getBookId()));
		}
		if (!bookEntityOptional.get().isAvailable()) {
			throw new BookNotFoundException(String.format("Book with id : %d was not found", borrowigRequestModel.getBookId()));
		}

		final ModelMapper modelMapper = new ModelMapper();
		modelMapper.typeMap(BorrowigRequestModel.class, BorrowingRequest.class)
				.addMapping(model -> bookEntityOptional.get(),
						BorrowingRequest::setBook);

		borrowingRequestRepo.save(modelMapper.map(borrowigRequestModel, BorrowingRequest.class));

	}
}
