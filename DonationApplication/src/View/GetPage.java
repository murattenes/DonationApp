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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;

public class GetPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static User user = new User();
	static Donation donation = new Donation();
	private static int quantity;
	private static Long number;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GetPage frame = new GetPage(user, number);
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
	public GetPage(User user, Long number) throws SQLException {
		setTitle("GET PAGE");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				UserPage.getPageControl = true;
			}
		});
		Donation d = Admin.getDonationbyNumber(number);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 169, 185));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel infoLabel = new JLabel("Donation: " + d.getSubCategory() + ", Available: " + d.getQuantity());
		infoLabel.setBounds(6, 6, 438, 16);
		contentPane.add(infoLabel);
		
		JLabel quantityLabel = new JLabel("Get quantity:");
		quantityLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		quantityLabel.setBounds(6, 106, 101, 20);
		contentPane.add(quantityLabel);
		
		
		SpinnerNumberModel model = new SpinnerNumberModel();
		model.setMinimum(1);
		model.setValue(1);
		JSpinner spinner = new JSpinner();
		spinner.setModel(model);
		spinner.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		spinner.setBounds(119, 104, 50, 26);
		JFormattedTextField txt = ((JSpinner.NumberEditor) spinner.getEditor()).getTextField();
		((NumberFormatter) txt.getFormatter()).setAllowsInvalid(false);
		contentPane.add(spinner);
		
		JButton getButton = new JButton("Get");
		getButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				try {
					
					Donation donation = Admin.getDonationbyNumber(number);
					int spinnerValue = (Integer)spinner.getValue();
					infoLabel.setText("Donation: " + donation.getSubCategory() + ", Available: " + donation.getQuantity());
					if(spinnerValue > donation.getQuantity()) {
						Message.showMsg("Your request quantity higher than available.\nYou can decrease the quantity.");
					}
					else if (spinnerValue < donation.getQuantity()) {
						Admin.addToFromDonation(donation.getCategory(), donation.getSubCategory(), donation.getParam1(), donation.getParam2(), donation.getCondition(), spinnerValue, donation.getDonor(), user.getId());
						Admin.editDonationQuantity(donation.getQuantity() - spinnerValue, number);
						Admin.ongoingItem(number);
						Message.showMsg("Your request has been notified to the donor");
						dispose();
					}
					else if (spinnerValue == donation.getQuantity()) {
						Admin.addToFromDonation(donation.getCategory(), donation.getSubCategory(), donation.getParam1(), donation.getParam2(), donation.getCondition(), spinnerValue, donation.getDonor(), user.getId());
						Admin.editDonationQuantity(donation.getQuantity() - spinnerValue, number);
						Admin.completeItem(number);
						Message.showMsg("Your request has been notified to the donor");
						dispose();
					}
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				
				}
				
			}
		});
		getButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		getButton.setBounds(270, 104, 117, 29);
		contentPane.add(getButton);
	}

}
