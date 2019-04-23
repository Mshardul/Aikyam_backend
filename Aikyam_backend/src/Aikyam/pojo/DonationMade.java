package Aikyam.pojo;

public class DonationMade {

	public DonationMade() {
		// TODO Auto-generated constructor stub
	}
	
	private int d_id;
	private int i_id;
	private int qty;
	private boolean isAnon;

	public int getD_id() {
		return d_id;
	}

	public void setD_id(int d_id) {
		this.d_id = d_id;
	}

	public boolean getIsAnon() {
		return isAnon;
	}

	public void setAnon(boolean isAnon) {
		this.isAnon = isAnon;
	}

	public int getI_id() {
		return i_id;
	}

	public void setI_id(int i_id) {
		this.i_id = i_id;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}
	
}
