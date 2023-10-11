import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		tc: while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			long A = Integer.parseInt(st.nextToken());
			long B = Integer.parseInt(st.nextToken());
			if (A >= B) {
				sb.append((A-B)+"\n");
				continue tc;
			}
			
			long ans = 1_000_000_000;
			for (int C = 0; C*C <= B-1; C++) {
				long k = (B-1)/(C+1) + 1;
				ans = Math.min(ans, (k+1)*Math.max(C+1-A, 0) + k*A - B);
			}
			
			for (int k = 1; k*k <= B; k++) {
				ans = Math.min(ans, (k+1)*Math.max((B-1)/k + 1 - A, 0) + k*A - B);
			}
			sb.append(ans).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}