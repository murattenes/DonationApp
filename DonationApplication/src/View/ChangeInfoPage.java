package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Donor;
import javax.swing.JLabel;
import java.awt.Font;

public class ChangeInfoPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static Donor donor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangeInfoPage frame = new ChangeInfoPage(donor);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ChangeInfoPage(Donor donor) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel newNameLabel = new JLabel("New Name:");
		newNameLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		newNameLabel.setBounds(6, 6, 88, 20);
		contentPane.add(newNameLabel);
		
		JLabel newSurnameLabel = new JLabel("New Surname:");
		newSurnameLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		newSurnameLabel.setBounds(6, 38, 112, 20);
		contentPane.add(newSurnameLabel);
		
		JLabel newNameLabel_1_1 = new JLabel("New Name:");
		newNameLabel_1_1.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
		newNameLabel_1_1.setBounds(6, 70, 88, 20);
		contentPane.add(newNameLabel_1_1);
	}

}
