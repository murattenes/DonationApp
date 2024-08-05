package Helper;

import java.sql.*;

public class DataBase {
	Connection c = null;
	
	public Connection connect() {
		try {
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/donationapp?user=root&password=Ekvator0");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return c;
	}
		
		public static void insertUser(String name, String surname, String username, String email, String password, String type) throws SQLException {
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/donationapp?user=root&password=Ekvator0");
			Statement st = c.createStatement();
			
			String query = "INSERT INTO users(name, surname, username, email, password, type) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = c.prepareStatement(query);
			ps.setString(1, name);
			ps.setString(2, surname);
			ps.setString(4, username);
			ps.setString(5, email);
			ps.setString(6, password);
			ps.setString(7, type);
			
			ps.executeUpdate();
			c.close();
			
			
		}

	

}
