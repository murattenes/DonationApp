package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Admin extends User{

	

	public Admin(String name, String surname, String gender, String username, String email, String password,
			String type) {
		super(name, surname, gender, username, email, password, type);
	}
	
	
	public static void addUser(String name, String surname, String gender, String username, String email, String password, String type) throws SQLException {
		Connection c = con.connect();
		User user = new User(name, surname, gender, username, email, password, type);
		Statement st = c.createStatement();
		
		String query = "INSERT INTO users(name, surname, gender, username, email, password, type) VALUES (?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = c.prepareStatement(query);
		ps.setString(1, user.getName());
		ps.setString(2, user.getSurname());
		ps.setString(3, user.getGender());
		ps.setString(4, user.getUsername());
		ps.setString(5, user.getEmail());
		ps.setString(6, user.getPassword());
		ps.setString(7,user.getType());
		
		ps.executeUpdate();
		c.close();
		
		
	}
	
}
