package Aikyam.pojo;

public class Donor extends User {
	
	private int points;
	
	public Donor(String name, String username, String password, String location, String email,  String phone) {
		super(name, username, password, location, email, phone);
		this.points = 0;
	}
	
	public Donor() {
		super();
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
}
