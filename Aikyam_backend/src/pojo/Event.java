package pojo;

public class Event {

	public Event(String date, String descr) {
		this.date = date;
		this.descr = descr;
		// TODO Auto-generated constructor stub
	}

	private String date;
	private String descr;

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

}
