package com.masterBook.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.masterBook.dao.AdminDAO;
import com.masterBook.model.AdminDataVO;

@Service
@Transactional
public class AdminService {
	public boolean verifyAdmin(AdminDataVO adminData) {
		AdminDAO adminDAO = new AdminDAO();
		boolean isAdmin = adminDAO.verifyAdmin(adminData);
		if(isAdmin){
			return true;
		}
		return false;
	}
}