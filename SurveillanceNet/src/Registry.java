import java.util.ArrayList;

public class Registry {
	private ArrayList<Suspect> suspects = new ArrayList<>();
	private ArrayList<Communication> communications = new ArrayList<>();

	public void addSuspect(Suspect suspect) {
		suspects.add(suspect);
	}

	public ArrayList<Suspect> getAllSuspects() {
		return suspects;
	}

	// Adds communication to ArrayList
	public void addCommunication(Communication communication) {
		communications.add(communication);

		Suspect sus1 = findSuspectByNumber(communication.getNumbers().get(0));
		Suspect sus2 = findSuspectByNumber(communication.getNumbers().get(1));

		sus1.addAssociate(sus2);
		sus2.addAssociate(sus1);
	}

	// Finds and returns a suspect based on their phone number
	private Suspect findSuspectByNumber(String number) {
		for (Suspect suspect : suspects) {
			for (String phonenum : suspect.getPhoneNumbers()) {
				if (phonenum == number) {
					return suspect;
				}
			}
		}
		return null;
	}

	public Suspect findSuspectByName(String name) {
		for (Suspect suspect : suspects) {
			if (name.equals(suspect.getName())) {
				return suspect;
			}
		}
		return null;
	}

	public Suspect getSuspectWithMostPartners() {
		Suspect max = suspects.get(0);
		for (Suspect suspect : suspects) {
			if (suspect.getPartners().size() > max.getPartners().size()) {
				max = suspect;
			}
		}
		return max;
	}

	public PhoneCall getLongestPhoneCallBetween(String number1, String number2) {
		int max = 0;
		PhoneCall maxCall = null;
		for (Communication communication : communications) {
			if (communication.getNumbers().contains(number1) && communication.getNumbers().contains(number2)) {
				if (communication instanceof PhoneCall) {
					PhoneCall phoneCall = (PhoneCall) communication;
					if (phoneCall.getDuration() > max) {
						max = phoneCall.getDuration();
						maxCall = phoneCall;
					}
				}
			}
		}
		return maxCall;
	}

	public ArrayList<SMS> getMessagesBetween(String number1, String number2) {
		ArrayList<SMS> messages = new ArrayList<>();

		for (Communication communication : communications) {
			if (communication.getNumbers().contains(number1) && communication.getNumbers().contains(number2)) {
				if (communication instanceof SMS) {
					SMS sms = (SMS) communication;
					if (sms.getContent().contains("Bomb") || sms.getContent().contains("Attack")
							|| sms.getContent().contains("Explosives") || sms.getContent().contains("Gun")) {
						messages.add(sms);
					}
				}
			}
		}
		return messages;
	}

	public ArrayList<Suspect> printSuspectsFromCountry(String country) {
		ArrayList<Suspect> countrySuspects = new ArrayList<>();
		for (Suspect suspect : suspects) {
			if (suspect.getCountry() == country) {
				countrySuspects.add(suspect);
			}
		}
		return countrySuspects;
	}

}
