package Aikyam.pojo;

import java.util.ArrayList;

public class UpdateEventItem {

	public UpdateEventItem() {
		// TODO Auto-generated constructor stub
		i_name = new ArrayList<String>();
		qty = new ArrayList<Integer>();
	}
	
	public UpdateEventItem(int e_id, String c_name, ArrayList<String> i_name, ArrayList<Integer> qty) {
		this.setE_id(e_id);
		this.setC_name(c_name);
		this.setI_name(i_name);
		this.setQty(qty);
	}
	
	private int e_id;
	private String c_name;
	private ArrayList<String> i_name;
	private ArrayList<Integer> qty;
	
	public int getE_id() {
		return e_id;
	}

	public void setE_id(int e_id) {
		this.e_id = e_id;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	public ArrayList<String> getI_name() {
		return i_name;
	}

	public void setI_name(ArrayList<String> iName) {
		this.i_name = iName;
	}

	public ArrayList<Integer> getQty() {
		return qty;
	}

	public void setQty(ArrayList<Integer> qty) {
		this.qty = qty;
	}
}
