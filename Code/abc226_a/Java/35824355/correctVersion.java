import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		
		double x = scan.nextDouble();
		scan.close();
		
		System.out.println((int)Math.round(x));
		
		
	}

}
