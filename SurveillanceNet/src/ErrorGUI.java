import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ErrorGUI extends JFrame {

	public ErrorGUI(String name) {

		JLabel errorMessage = new JLabel("Suspect" + " " + name + " Not Found");
		JPanel panel = new JPanel();
		JButton button = new JButton("OK");

		panel.add(errorMessage);
		panel.add(button);

		this.setContentPane(panel);

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		this.setSize(320, 250);
		this.setTitle("Message");
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}
