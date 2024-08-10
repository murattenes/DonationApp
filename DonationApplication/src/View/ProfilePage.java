package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Helper.DataBase;
import Helper.Message;
import Helper.NonEditableTableModel;
import Model.Admin;
import Model.User;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProfilePage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static User user = new User();
	private JTable myDonationsTable;
	private JTable myRequestsTable;
	static DataBase con = new DataBase();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProfilePage frame = new ProfilePage(user);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param user 
	 * @throws SQLException 
	 */
	public ProfilePage(User user) throws SQLException {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		usernameLabel.setBounds(6, 6, 83, 20);
		contentPane.add(usernameLabel);
		
		JLabel emailLabel = new JLabel("Email:");
		emailLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		emailLabel.setBounds(6, 38, 83, 20);
		contentPane.add(emailLabel);
		
		JButton changePasswordButton = new JButton("Change Password");
		changePasswordButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ChangePasswordPage p = new ChangePasswordPage(user);
				p.setVisible(true);
			}
		});
		changePasswordButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		changePasswordButton.setBounds(419, 3, 175, 29);
		contentPane.add(changePasswordButton);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 70, 600, 323);
		contentPane.add(tabbedPane);
		
		JPanel myDonationsPanel = new JPanel();
		tabbedPane.addTab("My Donations", null, myDonationsPanel, null);
		myDonationsPanel.setLayout(null);
		
		DefaultTableModel myDonationsTablee = new NonEditableTableModel();
		String[] columnNamesDonation = new String[] {
			    "No", "Category", "Subcategory", "Feature1", "Feature2", "Condition", 
			    "Quantity", "Date", "Status", "Donor"};
		myDonationsTablee.setColumnIdentifiers(columnNamesDonation);
		ArrayList<Object[]> lst = Admin.getDonationsbyDonor(user);
		for (Object[] row : lst) {
			myDonationsTablee.addRow(row);
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 579, 277);
		myDonationsPanel.add(scrollPane);
		myDonationsTable = new JTable(myDonationsTablee);
		myDonationsTable.setFocusable(false);
		scrollPane.setViewportView(myDonationsTable);
		myDonationsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		JPanel myRequestsPanel = new JPanel();
		tabbedPane.addTab("My Requests", null, myRequestsPanel, null);
		myRequestsPanel.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 579, 277);
		myRequestsPanel.add(scrollPane_1);
		
		DefaultTableModel myRequestsTablee = new NonEditableTableModel();
		String[] columnNamesRequest = new String[] {
			    "No", "Category", "Subcategory", "Feature1", "Feature2", "Condition", 
			    "Quantity", "Date", "Status", "Recipient"};
		myRequestsTablee.setColumnIdentifiers(columnNamesRequest);
		ArrayList<Object[]> liste = Admin.getRequestsbyDonor(user);
		for (Object[] row : liste) {
			myRequestsTablee.addRow(row);
		}
		myRequestsTable = new JTable(myRequestsTablee);
		myRequestsTable.setFocusable(false);
		scrollPane_1.setViewportView(myRequestsTable);
		myRequestsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		
		JButton completeButton = new JButton("Complete");
		completeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tabbedPane.getSelectedIndex() == 0) {
					int row = myDonationsTable.getSelectedRow();
					if("Active".equals(myDonationsTable.getValueAt(row, 8)) || "In progress".equals(myDonationsTable.getValueAt(row, 8))) {
						Long nmb = (Long) myDonationsTable.getValueAt(row, 0);
						try {
							Admin.completeItem(nmb);
							Message.showMsg("Donation completed.");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else if ("Completed".equals(myDonationsTable.getValueAt(row, 8))) {
						Message.showMsg("Donation completed already.");
					}
					else {
						Message.showMsg("Donation cancelled already.");
					}
					
				}
				else if(tabbedPane.getSelectedIndex() == 1) {
					int row = myRequestsTable.getSelectedRow();
					if("Active".equals(myRequestsTable.getValueAt(row, 8)) || "In progress".equals(myRequestsTable.getValueAt(row, 8))) {
						Long nmb = (Long) myRequestsTable.getValueAt(row, 0);
						try {
							Admin.completeItem(nmb);
							Message.showMsg("Donation completed.");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else if ("Completed".equals(myRequestsTable.getValueAt(row, 8))) {
						Message.showMsg("Donation completed already.");
					}
					else {
						Message.showMsg("Donation cancelled already.");
					}
				}
				
			}
		});
		completeButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		completeButton.setBounds(0, 393, 109, 29);
		contentPane.add(completeButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tabbedPane.getSelectedIndex() == 0) {
					int row = myDonationsTable.getSelectedRow();
					if("Active".equals(myDonationsTable.getValueAt(row, 8)) || "In progress".equals(myDonationsTable.getValueAt(row, 8))) {
						Long nmb = (Long) myDonationsTable.getValueAt(row, 0);
						try {
							Admin.cancelItem(nmb);
							Message.showMsg("Donation canceled.");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else if ("Completed".equals(myDonationsTable.getValueAt(row, 8))) {
						Message.showMsg("Donation completed already.");
					}
					else {
						Message.showMsg("Donation cancelled already.");
					}
					
				}
				else if(tabbedPane.getSelectedIndex() == 1) {
					int row = myRequestsTable.getSelectedRow();
					if("Active".equals(myRequestsTable.getValueAt(row, 8)) || "In progress".equals(myRequestsTable.getValueAt(row, 8))) {
						Long nmb = (Long) myRequestsTable.getValueAt(row, 0);
						try {
							Admin.cancelItem(nmb);
							Message.showMsg("Donation canceled.");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else if ("Completed".equals(myRequestsTable.getValueAt(row, 8))) {
						Message.showMsg("Donation completed already.");
					}
					else {
						Message.showMsg("Donation cancelled already.");
					}
				}
			}
		});
		cancelButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		cancelButton.setBounds(485, 393, 109, 29);
		contentPane.add(cancelButton);
		
		JLabel dynamicUsernameLabel = new JLabel(user.getUsername());
		dynamicUsernameLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		dynamicUsernameLabel.setBounds(101, 9, 103, 20);
		contentPane.add(dynamicUsernameLabel);
		
		JLabel dyanimcEmailLabel = new JLabel(user.getEmail());
		dyanimcEmailLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		dyanimcEmailLabel.setLocation(101, 41);
		dyanimcEmailLabel.setSize(user.getEmail().length() * 10, 20);
		contentPane.add(dyanimcEmailLabel);
	}
}
