import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.close();
		String s = "";
		for (int i = 0; i < N; i++) {
			s += "" + i;
		}
		System.out.println(s);
	}
}