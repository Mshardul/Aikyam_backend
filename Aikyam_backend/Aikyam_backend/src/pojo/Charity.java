package pojo;

public class Charity extends User {

	public Charity(String name, String username, String email, String password, String phone, String location) {
		super(name, username, email, password, phone, location);
		this.IDProof = "";
		this.Received = "";
	}
	
	private String IDProof;
	private String Received;
	
	public String getIDProof() {
		return IDProof;
	}
	public void setIDProof(String iDProof) {
		IDProof = iDProof;
	}

	public String getReceived() {
		return Received;
	}

	public void setReceived(String received) {
		Received = received;
	}
}
