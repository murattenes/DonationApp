package View;

import java.awt.EventQueue;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

import Helper.Message;
import Model.Admin;
import Model.Donation;
import Model.User;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import javax.swing.JTextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;

public class DonatePage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static User user = new User();
	static Donation donation = new Donation();
	private static int quantity;
	private static Long number;
	private JTextField textField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DonatePage frame = new DonatePage(user, number);
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
	public DonatePage(User user, Long number) throws SQLException {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				UserPage.donatePageControl = true;
			}
		});
		Donation d = Admin.getDonationbyNumber(number);
		setTitle("DONATE PAGE");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 169, 185));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel quantityLabel = new JLabel("Donation Quantity:");
		quantityLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		quantityLabel.setBounds(6, 106, 148, 20);
		contentPane.add(quantityLabel);
		
		JLabel infoLabel = new JLabel("Donation: " + d.getSubCategory() + ", Needed: " + d.getQuantity());
		infoLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		infoLabel.setBounds(6, 6, 438, 20);
		contentPane.add(infoLabel);
	
		
		SpinnerNumberModel model = new SpinnerNumberModel();
		model.setMinimum(1);
		model.setValue(1);
		JSpinner spinner = new JSpinner();
		spinner.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		spinner.setModel(model);
		spinner.setBounds(166, 103, 50, 26);
		JFormattedTextField txt = ((JSpinner.NumberEditor) spinner.getEditor()).getTextField();
		((NumberFormatter) txt.getFormatter()).setAllowsInvalid(false);
		contentPane.add(spinner);
		
		JButton donateButton = new JButton("Donate");
		donateButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					infoLabel.setText("Donation: " + d.getSubCategory() + ", Needed: " + d.getQuantity());
					Donation donation = Admin.getDonationbyNumber(number);
					int spinnerValue = (Integer)spinner.getValue();
					if(spinnerValue > donation.getQuantity()) {
						Message.showMsg("Your donation quantity higher than needed.\nYou can decrease the quantity.");
					}
					else if(spinnerValue < donation.getQuantity()) {
						Admin.addToFromDonation(donation.getCategory(), donation.getSubCategory(), donation.getParam1(), donation.getParam2(), donation.getCondition(), spinnerValue, user.getId(), donation.getRecipient());
						Admin.editDonationQuantity(donation.getQuantity() - spinnerValue, number);
						Admin.ongoingItem(number);
						Message.showMsg("Process succesful !");
						dispose();
					}
					else if(spinnerValue == donation.getQuantity()) {
						Admin.addToFromDonation(donation.getCategory(), donation.getSubCategory(), donation.getParam1(), donation.getParam2(), donation.getCondition(), spinnerValue, user.getId(), donation.getRecipient());
						Admin.editDonationQuantity(donation.getQuantity() - spinnerValue, number);
						Admin.completeItem(number);
						Message.showMsg("Process succesful !");
						dispose();
					}
					
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		donateButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		donateButton.setBounds(270, 104, 117, 29);
		contentPane.add(donateButton);
		

		
		
		
	}
}
