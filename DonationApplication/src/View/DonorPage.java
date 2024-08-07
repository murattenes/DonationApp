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
import Model.Admin;
import Model.Donor;

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

public class DonorPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultTableModel donationsTablee;
	private DefaultTableModel myDonationsTablee;
	private JTable donationsTable;
	static Donor donor = new Donor();
	private JTable myDonationsTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DonorPage frame = new DonorPage(donor);
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
	public DonorPage(Donor donor) throws SQLException {
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
		poolPanel.setLayout(null);
		
		JScrollPane donationsScrollPane = new JScrollPane();
		donationsScrollPane.setBounds(0, 0, 729, 356);
		poolPanel.add(donationsScrollPane);
		
		
		
		donationsTablee = new DefaultTableModel();
		String[] columnNames = new String[8];
		columnNames[0] = "Id";
		columnNames[1] = "Category";
		columnNames[2] = "Subcategory";
		columnNames[3] = "Param1";
		columnNames[4] = "Param2";
		columnNames[5] = "Condition";
		columnNames[6] = "Donor";
		columnNames[7] = "Recipient";
		ArrayList<Object[]> lst = Admin.getDonations();
		donationsTablee.setColumnIdentifiers(columnNames);
		for (int i = 0; i < Admin.getDonations().size(); i++) {
			donationsTablee.addRow(lst.get(i));
		}
		
		donationsTable = new JTable(donationsTablee);
		donationsScrollPane.setViewportView(donationsTable);
		
		JPanel makeDonationPanel = new JPanel();
		tabbedPane.addTab("Make Donation", null, makeDonationPanel, null);
		makeDonationPanel.setLayout(null);
		
		JLabel categoryLabel = new JLabel("Category:");
		categoryLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		categoryLabel.setBounds(6, 6, 75, 20);
		makeDonationPanel.add(categoryLabel);
		

		JLabel subcategoryLabel = new JLabel("Subcategory:");
		subcategoryLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		subcategoryLabel.setBounds(6, 56, 101, 20);
		makeDonationPanel.add(subcategoryLabel);
		
		
		JLabel param1Label = new JLabel("Param1:");
		param1Label.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		param1Label.setBounds(6, 106, 64, 20);
		makeDonationPanel.add(param1Label);
		
		JLabel param2Label = new JLabel("Param2:");
		param2Label.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		param2Label.setBounds(6, 156, 64, 20);
		makeDonationPanel.add(param2Label);
		
		JLabel lblCondition = new JLabel("Condition:");
		lblCondition.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		lblCondition.setBounds(6, 206, 82, 20);
		makeDonationPanel.add(lblCondition);
		
		JComboBox<?> param1ComboBox = new JComboBox();
		param1ComboBox.setBounds(119, 105, 101, 27);
		makeDonationPanel.add(param1ComboBox);
		
		JComboBox<?> param2ComboBox = new JComboBox();
		param2ComboBox.setBounds(119, 155, 101, 27);
		makeDonationPanel.add(param2ComboBox);
		
		JComboBox<?> conditionComboBox = new JComboBox();
		conditionComboBox.setModel(new DefaultComboBoxModel(new String[] {"", "New", "Good", "Okay"}));
		conditionComboBox.setBounds(119, 205, 101, 27);
		makeDonationPanel.add(conditionComboBox);
		
		JComboBox subcategoryComboBox = new JComboBox();
		subcategoryComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (subcategoryComboBox.getSelectedIndex() < 1) {
					param1ComboBox.setSelectedIndex(0);
					param2ComboBox.setSelectedIndex(0);
					conditionComboBox.setSelectedIndex(0);
				}
				if ("Notebook".equals(subcategoryComboBox.getSelectedItem())) {
					param1ComboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Plaid", "Striped"}));
					param2ComboBox.setModel(new DefaultComboBoxModel(new String[] {"", "60 pages", "90 pages", "120 pages"}));
				}
				if ("Book".equals(subcategoryComboBox.getSelectedItem())) {
					param1ComboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Science Fiction", "Whodunit", "History", "Romance"}));
					param2ComboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Hardback", "Paperback"}));
				}
				String[] clothes = {"Coat", "Sweatshirt","T-shirt","Pants", "Shorts", "Shoe"};
				List lst = Arrays.asList(clothes);
				if (lst.contains(subcategoryComboBox.getSelectedItem())) {
					param1ComboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Men", "Women"}));
					if("Shoe".equals(subcategoryComboBox.getSelectedItem())) {
						param2ComboBox.setModel(new DefaultComboBoxModel(new String[] {"", "38", "39", "40", "41", "42", "43", "44"}));
					} else {
						param2ComboBox.setModel(new DefaultComboBoxModel(new String[] {"", "S", "M", "L", "XL"}));
					}
				}
				if ("Baby Formula".equals(subcategoryComboBox.getSelectedItem())) {
					param1ComboBox.setModel(new DefaultComboBoxModel(new String[] {"", "0-6 Months", "6-12 Months", "12-18 Months", "18-24 Months"}));
					param2ComboBox.setModel(new DefaultComboBoxModel(new String[] {"", "600 gram", "800 gram", "1000 gram"}));
				}
				if ("Feedstuff".equals(subcategoryComboBox.getSelectedItem())) {
					param1ComboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Dog", "Cat", "Bird", "Fish"}));
					param2ComboBox.setModel(new DefaultComboBoxModel(new String[] {"", "600 gram", "800 gram", "1000 gram"}));
				}
				if ("Cellphone".equals(subcategoryComboBox.getSelectedItem())) {
					param1ComboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Iphone", "Samsung", "Huawei"}));
					param2ComboBox.setModel(new DefaultComboBoxModel(new String[] {"", "With charger", "Without charger"}));
				}
				if ("PC".equals(subcategoryComboBox.getSelectedItem())) {
					param1ComboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Asus", "Acer", "Dell", "Huawei"}));
					param2ComboBox.setModel(new DefaultComboBoxModel(new String[] {"", "With charger", "Without charger"}));
				}
				if ("Carpet".equals(subcategoryComboBox.getSelectedItem())) {
					param1ComboBox.setModel(new DefaultComboBoxModel(new String[] {"", "100x150 cm", "150x230 cm", "200x300 cm"}));
					param2ComboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Polyester", "Woolen", "Acrylic"}));
				}
				if ("Table".equals(subcategoryComboBox.getSelectedItem())) {
					param1ComboBox.setModel(new DefaultComboBoxModel(new String[] {"", "4 Persons", "6 Persons", "8 Persons"}));
					param2ComboBox.setModel(new DefaultComboBoxModel(new String[] {"", "With seats", "Without seats"}));
				}
			}
		});
		subcategoryComboBox.setBounds(119, 55, 101, 27);
		makeDonationPanel.add(subcategoryComboBox);
		
		JComboBox<?> categoryComboBox = new JComboBox();
		categoryComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int chosen = categoryComboBox.getSelectedIndex();
				param1ComboBox.setSelectedIndex(-1);
				param2ComboBox.setSelectedIndex(-1);
				conditionComboBox.setSelectedIndex(-1);
				switch (chosen) {
					case 1:
						subcategoryComboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Notebook", "Book"}));
						break;
					case 2:
						subcategoryComboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Coat", "Sweatshirt","T-shirt","Pants", "Shorts","Shoe"}));
						break;
					case 3:
						subcategoryComboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Baby Formula", "Feedstuff"}));
						break;
					case 4:
						subcategoryComboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Cellphone", "PC"}));
						break;
					case 5:
						subcategoryComboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Carpet", "Table"}));
						break;
					default:
						subcategoryComboBox.setSelectedIndex(-1);
						break;
				}
			}
		});
		categoryComboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Education", "Clothing", "Food", "Electronic", "Housing"}));
		categoryComboBox.setBounds(119, 5, 101, 27);
		makeDonationPanel.add(categoryComboBox);
		
		JButton donateButton = new JButton("Donate");
		donateButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(categoryComboBox.getSelectedIndex() < 1 || subcategoryComboBox.getSelectedIndex() < 1 || param1ComboBox.getSelectedIndex() < 1 || param2ComboBox.getSelectedIndex() < 1 || conditionComboBox.getSelectedIndex() < 1 ) {
					Message.showMsg("fill");
				}else {
					try {
						Admin.addDonation(categoryComboBox.getSelectedItem().toString(), subcategoryComboBox.getSelectedItem().toString(), param1ComboBox.getSelectedItem().toString(), param2ComboBox.getSelectedItem().toString(), conditionComboBox.getSelectedItem().toString(), donor.getUsername(), "deneme2");
						updateDonationsTable();
						updateMyDonationsTable(donor);
						Message.showMsg("We got your donation.\n Thank you!");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
			}
		});
		donateButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		donateButton.setBounds(119, 255, 117, 29);
		makeDonationPanel.add(donateButton);
			
		
		JPanel myDonationsPanel = new JPanel();
		tabbedPane.addTab("My Donations", null, myDonationsPanel, null);
		myDonationsPanel.setLayout(null);
		
		JScrollPane myDonationsScrollPane = new JScrollPane();
		myDonationsScrollPane.setBounds(0, 0, 729, 356);
		myDonationsPanel.add(myDonationsScrollPane);
		
		myDonationsTablee = new DefaultTableModel();
		String[] myColumnNames = new String[7];
		myColumnNames[0] = "Id";
		myColumnNames[1] = "Category";
		myColumnNames[2] = "Subcategory";
		myColumnNames[3] = "Param1";
		myColumnNames[4] = "Param2";
		myColumnNames[5] = "Condition";
		myColumnNames[6] = "Donor";
		
		ArrayList<Object[]> myLst = Admin.getDonationsbyDonor(donor);
		myDonationsTablee.setColumnIdentifiers(myColumnNames);
		for (int i = 0; i < Admin.getDonationsbyDonor(donor).size(); i++) {
			myDonationsTablee.addRow(myLst.get(i));
		}
		myDonationsTable = new JTable(myDonationsTablee);
		myDonationsScrollPane.setViewportView(myDonationsTable);
		
		JPanel profilePanel = new JPanel();
		tabbedPane.addTab("Profile", null, profilePanel, null);
		
		JLabel profileNameLabel = new JLabel("Name:");
		profileNameLabel.setBounds(6, 6, 50, 20);
		profileNameLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		
		JLabel profileSurnameLabel = new JLabel("Surname:");
		profileSurnameLabel.setBounds(6, 34, 74, 20);
		profileSurnameLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		
		JLabel profileEmailLabel = new JLabel("E-mail:");
		profileEmailLabel.setBounds(6, 66, 57, 20);
		profileEmailLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		
		JLabel profileUsernameLabel = new JLabel("Username:");
		profileUsernameLabel.setBounds(6, 98, 83, 20);
		profileUsernameLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		
		JLabel profileNameLabelD = new JLabel(donor.getName());
		profileNameLabelD.setBounds(68, 6, donor.getName().length()*10, 20);
		profileNameLabelD.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		
		JLabel profileSurnameLabelD = new JLabel(donor.getSurname());
		profileSurnameLabelD.setBounds(92, 34, donor.getSurname().length()*10, 20);
		profileSurnameLabelD.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		
		JLabel profileEmailLabelD = new JLabel(donor.getEmail());
		profileEmailLabelD.setBounds(75, 66, donor.getEmail().length()*10, 20);
		profileEmailLabelD.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		
		JLabel profileUsernameLabelD = new JLabel(donor.getUsername());
		profileUsernameLabelD.setBounds(101, 98, donor.getUsername().length()*10, 20);
		profileUsernameLabelD.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		
		JButton profileChangeInformationsButton = new JButton("Change Informations");
		profileChangeInformationsButton.setBounds(506, 3, 217, 29);
		profileChangeInformationsButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ChangeInfoPage p = new ChangeInfoPage(donor);
				p.setVisible(true);
				
			}
		});
		profileChangeInformationsButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		profilePanel.setLayout(null);
		profilePanel.add(profileNameLabel);
		profilePanel.add(profileSurnameLabel);
		profilePanel.add(profileEmailLabel);
		profilePanel.add(profileUsernameLabel);
		profilePanel.add(profileNameLabelD);
		profilePanel.add(profileSurnameLabelD);
		profilePanel.add(profileEmailLabelD);
		profilePanel.add(profileUsernameLabelD);
		profilePanel.add(profileChangeInformationsButton);
		
		JButton profileChangePasswordButton = new JButton("Change Password");
		profileChangePasswordButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		profileChangePasswordButton.setBounds(506, 32, 217, 29);
		profilePanel.add(profileChangePasswordButton);
		
		JList list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"sdsdsd", "sdssdsd", "swww", "dddddd", "dddd"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setBounds(250, 196, 172, 97);
		profilePanel.add(list);
		
		JLabel welcomeLabel = new JLabel();
		welcomeLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		welcomeLabel.setBounds(0, 0, 199, 58);
		welcomeLabel.setText("Welcome " + donor.getName());
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
	}
	
	public void updateDonationsTable() throws SQLException {
		donationsTablee.setRowCount(0);
		ArrayList<Object[]> lst = Admin.getDonations();
		for (int i = 0; i < Admin.getDonations().size(); i++) {
			donationsTablee.addRow(lst.get(i));
		}
		
	}
	public void updateMyDonationsTable(Donor donor) throws SQLException {
		myDonationsTablee.setRowCount(0);
		ArrayList<Object[]> myLst = Admin.getDonationsbyDonor(donor);
		for (int i = 0; i < Admin.getDonationsbyDonor(donor).size(); i++) {
			myDonationsTablee.addRow(myLst.get(i));
		}
	}
}
