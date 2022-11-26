
public class PhoneCall extends Communication {
	private int duration;

	public PhoneCall(String number1, String number2, int year, int month, int day, int duration) {
		super(number1, number2, year, month, day);
		this.duration = duration;
	}

	public void printInfo() {
		System.out.println("This phone call has the following info");
		super.printInfo();
		System.out.println("Duration: " + duration);
	}

	public int getDuration() {
		return duration;
	}
}
