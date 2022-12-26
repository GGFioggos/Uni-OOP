import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class InfoGUI extends JFrame {
	// Basic Suspect info
	private JTextField name;
	private JTextField codeName;
	private JList<String> phoneNumbers = new JList<>();
	private DefaultListModel phoneNumbersModel;

	// SMS info
	private JTextField phoneNumberInput;
	private JTextArea messages;
	private JButton messageButton = new JButton("Find SMS");
	
	// Return Button
	private JButton returnButton = new JButton("Return to Search Screen");

	// Panels
	private JPanel suspectInfo = new JPanel();
	private JPanel SMSpanel = new JPanel();
	private JPanel mainPanel = new JPanel();
	private JPanel partnersPanel = new JPanel();
	private JPanel suggestedPartnersPanel = new JPanel();
	private JPanel countrySuspectsPanel = new JPanel();
	
	public InfoGUI(Suspect suspect, Registry registry) {

		suspectInfo = createSuspectInfo(suspect);
		SMSpanel = createSMSPanel(suspect, registry);
		partnersPanel = createPartnersPanel(suspect);
		suggestedPartnersPanel = createSuggestedPartnersPanel(suspect);
		countrySuspectsPanel = createCountrySuspectsPanel(suspect, registry);
		
		mainPanel.add(suspectInfo);
		mainPanel.add(SMSpanel);
		mainPanel.add(partnersPanel);
		mainPanel.add(suggestedPartnersPanel);
		mainPanel.add(countrySuspectsPanel);
		mainPanel.add(returnButton);
		
		returnButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		this.setContentPane(mainPanel);

		this.setTitle("Suspect Page");
		this.setSize(400, 900);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private JPanel createSuspectInfo(Suspect suspect) {
		name = new JTextField(suspect.getName());
		name.setEditable(false);
		;
		codeName = new JTextField(suspect.getCodeName());
		codeName.setEditable(false);

		phoneNumbersModel = new DefaultListModel();
		phoneNumbers.setModel(phoneNumbersModel);

		for (String number : suspect.getPhoneNumbers()) {
			phoneNumbersModel.addElement(number);
		}

		suspectInfo.add(name);
		suspectInfo.add(codeName);
		suspectInfo.add(phoneNumbers);
		suspectInfo.setBorder(BorderFactory.createLineBorder(Color.black));
		return suspectInfo;
	}

	private JPanel createSMSPanel(Suspect suspect, Registry registry) {
		phoneNumberInput = new JTextField("00446999888888");
		messages = new JTextArea(12, 11);
		messages.setBorder(BorderFactory.createLineBorder(Color.black));
		messages.setEditable(false);
		SMSpanel = new JPanel();

		messageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				messages.setText("");
				;
				ArrayList<SMS> messagesBetween = new ArrayList<>();
				for (String number : suspect.getPhoneNumbers()) {
					messagesBetween.addAll(registry.getMessagesBetween(number, phoneNumberInput.getText()));
				}
				for (SMS message : messagesBetween) {

					messages.append(message.getContent() + "\n");
				}
			}
		});

		SMSpanel.add(phoneNumberInput);
		SMSpanel.add(messages);
		SMSpanel.add(messageButton);
		SMSpanel.setBorder(BorderFactory.createLineBorder(Color.black));
		return SMSpanel;
	}

	private JPanel createPartnersPanel(Suspect suspect) {
		partnersPanel = new JPanel();
		JTextArea partnersArea = new JTextArea(12, 11);
		partnersArea.setBorder(BorderFactory.createLineBorder(Color.black));
		partnersArea.setEditable(false);
		JLabel title = new JLabel("Partners");

		System.out.println(suspect.getPartners());
		for (Suspect partner : suspect.getPartners()) {
			partnersArea.append(partner.getName() + ", " + partner.getCodeName() + "\n");
		}

		partnersPanel.add(title);
		partnersPanel.add(partnersArea);
		partnersPanel.setBorder(BorderFactory.createLineBorder(Color.black));

		return partnersPanel;

	}
	
	private JPanel createSuggestedPartnersPanel(Suspect suspect) {
		suggestedPartnersPanel = new JPanel();
		JLabel title = new JLabel("Suggested Partners ----->");
		JTextArea suggestedPartners = new JTextArea(5,12);
		suggestedPartners.setEditable(false);
		suggestedPartners.setBorder(BorderFactory.createLineBorder(Color.black));
		
		for(Suspect suggested:suspect.getSuggestedPartners()) {
			suggestedPartners.append(suggested.getName() + "\n");
		}
		
		suggestedPartnersPanel.add(title);
		suggestedPartnersPanel.add(suggestedPartners);
		suggestedPartnersPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		return suggestedPartnersPanel;
		
	}
	
	private JPanel createCountrySuspectsPanel(Suspect suspect, Registry registry) {
		countrySuspectsPanel = new JPanel();
		JTextArea countrySuspects = new JTextArea(10,17);
		countrySuspects.setBorder(BorderFactory.createLineBorder(Color.black));
		countrySuspects.setEditable(false);
		countrySuspects.append("Suspects coming from " + suspect.getCountry() + "\n");
		for(Suspect sus: registry.printSuspectsFromCountry(suspect.getCountry())) {
			countrySuspects.append(sus.getName() + "\n");
		}
		
		countrySuspectsPanel.add(countrySuspects);
		countrySuspectsPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		return countrySuspectsPanel;
	}
}
