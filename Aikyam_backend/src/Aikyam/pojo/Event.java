package Aikyam.pojo;

public class Event {
	
	private static int id = 0;
	
	public Event(int e_id, String e_date, String descr, String c_name) {
		this.setE_id(e_id);
		this.setE_date(e_date);
		this.descr = descr;
		this.setC_name(c_name);
		// TODO Auto-generated constructor stub
	}
	
	public Event() {
		
	}
	
	private int e_id;
	private String e_date;
	private String descr;
	private String c_name;
	private String img;

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public int getE_id() {
		return e_id;
	}

	public void setE_id(int e_id) {
		this.e_id = e_id;
	}

	public String getE_date() {
		return e_date;
	}

	public void setE_date(String e_date) {
		this.e_date = e_date;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name2) {
		this.c_name = c_name2;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public static int getId() {
		return id;
	}

	public static void setId(int id) {
		Event.id = id;
	}

}
