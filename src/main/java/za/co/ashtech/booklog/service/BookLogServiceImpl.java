package za.co.ashtech.booklog.service;

import java.util.ArrayList;
import java.util.Date;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.Logger;
import za.co.ashtech.booklog.db.dao.BookLogDao;
import za.co.ashtech.booklog.db.entity.AuthorEntity;
import za.co.ashtech.booklog.db.entity.BookEntity;
import za.co.ashtech.booklog.model.Author;
import za.co.ashtech.booklog.model.Book;
import za.co.ashtech.booklog.util.BookLogApiException;
import za.co.ashtech.booklog.util.CONSTANTS;

@Service
public class BookLogServiceImpl implements BookLogService {
	
	private static final Logger logger =  (Logger) LoggerFactory.getLogger(BookLogServiceImpl.class);
	
	@Autowired
	private BookLogDao dao;

	@Override
	public void createBook(Book book) throws BookLogApiException{
		logger.info("Create Book");
		
		throw new NullPointerException("Test");
		
//		BookEntity bookEntity = new BookEntity();
//		bookEntity.setCreateDate(new Date());
//		bookEntity.setIsbn(book.getISBN());
//		bookEntity.setPublishDate(book.getPublishDate());
//		bookEntity.setPublishDate(new Date());
//		bookEntity.setPublisher(book.getPublisher());
//		bookEntity.setTitle(book.getTitle());
//		bookEntity.setAuthors(new ArrayList<AuthorEntity>());
//		
//		for(Author a: book.getAuthors()) {
//			AuthorEntity authorEntity = new AuthorEntity();
//			authorEntity.setBook(bookEntity);
//			authorEntity.setFirstname(a.getFirstname());
//			authorEntity.setLastname(a.getLastname());
//			bookEntity.getAuthors().add(authorEntity);
//		}
//		
//		try {
//			dao.persistBook(bookEntity);
//			logger.debug("After persisting book");
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//			throw new BookLogApiException(CONSTANTS.ERC001, CONSTANTS.ERC001_DESC);
//		}

	}

}
