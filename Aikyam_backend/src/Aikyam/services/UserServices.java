package Aikyam.services;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Aikyam.dao.AdminDao;
import Aikyam.dao.CharityDao;
import Aikyam.dao.DonorDao;
import Aikyam.pojo.Admin;
import Aikyam.pojo.Charity;
import Aikyam.pojo.DonationModel;
import Aikyam.pojo.Donor;
import Aikyam.pojo.LoginModel;

@Path("/user")
public class UserServices {
	
	@POST
	@Path("/loginD")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response LoginDonor(LoginModel lm) {
		System.out.println("logging in donor");
		DonorDao dao = new DonorDao();
		Donor ret = dao.Validate(lm);
		if(ret==null) {
			System.out.println("not found");
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		System.out.println("sending ok");
		return Response.ok().build(); //ok(ret) will send ret, as well as status 200
	}
	
	@POST
	@Path("/loginC")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response LoginCharity(LoginModel lm) {
		System.out.println("logging in charity");
		CharityDao dao = new CharityDao();
		Charity ret = dao.Validate(lm);
		if(ret==null) {
			System.out.println("not found");
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		System.out.println("sending ok");
		return Response.ok().build();
	}
	
	@POST
	@Path("/loginA")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response LoginAdmin(LoginModel lm) {
		System.out.println("logging in admin");
		AdminDao dao = new AdminDao();
		if(dao.Validate(lm))
			return Response.ok().build();
		return Response.status(Response.Status.BAD_REQUEST).build();
	}
	
	@POST
	@Path("/regD")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response RegDonor(Donor d) {
		System.out.println("registering donor");
		DonorDao dao = new DonorDao();
		Donor ret = dao.Register(d);
		if(ret==null) {
			System.out.println("not found");
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok().build();
	}
	
	@POST
	@Path("/regC")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response RegCharity(Charity c) {
		System.out.println("registering charity");
		CharityDao dao = new CharityDao();
		Charity ret = dao.Register(c);
		if(ret==null) {
			System.out.println("not found");
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok().build();
	}
	
	@POST
	@Path("/getListD")
	@Produces(MediaType.APPLICATION_JSON)
	public Response GetAllDonor(){
		System.out.println("getting donor list");
		DonorDao dao = new DonorDao();
		ArrayList<Donor> donorList = dao.GetAll();
		return Response.ok(donorList).build();
	}
	
	@POST
	@Path("/getListC")
	@Produces(MediaType.APPLICATION_JSON)
	public Response GetAllCharity(){
		System.out.println("getting charity list");
		CharityDao dao = new CharityDao();
		ArrayList<Charity> charityList = dao.GetAll();
		return Response.ok(charityList).build();
	}
	
	@POST
	@Path("/getInfoD/{username}")
	public Response GetDonorInfo(@PathParam("username") String username) {
		System.out.println("Searching info for " + username);
		DonorDao dao = new DonorDao();
		Donor ret = dao.GetInfo(username);
		if(ret==null)
			return Response.status(Response.Status.BAD_REQUEST).build();
		System.out.println(ret.toString());
		return Response.ok(ret).build();
	}
	
	@POST
	@Path("/getInfoC/{username}")
	public Response GetCharityInfo(@PathParam("username") String username) {
		System.out.println("Searching info for " + username);
		CharityDao dao = new CharityDao();
		Charity ret = dao.GetInfo(username);
		if(ret==null)
			return Response.status(Response.Status.BAD_REQUEST).build();
		System.out.println(ret.toString());
		return Response.ok(ret).build();
	}
	
	@POST
	@Path("/getInfoA")
	public Response GetAdminInfo() {
		System.out.println("getting admin info");
		AdminDao dao = new AdminDao();
		Admin ret = dao.GetInfo();
		if(ret==null)
			return Response.status(Response.Status.BAD_REQUEST).build();
		return Response.ok(ret).build();
	}
	
	@POST
	@Path("/updateInfoD")
	public Response UpdateDonorInfo(Donor d) { //not working as of now
		System.out.println(d.toString());
		DonorDao doa = new DonorDao();
		Boolean x = doa.UpdateInfo(d);
		if(x)
			return Response.ok().build();
		return Response.status(Response.Status.BAD_REQUEST).build();
	}
	
	@POST
	@Path("/updateInfoC")
	public Response UpdateCharityInfo(Charity c) {
		System.out.println(c.toString());
		CharityDao doa = new CharityDao();
		Boolean x = doa.UpdateInfo(c);
		if(x)
			return Response.ok().build();
		return Response.status(Response.Status.BAD_REQUEST).build();
	}
	
	@POST
	@Path("/updateInfoA")
	public Response UpdateAdminInfo(Admin a) {
		System.out.println(a.toString());
		AdminDao dao = new AdminDao();
		Boolean x = dao.UpdateInfo(a);
		if(x)
			return Response.ok().build();
		return Response.status(Response.Status.BAD_REQUEST).build();
	}
	@POST
	@Path("/donate")
	public Response Donate(DonationModel dm) {
		System.out.println(dm.toString());
		DonorDao dao = new DonorDao();
		if(dao.Donate(dm))
			return Response.ok().build();
		return Response.status(Response.Status.BAD_REQUEST).build();
	}
}
