package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.DataBase;
import Helper.Api;
import Helper.Message;
import Model.User;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTextPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ChangePasswordPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static User user = new User();
	private JPasswordField oldPasswordField;
	private JPasswordField newPasswordField;
	private JPasswordField reNewPasswordField;
	static DataBase con = new DataBase();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangePasswordPage frame = new ChangePasswordPage(user);
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
	public ChangePasswordPage(User user) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				ProfilePage.changePasswordPageControl = true;
			}
		});
		setTitle("CHANGE PASSWORD");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel oldPasswordLabel = new JLabel("Old Password:");
		oldPasswordLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		oldPasswordLabel.setBounds(6, 30, 110, 20);
		contentPane.add(oldPasswordLabel);
		
		JLabel newPasswordLabel = new JLabel("New Password:");
		newPasswordLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		newPasswordLabel.setBounds(6, 87, 151, 20);
		contentPane.add(newPasswordLabel);
		
		JLabel reNewPasswordLabel = new JLabel("Re-enter new password:");
		reNewPasswordLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		reNewPasswordLabel.setBounds(6, 119, 189, 20);
		contentPane.add(reNewPasswordLabel);
		
		oldPasswordField = new JPasswordField();
		oldPasswordField.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		oldPasswordField.setBounds(207, 27, 110, 26);
		contentPane.add(oldPasswordField);
		
		newPasswordField = new JPasswordField();
		newPasswordField.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		newPasswordField.setBounds(207, 84, 110, 26);
		contentPane.add(newPasswordField);
		
		reNewPasswordField = new JPasswordField();
		reNewPasswordField.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		reNewPasswordField.setBounds(207, 117, 110, 26);
		contentPane.add(reNewPasswordField);
		
		JButton changePasswordButton = new JButton("Change Password");
		changePasswordButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Long oldP = (long) new String(oldPasswordField.getPassword()).hashCode();
				Long newP = (long) new String(newPasswordField.getPassword()).hashCode();
				Long renewP = (long) new String(reNewPasswordField.getPassword()).hashCode();
				if(oldPasswordField.getPassword().length == 0 || newPasswordField.getPassword().length == 0 || reNewPasswordField.getPassword().length == 0) {
					Message.showMsg("Please don't leave any unfilled area regarding the password!");
				}			
				else if (newP != renewP) {
					Message.showMsg("New passwords have to be same.");
				}
				else if (newP == oldP) {
					Message.showMsg("New password cannot be same as old password.");
				}
				else {
					try {
						Connection c = con.connect();
						String query = "UPDATE users SET password = ? WHERE id = ?";
						PreparedStatement ps = c.prepareStatement(query);
						ps.setLong(1, new String(newPasswordField.getPassword()).hashCode());
						ps.setInt(2, user.getId());
						ps.executeUpdate();
						ps.close();
					    c.close();
					    
					    Message.showMsg("Your password has changed successfully.");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
			
		});
		changePasswordButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		changePasswordButton.setBounds(127, 257, 190, 29);
		contentPane.add(changePasswordButton);
		
		JLabel provinceLabel = new JLabel("Province:");
		provinceLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		provinceLabel.setBounds(365, 33, 71, 20);
		contentPane.add(provinceLabel);
		
		JLabel districtLabel = new JLabel("District:");
		districtLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		districtLabel.setBounds(365, 65, 62, 20);
		contentPane.add(districtLabel);
		
		JLabel detailLabel = new JLabel("Details:");
		detailLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		detailLabel.setBounds(365, 121, 71, 16);
		contentPane.add(detailLabel);
		
		JComboBox districtsComboBox = new JComboBox();
		districtsComboBox.setMaximumRowCount(12);
		districtsComboBox.setBounds(458, 64, 117, 27);
		contentPane.add(districtsComboBox);
		
		JComboBox provincesComboBox = new JComboBox();
		provincesComboBox.setMaximumRowCount(12);
		provincesComboBox.setBounds(458, 29, 117, 27);
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
		contentPane.add(provincesComboBox);
		
		
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(458, 119, 189, 91);
		contentPane.add(textPane);
		
		JButton changeAddressButton = new JButton("Change address");
		changeAddressButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(provincesComboBox.getSelectedIndex() < 0 || districtsComboBox.getSelectedIndex() < 0) {
					Message.showMsg("Please don't leave any unfilled area regarding the address!");
				}
				else {
					
					try {
						String address = textPane.getText() + ", " + districtsComboBox.getSelectedItem() + ", " + provincesComboBox.getSelectedItem();
						Connection c = con.connect();
						String query = "UPDATE users SET address = ? WHERE id = ?";
						PreparedStatement ps = c.prepareStatement(query);
						ps.setString(1, address);
						ps.setInt(2, user.getId());
						ps.executeUpdate();
						ps.close();
					    c.close();
					    
					    Message.showMsg("Your address has changed successfully.");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		changeAddressButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		changeAddressButton.setBounds(458, 258, 176, 29);
		contentPane.add(changeAddressButton);
	}
}
