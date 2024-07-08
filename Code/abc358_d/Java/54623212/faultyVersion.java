import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		int M = scanner.nextInt();
		long A[] = new long [N];
		long B[] = new long [M];
		int a =0;
		int b =0;
		int sum=0;
		
		
		for(int i=0; i<N;i++) {
			A[i]=scanner.nextLong();
		}
		
		
		for(int i=0;i<M;i++) {
			B[i] = scanner.nextLong();
		}
		
		Arrays.sort(A);
		Arrays.sort(B);
		
		while(a<N&&b<M) {if(A[a]>=B[b]) {
			sum+=A[a];
			a+=1;
			b+=1;
			
		}else{a+=1;}
		}
		
		
		
		
		
		if(b!=M) {
			System.out.println(-1);
		}else {
			System.out.println(sum);
		}
		
		
		
		scanner.close();
}
}
