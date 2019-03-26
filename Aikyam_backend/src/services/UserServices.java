package services;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.json.JSONObject;

import conn.ConnectionUtil;

import pojo.User;

import dao.UserDao;

import java.sql.Connection;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;


@Path("/user")
public class UserServices {

	public UserServices() {
		// TODO Auto-generated constructor stub
	}

	@POST
	@Path("/reg")
	@Consumes("application/json")
	@Produces("Integer")
	public int regUser(JSONObject obj){
		String name = (String) obj.get("name");
		String username = (String) obj.get("username");
		String password = (String) obj.get("password");
		String location = (String) obj.get("location");
		String email = (String) obj.get("email");
		String phone = (String) obj.get("phone");
		int type = (int) obj.get("type");
		
		int ans;
		
		User us = new User(name, username, password, location, email, phone);
		UserDao ud = new UserDao();
		if(type==1 || type==2) {
			ans = ud.AddUser(us, type);
		}
		else {
			ans=0;
		}
		return ans;
	}
	
	@POST
	@Path("/login")
	@Consumes("application/json")
	@Produces("Integer")
	public int loginUser(JSONObject obj) {
		String username = (String) obj.get("username");
		String password = (String) obj.get("password");
		int type = (int) obj.get("type");
		
		if(type>3 || type<1)
			return 1;
		
		UserDao ud = new UserDao();
		return (ud.CheckUser(username, password, type));
			
	}
	
}
