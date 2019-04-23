package Aikyam.pojo;

public class Charity extends User {
	
	private String IDProof;
	
	public Charity(String name, String username,  String password, String location, String email, String phone) {
		super(name, username, password, location, email, phone);
		this.IDProof = "";
	}
	
	public Charity() {
		super();
	}
	
	public String getIDProof() {
		return IDProof;
	}
	public void setIDProof(String iDProof) {
		IDProof = iDProof;
	}
	
	public String toString() {
		String str = this.getName()+" "+this.getUsername()+" "+this.getPassword()+" "+this.getEmail()+" "+this.getPhone()+" "+this.getLocation();
		return str;
	}
}
