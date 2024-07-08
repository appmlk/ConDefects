import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int takaHeight = scan.nextInt();
		scan.close();
		int plantHeight = 0;
		int date = 0;
		while (takaHeight >= plantHeight) {
			plantHeight += Math.pow(2, date);
			date++;
		}
		System.out.println(date);
	}
}
