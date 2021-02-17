package za.co.ashtech.booklog.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

import za.co.ashtech.booklog.db.dao.BookLogDao;
import za.co.ashtech.booklog.model.Author;
import za.co.ashtech.booklog.model.Book;
import za.co.ashtech.booklog.model.Editing;
import za.co.ashtech.booklog.model.Editing.ActionEnum;
import za.co.ashtech.booklog.util.BookLogApiException;
import za.co.ashtech.booklog.utility.TestDataUtil;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class BookLogServiceTest {
	
	@Autowired
	private BookLogService service;
	@Autowired
	private BookLogDao dao;
	
	private static String isbn =null;
	
	@BeforeAll
	static void setUp() {
		isbn = TestDataUtil.getIsbn();
	}
	
	@BeforeEach
	void validate() {
		assertNotNull(service);
	}
	
	@Test
	@Order(1) 
	void persistBook() throws BookLogApiException {
		
		Author author = new Author();
		author.setFirstname("Ashwin");
		author.setLastname("Scholtz");
		
		Book book = new Book();
		book.setAuthors(new ArrayList<>());
		book.getAuthors().add(author);
		book.setISBN(isbn);
		book.setPublishDate(new Date());
		book.setPublisher("Ashtech Publishing Co");
		book.setTitle("Your life your terms");
	
		service.createBook(book);
		
		assertNotNull(dao.getBook(isbn));

	}
	
	@Test
	@Order(2) 
	void editAuthorFistname() throws BookLogApiException{
		Editing editing = new Editing();
		editing.action(ActionEnum.EAF);
		editing.setNewFirstname("Update");
		editing.setOldFirstname("Ashwin");
		
		service.updateBook(editing, isbn);
		
		assertEquals("Update", dao.getBook(isbn).getAuthors().get(0).getFirstname());	
		
		editing.setNewFirstname("Ashwin");
		editing.setOldFirstname("Update");
		
		service.updateBook(editing, isbn);

	}
	
	@Test
	@Order(3) 
	void editAuthorLastname() throws BookLogApiException{
		Editing editing = new Editing();
		editing.action(ActionEnum.EAL);
		editing.setNewLastname("Update");
		editing.setOldLastname("Scholtz");
		editing.setOldFirstname("Ashwin");
		
		service.updateBook(editing, isbn);
		
		assertEquals("Update", dao.getBook(isbn).getAuthors().get(0).getLastname());
	}
	
	@Test
	@Order(4) 
	void editBookTitle() throws BookLogApiException{
		Editing editing = new Editing();
		editing.setAction(ActionEnum.ET);
		editing.setNewTitle("Update");
		
		service.updateBook(editing, isbn);
		
		assertEquals("Update", dao.getBook(isbn).getTitle());

	}

	
	@Test
	@Order(5) 
	void editAddAuthor() throws BookLogApiException{
		Editing editing = new Editing();
		editing.setAction(ActionEnum.AA);
		editing.setNewFirstname("New");
		editing.setNewLastname("New");

		service.updateBook(editing, isbn);
		
		assertEquals("New", dao.getBook(isbn).getAuthors().get(1).getFirstname());

	}
	
	@Test
	@Order(6) 
	void editPublisher() throws BookLogApiException{
		Editing editing = new Editing();
		editing.setAction(ActionEnum.EP);
		editing.setNewPublisher("New Publisher");
		
		service.updateBook(editing, isbn);
		
		assertEquals("New Publisher", dao.getBook(isbn).getPublisher());

	}
	
	@Test
	@Order(7) 
	void deleteBook() throws BookLogApiException{
		service.deleteBook(isbn);
	}


}
