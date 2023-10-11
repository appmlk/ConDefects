import java.util.Scanner;

public class Main {
	public static void main(String args[]) {

		Scanner scn = new Scanner(System.in);
		long num = scn.nextLong();
		long num2 = (long) Math.pow(2, 31);
		long num3 = (long) Math.pow(-2, 31);
		if (num3 <= num && num < num2) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}

		scn.close();
	}

}