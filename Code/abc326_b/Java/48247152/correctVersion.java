import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int x = scanner.nextInt();

		for (int i = x; i <= 919; i++) {
			int hundreds = i / 100;
			int tens = (i / 10) % 10;
			int ones = i % 10;

			if (hundreds * tens == ones) {
				System.out.println(i);
				break;
			}
		}

	}

}
