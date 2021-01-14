package za.co.ashtech.booklog.service;

import za.co.ashtech.booklog.model.Book;
import za.co.ashtech.booklog.util.BookLogApiException;

public interface BookLogService {
	
	public void createBook(Book book) throws BookLogApiException;

}
