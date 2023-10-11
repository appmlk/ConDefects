import java.util.Arrays;
import java.util.Scanner;
public class Main{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int  N = sc.nextInt();
		if(N ==1) {
			System.out.print(0);
		}else {
			long[] A = new long[N];
			long sum = 0;
			for(int i = 0; i < N; i ++) {
				A[i] = sc.nextInt();
				sum += A[i];
			}
			Arrays.sort(A);
			long x = sum / N;
			long a =N - sum + N*x;
			long count = 0;
	//		System.out.println(sum);
	//		System.out.println(x);
	//		System.out.println(a);
			breakOut:
			for(int i = 0; i < N; i ++) {
	//			System.out.print(A[i] + " ");
				if(i <= a) {
					if(x -A[i] <= 0) {
						break breakOut;
					} 
					count += x -A[i];
				}else {
					if(x + 1 -A[i] <= 0) {
						break breakOut;
					}
					count += x + 1 -A[i];
				}
			}
			
			System.out.print(count);
		}
	}
}