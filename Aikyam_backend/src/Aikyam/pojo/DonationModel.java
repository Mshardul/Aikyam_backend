package Aikyam.pojo;

import java.util.ArrayList;

public class DonationModel {

	public DonationModel(ArrayList<String> iName, ArrayList<Integer> qty, int isAnon, int donorId) {
		this.setI_name(iName);
		this.setQty(qty);
		this.setIsAnon(isAnon);
		this.setDonorId(donorId);
	}
	
	public DonationModel() {
		// TODO Auto-generated constructor stub
		iName = new ArrayList<String>();
		qty = new ArrayList<Integer>();
	}
	
	private ArrayList<String> iName;
	private ArrayList<Integer> qty;
	private int isAnon;
	private int donorId;
	
	public ArrayList<Integer> getQty() {
		return this.qty;
	}
	public void setQty(ArrayList<Integer> qty) {
		this.qty = qty;
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
	
	public String toString() {
		String ret = "";
		int n = iName.size();
		if(iName.isEmpty())
			ret+=" no item ";
		System.out.println(n);
		for(int i=0; i<n; i++)
			ret+=iName.get(i)+" ";
		int m = qty.size();
		for(int i=0; i<m; i++)
			ret+=qty.get(i)+" ";
		ret += Integer.toString(isAnon)+" ";
		ret += Integer.toString(donorId);
		return ret;
	}

	public ArrayList<String> getI_name() {
		return iName;
	}

	public void setI_name(ArrayList<String> iName) {
		this.iName= iName;
	}
}
