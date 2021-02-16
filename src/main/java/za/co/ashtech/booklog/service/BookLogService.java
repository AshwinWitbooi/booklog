package za.co.ashtech.booklog.service;

import za.co.ashtech.booklog.model.Book;
import za.co.ashtech.booklog.model.Editing;
import za.co.ashtech.booklog.util.BookLogApiException;


public interface BookLogService {
	
	public void createBook(Book book) throws BookLogApiException;
	public void updateBook(Editing editing, String isbn) throws BookLogApiException;
	public void deleteBook(String isbn) throws BookLogApiException;
}
