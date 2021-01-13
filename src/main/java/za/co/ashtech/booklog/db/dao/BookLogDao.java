package za.co.ashtech.booklog.db.dao;

import za.co.ashtech.booklog.db.entity.BookEntity;

public interface BookLogDao {

	public void persistBook(BookEntity book);
}
