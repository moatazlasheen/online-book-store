package com.example.demo;



import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.controllers.BookController;
import com.example.demo.integration.models.BookModel;
import com.example.demo.service.BookService;
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
	public void retrieveAllBooks_empty() throws Exception {

		List<BookModel> books = new ArrayList<BookModel>();
		Mockito.when(bookService.listAllBooks()).thenReturn(books);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/api/v1/books");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getContentAsString());
		String expected = "{\"books\": [] }";


		
		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
	
	
	@Test
	public void retrieveAllUsers_noneEmpty() throws Exception {

		List<BookModel> books = new ArrayList<BookModel>();
		BookModel bookModel = new BookModel();
		bookModel.setId(1);
		bookModel.setName("book1");
		books.add(bookModel);
		Mockito.when(bookService.listAllBooks()).thenReturn(books);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/api/v1/books");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getContentAsString());
		String expected = "{\"books\": [{\"id\" : 1, \"name\" : \"book1\"}] }";


		
		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
	
//	@Test
//	public void createUser() throws Exception {
//		Boo requestUserDto = new UserDto();
//		requestUserDto.setName("user1");
//		requestUserDto.setRoles(new HashSet<RoleDto>());
//
//		RoleDto requestRoleDto = new RoleDto();
//		requestRoleDto.setName("role1");
//
//		requestUserDto.getRoles().add(requestRoleDto);
//
//		UserDto reponseUserDto = new UserDto();
//		reponseUserDto.setName("user1");
//		reponseUserDto.setRoles(new HashSet<RoleDto>());
//		reponseUserDto.setId(1);
//
//		RoleDto responseRoleDto = new RoleDto();
//		responseRoleDto.setName("role1");
//		responseRoleDto.setId(1);
//
//		reponseUserDto.getRoles().add(responseRoleDto);
//
//
//
//
//		Mockito.when(userService.createUser(requestUserDto)).thenReturn(reponseUserDto);
//
//		ObjectMapper objectMapper = new ObjectMapper();
//
//		RequestBuilder requestBuilder = MockMvcRequestBuilders
//				.post("/users")
//				.accept(MediaType.APPLICATION_JSON)
//				.content(objectMapper.writeValueAsString(requestUserDto) )
//				.contentType(MediaType.APPLICATION_JSON);
//
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//
//		MockHttpServletResponse response = result.getResponse();
////		String x = response.getContentAsString();
//
//		assertEquals(HttpStatus.OK.value(), response.getStatus());
//
//	}



}
