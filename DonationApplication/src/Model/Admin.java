package Model;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Admin extends User{

	

	public Admin(String name, String surname, String username, String email, String password,
			String type) {
		super(name, surname, username, email, password, type);
	}
	public Admin() {
		
	}
	
	
	public static void addUser(String name, String surname, String username, String email, String password, String type) throws SQLException {
		Connection c = con.connect();
		User user = new User(name, surname, username, email, password, type);
		Statement st = c.createStatement();
		
		String query = "INSERT INTO users(name, surname, username, email, password, type) VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = c.prepareStatement(query);
		ps.setString(1, user.getName());
		ps.setString(2, user.getSurname());
		ps.setString(3, user.getUsername());
		ps.setString(4, user.getEmail());
		ps.setString(5, user.getPassword());
		ps.setString(6, user.getType());
		
		ps.executeUpdate();
		ps.close();
		c.close();
		
		
	}
	
	public static void addDonation(String category, String subCategory, String param1, String param2, String condition, String donor, String recipient) throws SQLException {
		Connection c = con.connect();
		Statement st = c.createStatement();
		
		String query = "INSERT INTO donations(category, subcategory, param1, param2, `condition`, donor, recipient) VALUES(?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = c.prepareStatement(query);
		ps.setString(1, category);
		ps.setString(2, subCategory);
		ps.setString(3, param1);
		ps.setString(4, param2);
		ps.setString(5, condition);
		ps.setString(6, donor);
		ps.setString(7, recipient);
		
		ps.executeUpdate();
		ps.close();
		c.close();
		
	}
	
	public static ArrayList<Object[]> getDonations() throws SQLException {
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		
		
		Connection c = con.connect();
		Statement st = c.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM donations");
		
		while(rs.next()) {
			Object[] items = new Object[8];
			items[0] = rs.getInt("id");
			items[1] = rs.getString("category");
			items[2] = rs.getString("subcategory");
			items[3] = rs.getString("param1");
			items[4] = rs.getString("param2");
			items[5] = rs.getString("condition");
			items[6] = rs.getString("donor");
			items[7] = rs.getString("recipient");
			
			list.add(items);
		}

		
		return list;
	}
	
	public static ArrayList<Object[]> getDonationsbyDonor(Donor donor) throws SQLException{
		ArrayList<Object[]> list = new ArrayList<Object[]>();

		
		
		Connection c = con.connect();
		Statement st = c.createStatement();
		String query = "SELECT * FROM donations WHERE donor = ?";
		PreparedStatement ps = c.prepareStatement(query);
		ps.setString(1, donor.getUsername());
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			Object[] items = new Object[7];
			items[0] = rs.getInt("id");
			items[1] = rs.getString("category");
			items[2] = rs.getString("subcategory");
			items[3] = rs.getString("param1");
			items[4] = rs.getString("param2");
			items[5] = rs.getString("condition");
			items[6] = rs.getString("donor");
			
			list.add(items);
		}		
		return list;
	}
	
	
}
