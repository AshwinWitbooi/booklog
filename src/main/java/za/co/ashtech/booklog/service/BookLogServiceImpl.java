package za.co.ashtech.booklog.service;

import java.util.ArrayList;
import java.util.Date;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.Logger;
import za.co.ashtech.booklog.db.dao.BookLogDao;
import za.co.ashtech.booklog.db.entity.AuthorEntity;
import za.co.ashtech.booklog.db.entity.BookEntity;
import za.co.ashtech.booklog.model.Author;
import za.co.ashtech.booklog.model.Book;
import za.co.ashtech.booklog.model.Editing;
import za.co.ashtech.booklog.util.BookLogApiException;
import za.co.ashtech.booklog.util.CONSTANTS;

@Service
public class BookLogServiceImpl implements BookLogService {

	private static final Logger logger = (Logger) LoggerFactory.getLogger(BookLogServiceImpl.class);

	@Autowired
	private BookLogDao dao;

	@Override
	public void createBook(Book book) throws BookLogApiException {
		logger.info("Create Book");

		BookEntity bookEntity = new BookEntity();
		bookEntity.setCreateDate(new Date());
		bookEntity.setIsbn(book.getISBN());
		bookEntity.setPublishDate(book.getPublishDate());
		bookEntity.setCreateDate(new Date());
		bookEntity.setPublisher(book.getPublisher());
		bookEntity.setTitle(book.getTitle());
		bookEntity.setAuthors(new ArrayList<AuthorEntity>());

		for (Author a : book.getAuthors()) {
			AuthorEntity authorEntity = new AuthorEntity();
			authorEntity.setBook(bookEntity);
			authorEntity.setFirstname(a.getFirstname());
			authorEntity.setLastname(a.getLastname());
			bookEntity.getAuthors().add(authorEntity);
		}

		try {
			dao.persistBook(bookEntity);
			logger.debug("After persisting book");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			if (e instanceof DataIntegrityViolationException) {
				throw new BookLogApiException(CONSTANTS.ERC004, CONSTANTS.ERC004_DESC,
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
			throw new BookLogApiException(CONSTANTS.ERC001, CONSTANTS.ERC001_DESC, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	public void updateBook(Editing editing, String isbn) throws BookLogApiException {
		
		/* check that correct data is sent */
		if(editing.getNewFirstname() == null &&
			editing.getOldFirstname() == null &&
			editing.getOldLastname() == null && 
			editing.getNewLastname() == null &&
			editing.getNewTitle() == null &&
			editing.getNewPublisher() == null &&
			editing.getNewPublishDate() == null) {
			
			throw new BookLogApiException(CONSTANTS.ERC007, "No data was send.", HttpStatus.BAD_REQUEST);
			
		}else {

			//HANDLE INVALID ISBN HIBERNATE ERROR - EmptyResultDataAccessException
			BookEntity bookEntity;
			try {
				bookEntity = dao.getBook(isbn);
				
				
				if(editing.getAction() != null) {		
					
					String action = editing.getAction().name();
					
					switch (action) {
					case "EAF":
												
						if(editing.getNewFirstname() != null && editing.getOldFirstname() == null) {
							
							throw new BookLogApiException(CONSTANTS.ERC007, "Firstname change data invalid.", HttpStatus.BAD_REQUEST);
						}else {
							
							for(int i =0; i < bookEntity.getAuthors().size();i++) {
								AuthorEntity ae = bookEntity.getAuthors().get(i);
								if(ae.getFirstname().equalsIgnoreCase(editing.getOldFirstname())) {
									 bookEntity.getAuthors().get(i).setFirstname(editing.getNewFirstname());
									 
									 break;
								}
								
								if(i+1==bookEntity.getAuthors().size()) {
									throw new BookLogApiException(CONSTANTS.ERC007, "Author name not found", HttpStatus.BAD_REQUEST);
								}
							}
							
							
						}						
						
						break;
					case "EAL":
							
						if(editing.getNewLastname() == null || editing.getOldLastname() == null || editing.getOldLastname() == null) {
							
							throw new BookLogApiException(CONSTANTS.ERC007, "Lastname change data invalid.", HttpStatus.BAD_REQUEST);
						}else {
							
							for(int i =0; i < bookEntity.getAuthors().size();i++) {
								AuthorEntity ae = bookEntity.getAuthors().get(i);
								if(ae.getFirstname().equalsIgnoreCase(editing.getOldFirstname())) {
									 bookEntity.getAuthors().get(i).setLastname(editing.getNewLastname());
									 
									 break;
								}
								
								if(i+1==bookEntity.getAuthors().size()) {
									throw new BookLogApiException(CONSTANTS.ERC007, "Author name not found", HttpStatus.BAD_REQUEST);
								}
							}
							
							
						}
											
						break;
					case "ET":
						
						if(editing.getNewTitle() != null) {
							
							bookEntity.setTitle(editing.getNewTitle());						
							
						}else {
							throw new BookLogApiException(CONSTANTS.ERC007, "New title not send in request.", HttpStatus.BAD_REQUEST);
						}
						
						break;
					case "AA":
						
						if(editing.getNewFirstname() != null && editing.getNewLastname() != null) {
							AuthorEntity ea = new AuthorEntity();
							ea.setFirstname(editing.getNewFirstname());
							ea.setLastname(editing.getNewLastname());
							ea.setBook(bookEntity);
							
							bookEntity.getAuthors().add(ea);
							
						}else {
							throw new BookLogApiException(CONSTANTS.ERC007, "Data incomplete to add author.", HttpStatus.BAD_REQUEST);
						}					
						
						break;
					case "EP":

						if(editing.getNewPublisher() != null) {
							
							bookEntity.setPublisher(editing.getNewPublisher());
							
						}else {
							throw new BookLogApiException(CONSTANTS.ERC007, "Data incomplete to edit publisher.", HttpStatus.BAD_REQUEST);
						}						
						
						break;
					case "EPD":
						
						if(editing.getNewPublishDate() != null) {
							
							bookEntity.setPublishDate(editing.getNewPublishDate());
							
						}else {
							throw new BookLogApiException(CONSTANTS.ERC007, "Data incomplete to edit publishing date.", HttpStatus.BAD_REQUEST);
						}
						
						break;
					default:
						throw new BookLogApiException(CONSTANTS.ERC006, CONSTANTS.ERC006_DESC, HttpStatus.BAD_REQUEST);
					}
					
				}else {
					throw new BookLogApiException(CONSTANTS.ERC007, "Invalid action.", HttpStatus.BAD_REQUEST);
				}
				
				dao.updateBook(bookEntity);

				
			} catch (EmptyResultDataAccessException e) {
				throw new BookLogApiException(CONSTANTS.ERC007, "ISBN invalid", HttpStatus.BAD_REQUEST);
			}
			
		}		
		

	}

	@Override
	public void deleteBook(String isbn) throws BookLogApiException {

		try {
			BookEntity bookEntity = dao.getBook(isbn);
			dao.deleteBook(bookEntity);
		} catch (EmptyResultDataAccessException e) {
			throw new BookLogApiException(CONSTANTS.ERC007, "ISBN invalid", HttpStatus.BAD_REQUEST);
		}

	}

}
