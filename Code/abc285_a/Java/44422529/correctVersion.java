import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner input = new Scanner(System.in);
		
		int a = input.nextInt();
		int b = input.nextInt();
		 
		if (a*2 + 1 == b || a*2 == b) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}
	}
}