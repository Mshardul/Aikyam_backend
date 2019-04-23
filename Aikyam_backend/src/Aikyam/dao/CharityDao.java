package Aikyam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Aikyam.conn.ConnectionUtil;
import Aikyam.pojo.Charity;
import Aikyam.pojo.LoginModel;

public class CharityDao {

	public CharityDao() {
		// TODO Auto-generated constructor stub
	}
	
	public Charity Validate(LoginModel lm) {
		if(ValidateLogin(lm)==true) {
			System.out.println("credentials validated");
			return GetInfo(lm.getUsername());}
		else {
			return null;
		}
	}

	public Boolean ValidateLogin(LoginModel lm) {
		Connection conn;
		try {
			String username = lm.getUsername();
			String password = lm.getPassword();
			System.out.println(username+" "+password);
			conn = ConnectionUtil.GetConn();
			PreparedStatement stmt = conn.prepareStatement("SELECT PASSWORD FROM CHARITY WHERE " + "USERNAME=?;");
			stmt.setString(1, username);
			ResultSet resSet = stmt.executeQuery();
			while (resSet.next()) {
				String pwd = resSet.getString(1);
				if (pwd.equals(password) == true) {
					return true;
				}
			}
			System.out.println("success");
		}catch(Exception e) {
			System.out.println("some error: ");
			System.out.println(e);
		}
		return false;
	}
	
	public Charity GetInfo(String username) {
		Connection conn;
		try {
			conn = ConnectionUtil.GetConn();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM CHARITY WHERE " + "USERNAME=?;");
			stmt.setString(1, username);
			ResultSet resSet = stmt.executeQuery();
			while (resSet.next()) {
				Charity cUser = new Charity(resSet.getString(2), resSet.getString(3), resSet.getString(4), resSet.getString(5), resSet.getString(6), resSet.getString(7));
				ConnectionUtil.CloseConn(conn);
				System.out.println("got info");
				cUser.setPassword("");
				return cUser;
			}
			System.out.println("got info");
		}catch(Exception e) {
			System.out.println("no info, coz");
			System.out.println(e);
		}
		return null;
	}
	
	public boolean Insert(Charity c) {
		Connection conn;
		try {
			conn = ConnectionUtil.GetConn();
			String sql = "INSERT INTO CHARITY (name, username, password, location, email, phone) VALUES (?, ?, ?, ?, ?, ?);";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, c.getName());
			stmt.setString(2, c.getUsername());
			stmt.setString(3, c.getPassword());
			stmt.setString(4, c.getLocation());
			stmt.setString(5, c.getEmail());
			stmt.setString(6, c.getPhone());
			int x = stmt.executeUpdate();
			System.out.println(x);
			if(x==1)
				return true;
			return false;
		}catch(Exception e) {
			System.out.println(e);
			return false;
		}
	}
	public Charity Register(Charity c) {
		System.out.println(c.toString());
		String username = c.getUsername();
		String password = c.getPassword();
		LoginModel lm = new LoginModel(username, password);
		if(ValidateLogin(lm)==true) {
			return null;
		}else {
			if(Insert(c)==true)
				return GetInfo(username);
		}
		return null;
	}
	
	@SuppressWarnings("null")
	public ArrayList<Charity> GetAll() {
		System.out.println("Getting the charity list");
		Connection conn;
		ArrayList<Charity> res = new ArrayList<Charity>();
		try {
			conn = ConnectionUtil.GetConn();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM CHARITY;");
			ResultSet resSet = stmt.executeQuery();
			while (resSet.next()) {
				int id = resSet.getInt(1);
//				System.out.println(resSet.getInt(2));
				String name = resSet.getString(2);
				String username = resSet.getString(3);
				String location = resSet.getString(5);
				String email = resSet.getString(6);
				String phone = resSet.getString(7);
//				Charity c = new Charity(name, username, email, "", phone, location);
//				System.out.println(c.toString());
				System.out.println(id);
				res.add(new Charity(name, username, "", location, email, phone));
//				temp.add(id);
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		return res;
	}
	
	public boolean UpdateInfo(Charity c) {
		String username = c.getUsername();
		String password = c.getPassword();
		String name = c.getName();
		String phone = c.getPhone();
		String idProof = c.getIDProof();
		String location = c.getLocation();
		String email = c.getEmail();
		String sql = "UPDATE CHARITY SET name = '"+name+"', phone = '"+phone+"', identity_proof = '"+idProof+"', location = '"+location+"', email = '"+email;
		if(!(password.isEmpty()))
			sql += "', password = '"+password;
		sql += "' WHERE username = '"+username+"';";
		System.out.println(sql);
		
		Connection conn = null;
		try {
			conn = ConnectionUtil.GetConn();
			PreparedStatement stmt = conn.prepareStatement(sql);
			int x = stmt.executeUpdate();
			if(x==1) {
				ConnectionUtil.CloseConn(conn);
				return true;
			}
			ConnectionUtil.CloseConn(conn);
			return false;
		}catch(Exception e) {
			System.out.println(e);
			ConnectionUtil.CloseConn(conn);
			return false;
		}
	}
}
