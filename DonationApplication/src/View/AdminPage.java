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
import java.util.ArrayList;

import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static Admin admin = new Admin();
	private DefaultTableModel donationsTablee;
	private DefaultTableModel usersTablee;
	private JTable donationsTable;
	private JTable usersTable;
	private JTextField textField;

	
	

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
	 */
	public AdminPage(Admin admin) throws SQLException {
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
		
		donationsTablee = new NonEditableTableModel();
		String[] donationsColumnNames = new String[] {
			    "No", "Category", "Subcategory", "Feature1", "Feature2", "Condition", 
			    "Quantity", "Date", "Status", "Donor", "Recipient"};
		donationsTablee.setColumnIdentifiers(donationsColumnNames);
		ArrayList<Object[]> donationsLst = Admin.getAllDonations();
		for (Object[] row : donationsLst) {
			donationsTablee.addRow(row);
		}
		
		JPanel donationsPanel = new JPanel();
		tabbedPane.addTab("All Donations", null, donationsPanel, null);
		donationsPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 979, 570);
		donationsPanel.add(scrollPane);
		
		donationsTable = new JTable(donationsTablee);
		donationsTable.setFocusable(false);
		scrollPane.setViewportView(donationsTable);
		
		JPanel usersPanel = new JPanel();
		tabbedPane.addTab("All users", null, usersPanel, null);
		usersPanel.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 979, 535);
		usersPanel.add(scrollPane_1);
		
		usersTablee = new NonEditableTableModel();
		String[] usersColumnNames = new String[] {
			    "Name", "Surname", "Username", "E-mail", "Type", "Status", "Address", "Registiration Date", "Last Login", "Completed Don.", "Completed Req."};
		usersTablee.setColumnIdentifiers(usersColumnNames);
		ArrayList<Object[]> usersLst = Admin.getAllUsers();
		for (Object[] row : usersLst) {
			usersTablee.addRow(row);
		}
		usersTable = new JTable(usersTablee);
		usersTable.setFocusable(false);
		scrollPane_1.setViewportView(usersTable);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		textField.setBounds(0, 538, 130, 26);
		usersPanel.add(textField);
		textField.setColumns(10);
		
		
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
