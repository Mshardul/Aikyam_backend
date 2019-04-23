package Aikyam.pojo;

import java.util.ArrayList;

public class EventModel {
	
	public EventModel(String date, String descr, ArrayList<String> c_name) {
		this.setDate(date);
		this.setDescr(descr);
		this.setC_name(c_name);
	}
	
	public EventModel() {
		
	}
	
	private int e_id;
	private String date;
	private String descr;
	private ArrayList<String> c_name;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public ArrayList<String> getC_name() {
		return c_name;
	}

	public void setC_name(ArrayList<String> c_name) {
		this.c_name = c_name;
	}
	public int getE_id() {
		return e_id;
	}
	public void setE_id(int e_id) {
		this.e_id = e_id;
	}
	
	public String toString() {
		String ret = this.getE_id() + this.getDate() + this.getDescr();
		
		int n = this.c_name.size();
		for(int i=0; i<n; i++) {
			ret += c_name.get(i);
		}
		
		return ret;
	}
	
}
