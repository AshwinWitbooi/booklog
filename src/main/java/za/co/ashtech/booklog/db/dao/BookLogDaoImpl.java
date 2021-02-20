package za.co.ashtech.booklog.db.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ch.qos.logback.classic.Logger;
import za.co.ashtech.booklog.db.entity.BookEntity;
import za.co.ashtech.booklog.db.entity.TxLogEntity;
import za.co.ashtech.booklog.util.CONSTANTS;

@Repository
@Transactional
public class BookLogDaoImpl implements BookLogDao {
	
	private static final Logger logger =  (Logger) LoggerFactory.getLogger(BookLogDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public void persistBook(BookEntity book) {
		logger.debug(CONSTANTS.APPINFOMARKER,"persistBook");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		try {
			session.save(book);

			tx.commit();

		} catch (Exception e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}

	}

	@Override
	public void persistTx(TxLogEntity tx) {
		logger.debug(CONSTANTS.APPINFOMARKER,"persistTx");
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		try {
			session.save(tx);

			transaction.commit();

		} catch (Exception e) {	
			transaction.rollback();
			throw e;
		} finally {			
			session.close();
		}
	}

	@Override
	public void updateBook(BookEntity book) {
		logger.debug(CONSTANTS.APPINFOMARKER,"updateBook");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		try {
			session.update(book);

			tx.commit();

		} catch (Exception e) {
			tx.rollback();
			throw e;
		} finally {			
			session.close();
		}
	}

	@Override
	public BookEntity getBook(String isbn) {
		logger.debug(CONSTANTS.APPINFOMARKER,"getBook");
		Session session = sessionFactory.openSession();
		BookEntity record = null;

//		try {
			Query<BookEntity> query =  session.createQuery("from BookEntity where isbn=:isbn", BookEntity.class);
			query.setParameter("isbn", isbn);
			
			record =  query.getSingleResult();
			
			//initialize lazy load
//			Hibernate.initialize(record.getAuthors());
			
			session.close();

//		} catch (Exception e) {
//			throw e;
//		} finally {
//			
//		}	
		
		return record;
	}

	@Override
	public void deleteBook(BookEntity book) {
		logger.debug(CONSTANTS.APPINFOMARKER,"deleteBook");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		try {
			session.delete(book);
			tx.commit();

		} catch (Exception e) {
			tx.rollback();
			throw e;
		} finally {			
			session.close();
		}
	}

	@Override
	public List<BookEntity> getBooks() {
		logger.debug(CONSTANTS.APPINFOMARKER,"getBook");
		Session session = sessionFactory.openSession();
		List<BookEntity> records = null;

//		try {
			Query<BookEntity> query =  session.createQuery("from BookEntity", BookEntity.class);
			
			records =  query.getResultList();
			
			for(BookEntity be:records) {
				Hibernate.initialize(be.getAuthors());
			}
			
			
			session.close();

//		} catch (Exception e) {
//			throw e;
//		} finally {
//			
//		}	
		
		return records;

	}

}
