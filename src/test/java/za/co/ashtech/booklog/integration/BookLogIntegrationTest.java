package za.co.ashtech.booklog.integration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import za.co.ashtech.booklog.model.Author;
import za.co.ashtech.booklog.model.Book;
import za.co.ashtech.booklog.utility.TestDataUtil;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BookLogIntegrationTest {
	
	@LocalServerPort
	private int port;
	private static final String host = "http://localhost:";

	@Autowired
	private TestRestTemplate restTemplate;
	
	@BeforeEach
	public void validate() {
		assertNotNull(port);
		assertNotNull(restTemplate);
	}
	
	@Test
	public void createBookTest() throws Exception {
		
		Author author = new Author();
		author.setFirstname("Ashwin");
		author.setLastname("Scholtz");
		
		Book request = new Book();
		request.setAuthors(new ArrayList<>());
		request.getAuthors().add(author);
		request.setISBN(TestDataUtil.getIsbn());
		request.setPublishDate(new Date());
		request.setPublisher("Ashtech Publishing Co");
		request.setTitle("Your life your terms");
		
		String url = host+port+"/booklog/v1/book";
		
		this.restTemplate.postForObject(host+port+"/booklog/v1/book", request, Void.class);
	}

}
