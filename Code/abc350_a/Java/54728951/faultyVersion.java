import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		// inputA
		Scanner sc = new Scanner(System.in);
		String S = sc.next();
		
		int num = Integer.parseInt(S.substring(3,6));
		if(num < 350 && num != 316) System.out.println("Yes");
		else System.out.println("No");
	} 
}