package Aikyam.pojo;

public class Item {

	public Item() {
		
	}
	public Item(String i_name) {
		this.i_name = i_name;
		this.qty = 0;
	}
	
	public Item(String i_name, int qty) {
		this.i_name = i_name;
		this.qty = qty;
	}
	
	private String i_name;
	private int qty;
	
	public String getI_name() {
		return i_name;
	}
	public void setI_name(String i_name) {
		this.i_name = i_name;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}


}
