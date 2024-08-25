package View;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import Helper.Message;
import Helper.MyComparator;
import Helper.NonEditableTableModel;
import Model.Admin;
import Model.Donation;
import Model.User;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultRowSorter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;

public class UserPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultTableModel poolTablee;
	private DefaultTableModel myDonationsTablee;
	static User user = new User();
	private JTable poolTable;
	public static Boolean donateRequestPageControl;
	public static Boolean profilePageControl;

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
		setTitle("USER PAGE");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				UserPage.donateRequestPageControl = true;
				UserPage.profilePageControl = true;
			}
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		JPanel poolPanel = new JPanel();
		tabbedPane.addTab("Pool", null, poolPanel, null);
		
		poolTablee = new NonEditableTableModel();
		String[] columnNames = new String[] {
			    "No", "Category", "Subcategory", "Feature1", "Feature2", "Condition", 
			    "Quantity", "Date", "Status", "Donor", "Recipient", "Address"};
		poolTablee.setColumnIdentifiers(columnNames);
		ArrayList<Object[]> lst = Admin.getDonations();
		for (Object[] row : lst) {
			poolTablee.addRow(row);
		}
		

		poolPanel.setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 957, 525);
		poolPanel.add(scrollPane);
		poolTable = new JTable(poolTablee);
		poolTable.setFocusable(false);
		poolTable.setAutoCreateRowSorter(true);
		
		DefaultRowSorter<?, ?> sorter = (DefaultRowSorter<?, ?>) poolTable.getRowSorter();
		sorter.setComparator(6, MyComparator.compInteger);
		
		scrollPane.setViewportView(poolTable);
		//poolTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		
		JLabel welcomeLabel = new JLabel();
		welcomeLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		welcomeLabel.setText("Welcome " + user.getName());
		
		JButton logoutButton = new JButton("Log out");
		logoutButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginPage p = new LoginPage();
				p.setVisible(true);
				dispose();
			}
		});
		logoutButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		
		JLabel photoLabel = new JLabel("PHOTO");
		
		JButton donateRequestButton = new JButton("Donate/Request");
		donateRequestButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(UserPage.donateRequestPageControl) {
					DonateRequestPage p = new DonateRequestPage(user);
					p.setVisible(true);
					UserPage.donateRequestPageControl = false;
				}
				
				
								
			}
		});
		donateRequestButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		
		JButton profileButton = new JButton("Profile");
		profileButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(UserPage.profilePageControl) {
					try {
						ProfilePage p = new ProfilePage(user);
						p.setVisible(true);
						UserPage.profilePageControl = false;
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				
			}
		});
		profileButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		
		
		JButton donateButton = new JButton("Donate");
		donateButton.setOpaque(true);
		donateButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = poolTable.getSelectedRow();
				if (row > -1 && poolTable.getValueAt(row, 9) == null && !user.getUsername().equals(poolTable.getValueAt(row, 10))) {
					
					try {
						Long number = (Long) poolTable.getValueAt(row, 0);
						DonatePage p = new DonatePage(user, number);
						p.setVisible(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					
				}
				
			}
		});
		donateButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		donateButton.setBounds(0, 529, 102, 29);
		poolPanel.add(donateButton);
		
		
		JButton getButton = new JButton("Get");
		getButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = poolTable.getSelectedRow();
				if(row > -1 && poolTable.getValueAt(row, 10) == null && !user.getUsername().equals(poolTable.getValueAt(row, 9))) {
					try {
						Long number = (Long) poolTable.getValueAt(row, 0);
						GetPage p = new GetPage(user, number);
						p.setVisible(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
		});
		getButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		getButton.setBounds(114, 530, 102, 29);
		poolPanel.add(getButton);
		
		
		
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
		refreshButton.setBounds(849, 529, 102, 29);
		poolPanel.add(refreshButton);
		
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(6)
					.addComponent(photoLabel, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(welcomeLabel, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
						.addComponent(profileButton, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
					.addGap(519)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(donateRequestButton, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
						.addComponent(logoutButton, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 984, Short.MAX_VALUE)
					.addGap(6))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(photoLabel, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(welcomeLabel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
								.addComponent(logoutButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(profileButton)
								.addComponent(donateRequestButton))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
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
