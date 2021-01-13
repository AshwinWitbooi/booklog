package za.co.ashtech.booklog.db.dao;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import za.co.ashtech.booklog.db.entity.BookEntity;

@Repository
@Transactional
public class BookLogDaoImpl implements BookLogDao {
	
	@Autowired
    private SessionFactory sessionFactory;
	
	public void persistBook(BookEntity book) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.save(book);
			
			tx.commit();
			
		} catch (Exception e) {
			throw e;
		}finally {
			session.close();
		}
		
	}

}