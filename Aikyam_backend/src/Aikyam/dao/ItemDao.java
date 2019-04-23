package Aikyam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Aikyam.conn.ConnectionUtil;
import Aikyam.pojo.Item;

public class ItemDao {

	public ItemDao() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<Item> GetItemList(){
		ArrayList<Item> itemList = new ArrayList<Item>();
		String sql = "SELECT * FROM ITEM;";
		Connection conn = null;
		try{
			conn = ConnectionUtil.GetConn();
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet resSet = stmt.executeQuery();
			while(resSet.next()) {
				Item temp = new Item(resSet.getString(2), resSet.getInt(3));
				itemList.add(temp);
			}
			ConnectionUtil.CloseConn(conn);
		}catch(Exception e) {
			System.out.println(e);
			return null;
		}
		return itemList;
	}
	
	public boolean AddItem(Item i) {
		String i_name = i.getI_name();
		int qty = i.getQty();
		String sql = "INSERT INTO ITEM(i_name, qty) VALUES('"+i_name+"', "+qty+");";
		Connection conn = ConnectionUtil.GetConn();
		try {
			PreparedStatement stmt_insert = conn.prepareStatement(sql);
			stmt_insert.executeUpdate();
			ConnectionUtil.CloseConn(conn);
		}catch(Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}
	
	public boolean DeleteItem(Item i) {
		String i_name = i.getI_name();
		String sql = "DELETE FROM ITEM WHERE i_name='"+i_name+"';";
		Connection conn = ConnectionUtil.GetConn();
		try {
			PreparedStatement stmt_insert = conn.prepareStatement(sql);
			stmt_insert.executeUpdate();
			ConnectionUtil.CloseConn(conn);
		}catch(Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}
	
	public boolean ModifyItem(Item i) {
		String i_name = i.getI_name();
		int qty = i.getQty();
		String sql = "UPDATE ITEM SET qty="+qty+" WHERE i_name='"+i_name+"';";
		Connection conn = ConnectionUtil.GetConn();
		try {
			PreparedStatement stmt_insert = conn.prepareStatement(sql);
			stmt_insert.executeUpdate();
			ConnectionUtil.CloseConn(conn);
		}catch(Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}
}
