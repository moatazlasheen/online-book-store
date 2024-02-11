package com.example.demo;

import com.example.demo.integration.models.BookModel;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private BookService bookService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		BookModel bookModel = new BookModel();
		bookModel.setName("name1");
		bookModel.setCategory("category1");

		BookModel bookModel2 = new BookModel();
		bookModel2.setName("name2");
		bookModel2.setCategory("category1");

		BookModel bookModel3 = new BookModel();
		bookModel3.setName("name3");
		bookModel3.setCategory("category2");

		BookModel bookModel4 = new BookModel();
		bookModel4.setName("name4");
		bookModel4.setCategory("category3");

		bookService.addBook(bookModel);
		bookService.addBook(bookModel2);
		bookService.addBook(bookModel3);
		bookService.addBook(bookModel4);

	}
}
