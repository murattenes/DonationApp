package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.DataBase;
import Helper.Message;
import Model.Admin;

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
		
		
		JLabel profileTypeLabel = new JLabel("Profile Type:");
		profileTypeLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		
		JComboBox typeComboBox = new JComboBox();
		typeComboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Donor", "Recipient"}));
		typeComboBox.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		typeComboBox.setBackground(new Color(238, 237, 238));
		
		joinButton = new JButton("Join :)");
		joinButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(nameField.getText().length() == 0 || surnameField.getText().length() == 0 || usernameField.getText().length() == 0 ||
						 emailField.getText().length() == 0 || rePasswordField.getPassword().length == 0 || passwordField.getPassword().length == 0 || typeComboBox.getSelectedIndex() == 0) {
					 Message.showMsg("fill");
					 
					 
				 }
				 else if(!Arrays.equals(rePasswordField.getPassword(), passwordField.getPassword())) {
					Message.showMsg("Passwords must be same!");
		
					
				 }
				 else {
					try {
						Admin.addUser(nameField.getText(), surnameField.getText(), usernameField.getText(), emailField.getText(), new String(rePasswordField.getPassword()), typeComboBox.getSelectedItem().toString());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						System.out.println(e1);
					}
					LoginPage page = new LoginPage();
					page.setVisible(true);
					dispose();
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
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(nameLabel, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, 97, Short.MAX_VALUE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(emailLabel, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, 95, Short.MAX_VALUE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(profileTypeLabel, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, 61, Short.MAX_VALUE)))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(passwordLabel)
										.addPreferredGap(ComponentPlacement.RELATED)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(rePasswordLabel)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(surnameLabel, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(usernameLabel)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
						.addComponent(usernameField, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
						.addComponent(nameField, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
						.addComponent(surnameField, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
						.addComponent(emailField, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(rePasswordField, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(typeComboBox, 0, 112, Short.MAX_VALUE)
									.addGap(61))
								.addComponent(joinButton, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE))))
					.addGap(398))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(514, Short.MAX_VALUE)
					.addComponent(backToLoginPageButton))
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
						.addComponent(profileTypeLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(typeComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(joinButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(45))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
