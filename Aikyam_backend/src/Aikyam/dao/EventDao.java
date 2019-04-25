package Aikyam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Aikyam.conn.ConnectionUtil;
import Aikyam.pojo.Event;
import Aikyam.pojo.EventModel;
import Aikyam.pojo.UpdateEventItem;

public class EventDao {

	public EventDao() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean AddEvent(EventModel em) {
		String date = em.getDate();
		String descr = em.getDescr();
		ArrayList<String> c_name = em.getC_name();
		int e_id = Event.getId();
		
		Connection conn = null;
		int n = c_name.size();

		conn = ConnectionUtil.GetConn();
		
		try {
			for(int i=0; i<n; i++) {
				String sql = "INSERT INTO EVENT (e_id, e_date, descr, c_name) VALUES (?, ?, ?, ?)";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, e_id);
				stmt.setString(2, date);
				stmt.setString(3, descr);
				stmt.setString(4, c_name.get(i));
				stmt.executeUpdate();
			}
			
			Event.setId(e_id+1);
			return true;
		}catch(Exception e) {
			System.out.println(e);
			ConnectionUtil.CloseConn(conn);
			return false;
		}
	}
	
	public ArrayList<Event> GetEvents(int x) {
		ArrayList<Event> evList = new ArrayList<Event>();
		Connection conn = null;
		try {
			conn = ConnectionUtil.GetConn();
			String sql = "SELECT * FROM EVENT";
			if(x>1) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date date = new Date();
				String today = (dateFormat.format(date)).toString();
				System.out.println(today);
				if(x==2) {
					sql += " WHERE e_date > '"+today+"';";
				}
				else if(x==3) {
					sql += " WHERE e_date < '"+today+"';";
				}
			}
			System.out.println(sql);
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			ResultSet resSet = stmt.executeQuery();
			while (resSet.next()) {
				System.out.println(resSet.getString(3));
				Event e = new Event(resSet.getInt(2), resSet.getString(3), resSet.getString(4), resSet.getString(5));
				e.setImg(resSet.getString(6));
				evList.add(e);
			}
			return evList;
		}catch(Exception e) {
			System.out.println(e);
			ConnectionUtil.CloseConn(conn);
			return null;
		}
	}
	
	public EventModel GetInfo(int id) {
		Connection conn = null;
		
		ArrayList<String> cName = new ArrayList<String>();
		String date = "";
		String descr = "";
		
		try {
			conn = ConnectionUtil.GetConn();
			String sql = "SELECT * FROM EVENT WHERE e_id="+Integer.toString(id)+";";
			PreparedStatement stmt = conn.prepareStatement(sql);
			//stmt.setInt(1, id); //this didnt work here, maybe coz its int;
			System.out.println(stmt);
			
			ResultSet resSet = stmt.executeQuery(sql);
			
			System.out.println(resSet.toString());
			
			while(resSet.next()) {
				date = resSet.getString(3);
				descr = resSet.getString(4);
				cName.add(resSet.getString(5));
			}
			
			if(date=="")
				return null;
			EventModel em = new EventModel(date, descr, cName);
			em.setE_id(id);
			
			System.out.println(em.toString());
			return em;
		} catch (Exception e) {
			System.out.println("Something went wrong: ");
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean UpdateInfo(Event e) {
		int e_id = e.getE_id();
		String e_date = e.getE_date();
		String descr = e.getDescr();
		String c_name = e.getC_name();
		String img = e.getImg();
		
		String sql = "UPDATE EVENT SET e_date='"+e_date+"', descr='"+descr+"', img='"+img+"'";
		sql += " WHERE e_id="+e_id+" AND c_name='"+c_name+"';";
		System.out.println(sql);
		
		Connection conn = null;
		try {
			conn = ConnectionUtil.GetConn();
			PreparedStatement stmt =  conn.prepareStatement(sql);
			int x = stmt.executeUpdate();
			if(x>0) {
				ConnectionUtil.CloseConn(conn);
				return true;
			}
			ConnectionUtil.CloseConn(conn);
			return false;
		}catch(Exception ex) {
			System.out.println(ex);
			ConnectionUtil.CloseConn(conn);
			return false;
		}
	}
	
	public boolean UpdateItemQty(UpdateEventItem uei) {
		int e_id = uei.getE_id();
		String c_name = uei.getC_name();
		ArrayList<String> iNameList = uei.getI_name();
		ArrayList<Integer> qtyList = uei.getQty();
		
		Connection conn = null;
		String sql_select = "";
		String sql_insert = "";
		int i_id = -1;
		
		int n = iNameList.size();
		System.out.println(n);
		for(int i=0; i<n; i++) {
			String iName = iNameList.get(i);
			int qty = qtyList.get(i);
			
			try {
				conn = ConnectionUtil.GetConn();
				sql_select = "SELECT i_id FROM ITEM WHERE i_name = '"+iName+"';";
				System.out.println(sql_select);
				PreparedStatement stmt = conn.prepareStatement(sql_select);
				ResultSet resSet = stmt.executeQuery();
				while(resSet.next()) {
					i_id = resSet.getInt(1);
					break;
				}
					
				sql_insert = "INSERT INTO CHARITYRECEIVED (e_id, c_name, i_id, qty)";
				sql_insert += " VALUES("+Integer.toString(e_id)+", '"+c_name+"', "+Integer.toString(i_id)+", "+qty+");";
				System.out.println(sql_insert);
				stmt = conn.prepareStatement(sql_insert);
				stmt.executeUpdate();
				}catch(Exception e) {
					System.out.println(e);
					return false;
				}
			}
			return true;
		}
	}
