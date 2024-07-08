import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long n = sc.nextLong();
		long s = sc.nextLong();
		long k = sc.nextLong();
		
		long sum = 0;
		for (long i = 0; i < n; i++) {
			sum += sc.nextLong() * sc.nextLong();
		}
		sc.close();
		System.out.println(sum > s ? sum : sum + k);
	}
}