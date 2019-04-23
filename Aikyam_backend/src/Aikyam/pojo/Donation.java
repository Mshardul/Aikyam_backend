package Aikyam.pojo;

public class Donation {

	public Donation() {
		// TODO Auto-generated constructor stub
	}

	public Donation(String itemName, int qty) {
		this.itemName = itemName;
		this.qty = qty;
	}
	
	private String itemName;
	private int qty;
	
	public String GetItemName() {
		return this.itemName;
	}
	
	public void SetItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public int GetQty(){
		return this.qty;
	}
	
	public void SetQty(int qty) {
		this.qty = qty;
	}
	
}
