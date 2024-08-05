package Model;

import Helper.DataBase;

public class User {
	String name;
	String surname;
	String gender;
	String username;
	String email;
	String password;
	String type;
	static DataBase con = new DataBase();
	
	public User(String name, String surname, String gender, String username, String email, String password, String type) {
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.username = username;
		this.email = email;
		this.password = password;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	

}
