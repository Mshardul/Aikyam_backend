package dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import conn.ConnectionUtil;

import pojo.User;

public class UserDao {

	public UserDao() {
		// TODO Auto-generated constructor stub
	}

	
	public int AddUser(User us, int type) {
		int ans = 1;
		Connection conn = null;
		String sql = "INSERT INTO";
		if(type==1)
			sql+=" Donor ";
		else
			sql+=" Charity ";
		sql += "(name, username, password, location, email, phone) ";
		String val = "VALUES ('"+us.getName()+"', '"+us.getUsername()+"', '"+us.getPassword()+"', '"+us.getLocation()+"', '"+us.getEmail()+"', '"+us.getPhone()+"');";
		System.out.println(sql+val);
		try{
			conn = ConnectionUtil.GetConn();
			Statement stmt = conn.createStatement();
			stmt.execute(sql+val);
		}
		catch(Exception e) {
			ans = 0;
			e.printStackTrace();
		}
		finally {
			ConnectionUtil.CloseConn(conn);
		}
		return ans;
	}
	
	public int CheckAdmin(String username, String password) {
		int ans = 0;
		Connection conn = null;
		String sql = "SELECT * FROM Admin";
		try {
			conn = ConnectionUtil.GetConn();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs==null) {
				sql = "INSERT INTO Admin (username, password) VALUES ('admin', 'admin');";
				if(stmt.execute(sql)==false)
					ans = CheckAdmin(username, password);
				else
					ans = -1;
			}
			else {
				sql += "WHERE username = '"+username+"' AND password = '"+password+"';";
				rs = stmt.executeQuery(sql);
				if(rs==null)
					ans = 0;
				else 
					ans = 1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			ConnectionUtil.CloseConn(conn);
		}
		return ans;
	}
	
	
	public int CheckUser(String username, String password, int type) {
		int ans = 1;
		
		Connection conn = null;
		String sql = "SELECT id FROM";
		if(type==3) {
			ans = CheckAdmin(username, password);
		}
		else{
			if(type==1) {
				sql+=" Donor ";
			}
			else{
				sql+=" Charity ";
			}
			sql+="(username, password) ";
			String val = "VALUES ("+username+", "+password+");";
			try {
				conn = ConnectionUtil.GetConn();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql+val);
				if(rs==null)
					ans=0;
				else
					ans=1;
			}
			catch(Exception e) {
				ans = -1;
			}
			finally {
				ConnectionUtil.CloseConn(conn);
			}
		}
		return ans;
	}
}
