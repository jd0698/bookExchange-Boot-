package com.masterBook.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.masterBook.service.BookService;
import com.masterBook.model.BookDataVO;
import com.masterBook.model.UserDataVO;

@Controller
public class BookController {
	
	@Autowired
	BookService bookService;
	
	@RequestMapping(value="manageMyBooks")
	public ModelAndView manageBooks(HttpSession session) {
		ArrayList<BookDataVO> listOfAllBooksByUser = null; 
		UserDataVO userData = (UserDataVO)session.getAttribute("userData");
		listOfAllBooksByUser = bookService.getAllBooksFromDb(userData.getId());
		ModelAndView mv = new ModelAndView();
		mv.setViewName("ShowAllBooksOfUser");
		mv.addObject("listOfAllBooksByUser", listOfAllBooksByUser);
		return mv;
	}
	
	@RequestMapping(value="removeBook")
	public ModelAndView removeBook(HttpSession session , HttpServletRequest request) {
		ModelAndView mv = null;
		int bookId = Integer.parseInt(request.getParameter("idOfBook"));
		bookService.removeBookFromDbById(bookId);
		mv = manageBooks(session);
		return mv;
	}
	
	@RequestMapping(value="updateBookDataPage")
	public ModelAndView updateBookDataPage(HttpServletRequest request) {
		int bookId = Integer.parseInt(request.getParameter("idOfBook"));
		BookDataVO bookData = bookService.getBookDetails(bookId);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("UpdateBookData");
		mv.addObject("bookDataUpdateForm",bookData);
		return mv;
	}
	
	@RequestMapping(value="updateBookData")
	public ModelAndView updateBookData(@ModelAttribute BookDataVO bookDataUpdateForm , HttpSession session) {
		System.out.println(bookDataUpdateForm.getId());
		bookService.updateBookDetails(bookDataUpdateForm);
		ModelAndView mv = manageBooks(session);
		return mv;
	}
	
	@RequestMapping("showAllBooks")
	public ModelAndView showBooks() {
		ArrayList<BookDataVO> listOfAllBooks = null;
		listOfAllBooks = bookService.getAllBooksFromDb();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("ShowAllBooks");
		mv.addObject("listOfAllBooks", listOfAllBooks);	
		return mv;
	}
	
	@RequestMapping("ShowBookInfo")
	public ModelAndView showBookInfo(HttpServletRequest request, HttpServletResponse response) {
		double bookId = Double.parseDouble(request.getParameter("idOfBook"));
		BookDataVO bookDetails = bookService.getBookDetails(bookId);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("ShowBookInfo");
		mv.addObject("bookDetails", bookDetails);
		
		return mv;
	}
	
	@RequestMapping(value="uploadBook")
	public ModelAndView uploadBook() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("UploadBook");
		mv.addObject("bookDataForm",new BookDataVO());
		return mv;
	}
	
	@RequestMapping(value="saveBookData" , method=RequestMethod.POST)
	public String submitBookData(@ModelAttribute() BookDataVO bookDataForm,@RequestParam("file") MultipartFile file,HttpSession session) {
		final String UPLOADED_FOLDER = session.getServletContext().getRealPath("/");
		UserDataVO userDataVO = (UserDataVO)session.getAttribute("userData");
		bookService.saveBookData(bookDataForm,file,UPLOADED_FOLDER,userDataVO.getId());
		return "redirect:/homePage";
	}
}