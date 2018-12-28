package com.masterBook.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.masterBook.dao.BookDAO;
import com.masterBook.model.BookDataVO;

@Service
@Transactional
public class BookService {
	
	@Autowired
	BookDAO bookDAO;
	
	public void saveBookData(BookDataVO data) {
		bookDAO.saveBookData(data);
	}
	
	public ArrayList<BookDataVO> getAllBooksFromDb(){
		return bookDAO.getAllBooks();
	}
	
	public ArrayList<BookDataVO> getAllBooksFromDb(int userId){
		return bookDAO.getAllBooksOfUser(userId);
	}
	
	public BookDataVO getBookDetails(double bookId) {
		return bookDAO.getBookById(bookId);
	}
	
	public void removeBookFromDbById(int bookId) {
		bookDAO.removeBookById(bookId);
	}
	
	public void updateBookDetails(BookDataVO bookDataUpdateForm) {
		bookDAO.updateBookDetails(bookDataUpdateForm);
	}
}