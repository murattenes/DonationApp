package View;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Helper.Message;
import Helper.NonEditableTableModel;
import Model.Admin;
import Model.User;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;

public class UserPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultTableModel poolTablee;
	private DefaultTableModel myDonationsTablee;
	static User user = new User();
	private JTable poolTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserPage frame = new UserPage(user);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param d 
	 * @throws SQLException 
	 */
	
	
	public UserPage(User user) throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 70, 750, 402);
		contentPane.add(tabbedPane);
		
		JPanel poolPanel = new JPanel();
		tabbedPane.addTab("Pool", null, poolPanel, null);
		
		poolTablee = new NonEditableTableModel();
		String[] columnNames = new String[] {
			    "No", "Category", "Subcategory", "Feature1", "Feature2", "Condition", 
			    "Quantity", "Date", "Status", "Donor", "Recipient"};
		poolTablee.setColumnIdentifiers(columnNames);
		ArrayList<Object[]> lst = Admin.getDonations();
		for (Object[] row : lst) {
			poolTablee.addRow(row);
		}
		poolPanel.setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 729, 330);
		poolPanel.add(scrollPane);
		poolTable = new JTable(poolTablee);
		poolTable.setFocusable(false);
		scrollPane.setViewportView(poolTable);
		poolTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		
		JLabel welcomeLabel = new JLabel();
		welcomeLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		welcomeLabel.setBounds(108, 0, 199, 29);
		welcomeLabel.setText("Welcome " + user.getName());
		contentPane.add(welcomeLabel);
		
		JButton logoutButton = new JButton("Logout");
		logoutButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginPage p = new LoginPage();
				p.setVisible(true);
				dispose();
			}
		});
		logoutButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		logoutButton.setBounds(633, 0, 117, 29);
		contentPane.add(logoutButton);
		
		JLabel photoLabel = new JLabel("PHOTO");
		photoLabel.setBounds(6, 0, 90, 90);
		contentPane.add(photoLabel);
		
		JButton donateRequestButton = new JButton("Donate/Request");
		donateRequestButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DonateRequestPage p = new DonateRequestPage(user);
				p.setVisible(true);				
			}
		});
		donateRequestButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		donateRequestButton.setBounds(577, 41, 167, 29);
		contentPane.add(donateRequestButton);
		
		JButton profileButton = new JButton("Profile");
		profileButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ProfilePage p;
				try {
					p = new ProfilePage(user);
					p.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		profileButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		profileButton.setBounds(108, 29, 117, 29);
		contentPane.add(profileButton);
		
		
		JButton donateButton = new JButton("Donate");
		donateButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		donateButton.setBounds(0, 327, 102, 29);
		poolPanel.add(donateButton);
		
		JButton refreshButton = new JButton("Refresh");
		refreshButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					updatePool();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		refreshButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		refreshButton.setBounds(627, 328, 102, 29);
		poolPanel.add(refreshButton);
	}
	
	public void updatePool() throws SQLException {
		NonEditableTableModel clear = (NonEditableTableModel) poolTable.getModel();
		clear.setRowCount(0);
		ArrayList<Object[]> lst = Admin.getDonations();
		for (Object[] row : lst) {
			poolTablee.addRow(row);
		}
		
	}
}
