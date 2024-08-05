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
	private JLabel genderLabel;
	private JLabel usernameLabel;
	private JLabel emailLabel;
	private JLabel passwordLabel;
	private JLabel rePasswordLabel;
	private JPasswordField rePasswordField;
	private JPasswordField passwordField;
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
		
		genderLabel = new JLabel("Gender:");
		genderLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		
		usernameLabel = new JLabel("Username:");
		usernameLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		
		emailLabel = new JLabel("E-mail:");
		emailLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		
		passwordLabel = new JLabel("Password:");
		passwordLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		
		rePasswordLabel = new JLabel("Re-enter Password:");
		rePasswordLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		
		rePasswordField = new JPasswordField();
		rePasswordField.setColumns(10);
		rePasswordField.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		passwordField.setColumns(10);
		
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
		
		JComboBox genderComboBox = new JComboBox();
		genderComboBox.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		genderComboBox.setBackground(new Color(238, 237, 238));
		genderComboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Male", "Female", "Other"}));
		
		
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
				if(nameField.getText().length() == 0 || surnameField.getText().length() == 0 || genderComboBox.getSelectedIndex() == 0 || usernameField.getText().length() == 0 ||
						 emailField.getText().length() == 0 || passwordField.getPassword().length == 0 || rePasswordField.getPassword().length == 0 || typeComboBox.getSelectedIndex() == 0) {
					 Message.showMsg("fill");
					 
					 
				 }
				 else if(!Arrays.equals(passwordField.getPassword(), rePasswordField.getPassword())) {
					Message.showMsg("Passwords must be same!");
		
					
				 }
				 else {
					try {
						Admin.addUser(nameField.getText(), surnameField.getText(), genderComboBox.getSelectedItem().toString(), usernameField.getText(), emailField.getText(), new String(passwordField.getPassword()), typeComboBox.getSelectedItem().toString());
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
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(rePasswordLabel, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
									.addComponent(passwordLabel, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.RELATED))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(emailLabel, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
								.addGap(90))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(usernameLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGap(90))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(surnameLabel, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
								.addGap(97))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(nameLabel, GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
								.addGap(97))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(genderLabel, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
								.addGap(97)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(profileTypeLabel, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
							.addGap(65)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(typeComboBox, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(455, Short.MAX_VALUE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(joinButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(genderComboBox, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(nameField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
									.addComponent(surnameField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
									.addComponent(usernameField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
									.addComponent(passwordField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
									.addComponent(rePasswordField, Alignment.LEADING))
								.addGap(408)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(emailField, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
							.addGap(408))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(55)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(nameLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(nameField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(surnameLabel, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
						.addComponent(surnameField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(genderLabel, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
						.addComponent(genderComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(usernameLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(usernameField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(emailLabel, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
						.addComponent(emailField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordLabel, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(rePasswordLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(rePasswordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(profileTypeLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(typeComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(joinButton, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
					.addGap(33))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
