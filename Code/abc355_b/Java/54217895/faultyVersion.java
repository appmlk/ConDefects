import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		final int N = sc.nextInt();
		final int M = sc.nextInt();
		
		if(N>M+2){
		  System.out.println("Yes");
		  System.exit(0);
		}

		int[] numA = new int[N];
		int[] numB = new int[M];
		int[] numC = new int[N + M];

		for (int i = 0; i < N; i++) {
			numA[i] = sc.nextInt();
		}
		for (int j = 0; j < M; j++) {
			numB[j] = sc.nextInt();
		}

		for (int k = 0; k < N + M; k++) {
			if (k < N) {
				numC[k] = numA[k];
			} else {
				numC[k] = numB[k - N];
			}
		}

		sort(numC);

		boolean judgA = false;
		boolean judg = false;

		loopC: for (int i = 0; i < numC.length - 1; i++) {
			for (int a : numA) {
				if (numC[i] == a) {
					if (judgA == true) {
						judg = true;
						break loopC;
					} else {
						judgA = true;
						continue loopC;
					}
				}
			}
			judgA = false;

		}

		if (judg) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}
	}

	public static void sort(int[] a) {
		for (int i = a.length - 1; i >= 1; i--) {
			for (int j = 0; j < i; j++) {
				if (a[j] > a[j + 1]) {
					int w = a[j];
					a[j] = a[j + 1];
					a[j + 1] = w;
				}
			}
		}
	}

}