package services;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.json.JSONObject;

import pojo.Event;

import dao.EventDao;

@Path("/event")
public class EventServices {

	public EventServices() {
		// TODO Auto-generated constructor stub
	}
	
	@POST
	@Path("/create")
	@Consumes("application/json")
	@Produces("Integer")
	public int CreateEvent(JSONObject obj) {
		String date = (String) obj.get("date");
		String descr = (String) obj.get("descr");
		ArrayList<ArrayList> charityList = (ArrayList<ArrayList>) obj.get("charityList");
		
		Event ev = new Event(date, descr);
		EventDao ed = new EventDao();
		int ans = ed.AddEvent(ev, charityList);
		return ans;
	}

	
}
