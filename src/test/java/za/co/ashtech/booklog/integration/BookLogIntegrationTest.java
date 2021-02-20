package za.co.ashtech.booklog.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import za.co.ashtech.booklog.db.dao.BookLogDao;
import za.co.ashtech.booklog.model.Author;
import za.co.ashtech.booklog.model.Book;
import za.co.ashtech.booklog.model.Books;
import za.co.ashtech.booklog.model.Editing;
import za.co.ashtech.booklog.model.Editing.ActionEnum;
import za.co.ashtech.booklog.utility.TestDataUtil;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class BookLogIntegrationTest {
	
	@LocalServerPort
	private int port;
	private static final String host = "http://localhost:";

	@Autowired
	private TestRestTemplate restTemplate;
	@Autowired
	private BookLogDao dao;
	
	private static String isbn =null;
	
	@BeforeAll
	static void setUp() {
		isbn = TestDataUtil.getIsbn();
	}
	
	@BeforeEach
	void validate() {
		assertNotEquals(0, port);
		assertNotNull(restTemplate);
	}
	
	@Test
	@Order(1)
	void createBookTest() throws Exception {
		
		Author author = new Author();
		author.setFirstname("Ashwin");
		author.setLastname("Scholtz");
		
		Book request = new Book();
		request.setAuthors(new ArrayList<>());
		request.getAuthors().add(author);
		request.setISBN(isbn);
		request.setPublishDate(new Date());
		request.setPublisher("Ashtech Publishing Co");
		request.setTitle("Your life your terms");
		
		String url = host+port+"/booklog/v1/book";
		
		this.restTemplate.postForObject(url, request, Void.class);
		
		assertNotNull(dao.getBook(isbn));
	}
	
	@Test
	@Order(2)
	void getBookTest() throws Exception {
		
		Map<String,String> uriVariables = new HashMap<>();
		uriVariables.put("isbn", isbn);
		
		String url = host+port+"/booklog/v1/book/{isbn}";
		
		assertEquals(isbn,this.restTemplate.getForObject(url, Book.class,uriVariables).getISBN());
		
	}
	
	@Test
	@Order(3)
	void updateBookTest() throws Exception {
		Editing editing = new Editing();
		editing.action(ActionEnum.EAF);
		editing.setNewFirstname("Update");
		editing.setOldFirstname("Ashwin");
		
		Map<String,String> uriVariables = new HashMap<>();
		uriVariables.put("isbn", isbn);
		
		String url = host+port+"/booklog/v1/book/update/{isbn}";
		
		this.restTemplate.postForObject(url, editing, Void.class,uriVariables);

		assertEquals("Update", dao.getBook(isbn).getAuthors().get(0).getFirstname());
	}
	
	@Test
	@Order(4)
	void deleteBookTest() throws Exception {
		
		Map<String,String> uriVariables = new HashMap<>();
		uriVariables.put("isbn", isbn);
		
		String url = host+port+"/booklog/v1/book/delete/{isbn}";
		
		this.restTemplate.delete(url,uriVariables);


	}
	
	@Test
	@Order(5)
	void getBooksTest() throws Exception {
		
		Map<String,String> uriVariables = new HashMap<>();
		uriVariables.put("username", "ashtech@test.co.za");
		
		String url = host+port+"/booklog/v1/books/{username}";
		
		assertNotNull(this.restTemplate.getForObject(url,Books.class,uriVariables));

	}


}
