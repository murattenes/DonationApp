package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Helper.NonEditableTableModel;
import Model.Admin;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class AdminPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static Admin admin = new Admin();
	private DefaultTableModel donationsTablee;
	private DefaultTableModel usersTablee;
	private JTable donationsTable;
	private JTable usersTable;
	private JTextField dynamicUsernameTextField;
	private JTextField dynamicNoTextField;

	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPage frame = new AdminPage(admin);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ParseException 
	 */
	public AdminPage(Admin admin) throws SQLException, ParseException {
		setTitle("ADMIN PAGE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel photoLabel = new JLabel("PHOTO");
		photoLabel.setBounds(0, 0, 81, 81);
		contentPane.add(photoLabel);
		
		JLabel welcomeLAbel = new JLabel("New label");
		welcomeLAbel.setText("Welcome " + admin.getName());
		welcomeLAbel.setSize(admin.getName().length() * 20, 20);
		welcomeLAbel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		welcomeLAbel.setLocation(93, 0);
		contentPane.add(welcomeLAbel);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 106, 1000, 616);
		contentPane.add(tabbedPane);
		
		JPanel donationsPanel = new JPanel();
		tabbedPane.addTab("All Donations", null, donationsPanel, null);
		donationsPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 979, 535);
		donationsPanel.add(scrollPane);
		
		
		
		dynamicNoTextField = new JTextField();
		dynamicNoTextField.setEnabled(false);
		dynamicNoTextField.setEditable(false);
		dynamicNoTextField.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		dynamicNoTextField.setBounds(0, 538, 130, 26);
		donationsPanel.add(dynamicNoTextField);
		dynamicNoTextField.setColumns(10);
		
		
		donationsTablee = new NonEditableTableModel();
		String[] donationsColumnNames = new String[] {
			    "No", "Category", "Subcategory", "Feature1", "Feature2", "Condition", 
			    "Quantity", "Date", "Status", "Donor", "Recipient"};
		donationsTablee.setColumnIdentifiers(donationsColumnNames);
		ArrayList<Object[]> donationsLst = Admin.getAllDonations();
		for (Object[] row : donationsLst) {
			donationsTablee.addRow(row);
		}
		

		
		donationsTable = new JTable(donationsTablee);
		donationsTable.setFocusable(false);
		donationsTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = donationsTable.getSelectedRow();
				if(row > -1) {
					Long no = (Long) donationsTable.getValueAt(row, 0);
					dynamicNoTextField.setText(no + "");
				}
				
			}
		});
		scrollPane.setViewportView(donationsTable);
		
		
		JButton completeButton = new JButton("Complete");
		completeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = donationsTable.getSelectedRow();
				if(row > -1) {
					Long number = (Long) donationsTable.getValueAt(row, 0);
					try {
						Admin.completeItem(number);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		completeButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		completeButton.setBounds(142, 539, 121, 29);
		donationsPanel.add(completeButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = donationsTable.getSelectedRow();
				if(row > -1) {
					Long number = (Long) donationsTable.getValueAt(row, 0);
					try {
						Admin.cancelItem(number);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		cancelButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		cancelButton.setBounds(275, 539, 121, 29);
		donationsPanel.add(cancelButton);
		
		JPanel usersPanel = new JPanel();
		tabbedPane.addTab("All users", null, usersPanel, null);
		usersPanel.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 979, 535);
		usersPanel.add(scrollPane_1);
		
		dynamicUsernameTextField = new JTextField();
		dynamicUsernameTextField.setEnabled(false);
		dynamicUsernameTextField.setEditable(false);
		dynamicUsernameTextField.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		dynamicUsernameTextField.setBounds(0, 538, 130, 26);
		usersPanel.add(dynamicUsernameTextField);
		dynamicUsernameTextField.setColumns(10);
		
		
		usersTablee = new NonEditableTableModel();
		String[] usersColumnNames = new String[] {
			    "Name", "Surname", "Username", "E-mail", "Type", "Status", "Address", "Registiration Date", "Last Login", "Completed Don.", "Completed Req."};
		usersTablee.setColumnIdentifiers(usersColumnNames);
		ArrayList<Object[]> usersLst = Admin.getAllUsers();
		for (Object[] row : usersLst) {
			usersTablee.addRow(row);
		}
		usersTable = new JTable(usersTablee);
		usersTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = usersTable.getSelectedRow();
				if(row > -1) {
					String txt = (String) usersTable.getValueAt(row, 2);
					dynamicUsernameTextField.setText(txt);
				}
				
			}
		});
		usersTable.setFocusable(false);
		scrollPane_1.setViewportView(usersTable);
		
		

		
		JButton inactiveButton = new JButton("Make Inactive");
		inactiveButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = usersTable.getSelectedRow();
				if (row > -1) {
					try {
						String username = (String) usersTable.getValueAt(row, 2);
						Admin.makeInactiveUser(username);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		inactiveButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		inactiveButton.setBounds(142, 539, 155, 29);
		usersPanel.add(inactiveButton);
		
		JButton activeButton = new JButton("Make Active");
		activeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = usersTable.getSelectedRow();
				if (row > -1) {
					try {
						String username = (String) usersTable.getValueAt(row, 2);
						Admin.makeActiveUser(username);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		activeButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		activeButton.setBounds(309, 539, 155, 29);
		usersPanel.add(activeButton);
				
		
		
		
	}
}
