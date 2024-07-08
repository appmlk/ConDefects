import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.close();
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < n; i++) {
			if (i % 3 == 0) {
				sb.append("x");
			} else {
				sb.append("o");
			}
		}
		System.out.println(sb);
	}
}