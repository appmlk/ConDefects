import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int sum = 0;
		for (int i = 1; i <= n; i++) {
			sum += sc.nextInt();
			sum -= sc.nextInt();
		}
		sc.close();
		
		String str = "Draw";
		if (sum > 0) {
			str = "Takahashi";
		} else if (sum < 0) {
			str = "Aoki";
		}
		System.out.println(str);
	}
}