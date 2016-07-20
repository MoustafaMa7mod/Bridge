package com.services;

import java.sql.Connection; 
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import com.models.*;

import org.glassfish.*;
import org.json.simple.JSONArray;
//import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONObject;

import com.models.DBConnection;
import com.models.User;


@Path("/")
public class Services {

	
		
	

	@POST
	@Path("/favOrg")
	@Produces(MediaType.TEXT_PLAIN)
	public String favOrg(@FormParam("orgName") String orgName, @FormParam("userName") String userName  ) throws SQLException {
		
	    boolean b = favorite.addFavOrg(orgName, userName);
		JSONObject json = new JSONObject();
		json.put("status", b? 1 : 0);
		return json.toJSONString();
	}
	
	@POST
	@Path("/favCourse")
	@Produces(MediaType.TEXT_PLAIN)
	public String favCourse(@FormParam("cName") String cName, @FormParam("userName") String userName  ) throws SQLException {
		
	    boolean b = favorite.addFavcourse(cName, userName);
		JSONObject json = new JSONObject();
		json.put("status", b? 1 : 0);
		return json.toJSONString();
	}
	
	@POST
	@Path("/favCat")
	@Produces(MediaType.TEXT_PLAIN)
	public String favCategory(@FormParam("category") String category, @FormParam("userName") String userName  ) throws SQLException {
		
	    boolean b = favorite.addFavCat(category, userName);
		JSONObject json = new JSONObject();
		json.put("status", b? 1 : 0);
		return json.toJSONString();
	}
	
	@POST
	@Path("/signup")
	@Produces(MediaType.TEXT_PLAIN)
	public String signup(@FormParam("fName") String fName,@FormParam("lName") String lName, @FormParam("userName") String userName,@FormParam("password") String password,@FormParam("mail") String mail,@FormParam("gender") String gender) throws SQLException {
		
	    String b = User.signup(fName, lName, userName, password, mail, gender);
		JSONObject json = new JSONObject();
		json.put("status", b);
		return json.toJSONString();
	}
	
	@POST
	@Path("/signin")
	@Produces(MediaType.TEXT_PLAIN)
	public String signin( @FormParam("userName") String userName,@FormParam("password") String password) throws SQLException {
		
	    String b = User.signin(userName, password);
		JSONObject json = new JSONObject();
		json.put("status", b);
		return json.toJSONString();
	}
	@POST
	@Path("/showFavoriteOrg")
	@Produces(MediaType.TEXT_PLAIN)
	public String showFavoriteOrg(@FormParam("userName")  String userName ) throws SQLException {
		
	    ArrayList<organization>org = organization.showFavouriteOrganization(userName);
	    JSONArray jsonArray =new JSONArray();
	    for (int i=0;i<org.size();i++)
	    {
	    	JSONObject json = new JSONObject();
	    	
	    	jsonArray.add(json);
	    	
	    }
		
		
		return jsonArray.toJSONString();
	}
	
	
	@POST
	@Path("/addCourse")
	@Produces(MediaType.TEXT_PLAIN)
	public String addCourse( @FormParam("companyName") String companyName,@FormParam("name") String name,
			@FormParam("hours") String hours,@FormParam("experience") String experience,
			@FormParam("requirments") String requirments,@FormParam("careerLevel") String careerLevel,
			@FormParam("applicationDeadline") String applicationDeadline,
			@FormParam("resbonsabilities") String resbonsabilities,@FormParam("duration") String duration,
			@FormParam("about") String about,@FormParam("startTime") String startTime,@FormParam("kind") String kind,
			@FormParam("category") String category,@FormParam("price") String price) throws SQLException {
		
	    boolean b =Course.addCourse(name, hours, experience, requirments, careerLevel, applicationDeadline, 
	    		resbonsabilities, duration, about, startTime, kind, category, price, companyName);
	    		
		JSONObject json = new JSONObject();
		json.put("status", b);
		return json.toJSONString();
	}
}
