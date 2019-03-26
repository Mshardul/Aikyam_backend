package dao;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

import org.json.JSONObject;

import conn.ConnectionUtil;
import pojo.Event;

public class EventDao {

	public EventDao() {
		// TODO Auto-generated constructor stub
	}
	
	public int AddEvent(Event ev, ArrayList<ArrayList> charityList) {
		int ans = 1;
		Connection conn = null;
		String sql = "INSERT INTO Event (date, descr) ";
		String val = "VALUES ("+ev.getDate()+", "+ev.getDescr()+");";
		try{
			conn = ConnectionUtil.GetConn();
			Statement stmt = conn.createStatement();
			stmt.execute(sql+val);
			
			String sql2 = "INSERT INTO CharityReceived (id, descr, donated) ";
			String val2 = "";
			for(int i=0; i<charityList.size(); i++) {
				val2 = "VALUES ("+charityList.get(i).get(0)+", "+charityList.get(i).get(1)+", "+charityList.get(i).get(2)+");";
				stmt.execute(sql2+val2);
			}
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
}
