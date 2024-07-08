import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);) {
			int n = Integer.parseInt(sc.next());
			int q = Integer.parseInt(sc.next());
			long cnt = 0;

			int[] push = new int[n];
			Arrays.fill(push, -1);
			long[] sum = new long[q + 1];
			long[] ans = new long[n];
			
			for(int i = 0; i < q; i++) {
				int x = Integer.parseInt(sc.next());
				x--;
				
				if(push[x] < 0) {
					push[x] = i;
					cnt++;
				} else {
					long v = sum[i] - push[x];
					ans[x] += v;
					push[x] = -1;
					cnt--;
				}
				
				sum[i + 1] = sum[i] + cnt;
			}
			
			for(int i = 0; i < n; i++) {
				if(push[i] >= 0) ans[i] += sum[q] - sum[push[i]];
			}
			
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < n; i++) sb.append(ans[i]).append(" ");
			System.out.println(sb.toString());
		}
	}
}

