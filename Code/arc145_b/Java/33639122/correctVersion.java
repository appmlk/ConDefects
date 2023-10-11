import java.util.*;
import java.util.stream.Collectors;

public class Main{
//	static final int INF = Integer.MAX_VALUE;
//	static final long INF = Long.MAX_VALUE;
//	static final long MOD = 998244353;

	static long N;
	static long A;
	static long B;
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		N = sc.nextLong();
		A = sc.nextLong();
		B = sc.nextLong();

		class P{
		}

		if(A > B){
			long M = N / A;
//			long ans = B * (N / A - 1);
//			N -= A * (N / A) - 1;
//			System.out.println(ans);
//			System.out.println(N);
//			ans += Math.min(B, N);
//			int[] a = new int[5];
//			a[6] = 0;
			System.out.println(Math.max(B*(M-1)+Math.min(N - M*A + 1, B), 0));
//			long ans = Math.max(f(N) - f(A-1), 0);
//			System.out.println(ans);
		}else{
			long ans = Math.max(N - A + 1, 0);
//			long ans = Math.max((N/A)*A + Math.min(N % A, B - 1) - A + 1, 0);
			System.out.println(ans);
		}
	}
	static long f(long x){
		return (x/A)*Math.min(A, B) + Math.min(x % A, B - 1);
	}

}
