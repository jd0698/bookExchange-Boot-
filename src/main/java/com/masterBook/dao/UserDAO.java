package com.masterBook.dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.Query;

import com.masterBook.model.UserDataVO;

@Repository
public class UserDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	

	public void registerUser(UserDataVO data) {
		Session session = sessionFactory.getCurrentSession();
		session.save(data);		
	}
	
	public UserDataVO verifyUser(UserDataVO userData) {
		UserDataVO user = null;
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM UserDataVO WHERE email='"+userData.getEmail()+"' and password='"+userData.getPassword()+"'");
		ArrayList<UserDataVO> list = (ArrayList<UserDataVO>)query.list();
		if(!list.isEmpty()) {
			user = list.get(0);
		}		
		return user;
	}	
}
