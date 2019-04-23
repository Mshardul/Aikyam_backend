package Aikyam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Aikyam.conn.ConnectionUtil;
import Aikyam.pojo.Donation;
import Aikyam.pojo.DonationModel;
import Aikyam.pojo.Donor;
import Aikyam.pojo.LoginModel;

public class DonorDao {

	public DonorDao() {
		// TODO Auto-generated constructor stub
	}
	
	public Donor Validate(LoginModel lm) {
		if(ValidateLogin(lm)==true) {
			System.out.println("credentials validated");
			return GetInfo(lm.getUsername());}
		else {
			return null;
		}
	}

	public Boolean ValidateLogin(LoginModel lm) {
		Connection conn = null;
		try {
			String username = lm.getUsername();
			String password = lm.getPassword();
			System.out.println(username+" "+password);
			conn = ConnectionUtil.GetConn();
			PreparedStatement stmt = conn.prepareStatement("SELECT PASSWORD FROM DONOR WHERE " + "USERNAME=?;");
			stmt.setString(1, username);
			ResultSet resSet = stmt.executeQuery();
			while (resSet.next()) {
				String pwd = resSet.getString(1);
				if (pwd.equals(password) == true) {
					ConnectionUtil.CloseConn(conn);
					return true;
				}
			}
			System.out.println("success");
		}catch(Exception e) {
			System.out.println("some error: ");
			System.out.println(e);
		}
		ConnectionUtil.CloseConn(conn);
		return false;
	}
	
	public Donor GetInfo(String username) {
		Connection conn = null;
		try {
//			String username = lm.getUsername();
			conn = ConnectionUtil.GetConn();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM DONOR WHERE " + "USERNAME=?;");
			stmt.setString(1, username);
			ResultSet resSet = stmt.executeQuery();
			while (resSet.next()) {
				Donor dUser = new Donor(resSet.getString(2), resSet.getString(3), resSet.getString(4), resSet.getString(5), resSet.getString(6), resSet.getString(7));
				ConnectionUtil.CloseConn(conn);
				System.out.println("got info");
				dUser.setPassword("");
				return dUser;
			}
		}catch(Exception e) {
			System.out.println("no info, coz");
			System.out.println(e);
		}
		ConnectionUtil.CloseConn(conn);
		return null;
	}
	
	public boolean Insert(Donor d) {
		Connection conn = null;
		try {
			conn = ConnectionUtil.GetConn();
			String sql = "INSERT INTO DONOR (name, username, password, location, email, phone) VALUES (?, ?, ?, ?, ?, ?);";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, d.getName());
			stmt.setString(2, d.getUsername());
			stmt.setString(3, d.getPassword());
			stmt.setString(4, d.getLocation());
			stmt.setString(5, d.getEmail());
			stmt.setString(6, d.getPhone());
			int x = stmt.executeUpdate();
			System.out.println(x);
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
	
	public Donor Register(Donor d) {
		String username = d.getUsername();
		String password = d.getPassword();
		LoginModel lm = new LoginModel(username, password);
		if(ValidateLogin(lm)==true) {
			return null;
		}else {
			if(Insert(d)==true)
				return GetInfo(username);
		}
		return null;
	}
	
	public ArrayList<Donor> GetAll() {
		System.out.println("Getting the donor list");
		Connection conn;
		ArrayList<Donor> res = new ArrayList<Donor>();
		try {
			conn = ConnectionUtil.GetConn();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM DONOR;");
			ResultSet resSet = stmt.executeQuery();
			while (resSet.next()) {
				int id = resSet.getInt(1);
				String name = resSet.getString(2);
				String username = resSet.getString(3);
				String location = resSet.getString(5);
				String email = resSet.getString(6);
				String phone = resSet.getString(7);
				System.out.println(id);
				res.add(new Donor(name, username, "", location, email, phone));
			}
			ConnectionUtil.CloseConn(conn);
		} catch(Exception e) {
			System.out.println("error while fetching donor");
			System.out.println(e);
		}
		return res;
	}
	
	public boolean UpdateInfo(Donor d) {
		String username = d.getUsername();
		String password = d.getPassword();
		String name = d.getName();
		String phone = d.getPhone();
		int points = d.getPoints();
		String location = d.getLocation();
		String email = d.getEmail();
		String sql = "UPDATE DONOR SET name = '"+name+"', phone = '"+phone+"', points = "+points+", location = '"+location+"', email = '"+email;
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
	
	public boolean Donate(DonationModel dm) {
		Connection conn;
		int donorId = dm.getDonorId();
		boolean isAnon = false;
		if(dm.getIsAnon()==1)
			isAnon = true;
		ArrayList<Donation> donations = dm.getDonations();

		String itemName;
		int qty;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String today = (dateFormat.format(date)).toString();
		
		conn = ConnectionUtil.GetConn();
		
		int n=donations.size();
		for(int i=0; i<n; i++) {
			itemName = (donations.get(i)).GetItemName();
			qty = (donations.get(i)).GetQty();
			int itemId = -1;
			try {
				String sql_select = "SELECT i_id FROM ITEM WHERE i_name="+itemName+";";
				PreparedStatement stmt = conn.prepareStatement(sql_select);
				ResultSet resSet = stmt.executeQuery();
				while(resSet.next()) {
					itemId = resSet.getInt(1);
					break;
				}
				if(itemId!=-1) {
					String sql_insert = "INSERT INTO DONATIONMADE (d_id, i_id, qty, isAnon, donatedOn)";
					sql_insert += "VALUES ("+Integer.toString(donorId)+", "+Integer.toString(itemId)+", "+Integer.toString(qty)+", "+String.valueOf(isAnon)+", "+today+";";
					System.out.println(sql_insert);
					PreparedStatement stmt_insert = conn.prepareStatement(sql_insert);
					stmt_insert.executeUpdate();
				}
			} catch (Exception e) {
				System.out.println(e);
				return false;
			}
		}
		return true;
	}
}
