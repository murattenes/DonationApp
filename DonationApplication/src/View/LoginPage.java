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
import javax.swing.LayoutStyle.ComponentPlacement;

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
		setTitle("USER LOGIN PAGE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(242, 235, 191));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel welcomeLabel = new JLabel("Welcome to DONATE APP login page");
		welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		welcomeLabel.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 24));
		welcomeLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		JLabel usernameLabel = new JLabel("Username or E-mail");
		usernameLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 14));
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 14));
		
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		passwordField.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		
		usernameField = new JTextField();
		usernameField.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		usernameField.setColumns(10);
		
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		loginButton.setForeground(new Color(0, 0, 0));
		loginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(usernameField.getText().length() == 0 || passwordField.getPassword().length == 0) {
					 Message.showMsg("fill");
					 System.out.println(usernameField.getText().hashCode());
				} 
				
				else {
					String inputUsername = usernameField.getText();
			        String inputPassword = new String(passwordField.getPassword());
			        
			        	//CONNECT TO DB
						
						try {
							Connection c = con.connect();
							String query = "SELECT users.*, " +
		                               "user_types.typename, user_status.statusname " +
		                               "FROM users " +
		                               "INNER JOIN user_types ON users.type = user_types.id " +
		                               "INNER JOIN user_status ON users.status = user_status.id "+
		                               "WHERE users.username = ? OR users.email = ?";
							
							PreparedStatement ps = c.prepareStatement(query);
							ps.setString(1, inputUsername);
							ps.setString(2, inputUsername);
							
							ResultSet rs = ps.executeQuery();
							
							if(rs.next()) {
								Integer dbUserId = rs.getInt("id");
						        String dbUsername = rs.getString("username");
						        String dbEmail = rs.getString("email");
						        Long dbPassword = rs.getLong("password");
						        String userType = rs.getString("typename");
						        String userStatus = rs.getString("statusname");
						        if (inputPassword.hashCode() == dbPassword) {
						        	
						        	if ("User".equals(userType)) {
						                
						                if ("Active".equals(userStatus)) {
						                	//UPDATE LAST LOGIN
						                	String updateQuery = "UPDATE users SET lastLogin = ? WHERE id = ?";
						                	PreparedStatement pst = c.prepareStatement(updateQuery);
						                	LocalDateTime time = LocalDateTime.now();
						                	pst.setTimestamp(1, Timestamp.valueOf(time));
						                	pst.setInt(2, dbUserId);
						                	pst.executeUpdate();
						                	pst.close();
						                	
						                	
						                	//LOGIN
						                    User user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), rs.getString("username"), rs.getString("email"), rs.getLong("password"), rs.getString("address"), null);
						                    UserPage p = new UserPage(user);
						                    p.setVisible(true);
						                    dispose();
						                    
						                    
						                } else {
						                    Message.showMsg("Since your account isn't active, you cannot log in :(");
						                    
						                }
						                
						            } else {
						                Message.showMsg("You have to use the admin login page.\nClick the button on the bottom right.");
						                
						            }
						        
						        }
						        else {
						        	Message.showMsg("Incorrect username/email or password.");
						        }
							}
							else {
								Message.showMsg("Incorrect username/email or password.");
							}
									
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
			        }
			        
			        
			        /*
					
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
					        Integer dbUserId = rs.getInt("id");
					        String dbUsername = rs.getString("username");
					        String dbEmail = rs.getString("email");
					        Long dbPassword = rs.getLong("password");
					        String userType = rs.getString("typename");
					        String userStatus = rs.getString("statusname");
					     
					        if ((inputUsername.equals(dbUsername) || inputUsername.equals(dbEmail)) && inputPassword.hashCode() == dbPassword) {
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
					                    User user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), rs.getString("username"), rs.getString("email"), rs.getLong("password"), rs.getString("address"), null);
					                    UserPage p = new UserPage(user);
					                    p.setVisible(true);
					                    dispose();
					                    break;
					                    
					                } else {
					                    Message.showMsg("Since your account isn't active, you cannot log in :(");
					                    break;
					                }
					                
					            } else {
					                Message.showMsg("You have to use the admin login page.\nClick the button on the bottom right.");
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
					*/
				}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				loginButton.setForeground(new Color(0, 255, 102));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				loginButton.setForeground(new Color(0, 0, 0));
			}
		});
		loginButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		
		JButton registerButton = new JButton("Create an account");
		registerButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RegistrationPage p = new RegistrationPage();
				p.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				registerButton.setForeground(new Color(0, 255, 102));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				registerButton.setForeground(new Color(0, 0, 0));
			}
		});
		registerButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		
		
		JButton adminLoginButton = new JButton("Admin Login");
		adminLoginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AdminLoginPage p = new AdminLoginPage();
				p.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				adminLoginButton.setForeground(new Color(0, 255, 102));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				adminLoginButton.setForeground(new Color(0, 0, 0));
			}
		});
		adminLoginButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(165)
					.addComponent(welcomeLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(174))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(284)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(registerButton, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(loginButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(passwordLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(passwordField, Alignment.LEADING)
							.addComponent(usernameField, Alignment.LEADING)
							.addComponent(usernameLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)))
					.addGap(279))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(593, Short.MAX_VALUE)
					.addComponent(adminLoginButton, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
					.addGap(47))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(welcomeLabel, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
					.addGap(119)
					.addComponent(usernameLabel, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(usernameField, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(passwordLabel, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(loginButton, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(registerButton, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
					.addGap(31)
					.addComponent(adminLoginButton, GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
