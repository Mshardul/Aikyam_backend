package Aikyam.pojo;

public class User {
	
	public User(String name, String username, String password, String location, String email, String phone) {
		setName(name);
		setUsername(username);
		setEmail(email);
		setPassword(password);
		setPhone(phone);
		setLocation(location);
	}
	
	public User() {
		
	}
	
	private String name;
	private String username;
	private String password;
	private String location;
	private String email;
	private String phone;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
}
