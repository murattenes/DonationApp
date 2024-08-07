package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.DataBase;
import Helper.Message;
import Model.Admin;
import Model.Donor;

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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminLoginPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static Admin admin;
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
					AdminLoginPage frame = new AdminLoginPage(admin);
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
	public AdminLoginPage(Admin admin) {
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
				if(usernameField.getText().length() == 0 || passwordField.getPassword().length == 0) {
					 Message.showMsg("fill"); 	 
				} else {
					
					try {
						Connection c = con.connect();
						Statement st = c.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM users WHERE type = admin");
						Boolean flag = true;
						while(rs.next()) {
							if((usernameField.getText().equals(rs.getString("username")) || usernameField.getText().equals(rs.getString("email"))) && new String(passwordField.getPassword()).equals(rs.getString("password"))){
								flag = false;
								Admin admin = new Admin(rs.getString("name"), rs.getString("surname"), rs.getString("username"), rs.getString("email"), rs.getString("password"), rs.getString("type"));
								AdminPage p = new AdminPage(admin);
								p.setVisible(true);
								dispose();
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
