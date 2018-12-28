package com.masterBook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.springframework.stereotype.Repository;

import com.masterBook.model.AdminDataVO;

@Repository
public class AdminDAO {
	
	public boolean verifyAdmin(AdminDataVO adminData) {
		boolean verify=false;
		
		
		Connection  con=null;
		Statement stm=null;
		ResultSet rs=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/bookexchange", "root", "root");
			stm = con.createStatement();
			String sql = "SELECT * FROM ADMIN_DATA where USER_NAME='"+adminData.getUserName()+"' and password='"+adminData.getPassword()+"'";
			rs = stm.executeQuery(sql);
			
			if(rs.next()) {
				System.out.println(rs.getString("id"));
				verify=true;	
			}
		}catch(Exception e) {
			System.out.println("dao verify method "+e);
		}finally {
			try {
				rs.close();
				stm.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return verify;
	}
}
