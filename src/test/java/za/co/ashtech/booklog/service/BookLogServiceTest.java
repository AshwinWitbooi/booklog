package za.co.ashtech.booklog.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import za.co.ashtech.booklog.model.Author;
import za.co.ashtech.booklog.model.Book;
import za.co.ashtech.booklog.util.BookLogApiException;
import za.co.ashtech.booklog.utility.TestDataUtil;

@SpringBootTest
public class BookLogServiceTest {
	
	@Autowired
	private BookLogService service;
	
	@BeforeEach
	public void validate() {
		assertNotNull(service);
	}
	
	@Test
	public void persistBook() throws BookLogApiException {
		
		Author author = new Author();
		author.setFirstname("Ashwin");
		author.setLastname("Scholtz");
		
		Book book = new Book();
		book.setAuthors(new ArrayList<>());
		book.getAuthors().add(author);
		book.setISBN(TestDataUtil.getIsbn());
		book.setPublishDate(new Date());
		book.setPublisher("Ashtech Publishing Co");
		book.setTitle("Your life your terms");
		
		service.createBook(book);

	}
	
	

}
