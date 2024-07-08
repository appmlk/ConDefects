import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int N = scan.nextInt();
		int M = scan.nextInt();
		
		int[] A = new int[M];
		for (int i = 0; i < M; i++) {
			A[i] = scan.nextInt();
		}
		
		int[] X = new int[M];
		Arrays.fill(X, 0);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				X[j] += scan.nextInt();
			}
		}
		
		boolean result = true;
		for (int i = 0; i < M; i++) {
			if (X[i] < A[i]) {
				result = false;
			}
		}
		
		System.out.println(result ? "Yes" : "No");

	}

}