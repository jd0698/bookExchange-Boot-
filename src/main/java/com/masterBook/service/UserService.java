package com.masterBook.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masterBook.dao.UserDAO;
import com.masterBook.model.UserDataVO;

@Service
@Transactional
public class UserService {
	
	@Autowired
	UserDAO userDao;
	
	public void registerUser(UserDataVO data) {
		userDao.registerUser(data);
	}

	public UserDataVO verifyUser(UserDataVO userData) {
		UserDataVO data = userDao.verifyUser(userData);
		return data;
	}
}
