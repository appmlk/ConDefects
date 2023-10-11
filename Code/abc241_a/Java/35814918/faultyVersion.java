import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		String str = scan.nextLine();
		str = str.replace(" ", "");
		System.out.println(str);
		
		int answer = str.charAt(0) - 48; // 9
		
		for(int i = 0; i <2; i++) {
			System.out.println(answer);
			answer = str.charAt(answer) -48;
		}
		System.out.println(answer);
		
	}
}
