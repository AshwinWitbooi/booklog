package za.co.ashtech.booklog.db.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import za.co.ashtech.booklog.db.entity.AuthorEntity;
import za.co.ashtech.booklog.db.entity.BookEntity;
import za.co.ashtech.booklog.db.entity.TxLogEntity;
import za.co.ashtech.booklog.utility.TestDataUtil;
import org.junit.jupiter.api.MethodOrderer;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
 class BookLogDaoTest {
	
	@Autowired
	private BookLogDao dao;
	private static String isbn =null;
	
	@BeforeAll
	public static void setUp() {
		isbn = TestDataUtil.getIsbn();
	}
	
	@BeforeEach
	void validate() {
		assertNotNull(dao);
	}
	
	@Test
	@Order(1) 
	void persistBook() {		
		
		BookEntity book = new BookEntity();
		book.setCreateDate(new Date());
		book.setIsbn(isbn);
		book.setPublishDate(new Date());
		book.setPublisher("Ashtech Publishing");
		book.setTitle("Be your own hero");
		book.setAuthors(new ArrayList<AuthorEntity>());
				
		AuthorEntity author = new AuthorEntity();
		author.setBook(book);
		author.setFirstname("Ashwin");
		author.setLastname("Scholtz");
		book.getAuthors().add(author);
		
		dao.persistBook(book);
		
		assertNotEquals(0,book.getId());

	}
	
	@Test
	@Order(3)
	void persistTxLog() {
		
		TxLogEntity txLogEntity = new TxLogEntity();
		txLogEntity.setAction("ADD");
		txLogEntity.setActionDate(new Date());
		txLogEntity.setActionResult("S");
		txLogEntity.setUsername("ash@ashtech.co.za");
		
		dao.persistTx(txLogEntity);
		
		assertNotEquals(0,txLogEntity.getId());

	}
	
	@Test
	@Order(2) 
	void updateBook() {
		
		BookEntity record = dao.getBook(isbn);
		
		assertNotNull(record);
		
		record.setTitle("Updated Title");
		
		dao.updateBook(record);
		
		assertEquals("Updated Title", dao.getBook(isbn).getTitle());;
	}

	@Test
	@Order(4) 
	void deleteBook() {
		
		BookEntity record = dao.getBook(isbn);
		
		assertNotNull(record);
		
		record.setTitle("Updated Title");
		
		dao.deleteBook(record);
		
		assertEquals(null, dao.getBook(isbn));
	}

}
