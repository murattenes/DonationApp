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
	
	

	public Admin(int id, String name, String surname, String username, String email, Long password, String address, Integer point) {
		super(id, name, surname, username, email, password, address, point);
	}
	public Admin() {
		
	}
	
	//GET DONATION NUMBER
	public static long getDonationNumber() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		LocalDateTime time = LocalDateTime.now();
		
		long donationNumber = Long.parseLong(dtf.format(time));
		
		return donationNumber;
	}
	
	
	
	//ADD USER TO DATABASE
	public static void addUser(String name, String surname, String username, String email, Long password, String address) throws SQLException {
		Connection c = con.connect();
		Statement st = c.createStatement();
		
		String query = "INSERT INTO users(name, surname, username, email, password, type, status, registrationDate, lastLogin, address) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = c.prepareStatement(query);
		
		ps.setString(1, name);
		ps.setString(2, surname);
		ps.setString(3, username);
		ps.setString(4, email);
		ps.setLong(5, password);
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
	//ADD DONATION TO THE POOL
	public static void addDonation(int category, String subcategory, String param1, String param2, String condition, int quantity, Integer donor, Integer recipient ) throws SQLException {
		Connection c = con.connect();
		Statement st = c.createStatement();
		
		String query = "INSERT INTO donations(number, category, subcategory, param1, param2, `condition`, quantity, status, donationDate, donor, recipient, isEvaluated) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
		ps.setInt(12, 0);
		
		
		ps.executeUpdate();
		ps.close();
		c.close();
		
	}
	
	//ONE USER TO ANOTHER USER USING POOL
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
	
	//ADD REQUEST TO THE POOL
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
	
	//GET DONATION FROM DATABASE USING DONATION NUMBER
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
	
	//GET ALL DONATIONS. THIS IS FOR ADMIN PAGE.
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
	
	//GET DONATIONS. THIS IS FOR USER PAGE
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
	
	//GET DONATIONS USING USER. THIS IS FOR PROFILE PAGE
	public static ArrayList<Object[]> getDonationsbyUser(User user) throws SQLException{
		ArrayList<Object[]> list = new ArrayList<Object[]>();

		
		
		Connection c = con.connect();
		Statement st = c.createStatement();
		String query = "SELECT donations.*, donation_status.statusname, donation_categories.categoryname, " +
					   "donor_user.username AS donorname, "+
					   "recipient_user.username AS recipientname "+
					   "FROM donations " + 
					   "INNER JOIN donation_status ON donations.status = donation_status.id " +
				       "INNER JOIN donation_categories ON donations.category = donation_categories.id " +
					   "INNER JOIN users AS donor_user ON donations.donor = donor_user.id " +
				       "LEFT JOIN users AS recipient_user ON donations.recipient = recipient_user.id "+
				       "WHERE donations.donor = ?" ;
		PreparedStatement ps = c.prepareStatement(query);
		ps.setInt(1, user.getId());
		
		ResultSet rs = ps.executeQuery();
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
		return list;
	}
	
	
	//GET REQUESTS USING USER. THIS IS FOR PROFILE PAGE
	public static ArrayList<Object[]> getRequestsbyUser(User user) throws SQLException{
		ArrayList<Object[]> list = new ArrayList<Object[]>();

		
		
		Connection c = con.connect();
		Statement st = c.createStatement();
		String query = "SELECT donations.*, donation_status.statusname, donation_categories.categoryname, " +
					   "recipient_user.username AS recipientname, "+
					   "donor_user.username AS donorname "+
					   "FROM donations " + 
					   "INNER JOIN donation_status ON donations.status = donation_status.id " +
				       "INNER JOIN donation_categories ON donations.category = donation_categories.id " +
					   "INNER JOIN users AS recipient_user ON donations.recipient = recipient_user.id " +
				       "LEFT JOIN users AS donor_user ON donations.donor = donor_user.id "+
				       "WHERE recipient_user.id = ?" ;
		PreparedStatement ps = c.prepareStatement(query);
		ps.setInt(1, user.getId());
		
		ResultSet rs = ps.executeQuery();
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
			items[9] = rs.getString("recipientname");
			items[10] = rs.getString("donorname");
			
			list.add(items);
		}		
		return list;
	}
	
	//MAKE ACTIVE STATUS OF DONATION
	public static void activeItem(Long number) throws SQLException {
		Connection c = con.connect();
		Statement st = c.createStatement();
		
		String query = "UPDATE donations SET donations.status = ? WHERE donations.number = ?";
		PreparedStatement ps = c.prepareStatement(query);
		ps.setInt(1, 1);
		ps.setLong(2, number);
		ps.executeUpdate();
		
	}
	
	//MAKE COMPLETED STATUS OF DONATION
	public static void completeItem(Long number) throws SQLException {
		Connection c = con.connect();
		Statement st = c.createStatement();
		
		String query = "UPDATE donations SET donations.status = ? WHERE donations.number = ?";
		PreparedStatement ps = c.prepareStatement(query);
		ps.setInt(1, 3);
		ps.setLong(2, number);
		ps.executeUpdate();
		
	}
	
	//MAKE CANCELED STATUS OF DONATION
	public static void cancelItem(Long number) throws SQLException {
		Connection c = con.connect();
		Statement st = c.createStatement();
		
		String query = "UPDATE donations SET donations.status = ? WHERE donations.number = ?";
		PreparedStatement ps = c.prepareStatement(query);
		ps.setInt(1, 4);
		ps.setLong(2, number);
		ps.executeUpdate();
		
	}
	
	//MAKE INPROGRESS STATUS OF DONATION
	public static void inProgressItem(Long number) throws SQLException {
		Connection c = con.connect();
		Statement st = c.createStatement();
		
		String query = "UPDATE donations SET donations.status = ? WHERE donations.number = ?";
		PreparedStatement ps = c.prepareStatement(query);
		ps.setInt(1, 2);
		ps.setLong(2, number);
		ps.executeUpdate();
		
	}
	
	//WHEN SOMEONE DONATED LESS THAN NEEDED DECREASE THE NEEDED QUANTITY IN POOL
	public static void editDonationQuantity(int quantity, Long number) throws SQLException {
		Connection c = con.connect();
		Statement st = c.createStatement();
		
		String query = "UPDATE donations SET donations.quantity = ? WHERE donations.number = ?";
		PreparedStatement ps = c.prepareStatement(query);
		ps.setInt(1, quantity);
		ps.setLong(2, number);
		ps.executeUpdate();
		
	}
	
	//GET ALL USERS FROM DATABASE. THIS IS FOR ADMIN PAGE
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
			Object[] items = new Object[12];
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
			items[11] = rs.getFloat("point");
			
			
			list.add(items);

		}
		ps.close();
		rs.close();
		
		return list;
	}
	
	//MAKE INACTIVE STATUS OF USER. THIS IS FOR ADMIN PAGE
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
	
	//MAKE ACTIVE STATUS OF USER. THIS IS FOR ADMIN PAGE
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
	
	//GET ALL USERNAMES OF USERS FROM DATABASE. THIS IS FOR REGISTRATION PAGE. CHECK WHETHER USERNAME UNIQUE
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
	
	public static Integer getEvaluateCount(String username) throws SQLException {
		int value = 0;
		
		
		Connection c = con.connect();
		Statement st = c.createStatement();
		String query = "SELECT COUNT(donations.isEvaluated) " +
					   "FROM donations " +
					   "INNER JOIN users ON donations.donor = users.id " +
					   "WHERE donations.isEvaluated = 1 AND users.username = ?";
		PreparedStatement ps = c.prepareStatement(query);
		ps.setString(1, username);
		
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			value = rs.getInt(1);
		}
		
		
		
		return value;
	}
	
	public static Float getUserPoint(String username) throws SQLException {
		float value = 0;
		
		
		Connection c = con.connect();
		Statement st = c.createStatement();
		String query = "SELECT point FROM users WHERE username = ?";
		PreparedStatement ps = c.prepareStatement(query);
		ps.setString(1, username);
		
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			value = rs.getFloat("point");
			return value;
		}
		
		
		rs.close();
	    ps.close();
	    c.close();
		return value;
		
		
	}
	public static void updateUserPoint(String username, Float value) throws SQLException {
		Connection c = con.connect();
		Statement st = c.createStatement();
		
		String query = "UPDATE users SET users.point = ? WHERE users.username = ?";
		PreparedStatement ps = c.prepareStatement(query);
		
		ps.setFloat(1, value);
		ps.setString(2, username);
		ps.executeUpdate();
		
		ps.close();
		c.close();
		
	}
	
	public static void updateIsEvaluated(Long no) throws SQLException {
		Connection c = con.connect();
		Statement st = c.createStatement();
		
		String query = "UPDATE donations SET donations.isEvaluated = 1 WHERE donations.number = ?";
		PreparedStatement ps = c.prepareStatement(query);
		
		ps.setLong(1, no);
		ps.executeUpdate();
		
		ps.close();
		c.close();
	}
	
	public static Boolean checkIsEvaluated(Long no) throws SQLException {
		
		Connection c = con.connect();
		Statement st = c.createStatement();
		
		String query = "SELECT donations.* FROM donations WHERE donations.number = ?";
		PreparedStatement ps = c.prepareStatement(query);
		ps.setLong(1, no);
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			Integer value = rs.getInt("isEvaluated");
			if (value == 1) {
				return true;
			}
		}
		
		ps.close();
		c.close();
		
		return false;
	}
	
	
	
	
}
