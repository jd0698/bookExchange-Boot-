package com.masterBook.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.masterBook.dao.BookDAO;
import com.masterBook.model.BookDataVO;

@Service
@Transactional
public class BookService {
	
	@Autowired
	BookDAO bookDAO;
	
	public void saveBookData(BookDataVO data,MultipartFile file,String UPLOADED_FOLDER,int userId) {
		Path path = null;
		if (!file.isEmpty()) {													//check if file is available
            try {
            	 byte[] bytes = file.getBytes();
            	 
            	 String extensionOfFile = null,nameOfFile = null;
            	 nameOfFile = file.getOriginalFilename();
            	 int indexOfDot = nameOfFile.lastIndexOf('.');
            	 extensionOfFile = nameOfFile.substring(indexOfDot);
            	 
            	 int numberOfBooksOfUser = bookDAO.numberOfBooksByUser(userId);
            	 numberOfBooksOfUser++;
            	 
            	 nameOfFile = numberOfBooksOfUser + "_" + userId + extensionOfFile;							//kam baki
            	 
            	 
            	 Path path1 = Paths.get(UPLOADED_FOLDER + "userFile");
            	 if(!Files.exists(path1)) {
            		 Files.createDirectories(path1);
            	 }
            	 UPLOADED_FOLDER = UPLOADED_FOLDER + "userFile//";
            	 
            	 path1 = Paths.get(UPLOADED_FOLDER + "BookImage"); 
            	 //System.out.println(path1.toString());
            	 if(!Files.exists(path1)) {
            		 Files.createDirectories(path1);
            	 }
            	 UPLOADED_FOLDER = UPLOADED_FOLDER + "BookImage//";
            	 
            	 path1 = Paths.get(UPLOADED_FOLDER + userId);
            	 if(!Files.exists(path1)) {
            		 Files.createDirectories(path1);
            	 }
            	 UPLOADED_FOLDER = UPLOADED_FOLDER + userId + "//";

                 path = Paths.get(UPLOADED_FOLDER + nameOfFile);
                 Files.write(path,bytes);
            }catch (IOException e) {
				e.printStackTrace();
			}
            
            data.setPathOfImage(path.toString());
            bookDAO.saveBookData(data);
        }
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