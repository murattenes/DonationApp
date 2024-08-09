package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Model.User;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class ProfilePage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static User user = new User();
	private JTable myDonationsTable;
	private JTable myRequestsTable;
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
	 */
	public ProfilePage(User user) {
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
		changePasswordButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		changePasswordButton.setBounds(419, 3, 175, 29);
		contentPane.add(changePasswordButton);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 70, 600, 323);
		contentPane.add(tabbedPane);
		
		JPanel myDonationsPanel = new JPanel();
		tabbedPane.addTab("My Donations", null, myDonationsPanel, null);
		myDonationsPanel.setLayout(null);
		
		DefaultTableModel myDonationsTablee = new DefaultTableModel();
		String[] columnNames = new String[] {
			    "No", "Category", "Subcategory", "Feature1", "Feature2", "Condition", 
			    "Quantity", "Date", "Status", "Donor", "Recipient"};
		myDonationsTablee.setColumnIdentifiers(columnNames);
		
		myDonationsTable = new JTable(myDonationsTablee);
		myDonationsTable.setBounds(0, 0, 579, 277);
		myDonationsPanel.add(myDonationsTable);
		
		JPanel myRequestsPanel = new JPanel();
		tabbedPane.addTab("My Requests", null, myRequestsPanel, null);
		myRequestsPanel.setLayout(null);
		
		myRequestsTable = new JTable();
		myRequestsTable.setBounds(0, 0, 579, 277);
		myRequestsPanel.add(myRequestsTable);
		
		JButton btnNewButton = new JButton("CANCEL");
		btnNewButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnNewButton.setBounds(0, 393, 109, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("CANCEL");
		btnNewButton_1.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnNewButton_1.setBounds(121, 394, 109, 29);
		contentPane.add(btnNewButton_1);
	}
}
