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
import Model.Donor;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
						Connection c = con.connect();
						Statement st = c.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM users");
						Boolean flag = true;
						while(rs.next()) {
							if((usernameField.getText().equals(rs.getString("username")) || usernameField.getText().equals(rs.getString("email"))) && new String(passwordField.getPassword()).equals(rs.getString("password"))){
								flag = false;
								if("Donor".equals(rs.getString("type"))) {
									Donor d = new Donor(rs.getString("name"), rs.getString("surname"), rs.getString("username"), rs.getString("email"), rs.getString("password"), rs.getString("type"));
									DonorPage p = new DonorPage(d);
									p.setVisible(true);
									dispose();
									break;
								}
								else if("Recipient".equals(rs.getString("type"))) {
									RecipientPage p = new RecipientPage();
									p.setVisible(true);
									dispose();
									break;
							
								}
							}
							
						}
						if (flag) {
							Message.showMsg("Wrong username/email or password!");
						}
						
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
