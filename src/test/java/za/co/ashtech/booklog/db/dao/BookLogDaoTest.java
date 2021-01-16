package za.co.ashtech.booklog.db.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import za.co.ashtech.booklog.db.entity.AuthorEntity;
import za.co.ashtech.booklog.db.entity.BookEntity;
import za.co.ashtech.booklog.db.entity.TxLogEntity;
import za.co.ashtech.booklog.utility.TestDataUtil;

@SpringBootTest
public class BookLogDaoTest {
	
	@Autowired
	private BookLogDao dao;
	
	@BeforeEach
	public void validate() {
		assertNotNull(dao);
	}
	
//	@Test
	public void persistBook() {
		
		BookEntity book = new BookEntity();
		book.setCreateDate(new Date());
		book.setIsbn(TestDataUtil.getIsbn());
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

	}
	
	@Test
	public void persistTxLog() {
		
		TxLogEntity txLogEntity = new TxLogEntity();
		txLogEntity.setAction("ADD");
		txLogEntity.setActionDate(new Date());
		txLogEntity.setActionResult("S");
		txLogEntity.setUsername("ash@ashtech.co.za");
		
		dao.persistTx(txLogEntity);

	}

}
