package pojo;

public class CharityReceived {

	public CharityReceived() {
		// TODO Auto-generated constructor stub
	}
	
	private int eId;
	private int id;
	private String descr;
	private String donated;
	
	public int geteId() {
		return eId;
	}
	public void seteId(int eId) {
		this.eId = eId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getDonated() {
		return donated;
	}

	public void setDonated(String donated) {
		this.donated = donated;
	}
	
}
