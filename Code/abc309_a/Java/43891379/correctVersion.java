import java.util.Scanner;

public class Main {

	public static void main(String args[]) {

		// 入力
		Scanner sc = new Scanner(System.in);

		int a = Integer.parseInt(sc.next());
		int b = Integer.parseInt(sc.next());

		sc.close();

		// 主処理
		boolean judge = b % 3 != 1 && b - 1 == a;

		String result = judge ? "Yes" : "No";

		// 出力
		System.out.println(result);

	}

}
