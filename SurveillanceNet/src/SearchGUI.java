import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchGUI extends JFrame {

	private JTextField nameInputField = new JTextField("Please enter suspect's name");
	private JButton searchButton = new JButton("Find");

	private JPanel panel = new JPanel();

	public SearchGUI(Registry registry) {
		panel.add(nameInputField);
		panel.add(searchButton);

		this.setContentPane(panel);

		searchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Suspect searchedSuspect = registry.findSuspectByName(nameInputField.getText());
				if (searchedSuspect != null) {
					// If suspect exists open info window
					new InfoGUI(searchedSuspect, registry);
				} else {
					// if suspect does not exist open error window
					new ErrorGUI(nameInputField.getText());
				}
			}
		});

		this.setSize(400, 250);
		this.setTitle("Find Suspect");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
