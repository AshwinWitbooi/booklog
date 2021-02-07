package za.co.ashtech.booklog.service;

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

import za.co.ashtech.booklog.model.Author;
import za.co.ashtech.booklog.model.Book;
import za.co.ashtech.booklog.model.Editing;
import za.co.ashtech.booklog.model.Editing.ActionEnum;
import za.co.ashtech.booklog.util.BookLogApiException;
import za.co.ashtech.booklog.utility.TestDataUtil;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class BookLogServiceTest {
	
	@Autowired
	private BookLogService service;
	
	private static String isbn =null;
	
	@BeforeAll
	public static void setUp() {
		isbn = TestDataUtil.getIsbn();
	}
	
	@BeforeEach
	public void validate() {
		assertNotNull(service);
	}
	
	@Test
	@Order(1) 
	public void persistBook() throws BookLogApiException {
		
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

	}
	
	@Test
	@Order(2) 
	public void editAuthorFistname() throws BookLogApiException{
		Editing editing = new Editing();
		editing.action(ActionEnum.EAF);
		editing.setNewFirstname("Update");
		editing.setOldFirstname("Ashwin");
		
		service.updateBook(editing, isbn);
		
		editing.action(ActionEnum.EAF);
		editing.setNewFirstname("Ashwin");
		editing.setOldFirstname("Update");
		service.updateBook(editing, isbn);
		
		
	}
	
	@Test
	@Order(3) 
	public void editAuthorLastname() throws BookLogApiException{
		Editing editing = new Editing();
		editing.action(ActionEnum.EAL);
		editing.setNewLastname("Update");
		editing.setOldLastname("Scholtz");
		editing.setOldFirstname("Ashwin");
		
		service.updateBook(editing, isbn);
		
		editing.setNewLastname("Scholtz");
		editing.setOldLastname("Update");
		editing.setOldFirstname("Ashwin");
		
		service.updateBook(editing, isbn);
	}
	
	@Test
	@Order(4) 
	public void editBookTitle() throws BookLogApiException{
		Editing editing = new Editing();
		editing.setAction(ActionEnum.ET);
		editing.setNewTitle("Update");
		
		service.updateBook(editing, isbn);

	}

	
	@Test
	@Order(5) 
	public void editAddAuthor() throws BookLogApiException{
		Editing editing = new Editing();
		editing.setAction(ActionEnum.AA);
		editing.setNewFirstname("New");
		editing.setNewLastname("New");

		service.updateBook(editing, isbn);

	}
	
	@Test
	@Order(6) 
	public void editPublisher() throws BookLogApiException{
		Editing editing = new Editing();
		editing.setAction(ActionEnum.EP);
		editing.setNewPublisher("New Publisher");
		
		service.updateBook(editing, isbn);

	}
	
	@Test
	@Order(7) 
	public void editPublisherDate() throws BookLogApiException{
		Editing editing = new Editing();
		editing.setAction(ActionEnum.EPD);
		editing.setNewPublishDate(new Date());

		service.updateBook(editing, isbn);

	}
	
	@Test
	@Order(8) 
	public void deleteBook() throws BookLogApiException{
		service.deleteBook(isbn);
	}


}
