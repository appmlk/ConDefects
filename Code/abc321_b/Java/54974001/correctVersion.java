import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int X = sc.nextInt();
		int[] A = new int[N];
		
		for(int i=1;i<=N-1;i++) {
			A[i]=sc.nextInt();
		}
		
		A[0]=-1;
		Arrays.sort(A);
		
		int s1 = 0;
		for(int i=1;i<=N-2;i++) {
			s1+=A[i];
		}
		
		if(s1>=X) {
			System.out.println(0);
			return;
		}
		
		int s2=0;
		for(int i=2;i<=N-2;i++) {
			s2+=A[i];
		}
		
		if(A[1]<=X-s2 && X-s2<=A[N-1]) {
			System.out.println(X-s2);
			return;
		}
		
		int s3 =0;
		for(int i=2;i<=N-1;i++) {
			s3+=A[i];
		}
		
		if(s3>=X) {
			System.out.println(A[N-1]);
			return;
		}
		
		System.out.println(-1);
	}
}
