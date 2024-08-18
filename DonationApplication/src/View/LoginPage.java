package View;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;

import Helper.DataBase;
import Helper.Message;
import Model.Admin;
import Model.User;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class LoginPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField usernameField;
	DataBase con = new DataBase();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage frame = new LoginPage();
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
	public LoginPage() {
		setTitle("DONATE APP");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel welcomeLabel = new JLabel("Welcome to DONATE APP login page");
		welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		welcomeLabel.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 24));
		welcomeLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		JLabel usernameLabel = new JLabel("Username or E-mail:");
		usernameLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		passwordField.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		
		usernameField = new JTextField();
		usernameField.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		usernameField.setColumns(10);
		
		JButton loginButton = new JButton("Login");
		loginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(usernameField.getText().length() == 0 || passwordField.getPassword().length == 0) {
					 Message.showMsg("fill"); 	 
				} else {
					
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
					        String dbPassword = rs.getString("password");
					        String userType = rs.getString("typename");
					        String userStatus = rs.getString("statusname");
					     
					        if ((inputUsername.equals(dbUsername) || inputUsername.equals(dbEmail)) && inputPassword.equals(dbPassword)) {
					            userFound = true;
					            
					            
					            if ("User".equals(userType)) {
					                
					                
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
					                    User user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), rs.getString("username"), rs.getString("email"), rs.getString("password"), rs.getString("address"));
					                    UserPage p = new UserPage(user);
					                    p.setVisible(true);
					                    dispose();
					                    break;
					                    
					                } else {
					                    Message.showMsg("Since your account isn't active, you cannot log in :(");
					                    break;
					                }
					                
					            } else {
					                Message.showMsg("You have to use the admin login page.\nClick the button on the top right.");
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
					}
					
					
					
				}
				
				
				
			}
		});
		loginButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		
		JButton registerButton = new JButton("Register");
		registerButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrationPage p = new RegistrationPage();
				p.setVisible(true);
				dispose();
			}
		});
		
		JButton adminLoginButton = new JButton("Admin Login");
		adminLoginButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(165)
					.addComponent(welcomeLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(174))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(usernameLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(18))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(passwordLabel, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
							.addGap(101)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(loginButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
						.addComponent(passwordField, Alignment.LEADING, 159, 159, 159)
						.addComponent(usernameField, Alignment.LEADING, 159, 159, 159))
					.addGap(451))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(566)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(adminLoginButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(registerButton, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(49, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(welcomeLabel, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
					.addGap(15)
					.addComponent(adminLoginButton, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addGap(93)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(usernameLabel, GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
						.addComponent(usernameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(106)
					.addComponent(registerButton, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
