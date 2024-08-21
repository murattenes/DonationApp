package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.DataBase;
import Helper.Message;
import Model.Admin;
import Model.User;

import java.awt.Window.Type;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegistrationPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel nameLabel;
	private JLabel surnameLabel;
	private JLabel usernameLabel;
	private JLabel emailLabel;
	private JLabel rePasswordLabel;
	private JLabel passwordLabel;
	private JPasswordField passwordField;
	private JPasswordField rePasswordField;
	private JTextField usernameField;
	private JTextField surnameField;
	private JTextField nameField;
	private JButton joinButton;
	private JTextField emailField;
	private JTextField addressTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrationPage frame = new RegistrationPage();
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
	public RegistrationPage() {
		setTitle("Registration Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		nameLabel = new JLabel("Name:");
		nameLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		
		surnameLabel = new JLabel("Surname:");
		surnameLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		
		usernameLabel = new JLabel("Username:");
		usernameLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		
		emailLabel = new JLabel("E-mail:");
		emailLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		
		rePasswordLabel = new JLabel("Re-enter Password:");
		rePasswordLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		
		passwordLabel = new JLabel("Password:");
		passwordLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		passwordField.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		
		rePasswordField = new JPasswordField();
		rePasswordField.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		rePasswordField.setColumns(10);
		
		usernameField = new JTextField();
		usernameField.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		usernameField.setColumns(10);
		
		surnameField = new JTextField();
		surnameField.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		surnameField.setColumns(10);
		
		nameField = new JTextField();
		nameField.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		nameField.setColumns(10);
		
		emailField = new JTextField();
		emailField.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		emailField.setColumns(10);
		
		joinButton = new JButton("Join :)");
		joinButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Boolean flag = false;
					ArrayList<String> lst = Admin.getUsernames();
					for(String str:lst) {
						if(usernameField.getText().equals(str)) {
							flag = true;
						}
					}
					if(nameField.getText().length() == 0 || surnameField.getText().length() == 0 || usernameField.getText().length() == 0 ||
							 emailField.getText().length() == 0 || rePasswordField.getPassword().length == 0 || passwordField.getPassword().length == 0 || addressTextField.getText().length() == 0) {
						 Message.showMsg("fill");
						 
						 
					 }
					 else if(!Arrays.equals(rePasswordField.getPassword(), passwordField.getPassword())) {
						Message.showMsg("Passwords must be same!");
			
						
					 }
					 else if(flag) {
						 Message.showMsg("This username already exist.");
					 }
					 
					 else {
						try {
							Admin.addUser(nameField.getText(), surnameField.getText(), usernameField.getText(), emailField.getText(), new String(passwordField.getPassword()), addressTextField.getText());
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							System.out.println(e1);
						}
						LoginPage page = new LoginPage();
						page.setVisible(true);
						dispose();
					 }
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		joinButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		
		JButton backToLoginPageButton = new JButton("Back to the login page");
		backToLoginPageButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginPage p = new LoginPage();
				p.setVisible(true);
				dispose();
			}
		});
		backToLoginPageButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		
		JLabel addressLabel = new JLabel("Address:");
		addressLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		
		addressTextField = new JTextField();
		addressTextField.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		addressTextField.setColumns(10);
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(nameLabel, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
						.addComponent(emailLabel, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordLabel)
						.addComponent(rePasswordLabel)
						.addComponent(surnameLabel, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
						.addComponent(usernameLabel))
					.addPreferredGap(ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
						.addComponent(usernameField, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
						.addComponent(nameField, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
						.addComponent(surnameField, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
						.addComponent(emailField, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(rePasswordField, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)))
					.addGap(398))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(514, Short.MAX_VALUE)
					.addComponent(backToLoginPageButton))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addComponent(addressLabel, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(joinButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(addressTextField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE))
					.addGap(397))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(backToLoginPageButton)
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(nameLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(nameField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(surnameField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(surnameLabel))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(emailLabel)
						.addComponent(emailField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(usernameField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(usernameLabel))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordLabel)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(rePasswordField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(rePasswordLabel))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(addressLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(addressTextField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(joinButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(42))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
