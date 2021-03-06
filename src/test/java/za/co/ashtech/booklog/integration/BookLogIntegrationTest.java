package za.co.ashtech.booklog.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import za.co.ashtech.booklog.db.dao.BookLogDao;
import za.co.ashtech.booklog.model.Author;
import za.co.ashtech.booklog.model.Book;
import za.co.ashtech.booklog.model.Editing;
import za.co.ashtech.booklog.model.User;
import za.co.ashtech.booklog.model.Editing.ActionEnum;
import za.co.ashtech.booklog.utility.TestDataUtil;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@WebAppConfiguration
class BookLogIntegrationTest {
	
	private static final String testUsername ="test_user";
	private static final String testPassword ="test_user";
	private static String username =null;
	@Autowired
	private BookLogDao dao;
	@Autowired
	private WebApplicationContext context;
	private MockMvc mvc;
	private static String isbn =null;
	
	@BeforeAll
	static void setUp() {		
		isbn = TestDataUtil.getIsbn();
		username = TestDataUtil.getUsername();
	}
	
	@BeforeEach
	public void setup() {
		mvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(springSecurity())
				.build();
	}

	@Test
	@Order(1)
	@WithMockUser(username = "test_user", password = "test_user")
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
		
		mvc.perform(post("/v1/book")
		           .contentType(MediaType.APPLICATION_JSON)
		           .content(TestDataUtil.getJSONString(request)) 
		           .accept(MediaType.APPLICATION_JSON))
		           .andExpect(status().isCreated());
		
		assertNotNull(dao.getBook(isbn));
	}
	
	@Test
	@Order(2)
	@WithMockUser(username = testUsername, password = testPassword)
	void getBookTest() throws Exception {
		
		mvc.perform(get("/v1/book/"+isbn)
		            .accept(MediaType.APPLICATION_JSON))
		            .andExpect(status().isOk());
		
	}
	
	@Test
	@Order(3)
	@WithMockUser(username = testUsername, password = testPassword)
	void updateBookTest() throws Exception {
		Editing editing = new Editing();
		editing.action(ActionEnum.EAF);
		editing.setNewFirstname("Update");
		editing.setOldFirstname("Ashwin");
		
		mvc.perform(post("/v1/book/update/"+isbn)
		           .contentType(MediaType.APPLICATION_JSON)
		           .content(TestDataUtil.getJSONString(editing)) 
		           .accept(MediaType.APPLICATION_JSON))
		           .andExpect(status().isOk());

		assertEquals("Update", dao.getBook(isbn).getAuthors().get(0).getFirstname());
	}
	
	@Test
	@Order(4)
	@WithMockUser(username = testUsername, password = testPassword)
	void deleteBookTest() throws Exception {
		
		mvc.perform(delete("/v1/book/delete/"+isbn))
		           .andExpect(status().isOk());
	}
	
	@Test
	@Order(5)
	@WithMockUser(username = testUsername, password = testPassword)
	void getBooksTest() throws Exception {
		
		mvc.perform(get("/v1/books/"+testUsername)
	            .accept(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk());
	}
	
	@Test
	@Order(6)
	@WithMockUser(username = "admin", password = "admin")
	void createUser() throws Exception {
		User user = new User();
		user.setUsername(username);
		user.setPassword("password");
		user.setConfirmPassword("password");
		
		mvc.perform(post("/v1/admin/user")
		           .contentType(MediaType.APPLICATION_JSON)
		           .content(TestDataUtil.getJSONString(user)) 
		           .accept(MediaType.APPLICATION_JSON))
		           .andExpect(status().isCreated());

	}


}
