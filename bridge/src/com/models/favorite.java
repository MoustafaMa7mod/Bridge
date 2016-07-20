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
public class favorite {

	
	
	
	public static boolean addFavOrg(String orgName, String userName) throws SQLException
	{
		    Connection con = DBConnection.getActiveConnection();
	        String sql = "select companyID from organization where name= '"+orgName+"' ;" ; 
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			int ID =0;
			if (rs.next())
			{
				ID=rs.getInt("companyID");
				sql ="insert into fav_org values ('"+userName+"' , "+ID+ ");";
				stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				stmt.executeUpdate();
				return true;
			}
		return false;
	}
	
	public static boolean addFavcourse(String cName, String userName) throws SQLException
	{
		    Connection con = DBConnection.getActiveConnection();
	        String sql = "select courseID from course where name= '"+cName+"' ;" ; 
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			int ID =0;
			if (rs.next())
			{
				ID=rs.getInt("courseID");
				sql ="insert into fav_course values ("+ID+ " , '"+userName+"' );";
				stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				stmt.executeUpdate();
				return true;
			}
		return false;
	}
	
	public static boolean addFavCat(String category, String userName) throws SQLException
	{
		    Connection con = DBConnection.getActiveConnection();
	        String sql = "select courseID from course where category= '"+category+"' ;" ; 
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			int ID =0;
			if (rs.next())
			{
				ID=rs.getInt("courseID");
				sql ="insert into fav_cat values ('"+userName+"',"+ID+ "  );";
				stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				stmt.executeUpdate();
				return true;
			}
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
