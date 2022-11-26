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
		// Check that associate is not already in the list
		partners.add(associate);
	}

	public boolean isConnectedTo(Suspect suspect) {
		return partners.contains(suspect);
	}

	public ArrayList<Suspect> getPartners() {
		return partners;
	}

	public ArrayList<Suspect> getCommonPartners(Suspect suspect) {
		ArrayList<Suspect> common = new ArrayList<>();

		for (Suspect sus : suspect.getPartners()) {
			if (partners.contains(sus)) {
				common.add(sus);
			}
		}
		return common;
	}
	
	public String getName() {
		return name;
	}
	
	public String getCodename() {
		return encname;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void printPartners() {
		for (Suspect suspect: partners) {
			System.out.println("Name: " + suspect.getName() + "Code Name: " + suspect.getCodename() + (suspect.getCountry() == this.getCountry() ? '*' : ""));
		}
	}

}
