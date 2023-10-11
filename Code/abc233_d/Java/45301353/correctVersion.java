import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		long k = sc.nextLong();
		long[] sum = new long[n + 1];
		sum[0] = 0;
		
		int a = 0;
		for(int i = 0 ; i < n ; i++) {
			a = sc.nextInt();
			sum[i + 1] = sum[i] + a;
		}
		
		Map<Long, Integer> map = new HashMap<>();
		long ans = 0;
		for(int i = n ; i >= 0 ; i--) {
			ans += map.getOrDefault(sum[i] + k, 0);
			map.put(sum[i] , map.getOrDefault(sum[i], 0) + 1);
		}
		
		System.out.println(ans);		
	}	
}