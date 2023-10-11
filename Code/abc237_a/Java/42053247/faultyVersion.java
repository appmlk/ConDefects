import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		double s = sc.nextDouble();
		double l = Math.pow(-2, 31);
		double h = Math.pow(2, 31);

		if (l <= s && s <= h) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}
	}
}