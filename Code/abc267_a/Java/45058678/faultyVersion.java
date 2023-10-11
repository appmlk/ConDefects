import java.util.Scanner;

public class Main {
	public static void main(final String[] args) {
		try (final Scanner sc = new Scanner(System.in)) {
			switch (sc.next()) {
			case "Monday":
				System.out.println("5");
				break;
			case "Tuesday":
				System.out.println("4");
				break;
			case "Wednesday":
				System.out.println("3");
				break;
			case "Thursday":
				System.out.println("2");
				break;
			case "Friday ":
				System.out.println("1");
				break;
			}
		}
	}
}
