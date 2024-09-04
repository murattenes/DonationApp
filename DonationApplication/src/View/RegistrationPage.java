package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.DataBase;
import Helper.Api;
import Helper.Message;
import Model.Admin;
import Model.User;

import java.awt.Window.Type;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;

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
	private JComboBox provincesComboBox;
	private JLabel districtLabel;
	private JComboBox districtsComboBox;

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
		setTitle("REGISTRATION PAGE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(242, 231, 230));
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
		
		JTextPane detailsTextPane = new JTextPane();
		
		joinButton = new JButton("Join :)");
		joinButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Boolean usernameFlag = Admin.getSpecificUsername(usernameField.getText());
					Boolean emailFlag = Admin.getSpecificEmail(emailField.getText());
					
					if(nameField.getText().length() == 0 || surnameField.getText().length() == 0 || usernameField.getText().length() == 0 ||
							 emailField.getText().length() == 0 || rePasswordField.getPassword().length == 0 || passwordField.getPassword().length == 0 ||
							 provincesComboBox.getSelectedIndex() < 0 || districtsComboBox.getSelectedIndex() < 0) {
						 Message.showMsg("fill");
					}
					else if(usernameField.getText().contains("@")) {
						Message.showMsg("Username cannot contaion '@' symbol!");
					}
					else if(!Arrays.equals(rePasswordField.getPassword(), passwordField.getPassword())) {
						Message.showMsg("Passwords must be same!");
						
					}
					else if(usernameFlag) {
						 Message.showMsg("There is an account with this username.");
					}
					else if(emailFlag) {
						 Message.showMsg("There is an account with this e-mail.");
					}
					
				    else {
						try {
							Long passwordHashed = (long) new String(passwordField.getPassword()).hashCode();
							String address = detailsTextPane.getText() + ", " + districtsComboBox.getSelectedItem() + ", " + (String) provincesComboBox.getSelectedItem();
							
							Admin.addUser(nameField.getText(), surnameField.getText(), usernameField.getText(), emailField.getText(), passwordHashed, address);
							
							Message.showMsg("Your account is created successfully.\nYou are directed to the login page.");
							
							LoginPage page = new LoginPage();
							page.setVisible(true);
							dispose();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							System.out.println(e1);
						}
						
					 }
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				joinButton.setForeground(new Color(0, 255, 102));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				joinButton.setForeground(new Color(0, 0, 0));
			}
		});
		joinButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		
		JButton backToLoginPageButton = new JButton("← Login Page");
		backToLoginPageButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginPage p = new LoginPage();
				p.setVisible(true);
				dispose();
			}
		});
		backToLoginPageButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		
		JLabel addressLabel = new JLabel("Address");
		addressLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		
		
		JLabel provinceLabel = new JLabel("Province:");
		provinceLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		
		
		districtLabel = new JLabel("District:");
		districtLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		
		
		//DISTRICT
		districtsComboBox = new JComboBox();
		districtsComboBox.setMaximumRowCount(12);
		
		
		//PROVINCE
		provincesComboBox = new JComboBox();
		provincesComboBox.setMaximumRowCount(12);
		List<String> provinces = Api.getProvinces();
		provincesComboBox.setModel(new DefaultComboBoxModel<>(provinces.toArray(new String[0])));
		provincesComboBox.setSelectedIndex(-1);
		provincesComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int chosen = provincesComboBox.getSelectedIndex();
				if (provincesComboBox.getSelectedIndex() > -1) {
					String chosenProvince = (String) provincesComboBox.getSelectedItem();
					List<String> districts = Api.getDistricts(chosenProvince);
					districtsComboBox.setModel(new DefaultComboBoxModel<>(districts.toArray(new String[0])));
					districtsComboBox.setSelectedIndex(-1);
				}
			}
		});
		
		
		
		JLabel detailsLabel = new JLabel("Details:");
		detailsLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		
		
		
		
		

		
		
		
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(nameLabel, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
						.addComponent(emailLabel, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordLabel)
						.addComponent(rePasswordLabel)
						.addComponent(surnameLabel, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
						.addComponent(usernameLabel))
					.addGap(17)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
						.addComponent(usernameField, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
						.addComponent(nameField, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
						.addComponent(surnameField, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
						.addComponent(emailField, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(joinButton, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
								.addComponent(rePasswordField, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE))))
					.addGap(69)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(addressLabel, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
								.addComponent(districtLabel, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
								.addComponent(provinceLabel, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(detailsLabel, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
							.addGap(21)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(detailsTextPane, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
						.addComponent(districtsComboBox, 0, 156, Short.MAX_VALUE)
						.addComponent(provincesComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(51))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(backToLoginPageButton)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(backToLoginPageButton, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(nameLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(nameField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(addressLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
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
								.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(provinceLabel)
								.addComponent(provincesComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(districtLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addComponent(districtsComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(detailsLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(16)
									.addComponent(detailsTextPane, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)))))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(rePasswordField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(rePasswordLabel))
					.addGap(18)
					.addComponent(joinButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(79))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
