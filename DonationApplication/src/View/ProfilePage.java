package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Helper.DataBase;
import Helper.Message;
import Helper.MyComparator;
import Helper.NonEditableTableModel;
import Helper.SetColumnWidth;
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
import java.util.Comparator;

import javax.swing.DefaultRowSorter;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.JavaBean;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ProfilePage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static User user = new User();
	private DefaultTableModel myDonationsTablee;
	private JTable myDonationsTable;
	private DefaultTableModel myRequestsTablee;
	private JTable myRequestsTable;
	static DataBase con = new DataBase();
	public static Boolean changePasswordPageControl;
	private JTable inProgressTable;
	private DefaultTableModel inProgressTablee;
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
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				UserPage.profilePageControl = true;
			}
			public void windowOpened(WindowEvent e) {
				ProfilePage.changePasswordPageControl = true;
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 750);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(166, 143, 151));
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
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 128, 994, 571);
		contentPane.add(tabbedPane);
		
		myDonationsTablee = new NonEditableTableModel();
		String[] columnNamesDonation = new String[] {
			    "No", "Category", "Subcategory", "Feature1", "Feature2", "Condition", 
			    "Quantity", "Date", "Status", "Donor", "Recipient"};
		myDonationsTablee.setColumnIdentifiers(columnNamesDonation);
		ArrayList<Object[]> lst = Admin.getDonationsbyUser(user);
		for (Object[] row : lst) {
			myDonationsTablee.addRow(row);
		}
		
		JPanel myDonationsPanel = new JPanel();
		myDonationsPanel.setBackground(new Color(166, 143, 151));
		tabbedPane.addTab("My Donations", null, myDonationsPanel, null);
		myDonationsPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(166, 143, 151));
		scrollPane.setBounds(0, 0, 973, 552);
		myDonationsPanel.add(scrollPane);
		myDonationsTable = new JTable(myDonationsTablee);
		myDonationsTable.setBackground(new Color(255, 255, 255));
		myDonationsTable.setFocusable(false);
		myDonationsTable.setAutoCreateRowSorter(true);
		
		SetColumnWidth.columnWidth(myDonationsTable, 90);
		myDonationsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		DefaultRowSorter<?, ?> donationsTableSorter = (DefaultRowSorter<?, ?>) myDonationsTable.getRowSorter();
		donationsTableSorter.setComparator(6, MyComparator.compInteger);
		scrollPane.setViewportView(myDonationsTable);
		
		
		JPanel myRequestsPanel = new JPanel();
		myRequestsPanel.setBackground(new Color(166, 143, 151));
		tabbedPane.addTab("My Requests", null, myRequestsPanel, null);
		myRequestsPanel.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 973, 494);
		myRequestsPanel.add(scrollPane_1);

		
		myRequestsTablee = new NonEditableTableModel();
		String[] columnNamesRequest = new String[] {
			    "No", "Category", "Subcategory", "Feature1", "Feature2", "Condition", 
			    "Quantity", "Date", "Status", "Recipient", "Donor"};
		myRequestsTablee.setColumnIdentifiers(columnNamesRequest);
		ArrayList<Object[]> liste = Admin.getRequestsbyUser(user);
		for (Object[] row : liste) {
			myRequestsTablee.addRow(row);
		}
		myRequestsTable = new JTable(myRequestsTablee);
		myRequestsTable.setFocusable(false);
		myRequestsTable.setAutoCreateRowSorter(true);
		
		DefaultRowSorter<?, ?> requestsTableSorter = (DefaultRowSorter<?, ?>) myDonationsTable.getRowSorter();
		requestsTableSorter.setComparator(6, MyComparator.compInteger);
		
		SetColumnWidth.columnWidth(myRequestsTable, 90);
		myRequestsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		scrollPane_1.setViewportView(myRequestsTable);
		
		
		
		
		
		JButton evaluateButton = new JButton("Evaluate");
		evaluateButton.setBounds(0, 496, 112, 29);
		myRequestsPanel.add(evaluateButton);
		evaluateButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = myRequestsTable.getSelectedRow();
				if(row > -1) {
					if("Completed".equals(myRequestsTable.getValueAt(row, 8))) {
						Long number = (Long) myRequestsTable.getValueAt(row, 0);
						String username = (String) myRequestsTable.getValueAt(row, 10);
						
						try {
							if(Admin.checkIsEvaluated(number)) {
								Message.showMsg("This donation is already evaluated.");
							}
							else {
								Integer[] lst = {5, 4, 3, 2, 1, 0};
								Integer chosen = JOptionPane.showInternalOptionDialog(null, "Please evaluate the donation in range 0 - 5", "EVAULATION WINDOW", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, lst, null);
								Integer value;
								if (chosen > -1) {
									value = lst[chosen];
								}
								else {
									value = 0;
								}
								Integer evaluateCount = Admin.getEvaluateCount(username);
								Float point = Admin.getUserPoint(username);
								Float calculated = ((evaluateCount * point) + value ) / (evaluateCount + 1);
								
								Admin.updateIsEvaluated(number);
								Admin.updateUserPoint(username, calculated);
								
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else {
						Message.showMsg("Donation hasn't completed.");
					}
				}
		}});
		evaluateButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		
		JPanel inProgressPanel = new JPanel();
		inProgressPanel.setBackground(new Color(166, 143, 151));
		tabbedPane.addTab("In Progress", null, inProgressPanel, null);
		inProgressPanel.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(0, 0, 973, 493);
		inProgressPanel.add(scrollPane_2);
		
		inProgressTablee = new NonEditableTableModel();
		String[] columnNamesInProgress = new String[] {
			    "No", "Category", "Subcategory", "Feature1", "Feature2", "Condition", 
			    "Quantity", "Date", "Status", "Donor", "Recipient", "Address", "Cargo Number"};
		inProgressTablee.setColumnIdentifiers(columnNamesInProgress);
		ArrayList<Object[]> lste = Admin.getInProgressbyUser(user);
		for (Object[] row : lste) {
			inProgressTablee.addRow(row);
		}
		
		inProgressTable = new JTable(inProgressTablee);
		inProgressTable.setFocusable(false);
		inProgressTable.setAutoCreateRowSorter(true);
		
		DefaultRowSorter<?, ?> inProgressTableSorter = (DefaultRowSorter<?, ?>) myDonationsTable.getRowSorter();
		inProgressTableSorter.setComparator(6, MyComparator.compInteger);
		
		SetColumnWidth.columnWidth(inProgressTable, 90);
		inProgressTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		scrollPane_2.setViewportView(inProgressTable);
		
		JButton shipButton = new JButton("Ship");
		shipButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = inProgressTable.getSelectedRow();
				if (row > -1) {
					Long number = (Long) inProgressTable.getValueAt(row, 0);
					String status = (String) inProgressTable.getValueAt(row, 8);
					String donor = (String) inProgressTable.getValueAt(row, 9);
					String recipient = (String) inProgressTable.getValueAt(row, 10);
					if (donor.equals(user.getUsername())) {
						if(status.equals("In cargo")) {
							Message.showMsg("It is already shipped.");
						}
						else {
							String pane = JOptionPane.showInputDialog(null, "Please ente the cargo number", "Cargo Number", JOptionPane.PLAIN_MESSAGE);
							if(pane != null) {
								try {
									Message.showMsg("Shipped successfully.");
									Admin.editDonationCargoNumber(pane, number);
									Admin.shipItem(number);
									updateInProgressTable();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						}
					}
					else if(recipient.equals(user.getUsername())) {
						Message.showMsg("Since you are recipient you cannot ship the donation.");
					}
				}
			}
		});
		shipButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		shipButton.setBounds(0, 496, 117, 29);
		inProgressPanel.add(shipButton);
		
		
		JButton completeButton = new JButton("Complete");
		completeButton.setForeground(new Color(0, 0, 0));
		completeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tabbedPane.getSelectedIndex() == 0 /* MY DONATIONS */) {
					int row = myDonationsTable.getSelectedRow();
					if(row > -1 && "Active".equals(myDonationsTable.getValueAt(row, 8)) || "Ongoing".equals(myDonationsTable.getValueAt(row, 8))) {
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
				else if(tabbedPane.getSelectedIndex() == 1 /* MY REQUESTS */) {
					int row = myRequestsTable.getSelectedRow();
					if(row > -1 && "Active".equals(myRequestsTable.getValueAt(row, 8)) || "Ongoing".equals(myRequestsTable.getValueAt(row, 8))) {
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
				else if(tabbedPane.getSelectedIndex() == 2 /* IN PROGRESS */) {
					int row = inProgressTable.getSelectedRow();
					Long nmb = (Long) inProgressTable.getValueAt(row, 0);
					if(row > -1 && "Waiting".equals(inProgressTable.getValueAt(row, 8))) {
						if(user.getUsername().equals(inProgressTable.getValueAt(row, 9))) { //IF USER IS DONOR
							Message.showMsg("You have to ship the item to complete it or you can cancel it.");
						}
						else if(user.getUsername().equals(inProgressTable.getValueAt(row, 10))) { //IF USER IS RECIPIENT
							try {
								Admin.completeItem(nmb);
								Message.showMsg("Donation completed.");
								updateInProgressTable();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
					else if(row > -1 && "In cargo".equals(inProgressTable.getValueAt(row, 8))) {
						if(user.getUsername().equals(inProgressTable.getValueAt(row, 9))) { //IF USER IS DONOR
							Message.showMsg("When recipient got the donation he/she can mark as completed.");
						}
						else if(user.getUsername().equals(inProgressTable.getValueAt(row, 10))) { //IF USER IS RECIPIENT
							try {
								Admin.completeItem(nmb);
								Message.showMsg("Donation completed.");
								updateInProgressTable();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						
					}
				}
				
			}
		});
		completeButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		completeButton.setBounds(10, 687, 109, 29);
		contentPane.add(completeButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setForeground(new Color(0, 0, 0));
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tabbedPane.getSelectedIndex() == 0 /* MY DONATIONS */) {
					int row = myDonationsTable.getSelectedRow();
					if("Active".equals(myDonationsTable.getValueAt(row, 8)) || "Ongoing".equals(myDonationsTable.getValueAt(row, 8))) {
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
				else if(tabbedPane.getSelectedIndex() == 1 /* MY REQUESTS */) {
					int row = myRequestsTable.getSelectedRow();
					if("Active".equals(myRequestsTable.getValueAt(row, 8)) || "Ongoing".equals(myRequestsTable.getValueAt(row, 8))) {
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
				else if(tabbedPane.getSelectedIndex() == 2 /* IN PROGRESS */) {
					int row = inProgressTable.getSelectedRow();
					Long nmb = (Long) inProgressTable.getValueAt(row, 0);
					if(row > -1 && "In cargo".equals(inProgressTable.getValueAt(row, 8))) {
						Message.showMsg("Since it is shipped you cannot cancel it.");
					}
					else if(row > -1 && "Waiting".equals(inProgressTable.getValueAt(row, 8))) {
						try {
							Admin.cancelItem(nmb);
							Message.showMsg("Donation canceled.");
							updateInProgressTable();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		cancelButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		cancelButton.setBounds(885, 687, 109, 29);
		contentPane.add(cancelButton);
		
		JButton changePasswordButton = new JButton("Change Password/Address");
		changePasswordButton.setForeground(new Color(0, 0, 0));
		changePasswordButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(ProfilePage.changePasswordPageControl) {
					ChangePasswordPage p = new ChangePasswordPage(user);
					p.setVisible(true);
					ProfilePage.changePasswordPageControl = false;
				}
				
			}
		});
		changePasswordButton.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		changePasswordButton.setBounds(0, 102, 237, 29);
		contentPane.add(changePasswordButton);
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
	public void updateInProgressTable() throws SQLException {
		NonEditableTableModel clear = (NonEditableTableModel) inProgressTable.getModel();
		clear.setRowCount(0);
		ArrayList<Object[]> lst = Admin.getInProgressbyUser(user);
		for (Object[] row : lst) {
			inProgressTablee.addRow(row);
		}
	}
}

