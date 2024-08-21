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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class Admin extends User{
	
	

	public Admin(int id, String name, String surname, String username, String email, String password, String address) {
		super(id, name, surname, username, email, password, address);
	}
	public Admin() {
		
	}
	public static long getDonationNumber() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		LocalDateTime time = LocalDateTime.now();
		
		long donationNumber = Long.parseLong(dtf.format(time));
		
		return donationNumber;
	}
	
	
	
	
	public static void addUser(String name, String surname, String username, String email, String password, String address) throws SQLException {
		Connection c = con.connect();
		Statement st = c.createStatement();
		
		String query = "INSERT INTO users(name, surname, username, email, password, type, status, registrationDate, lastLogin, address) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
		ps.setString(10, address);
		
		
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
	public static void addToFromDonation(int category, String subcategory, String param1, String param2, String condition, int quantity, Integer donor, Integer recipient ) throws SQLException {
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
		ps.setInt(8, 3);
		
		LocalDateTime time = LocalDateTime.now();
		ps.setTimestamp(9, Timestamp.valueOf(time));
		ps.setInt(10, donor);
		ps.setInt(11, recipient);
		
		
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
	public static Donation getDonationbyNumber(Long number) throws SQLException{
		Connection c = con.connect();
		Statement st = c.createStatement();
		String query = "SELECT donations.* FROM donations WHERE donations.number = ?";
		PreparedStatement ps = c.prepareStatement(query);
		ps.setLong(1, number);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			Long nmb = rs.getLong("number");
			int category = rs.getInt("category");
			String subCategory = rs.getString("subcategory");
			String param1 = rs.getString("param1");
			String param2 = rs.getString("param2");
			String condition = rs.getString("condition");
			int quantity = rs.getInt("quantity");
			Date date = rs.getDate("donationDate");
			int status = rs.getInt("status");
			int donor = rs.getInt("donor");
			int recipient = rs.getInt("recipient");
			
			Donation d = new Donation(nmb, category, subCategory, param1, param2, condition, quantity, status, donor, recipient);
			return d;
		}

	return null;	

	}
	public static ArrayList<Object[]> getAllDonations() throws SQLException {
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
				       "LEFT JOIN users AS recipient_user ON donations.recipient = recipient_user.id ";
			
		
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
	
	public static ArrayList<Object[]> getDonations() throws SQLException {
		ArrayList<Object[]> list = new ArrayList<Object[]>();
				
		Connection c = con.connect();
		Statement st = c.createStatement();
		String query = "SELECT donations.*, " +
				       "donation_status.statusname, " +
				       "donation_categories.categoryname, " + 
				       "donor_user.username AS donorname, " + 
				       "recipient_user.username AS recipientname, " +
				       "COALESCE(donor_user.address, recipient_user.address) AS address " +
				       "FROM donations " +
				       "INNER JOIN donation_status ON donations.status = donation_status.id " +
				       "INNER JOIN donation_categories ON donations.category = donation_categories.id " +
				       "LEFT JOIN users AS donor_user ON donations.donor = donor_user.id " +
				       "LEFT JOIN users AS recipient_user ON donations.recipient = recipient_user.id " +
				       "WHERE donations.status = 1 OR donations.status = 2";
			
		
		ResultSet rs = st.executeQuery(query);

		while(rs.next()) {
			Object[] items = new Object[12];
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
			items[11] = rs.getString("address");
			
			
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
	
	public static void activeItem(Long number) throws SQLException {
		Connection c = con.connect();
		Statement st = c.createStatement();
		
		String query = "UPDATE donations SET donations.status = ? WHERE donations.number = ?";
		PreparedStatement ps = c.prepareStatement(query);
		ps.setInt(1, 1);
		ps.setLong(2, number);
		ps.executeUpdate();
		
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
	public static void inProgressItem(Long number) throws SQLException {
		Connection c = con.connect();
		Statement st = c.createStatement();
		
		String query = "UPDATE donations SET donations.status = ? WHERE donations.number = ?";
		PreparedStatement ps = c.prepareStatement(query);
		ps.setInt(1, 2);
		ps.setLong(2, number);
		ps.executeUpdate();
		
	}
	
	public static void editDonationQuantity(int quantity, Long number) throws SQLException {
		Connection c = con.connect();
		Statement st = c.createStatement();
		
		String query = "UPDATE donations SET donations.quantity = ? WHERE donations.number = ?";
		PreparedStatement ps = c.prepareStatement(query);
		ps.setInt(1, quantity);
		ps.setLong(2, number);
		ps.executeUpdate();
		
	}
	
	public static ArrayList<Object[]> getAllUsers() throws SQLException, ParseException {
		ArrayList<Object[]> list = new ArrayList<Object[]>();

		
		
		Connection c = con.connect();
		Statement st = c.createStatement();
		String query = "SELECT users.*, user_types.typename, user_status.statusname "+
					   "FROM users "+
					   "INNER JOIN user_types ON users.type = user_types.id "+
					   "INNER JOIN user_status ON users.status = user_status.id";
		PreparedStatement ps = c.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			Object[] items = new Object[11];
			items[0] = rs.getString("name");
			items[1] = rs.getString("surname");
			items[2] = rs.getString("username");
			items[3] = rs.getString("email");
			items[4] = rs.getString("typename");
			items[5] = rs.getString("statusname");
			items[6] = rs.getString("address");
			items[7] = rs.getString("registrationDate");
			
			Timestamp lastLoginTimestamp = rs.getTimestamp("lastLogin");
			LocalDateTime lastLogin = lastLoginTimestamp.toLocalDateTime();
			LocalDateTime now = LocalDateTime.now();
			Duration duration = Duration.between(lastLogin, now);
			
			long seconds = duration.getSeconds();
	        long minutes = duration.toMinutes();
	        long hours = duration.toHours();
	        long days = duration.toDays();
	        Period period = Period.between(lastLogin.toLocalDate(), now.toLocalDate());
	        int months = period.getYears() * 12 + period.getMonths();
			
	        if (seconds < 60) {
	            items[8] = seconds + " minutes ago";
	        } else if (minutes < 60) {
	        	items[8] = minutes + " minutes ago";
	        } else if (hours < 24) {
	        	items[8] = hours + " hours ago";
	        } else if (days < 30) {
	        	items[8] = days + " days ago";
	        } else {
	        	items[8] = months + " months ago";
	        }

			
			String secondQuery = "SELECT donations.* FROM donations WHERE donations.status = 3 AND donations.donor = ?";
			PreparedStatement secondPs = c.prepareStatement(secondQuery);
			secondPs.setInt(1, rs.getInt("id"));
			
			ResultSet secondRs = secondPs.executeQuery();
			int secondSize = 0;
			while(secondRs.next()) {
				secondSize ++;
			}
			secondPs.close();
			secondRs.close();
			items[9] = secondSize;
			
			String thirdQuery = "SELECT donations.* FROM donations WHERE donations.status = 3 AND donations.recipient = ?";
			PreparedStatement thirdPs = c.prepareStatement(thirdQuery);
			thirdPs.setInt(1, rs.getInt("id"));
			
			ResultSet thirdRs = thirdPs.executeQuery();
			int thirdSize = 0;
			while(thirdRs.next()) {
				thirdSize ++;
			}
			thirdPs.close();
			thirdRs.close();
			items[10] = thirdSize;
			
			
			list.add(items);

		}
		ps.close();
		rs.close();
		
		return list;
	}
	
	public static void makeInactiveUser(String username) throws SQLException {
		Connection c = con.connect();
		Statement st = c.createStatement();
		
		String query = "UPDATE users SET users.status = 2 WHERE users.username = ?";
		PreparedStatement ps = c.prepareStatement(query);
		ps.setString(1, username);
		ps.executeUpdate();
		
		ps.close();
		c.close();
		
	}
	public static void makeActiveUser(String username) throws SQLException {
		Connection c = con.connect();
		Statement st = c.createStatement();
		
		String query = "UPDATE users SET users.status = 1 WHERE users.username = ?";
		PreparedStatement ps = c.prepareStatement(query);
		ps.setString(1, username);
		ps.executeUpdate();
		
		ps.close();
		c.close();
		
	}
	
	public static ArrayList<String> getUsernames() throws SQLException {
		ArrayList<String> lst = new ArrayList<String>();
		
		Connection c = con.connect();
		Statement st = c.createStatement();
		String query = "SELECT users.username FROM users";
		ResultSet rs = st.executeQuery(query);
		
		while(rs.next()){
			lst.add(rs.getString("username"));
		}
		st.close();
		c.close();
		return lst;
		
	}
	
	
	
	
}
