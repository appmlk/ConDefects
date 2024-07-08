import java.util.*;
public class Main {
	public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		long[] w = new long[n];
		long[] x = new long[n];
		for(int i = 0; i<n; i++){
		  w[i] = sc.nextLong();
		  x[i] = sc.nextLong();
		}
		
		long[] suffix = new long[25];
		for(int i = 0; i<n; i++){
		  suffix[(int)(x[i] + 9)%24] += w[i];
		  suffix[(int)(x[i]+18)%24] -= w[i];
		  if(x[i] > 5 && x[i]<16)suffix[0] += w[i];
		}
		long[] arr = new long[25];
		long ans = suffix[0];
		arr[0] = suffix[0];
		for(int i=1;i<25; i++){
		  arr[i] = arr[i-1] + suffix[i];
		  ans = Math.max(ans, arr[i]);
		}
		// for(int i = 0; i<25; i++)System.out.println(arr[i]);
		System.out.println(ans);
	}

}