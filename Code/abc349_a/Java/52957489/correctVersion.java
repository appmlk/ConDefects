import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int a[] = new int[n];
		int ans = 0;
		for (int i = 0; i < n - 1; i++) {
			a[i] = sc.nextInt();
		}
		for (int i = 0; i < n - 1; i++) {
			ans += a[i];
		}
		System.out.println(-1 * ans);
	}

}
