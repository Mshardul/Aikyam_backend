package pojo;

public class Donor extends User {
	
	public Donor(String name, String username, String email, String password, String phone, String location) {
		super(name, username, email, password, phone, location);
		this.rating = 0;
		this.donated = "";
	}
	
	private float rating;
	private String donated;
	
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}

	public String getDonated() {
		return donated;
	}

	public void setDonated(String donated) {
		this.donated = donated;
	}
	
}
