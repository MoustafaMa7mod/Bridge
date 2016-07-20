package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

public class organization {
	int companyID ;
	String name;
	String password;
	String location;
	String review;
	String email;
	String facebook;
	String gmail;
	public int getCompanyID() {
		return companyID;
	}
	public void setCompanyID(int companyID) {
		this.companyID = companyID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFacebook() {
		return facebook;
	}
	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}
	public String getGmail() {
		return gmail;
	}
	public void setGmail(String gmail) {
		this.gmail = gmail;
	}
	
	
	public organization(int companyID, String name, String password,
			String location, String review, String email, String facebook,
			String gmail) {
		this.companyID = companyID;
		this.name = name;
		this.password = password;
		this.location = location;
		this.review = review;
		this.email = email;
		this.facebook = facebook;
		this.gmail = gmail;
	}
	public organization() {
		this.companyID = 0;
		this.name = "";
		this.password ="";
		this.location = "";
		this.review ="";
		this.email = "";
		this.facebook = "";
		this.gmail = "";
	}
	public static ArrayList<organization> showFavouriteOrganization(String userName) throws SQLException
	{
		ArrayList<Integer>IDs=new ArrayList<>();
		 Connection con = DBConnection.getActiveConnection();
		  ArrayList<organization> org = null;
	        String sql = "select companyID from fav_org where userName= '"+userName+"' ;" ; 
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{
				IDs.add(rs.getInt("companyID"));
			}
			for(int i=0;i<IDs.size();i++)
			{
				sql="select * from organization where companyID ="+IDs.get(i)+ ";";
				stmt = con.prepareStatement(sql);
				rs = stmt.executeQuery();
				organization or=new organization(rs.getInt("companyID"),rs.getString("name"),rs.getString("password"),rs.getString("location"),rs.getString("review"),rs.getString("email"),rs.getString("facebook"),rs.getString("gmail"));
				org.add(or);
				
				
			}
		return null;
	}
	
	public static boolean addOrg(String name, String password,String location, String review, String email, String facebook,
			String gmail) throws SQLException 
	{
		    Connection con = DBConnection.getActiveConnection();
	        String sql = "select name from organization where name= '"+name+"' ;" ; 
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
		    if(rs.next())
		    {
		    	return false; 
		    }
		    
		    sql ="insert into organization (name,password,location,review,email,facebook,gmail)values ( '"+name+"','"+password+"','"+location+"' , '"+review+"','"+email+"','"+facebook+"','"+gmail+"');";
			stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.executeUpdate();
            return true;
		     
	}
	
	public static organization searchOrganization(String orgName) throws SQLException
	
	{
		   Connection con = DBConnection.getActiveConnection();
	        String sql = "select * from organization where name= '"+orgName+"' ;" ; 
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
		    if(rs.next())
		    {
		    	organization or=new organization(rs.getInt("companyID"),rs.getString("name"),rs.getString("password"),rs.getString("location"),rs.getString("review"),rs.getString("email"),rs.getString("facebook"),rs.getString("gmail"));
		        return or;
		    }
		    organization or=new organization();
		    return or;
		    	
	}

}
