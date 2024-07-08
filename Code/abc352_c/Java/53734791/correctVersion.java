import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		long h = 0;
		long max = 0;
		long hold1 = 0;
		long hold2 = 0;
		//int hold3 = 0;
		long N = sc.nextInt();
		for (int i = 1; i <= N; i++) {
			hold1 = sc.nextInt();
			hold2 = sc.nextInt();
			h += hold1;
			//System.out.println(h);
			if (max < hold2-hold1) {
				max = hold2-hold1;
			}
		}
		h+=max;
		System.out.println(h);
	}

}
