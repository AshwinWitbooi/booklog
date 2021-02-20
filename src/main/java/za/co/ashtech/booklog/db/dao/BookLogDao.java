package za.co.ashtech.booklog.db.dao;

import java.util.List;

import za.co.ashtech.booklog.db.entity.BookEntity;
import za.co.ashtech.booklog.db.entity.TxLogEntity;

public interface BookLogDao {

	public void persistBook(BookEntity book);
	public void updateBook(BookEntity book);
	public BookEntity getBook(String isbn);
	public void deleteBook(BookEntity book);
	public void persistTx(TxLogEntity tx);
	public List<BookEntity> getBooks();
}
