package Aikyam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Aikyam.conn.ConnectionUtil;
import Aikyam.pojo.Admin;
import Aikyam.pojo.LoginModel;

public class AdminDao {

	public AdminDao() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean Validate(LoginModel lm) {
		Connection conn = null;
		try {
			String username = lm.getUsername();
			String password = lm.getPassword();
			System.out.println(username+" "+password);
			conn = ConnectionUtil.GetConn();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ADMIN WHERE USERNAME=? AND PASSWORD=?;");
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet resSet = stmt.executeQuery();
			while (resSet.next()) {
				ConnectionUtil.CloseConn(conn);
				return true;
			}
			ConnectionUtil.CloseConn(conn);
		}catch(Exception e) {
			System.out.println("some error: ");
			System.out.println(e);
		}
		return false;
	}
	
	public Admin GetInfo() {
		Connection conn = null;
		try {
			conn = ConnectionUtil.GetConn();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ADMIN");
			ResultSet resSet = stmt.executeQuery();
			while(resSet.next()) {
				Admin ret = new Admin(resSet.getString(1), "");
				ret.setAim(resSet.getString(3));
				ConnectionUtil.CloseConn(conn);
				return ret;
			}
			ConnectionUtil.CloseConn(conn);
		}catch(Exception e) {
			System.out.println(e);
			return null;
		}
		return null;
	}
	
	public boolean UpdateInfo(Admin a) {
		String username = a.getUsername();
		String password = a.getPassword();
		String aim = a.getAim();
		
		String sql = "UPDATE ADMIN SET AIM='"+aim;
		if(!(password.isEmpty()))
			sql += "', PASSWORD='"+password;
		sql += "' WHERE USERNAME='"+username+"';";
		
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
