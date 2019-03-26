package pojo;

public class DonationMade {

	public DonationMade() {
		// TODO Auto-generated constructor stub
	}
	
	private int id;
	private int d_id;
	private String donated;
	private boolean isAnon;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getD_id() {
		return d_id;
	}

	public void setD_id(int d_id) {
		this.d_id = d_id;
	}

	public String getDonated() {
		return donated;
	}

	public void setDonated(String donated) {
		this.donated = donated;
	}

	public boolean isAnon() {
		return isAnon;
	}

	public void setAnon(boolean isAnon) {
		this.isAnon = isAnon;
	}
	
}
