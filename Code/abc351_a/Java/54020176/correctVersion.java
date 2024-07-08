import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner sc = new Scanner(System.in);
		int sumA = 0;
		int sumB = 0;

		for (int i = 0; i < 9; i++) {
			sumA += Integer.parseInt(sc.next());
		}

		for (int i = 0; i < 8; i++) {
			sumB += Integer.parseInt(sc.next());
		}

		System.out.print((sumA - sumB + 1));

	}

}
