package Model;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class Admin extends User{
	
	

	public Admin(int id, String name, String surname, String username, String email, String password) {
		super(id, name, surname, username, email, password);
	}
	public Admin() {
		
	}
	public static long getDonationNumber() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		LocalDateTime time = LocalDateTime.now();
		
		long donationNumber = Long.parseLong(dtf.format(time));
		
		return donationNumber;
	}
	
	
	
	
	public static void addUser(String name, String surname, String username, String email, String password) throws SQLException {
		Connection c = con.connect();
		Statement st = c.createStatement();
		
		String query = "INSERT INTO users(name, surname, username, email, password, type, status, registrationDate, lastLogin) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = c.prepareStatement(query);
		
		ps.setString(1, name);
		ps.setString(2, surname);
		ps.setString(3, username);
		ps.setString(4, email);
		ps.setString(5, password);
		ps.setInt(6, 2);
		ps.setInt(7, 1);
		
		LocalDateTime time = LocalDateTime.now();
		ps.setTimestamp(8, Timestamp.valueOf(time));
		ps.setTimestamp(9, null);
		
		
		ps.executeUpdate();
		ps.close();
	    c.close();
		
		
	}
	public static void addDonation(int category, String subcategory, String param1, String param2, String condition, int quantity, Integer donor, Integer recipient ) throws SQLException {
		Connection c = con.connect();
		Statement st = c.createStatement();
		
		String query = "INSERT INTO donations(number, category, subcategory, param1, param2, `condition`, quantity, status, donationDate, donor, recipient) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = c.prepareStatement(query);
		
		
		ps.setLong(1, getDonationNumber());
		ps.setInt(2, category);
		ps.setString(3, subcategory);
		ps.setString(4, param1);
		ps.setString(5, param2);
		ps.setString(6, condition);
		ps.setInt(7, quantity);
		ps.setInt(8, 1);
		
		LocalDateTime time = LocalDateTime.now();
		ps.setTimestamp(9, Timestamp.valueOf(time));
		ps.setInt(10, donor);
		ps.setObject(11, recipient, java.sql.Types.INTEGER);
		
		
		ps.executeUpdate();
		ps.close();
		c.close();
		
	}
	public static void requestDonation(int category, String subcategory, String param1, String param2, String condition, int quantity, Integer donor, Integer recipient ) throws SQLException {
		Connection c = con.connect();
		Statement st = c.createStatement();
		
		String query = "INSERT INTO donations(number, category, subcategory, param1, param2, `condition`, quantity, status, donationDate, donor, recipient) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = c.prepareStatement(query);
		
		ps.setLong(1, getDonationNumber());
		ps.setInt(2, category);
		ps.setString(3, subcategory);
		ps.setString(4, param1);
		ps.setString(5, param2);
		ps.setString(6, condition);
		ps.setInt(7, quantity);
		ps.setInt(8, 1);
		
		LocalDateTime time = LocalDateTime.now();
		ps.setTimestamp(9, Timestamp.valueOf(time));
		ps.setObject(10, donor, java.sql.Types.INTEGER);
		ps.setInt(11, recipient);
		
		
		ps.executeUpdate();
		ps.close();
		c.close();
		
	}
	
	public static ArrayList<Object[]> getDonations() throws SQLException {
		ArrayList<Object[]> list = new ArrayList<Object[]>();
				
		Connection c = con.connect();
		Statement st = c.createStatement();
		String query = "SELECT donations.*, " +
				       "donation_status.statusname, " +
				       "donation_categories.categoryname, " + 
				       "donor_user.username AS donorname, " + 
				       "recipient_user.username AS recipientname " +
				       "FROM donations " +
				       "INNER JOIN donation_status ON donations.status = donation_status.id " +
				       "INNER JOIN donation_categories ON donations.category = donation_categories.id " +
				       "LEFT JOIN users AS donor_user ON donations.donor = donor_user.id " +
				       "LEFT JOIN users AS recipient_user ON donations.recipient = recipient_user.id " +
				       "WHERE donations.status = 1 OR donations.status = 2";
			
		
		ResultSet rs = st.executeQuery(query);

		while(rs.next()) {
			Object[] items = new Object[11];
			items[0] = rs.getLong("number");
			items[1] = rs.getString("categoryname");
			items[2] = rs.getString("subcategory");
			items[3] = rs.getString("param1");
			items[4] = rs.getString("param2");
			items[5] = rs.getString("condition");
			items[6] = rs.getInt("quantity");
			items[7] = rs.getDate("donationDate");
			items[8] = rs.getString("statusname");
			items[9] = rs.getString("donorname");
			items[10] = rs.getString("recipientname");
			
			
			list.add(items);

		}
		
		rs.close();
	    st.close();
	    c.close();

		return list;
	}
	
	public static ArrayList<Object[]> getDonationsbyDonor(User user) throws SQLException{
		ArrayList<Object[]> list = new ArrayList<Object[]>();

		
		
		Connection c = con.connect();
		Statement st = c.createStatement();
		String query = "SELECT donations.*, donation_status.statusname, donation_categories.categoryname, " +
					   "users.username FROM donations " + 
					   "INNER JOIN donation_status ON donations.status = donation_status.id " +
				       "INNER JOIN donation_categories ON donations.category = donation_categories.id " +
					   "INNER JOIN users ON donations.donor = users.id " +
				       "WHERE users.id = ?" ;
		PreparedStatement ps = c.prepareStatement(query);
		ps.setInt(1, user.getId());
		
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Object[] items = new Object[10];
			items[0] = rs.getLong("number");
			items[1] = rs.getString("categoryname");
			items[2] = rs.getString("subcategory");
			items[3] = rs.getString("param1");
			items[4] = rs.getString("param2");
			items[5] = rs.getString("condition");
			items[6] = rs.getInt("quantity");
			items[7] = rs.getDate("donationDate");
			items[8] = rs.getString("statusname");
			items[9] = rs.getString("username");
			
			list.add(items);
		}		
		return list;
	}
	
	
	
	public static ArrayList<Object[]> getRequestsbyDonor(User user) throws SQLException{
		ArrayList<Object[]> list = new ArrayList<Object[]>();

		
		
		Connection c = con.connect();
		Statement st = c.createStatement();
		String query = "SELECT donations.*, donation_status.statusname, donation_categories.categoryname, " +
					   "users.username FROM donations " + 
					   "INNER JOIN donation_status ON donations.status = donation_status.id " +
				       "INNER JOIN donation_categories ON donations.category = donation_categories.id " +
					   "INNER JOIN users ON donations.recipient = users.id " +
				       "WHERE users.id = ?" ;
		PreparedStatement ps = c.prepareStatement(query);
		ps.setInt(1, user.getId());
		
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Object[] items = new Object[10];
			items[0] = rs.getLong("number");
			items[1] = rs.getString("categoryname");
			items[2] = rs.getString("subcategory");
			items[3] = rs.getString("param1");
			items[4] = rs.getString("param2");
			items[5] = rs.getString("condition");
			items[6] = rs.getInt("quantity");
			items[7] = rs.getDate("donationDate");
			items[8] = rs.getString("statusname");
			items[9] = rs.getString("username");
			
			list.add(items);
		}		
		return list;
	}
	
	public static void completeItem(Long number) throws SQLException {
		Connection c = con.connect();
		Statement st = c.createStatement();
		
		String query = "UPDATE donations SET donations.status = ? WHERE donations.number = ?";
		PreparedStatement ps = c.prepareStatement(query);
		ps.setInt(1, 3);
		ps.setLong(2, number);
		ps.executeUpdate();
		
	}
	public static void cancelItem(Long number) throws SQLException {
		Connection c = con.connect();
		Statement st = c.createStatement();
		
		String query = "UPDATE donations SET donations.status = ? WHERE donations.number = ?";
		PreparedStatement ps = c.prepareStatement(query);
		ps.setInt(1, 4);
		ps.setLong(2, number);
		ps.executeUpdate();
		
	}
	
	
}
