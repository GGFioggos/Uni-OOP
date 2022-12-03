
public class SMS extends Communication {
	private String content;

	public SMS(String number1, String number2, int year, int month, int day, String content) {
		super(number1, number2, year, month, day);
		this.content = content;
	}

	public void printInfo() {
		System.out.println("This SMS has the following info");
		super.printInfo();
		System.out.println("Text: " + content);
	}

	public String getContent() {
		return content;
	}
}
