package Aikyam.services;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Aikyam.dao.ItemDao;
import Aikyam.pojo.Item;

@Path("/item")
public class ItemServices {
	
	@POST
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public Response GetAll(){
		System.out.println("getting item list");
		ItemDao dao = new ItemDao();
		ArrayList<Item> itemList = dao.GetItemList();
		if(itemList==null) 
			return Response.status(Response.Status.BAD_REQUEST).build();
		return Response.ok(itemList).build();
	}
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response AddItem(Item i) {
		System.out.println("adding item");
		ItemDao dao = new ItemDao();
		if(dao.AddItem(i))
			return Response.ok().build();
		return Response.status(Response.Status.BAD_REQUEST).build();
	}
	
	@POST
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response DelItem(Item i) {
		System.out.println("deleting item");
		ItemDao dao = new ItemDao();
		if(dao.DeleteItem(i))
			return Response.ok().build();
		return Response.status(Response.Status.BAD_REQUEST).build();
	}
	
	@POST
	@Path("/modify")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response ModItem(Item i) {
		System.out.println("deleting item");
		ItemDao dao = new ItemDao();
		if(dao.ModifyItem(i))
			return Response.ok().build();
		return Response.status(Response.Status.BAD_REQUEST).build();
	}
}
