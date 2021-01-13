package za.co.ashtech.booklog.db.dao;

import za.co.ashtech.booklog.db.entity.Book;

public interface BookLogDao {

	public void persistBook(Book book);
}
