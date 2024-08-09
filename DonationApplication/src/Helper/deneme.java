package Helper;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Model.Admin;
import Model.User;
import View.UserPage;

public class deneme {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();
		LocalDate nw = LocalDate.now();
		//System.out.println(now);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    Date date = new Date();  
	    //System.out.println(formatter.format(date));
	    
	    getDonationNumber();
	    
	    
		
	    
	    
	    /*
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
		*/
	    
	    
	    /*
	    
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
						updateMyDonationsTable(user);
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
		
		}
		
	*/
	    /*
	    if((usernameField.getText().equals(rs.getString("username")) || usernameField.getText().equals(rs.getString("email"))) && 
				new String(passwordField.getPassword()).equals(rs.getString("password"))) {
			if("User".equals(rs.getString("typename"))) {
				if("Active".equals(rs.getString("statusname"))) {
					User user = new User(rs.getString("name"), rs.getString("surname"), rs.getString("username"), rs.getString("email"), rs.getString("password"));
					UserPage p = new UserPage(user);
					p.setVisible(true);
					dispose();
					break;
				}
				else {
					Message.showMsg("Since your account isn't active you cannot login :(");
					break;
					
				}
			}
			else {
				Message.showMsg("You have to use admin login page.\nClick the button on the top right.");
				break;
			}
				
		}
		*/
	    
	    
	    
	
	
	
	}
	
	public static void getDonationNumber() {
		int donationNumber;
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		LocalDateTime time = LocalDateTime.now();
		
		
		
		
		System.out.println(dtf.format(time));
	}
}
