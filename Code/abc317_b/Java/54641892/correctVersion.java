import java.util.Scanner;

public class Main{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.next());
		long min = 1001;
		long sum = 0;
		for (int i = 1; i <= n; i++) {
			long a = Long.parseLong(sc.next());
			if (min > a) {
				min = a;
			}
			sum += a;
		}
		sc.close();
		System.out.println(n * (n + 1) / 2 - sum + min * (n + 1));
	}
}
