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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Canvas;
import java.awt.Color;

public class ProfilePage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static User user = new User();
	private DefaultTableModel myDonationsTablee;
	private JTable myDonationsTable;
	private DefaultTableModel myRequestsTablee;
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
		setTitle("PROFILE PAGE");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 750);
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
		changePasswordButton.setBounds(819, 3, 175, 29);
		contentPane.add(changePasswordButton);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 101, 994, 574);
		contentPane.add(tabbedPane);
		
		myDonationsTablee = new NonEditableTableModel();
		String[] columnNamesDonation = new String[] {
			    "No", "Category", "Subcategory", "Feature1", "Feature2", "Condition", 
			    "Quantity", "Date", "Status", "Donor"};
		myDonationsTablee.setColumnIdentifiers(columnNamesDonation);
		ArrayList<Object[]> lst = Admin.getDonationsbyUser(user);
		for (Object[] row : lst) {
			myDonationsTablee.addRow(row);
		}
		
		JPanel myRequestsPanel = new JPanel();
		tabbedPane.addTab("My Requests", null, myRequestsPanel, null);
		myRequestsPanel.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 973, 559);
		myRequestsPanel.add(scrollPane_1);
		
		myRequestsTablee = new NonEditableTableModel();
		String[] columnNamesRequest = new String[] {
			    "No", "Category", "Subcategory", "Feature1", "Feature2", "Condition", 
			    "Quantity", "Date", "Status", "Recipient"};
		myRequestsTablee.setColumnIdentifiers(columnNamesRequest);
		ArrayList<Object[]> liste = Admin.getRequestsbyUser(user);
		for (Object[] row : liste) {
			myRequestsTablee.addRow(row);
		}
		myRequestsTable = new JTable(myRequestsTablee);
		myRequestsTable.setFocusable(false);
		scrollPane_1.setViewportView(myRequestsTable);
		//myRequestsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		JPanel myDonationsPanel = new JPanel();
		tabbedPane.addTab("My Donations", null, myDonationsPanel, null);
		myDonationsPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 973, 559);
		myDonationsPanel.add(scrollPane);
		myDonationsTable = new JTable(myDonationsTablee);
		myDonationsTable.setFocusable(false);
		scrollPane.setViewportView(myDonationsTable);
		//myDonationsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		
		JButton completeButton = new JButton("Complete");
		completeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tabbedPane.getSelectedIndex() == 1 /* MY DONATIONS */) {
					int row = myDonationsTable.getSelectedRow();
					if(row > -1 && "Active".equals(myDonationsTable.getValueAt(row, 8)) || "In progress".equals(myDonationsTable.getValueAt(row, 8))) {
						Long nmb = (Long) myDonationsTable.getValueAt(row, 0);
						try {
							Admin.completeItem(nmb);
							updateMyDonationsTable();
							Message.showMsg("Donation completed.");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else if (row > -1 && "Completed".equals(myDonationsTable.getValueAt(row, 8))) {
						Message.showMsg("Donation completed already.");
					}
					else {
						Message.showMsg("Donation cancelled already.");
					}
					
				}
				else if(tabbedPane.getSelectedIndex() == 0 /* MY REQUESTS */) {
					int row = myRequestsTable.getSelectedRow();
					if(row > -1 && "Active".equals(myRequestsTable.getValueAt(row, 8)) || "In progress".equals(myRequestsTable.getValueAt(row, 8))) {
						Long nmb = (Long) myRequestsTable.getValueAt(row, 0);
						try {
							Admin.completeItem(nmb);
							updateMyRequestsTable();
							Message.showMsg("Donation completed.");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else if (row > -1 && "Completed".equals(myRequestsTable.getValueAt(row, 8))) {
						Message.showMsg("Donation completed already.");
					}
					else {
						Message.showMsg("Donation cancelled already.");
					}
				}
				
			}
		});
		completeButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		completeButton.setBounds(6, 687, 109, 29);
		contentPane.add(completeButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tabbedPane.getSelectedIndex() == 1 /* MY DONATIONS */) {
					int row = myDonationsTable.getSelectedRow();
					if("Active".equals(myDonationsTable.getValueAt(row, 8)) || "In progress".equals(myDonationsTable.getValueAt(row, 8))) {
						Long nmb = (Long) myDonationsTable.getValueAt(row, 0);
						try {
							Admin.cancelItem(nmb);
							updateMyDonationsTable();
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
				else if(tabbedPane.getSelectedIndex() == 0 /* MY REQUESTS */) {
					int row = myRequestsTable.getSelectedRow();
					if("Active".equals(myRequestsTable.getValueAt(row, 8)) || "In progress".equals(myRequestsTable.getValueAt(row, 8))) {
						Long nmb = (Long) myRequestsTable.getValueAt(row, 0);
						try {
							Admin.cancelItem(nmb);
							updateMyRequestsTable();
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
		cancelButton.setBounds(885, 687, 109, 29);
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
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		lblAddress.setBounds(6, 70, 83, 20);
		contentPane.add(lblAddress);
		
		JLabel dynamicAddressLabel = new JLabel(user.getAddress());
		dynamicAddressLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		dynamicAddressLabel.setLocation(101, 73);
		dynamicAddressLabel.setSize(user.getAddress().length() * 10, 20);
		contentPane.add(dynamicAddressLabel);
	}
	public void updateMyDonationsTable() throws SQLException {
		NonEditableTableModel clear = (NonEditableTableModel) myDonationsTable.getModel();
		clear.setRowCount(0);
		ArrayList<Object[]> lst = Admin.getDonationsbyUser(user);
		for (Object[] row : lst) {
			myDonationsTablee.addRow(row);
		}
	}
	public void updateMyRequestsTable() throws SQLException {
		NonEditableTableModel clear = (NonEditableTableModel) myRequestsTable.getModel();
		clear.setRowCount(0);
		ArrayList<Object[]> lst = Admin.getRequestsbyUser(user);
		for (Object[] row : lst) {
			myRequestsTablee.addRow(row);
		}
	}
}

