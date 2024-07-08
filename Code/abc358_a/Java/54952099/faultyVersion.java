
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner scan = new Scanner(System.in);
		String s = scan.next();

		String t = scan.next();
		if (s.equals("AtCoder") && t.equals("Lnad")) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}
		scan.close();
	}
}
