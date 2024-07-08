import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int A = sc.nextInt();
		int[] T = new int[N];
		int ansT = 0;
		
		for (int i=0; i<N; i++) {
			T[i]=sc.nextInt();
		}
		
		ansT = T[0] + A;
		System.out.println(ansT);
		
		for (int i=1; i<N; i++) {
			if (ansT>=T[i]) {
				System.out.println(ansT+A);
				ansT = ansT + A;
			}else {
				ansT = T[i] + A;
				System.out.println(ansT);
			}
		}
	}
}