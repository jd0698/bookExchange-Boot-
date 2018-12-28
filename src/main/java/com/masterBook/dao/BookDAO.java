package com.masterBook.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.Query;

import com.masterBook.model.BookDataVO;

@Repository
public class BookDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	public ArrayList<BookDataVO> getAllBooks() {		
		ArrayList<BookDataVO> listOfAllBook = new ArrayList<BookDataVO>();
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("From BookDataVO");
		listOfAllBook = (ArrayList<BookDataVO>)query.list();
		return listOfAllBook;
	}
	
	public BookDataVO getBookById(double bookId) {      // when more Info is clicked
		BookDataVO bookData = null;
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("From BookDataVO WHERE id="+bookId);
		List<BookDataVO> list = (List<BookDataVO>)query.list();
		if(!list.isEmpty()) {
			bookData = list.get(0);
		}
		return bookData;
	}
	
	public ArrayList<BookDataVO> getAllBooksOfUser(int userId){
		ArrayList<BookDataVO> allBooksOfUser = null;
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("From BookDataVO WHERE idOfUser="+userId);
		allBooksOfUser = (ArrayList<BookDataVO>)query.list();
		return allBooksOfUser;
	}
	
	public void removeBookById(int bookId){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("DELETE FROM BookDataVO WHERE id="+bookId);
		query.executeUpdate();
	}
	
	public void updateBookDetails(BookDataVO bookDataUpdateForm) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(bookDataUpdateForm);
	}
	
	public void saveBookData(BookDataVO data) {
		Session session = sessionFactory.getCurrentSession();
		session.save(data);
	}
}
