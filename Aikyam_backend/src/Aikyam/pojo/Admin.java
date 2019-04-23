package Aikyam.pojo;

public class Admin {

	public Admin(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}
	
	public Admin() {
		
	}
	
	private String username;
	private String password;
	private String aim;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAim() {
		return aim;
	}

	public void setAim(String aim) {
		this.aim = aim;
	}

}
