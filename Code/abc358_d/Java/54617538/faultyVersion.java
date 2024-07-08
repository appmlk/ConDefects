import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		long[] A = new long[N];
		long[] B = new long[M];
		
		for(int i = 0; i < N; i++) {
			A[i] = sc.nextInt();
		}
		for(int i = 0; i < M; i++) {
			B[i] = sc.nextInt();
		}
		
		Arrays.sort(A);
		Arrays.sort(B);
		
		int totalPayment = 0;
		boolean isSucceed =false;
		
		int j = 0;
		for (int i = 0; i < M; i++) {
			
			if(B[i] > A[j]) {
				i--;
			} else {
				totalPayment += A[j];
			}
			j++;
			
			if(i == M-1) {
				isSucceed = true;
			}
			if(j==N) {
				break;
			}
			
		}
		
		if(isSucceed) {
			System.out.println(totalPayment);
		} else { 
			System.out.println(-1);
		}
	}
}
