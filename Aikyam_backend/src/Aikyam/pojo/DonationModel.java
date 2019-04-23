package Aikyam.pojo;

import java.util.ArrayList;

public class DonationModel {

	public DonationModel(ArrayList<Donation> donations, int isAnon, int donorId) {
		this.setDonations(donations);
		this.setIsAnon(isAnon);
		this.setDonorId(donorId);
	}
	
	public DonationModel() {
		// TODO Auto-generated constructor stub
	}
	
	private ArrayList<Donation> donations = new ArrayList<Donation>();
	private int isAnon;
	private int donorId;
	
	public ArrayList<Donation> getDonations() {
		return donations;
	}
	public void setDonations(ArrayList<Donation> donations) {
		this.donations = donations;
	}

	public int getIsAnon() {
		return isAnon;
	}

	public void setIsAnon(int isAnon) {
		this.isAnon = isAnon;
	}

	public int getDonorId() {
		return donorId;
	}

	public void setDonorId(int donorId) {
		this.donorId = donorId;
	}
}
