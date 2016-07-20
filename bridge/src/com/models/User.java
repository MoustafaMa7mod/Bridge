package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.models.User;
import com.models.DBConnection;
import com.models.User;
import com.mysql.jdbc.Statement;

public class User {
	String firstName,lastName,userName,password,mail,gender;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public static String signup(String firstName,String lastName,String userName,String password,String mail,String gender) throws SQLException
	{
		 Connection con = DBConnection.getActiveConnection();
	        String sql = "select userName from user where userName= '"+userName+"' ;" ; 
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			String res="";
			if(rs.next())
			{
				return res;
			}
			res=userName;
			sql ="insert into user values ('"+firstName+"','"+lastName+"','"+userName+"' , '"+password+"','"+mail+"','"+gender+"');";
			stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.executeUpdate();
			return res;
			
	}
	
	public static String signin(String userName,String password) throws SQLException
	{
		    Connection con = DBConnection.getActiveConnection();
	        String sql = "select userName,password from user where userName= '"+userName+"' ;" ; 
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			String res="";
			if(rs.next())
			{
				res=userName;
				return res;
			}
			return res;
			
	}
	

}
