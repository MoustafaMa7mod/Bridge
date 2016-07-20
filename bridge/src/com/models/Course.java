package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

public class Course {
    int courseID,hours;
    double price;
	String companyName,name,experience,requirments,careerLevel,applicationDeadline,resbonsabilities,duration,about,startTime,kind,category;
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getExperience() {
		return experience;
	}
	
	public int getCourseID() {
		return courseID;
	}
	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}
	public int getHours() {
		return hours;
	}
	public void setHours(int hours) {
		this.hours = hours;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public String getRequirments() {
		return requirments;
	}
	public void setRequirments(String requirments) {
		this.requirments = requirments;
	}
	public String getCareerLevel() {
		return careerLevel;
	}
	public void setCareerLevel(String careerLevel) {
		this.careerLevel = careerLevel;
	}
	public String getApplicationDeadline() {
		return applicationDeadline;
	}
	public void setApplicationDeadline(String applicationDeadline) {
		this.applicationDeadline = applicationDeadline;
	}
	public String getResbonsabilities() {
		return resbonsabilities;
	}
	public void setResbonsabilities(String resbonsabilities) {
		this.resbonsabilities = resbonsabilities;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	
	public double getPrice() {
		return price;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	
	public static boolean addCourse(String name,String hours,String experience,String requirments,String careerLevel,String applicationDeadline,String resbonsabilities,
			String duration,String about,String startTime,String kind,String category,String price,String companyName) throws SQLException
	{
		   	
		    Connection con = DBConnection.getActiveConnection();
		    String sql = "select companyID from organization where name= '"+companyName+"' ;" ; 
		    PreparedStatement stmt = con.prepareStatement(sql);
		    ResultSet rs = stmt.executeQuery();
		    int ID=0;
		    if(rs.next())
		    {
		    	ID=rs.getInt("companyID");
		        sql = "insert into course (companyID,name,hours,experience,requirements,careerLevel,applicationDeadline,resbonsibilites,duration,about,price,startTime,kind,category)"
	        		+ " values ("+ID+",'"+name+"',"+hours+" ,'"+experience+"' , '"+requirments+"','"+careerLevel+"','"+applicationDeadline+"','"+resbonsabilities+"','"+duration+"','"+about+"','"+price+"','"+startTime+"','"+kind+"','"+category+"');" ; 
			  stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			  stmt.executeUpdate();
		      return true;
		    }
		    return false;
	}
	
	public Course(int courseID, int hours, double price, String companyName,
			String name, String experience, String requirments,
			String careerLevel, String applicationDeadline,
			String resbonsabilities, String duration, String about,
			String startTime, String kind, String category) {
		super();
		this.courseID = courseID;
		this.hours = hours;
		this.price = price;
		this.companyName = companyName;
		this.name = name;
		this.experience = experience;
		this.requirments = requirments;
		this.careerLevel = careerLevel;
		this.applicationDeadline = applicationDeadline;
		this.resbonsabilities = resbonsabilities;
		this.duration = duration;
		this.about = about;
		this.startTime = startTime;
		this.kind = kind;
		this.category = category;
	}
	public static ArrayList<Course> showFavouriteCourse(String userName) throws SQLException
	{
		ArrayList<Integer>IDs=new ArrayList<>();
		 Connection con = DBConnection.getActiveConnection();
		ArrayList<Course> cor = null;
	        String sql = "select courseID from fav_course where userName= '"+userName+"' ;" ; 
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{
				IDs.add(rs.getInt("courseID"));
			}
			for(int i=0;i<IDs.size();i++)
			{
				sql="select * from course where courseID ="+IDs.get(i)+ ";";
				stmt = con.prepareStatement(sql);
				rs = stmt.executeQuery();
				Course co=new Course(rs.getInt("courseID"),rs.getInt("hours"),rs.getDouble("price"),rs.getString("companyName"),
						rs.getString("name"),rs.getString("experience"),rs.getString("requirements"),
						rs.getString("careerLevel"),rs.getString("applicationDeadLine"),rs.getString("resbonsibilites"),rs.getString("duration"),
						rs.getString("about"),rs.getString("startTime"),rs.getString("kind"),rs.getString("category"));
				cor.add(co);
				
				
			}
			return cor;
		
		
	
	}
	}
