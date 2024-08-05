package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JButton;

public class DonorPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DonorPage frame = new DonorPage();
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
	public DonorPage() {
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
		
		JPanel donorPanel = new JPanel();
		tabbedPane.addTab("Donor Tab", null, donorPanel, null);
		donorPanel.setLayout(null);
		
		JPanel profilePanel = new JPanel();
		tabbedPane.addTab("Profile", null, profilePanel, null);
		profilePanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(0, 0, 254, 58);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(633, 0, 117, 29);
		contentPane.add(btnNewButton);
	}
}
