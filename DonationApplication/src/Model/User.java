package Model;

import java.sql.Connection;

import Helper.DataBase;

public class User{
	protected static Integer id;
	protected String name;
	protected String surname;
	protected String username;
	protected String email;
	protected Long password;
	protected String address;
	protected Integer point;
	static DataBase con = new DataBase();
	
	
	

	public User(Integer id, String name, String surname, String username, String email, Long password, String address, Integer point) {
		
		super();
		User.id = id;
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.email = email;
		this.password = password;
		this.address = address;
		this.point = point;
	}




	public User() {
		// TODO Auto-generated constructor stub
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


	public Long getPassword() {
		return password;
	}
	
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPassword(Long password) {
		this.password = password;
	}
	
	public Integer getId() {
		return User.id;
	}
	
	public Integer getPoint() {
		return this.point;
	}


	
	

}
