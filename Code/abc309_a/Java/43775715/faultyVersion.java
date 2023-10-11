import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();

		if ((a * b) % 3 == 0 && Math.abs(a-b) != 1) {
			System.out.println("No");
		} else {

			System.out.println("Yes");
		}
	}

}