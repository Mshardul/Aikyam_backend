package Aikyam.services;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Aikyam.dao.EventDao;
import Aikyam.pojo.Event;
import Aikyam.pojo.EventModel;

@Path("/event")
public class EventServices {

	public EventServices() {
		// TODO Auto-generated constructor stub
	}
	
	@Path("/create")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response CreateEvent(EventModel e) {
		System.out.println("Creating event");
		EventDao dao = new EventDao();
		if(dao.AddEvent(e)==true)
			return Response.ok().build();
		return Response.status(Response.Status.BAD_REQUEST).build();
	}
	
	@POST
	@Path("/getAll")
	public Response GetAllEvents() {
		System.out.println("getting all events");
		EventDao dao = new EventDao();
		ArrayList<Event> e = dao.GetEvents(1);
		if(e!=null)
			return Response.ok(e).build();
		return Response.status(Response.Status.BAD_REQUEST).build();
	}
	
	@POST
	@Path("/getUpcoming")
	public Response GetUpcomingEvents() {
		System.out.println("getting upcoming events");
		EventDao dao = new EventDao();
		ArrayList<Event> e = dao.GetEvents(2);
		if(e!=null)
			return Response.ok(e).build();
		return Response.status(Response.Status.BAD_REQUEST).build();
	}
	
	@POST
	@Path("/getPast")
	public Response GetPastEvents() {
		System.out.println("getting past events");
		EventDao dao = new EventDao();
		ArrayList<Event> e = dao.GetEvents(3);
		if(e!=null)
			return Response.ok(e).build();
		return Response.status(Response.Status.BAD_REQUEST).build();
	}
	
	@POST
	@Path("/getInfo/{id}")
	public Response GetInfo(@PathParam("id") int id) {
		System.out.println("getting event info");
		EventDao dao = new EventDao();
		EventModel em = dao.GetInfo(id);
		if(em!=null)
			return Response.ok(em).build();
		return Response.status(Response.Status.BAD_REQUEST).build();
	}
	
	@POST
	@Path("/updateInfo")
	public Response UpdateEventInfo(Event e) {
		System.out.println(e.toString());
		EventDao doa = new EventDao();
		Boolean x = doa.UpdateInfo(e);
		if(x)
			return Response.ok().build();
		return Response.status(Response.Status.BAD_REQUEST).build();
	}

}
