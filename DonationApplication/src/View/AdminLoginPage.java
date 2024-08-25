package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.DataBase;
import Helper.Message;
import Model.Admin;
import Model.User;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JToggleButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDateTime;

public class AdminLoginPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	DataBase con = new DataBase();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminLoginPage frame = new AdminLoginPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminLoginPage() {
		setTitle("ADMIN LOGIN PAGE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel usernameLabel = new JLabel("Username or e-mail:");
		usernameLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		usernameLabel.setBounds(6, 170, 162, 20);
		contentPane.add(usernameLabel);
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		passwordLabel.setBounds(6, 226, 162, 20);
		contentPane.add(passwordLabel);
		
		usernameField = new JTextField();
		usernameField.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		usernameField.setBounds(180, 167, 130, 26);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		passwordField.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		passwordField.setBounds(180, 224, 130, 26);
		contentPane.add(passwordField);
		
		JButton loginButton = new JButton("Login");
		loginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					//CONNECT TO DB
					Connection c = con.connect();
					Statement st = c.createStatement();
					String query = "SELECT users.*, " +
                               "user_types.typename, user_status.statusname " +
                               "FROM users " +
                               "INNER JOIN user_types ON users.type = user_types.id " +
                               "INNER JOIN user_status ON users.status = user_status.id ";
					ResultSet rs = st.executeQuery(query);
					boolean userFound = false;
					while(rs.next()) {
						//USER'S DATAS
						String inputUsername = usernameField.getText();
				        String inputPassword = new String(passwordField.getPassword());
				        
				        Integer dbUserId = rs.getInt("id");
				        String dbUsername = rs.getString("username");
				        String dbEmail = rs.getString("email");
				        Long dbPassword = rs.getLong("password");
				        String userType = rs.getString("typename");
				        String userStatus = rs.getString("statusname");
				     
				        if ((inputUsername.equals(dbUsername) || inputUsername.equals(dbEmail)) && inputPassword.hashCode() == dbPassword) {
				            userFound = true;
				            
				            
				            if ("Admin".equals(userType)) {
				                
				                
				                if ("Active".equals(userStatus)) {
				                	//UPDATE LAST LOGIN
				                	String updateQuery = "UPDATE users SET lastLogin = ? WHERE id = ?";
				                	PreparedStatement ps = c.prepareStatement(updateQuery);
				                	LocalDateTime time = LocalDateTime.now();
				                	ps.setTimestamp(1, Timestamp.valueOf(time));
				                	ps.setInt(2, dbUserId);
				                	ps.executeUpdate();
				                	ps.close();
				                	
				                	
				                	//LOGIN
				                    Admin user = new Admin(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), rs.getString("username"), rs.getString("email"), rs.getLong("password"), rs.getString("address"), null);
				                    AdminPage p = new AdminPage(user);
				                    p.setVisible(true);
				                    dispose();
				                    break;
				                    
				                } else {
				                    Message.showMsg("Since your account isn't active, you cannot log in :(");
				                    break;
				                }
				                
				            } else {
				                Message.showMsg("You have to use the user login page.\nClick the button on the bottom right.");
				                break;
				            }
				        }
						
						
					}
					if (!userFound) {
				        Message.showMsg("Incorrect username/email or password.");
				    }
				
					
					st.close();
					rs.close();
					c.close();
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		loginButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		loginButton.setBounds(180, 280, 130, 29);
		contentPane.add(loginButton);
		
		JLabel welcomeLabel = new JLabel("Welcome to DONATE APP admin login page");
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 24));
		welcomeLabel.setBounds(0, 0, 750, 50);
		contentPane.add(welcomeLabel);
		
		JButton userLoginPageButton = new JButton("To user login page");
		userLoginPageButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginPage p = new LoginPage();
				p.setVisible(true);
				dispose();
			}
		});
		userLoginPageButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		userLoginPageButton.setBounds(552, 443, 198, 29);
		contentPane.add(userLoginPageButton);
	}
}
