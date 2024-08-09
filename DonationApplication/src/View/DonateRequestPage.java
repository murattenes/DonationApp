package View;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Message;
import Model.Admin;
import Model.User;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JFormattedTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JSpinner;

public class DonateRequestPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static User user = new User();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DonateRequestPage frame = new DonateRequestPage(user);
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
	public DonateRequestPage(User user) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel categoryLabel = new JLabel("Category:");
		categoryLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		categoryLabel.setBounds(6, 6, 75, 20);
		contentPane.add(categoryLabel);
		
		JLabel subcategoryLabel = new JLabel("Subcategory:");
		subcategoryLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		subcategoryLabel.setBounds(6, 56, 101, 20);
		contentPane.add(subcategoryLabel);
		
		JLabel param1Label = new JLabel("Param 1:");
		param1Label.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		param1Label.setBounds(6, 106, 69, 20);
		contentPane.add(param1Label);
		
		JLabel param2Label = new JLabel("Param 2:");
		param2Label.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		param2Label.setBounds(6, 156, 69, 20);
		contentPane.add(param2Label);
		
		JLabel conditionLabel = new JLabel("Condition:");
		conditionLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		conditionLabel.setBounds(6, 206, 82, 20);
		contentPane.add(conditionLabel);
		
		JLabel quantityLabel = new JLabel("Quantity:");
		quantityLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		quantityLabel.setBounds(6, 256, 82, 20);
		contentPane.add(quantityLabel);
		
		JComboBox param1ComboBox = new JComboBox();
		param1ComboBox.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		param1ComboBox.setBounds(119, 105, 125, 27);
		contentPane.add(param1ComboBox);
		
		JComboBox param2ComboBox = new JComboBox();
		param2ComboBox.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		param2ComboBox.setBounds(119, 155, 125, 27);
		contentPane.add(param2ComboBox);
		
		JComboBox conditionComboBox = new JComboBox();
		conditionComboBox.setModel(new DefaultComboBoxModel(new String[] {"", "New", "Good", "Okay"}));
		conditionComboBox.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		conditionComboBox.setBounds(119, 205, 125, 27);
		contentPane.add(conditionComboBox);
		
		JComboBox subcategoryComboBox = new JComboBox();
		subcategoryComboBox.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		subcategoryComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (subcategoryComboBox.getSelectedIndex() < 1) {
					param1ComboBox.setModel(new DefaultComboBoxModel(new String[] {}));
					param2ComboBox.setModel(new DefaultComboBoxModel(new String[] {}));
					conditionComboBox.setSelectedIndex(-1);
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
		subcategoryComboBox.setBounds(119, 55, 125, 27);
		contentPane.add(subcategoryComboBox);
		
		
		JComboBox categoryComboBox = new JComboBox();
		categoryComboBox.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		categoryComboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Education", "Clothing", "Food", "Electronic", "Housing"}));
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
						subcategoryComboBox.setModel(new DefaultComboBoxModel(new String[] {}));
						subcategoryComboBox.setSelectedIndex(-1);
						break;
				}
			}
		});
		categoryComboBox.setBounds(119, 5, 125, 27);
		contentPane.add(categoryComboBox);
		
		
		SpinnerNumberModel model = new SpinnerNumberModel();
		model.setMinimum(1);
		model.setValue(1);
		JSpinner quantitySpinner = new JSpinner();
		quantitySpinner.setModel(model);
		quantitySpinner.setBounds(119, 254, 69, 26);
		contentPane.add(quantitySpinner);
		
		JButton donateButton = new JButton("DONATE");
		donateButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(categoryComboBox.getSelectedIndex() < 1 || subcategoryComboBox.getSelectedIndex() < 1 || param1ComboBox.getSelectedIndex() < 1 || param2ComboBox.getSelectedIndex() < 1 || conditionComboBox.getSelectedIndex() < 1) {
					Message.showMsg("fill");
				}
				else {
					int donCategory = categoryComboBox.getSelectedIndex();
					String donSubcategory = subcategoryComboBox.getSelectedItem().toString();
					String donParam1 = param1ComboBox.getSelectedItem().toString();
					String donParam2 = param2ComboBox.getSelectedItem().toString();
					String donCondition = conditionComboBox.getSelectedItem().toString();
					int donQuantity = (Integer) quantitySpinner.getValue();
					try {
						Admin.addDonation(donCategory, donSubcategory, donParam1, donParam2, donCondition, donQuantity, user.getId(), (Integer) null);
						Message.showMsg("We got your donation.");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
				
				
			}
		});
		donateButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		donateButton.setBounds(50, 307, 125, 29);
		contentPane.add(donateButton);
		
		JButton requestButton = new JButton("REQUEST");
		requestButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(categoryComboBox.getSelectedIndex() < 1 || subcategoryComboBox.getSelectedIndex() < 1 || param1ComboBox.getSelectedIndex() < 1 || param2ComboBox.getSelectedIndex() < 1 || conditionComboBox.getSelectedIndex() < 1) {
					Message.showMsg("fill");
				}
				else {
					int donCategory = categoryComboBox.getSelectedIndex();
					String donSubcategory = subcategoryComboBox.getSelectedItem().toString();
					String donParam1 = param1ComboBox.getSelectedItem().toString();
					String donParam2 = param2ComboBox.getSelectedItem().toString();
					String donCondition = conditionComboBox.getSelectedItem().toString();
					int donQuantity = (Integer) quantitySpinner.getValue();
					try {
						Admin.requestDonation(donCategory, donSubcategory, donParam1, donParam2, donCondition, donQuantity, (Integer) null, user.getId());
						Message.showMsg("We got your request.");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
			}
		});
		requestButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		requestButton.setBounds(200, 307, 125, 29);
		contentPane.add(requestButton);
		
		
		
		
		
	}
}
