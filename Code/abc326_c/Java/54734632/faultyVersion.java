import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		long M = sc.nextLong();
		long[] A = new long[N+1];
		for(int i=1;i<=N;i++) {
			A[i]=sc.nextLong();
		}
		
		A[0]=-1;
		Arrays.sort(A);
		int r=1;
		long ans = 0;
		
		for(int l=1;l<=N;l++) {
			while(r<N && A[l] <= A[r] && A[r] < A[l]+M ) {
				r++;
			}
			ans=Math.max(ans, r-l);
		}
		
		System.out.println(ans);
	}
}
