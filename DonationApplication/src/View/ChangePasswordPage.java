package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.DataBase;
import Helper.Message;
import Model.User;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

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
		setTitle("CHANGE PASSWORD");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
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
		
		JButton changeButton = new JButton("CHANGE");
		changeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String oldP = new String(oldPasswordField.getPassword());
				String newP = new String(newPasswordField.getPassword());
				String renewP = new String(reNewPasswordField.getPassword());
				if(oldPasswordField.getPassword().length == 0 || newPasswordField.getPassword().length == 0 || reNewPasswordField.getPassword().length == 0) {
					Message.showMsg("fill");
				}			
				else if (!newP.equals(renewP)) {
					Message.showMsg("New passwords have to be same.");
				}
				else if (newP.equals(oldP)) {
					Message.showMsg("New password cannot be same as old password.");
				}
				else {
					try {
						Connection c = con.connect();
						Statement st = c.createStatement();
						String query = "UPDATE users SET password = ? WHERE id = ?";
						PreparedStatement ps = c.prepareStatement(query);
						ps.setString(1, new String(newPasswordField.getPassword()));
						ps.setInt(2, user.getId());
						ps.executeUpdate();
						ps.close();
					    c.close();
					    
					    Message.showMsg("Your password has changed successfully.");
					    dispose();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
			
		});
		changeButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		changeButton.setBounds(207, 181, 117, 29);
		contentPane.add(changeButton);
	}
}
