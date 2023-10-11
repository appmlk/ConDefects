import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		int X = scan.nextInt();
		String result = "";
		
		if(X == 0) {
			result = "No";
		} else if(X < 100 || (X > 100 && X % 100 != 0)) {
			result = "No";
		} else if(X > 100 && X % 100 ==0) {
			result = "Yes";
		}
		System.out.println(result);
		
	}

}
