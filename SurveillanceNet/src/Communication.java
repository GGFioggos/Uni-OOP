import java.util.ArrayList;

public class Communication {
	private ArrayList<String> numbers = new ArrayList<>();
	private int year;
	private int month;
	private int day;

	public Communication(String number1, String number2, int day, int month, int year) {
		this.numbers.add(number1);
		this.numbers.add(number2);
		this.year = year;
		this.month = month;
		this.day = day;
	}

	public void printInfo() {
		System.out.println("Between " + numbers.get(0) + " --- " + numbers.get(1) + "\non " + getFullDate());
	}

	private String getFullDate() {
		return (year + "/" + month + "/" + day);
	}

	public ArrayList<String> getNumbers() {
		return numbers;
	}
}
