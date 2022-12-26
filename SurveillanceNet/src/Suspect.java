import java.util.ArrayList;

public class Suspect {
	private String name;
	private String encname;
	private String country;
	private String city;
	private ArrayList<String> phonenums = new ArrayList<>();
	private ArrayList<Suspect> partners = new ArrayList<>();

	public Suspect(String name, String encname, String country, String city) {
		this.name = name;
		this.encname = encname;
		this.country = country;
		this.city = city;
	}

	public void addNumber(String number) {
		phonenums.add(number);
	}

	public void addAssociate(Suspect associate) {
		if (!partners.contains(associate)) {
			partners.add(associate);
		}
	}

	public ArrayList<Suspect> getSuggestedPartners() {
		ArrayList<Suspect> suggestedPartners = new ArrayList<>();
		for (Suspect suspect : partners) {
			for (Suspect suspectsPartner : suspect.getPartners()) {
				if (!suggestedPartners.contains(suspectsPartner)
						&& (!partners.contains(suspectsPartner) && (suspectsPartner != this))) {
					suggestedPartners.add(suspectsPartner);
				}
			}
		}

		return suggestedPartners;
	}

	public boolean isConnectedTo(Suspect suspect) {
		return partners.contains(suspect);
	}

	public ArrayList<Suspect> getPartners() {
		return partners;
	}

	public ArrayList<Suspect> getCommonPartners(Suspect suspect) {
		ArrayList<Suspect> common = new ArrayList<>();

		for (Suspect sus : partners) {
			if (suspect.getPartners().contains(sus) && !common.contains(sus)) {
				common.add(sus);
			}
		}
		return common;
	}

	public String getName() {
		return name;
	}

	public String getCodeName() {
		return encname;
	}

	public String getCountry() {
		return country;
	}

	public ArrayList<String> getPhoneNumbers() {
		return phonenums;
	}

	public void printPartners() {
		for (Suspect suspect : partners) {
			System.out.println("Name: " + suspect.getName() + "Code Name: " + suspect.getCodeName()
					+ (suspect.getCountry() == this.getCountry() ? '*' : ""));
		}
	}

}
