package com.example.demo;



import static org.mockito.ArgumentMatchers.anyInt;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.controllers.BookController;
import com.example.demo.exceptions.BookNotFoundException;
import com.example.demo.integration.models.BookModel;
import com.example.demo.service.BookService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = BookController.class)
public class BooksControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BookService bookService;
	
	@Test
	public void getBooks_empty() throws Exception {

		List<BookModel> books = new ArrayList<BookModel>();
		Mockito.when(bookService.listBooks(false)).thenReturn(books);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/api/v1/admin/books");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getContentAsString());
		String expected = "{\"books\": [] }";


		
		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
	
	
	@Test
	public void getBooks_noneEmpty() throws Exception {

		List<BookModel> books = new ArrayList<BookModel>();
		BookModel bookModel = new BookModel();
		bookModel.setId(1);
		bookModel.setName("book1");
		bookModel.setCategory("category1");
		books.add(bookModel);
		Mockito.when(bookService.listBooks(false)).thenReturn(books);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/api/v1/admin/books");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getContentAsString());
		String expected = "{\"books\": [{\"id\" : 1, \"name\" : \"book1\", \"category\":\"category1\", \"available\":false}] }";


		
		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}

	@Test
	public void retrieveBookDetails_success() throws Exception {

		BookModel bookModel = new BookModel();
		bookModel.setId(1);
		bookModel.setName("book1");
		Mockito.when(bookService.getBookById(anyInt())).thenReturn(bookModel);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/api/v1/books/1");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getContentAsString());
		String expected = "{\"id\" : 1, \"name\" : \"book1\"}";



		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}

	@Test
	public void retrieveBookDetails_notFound() throws Exception {


		Mockito.when(bookService.getBookById(anyInt())).thenThrow(new BookNotFoundException(""));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/api/v1/books/1");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getContentAsString());
		Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(404);
	}

	@Test
	public void testAddBook_invalid() throws Exception {


		Mockito.when(bookService.getBookById(anyInt())).thenThrow(new BookNotFoundException(""));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				"/api/v1/admin/books").contentType(MediaType.APPLICATION_JSON)
		 .content("{}");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getContentAsString());
		Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(400);
	}
	


}
