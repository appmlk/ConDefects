import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);) {
			int n = Integer.parseInt(sc.next());
			char[] s = sc.next().toCharArray();
			int[] cnt = new int[10];
			for(int i = 0; i < n; i++) {
				cnt[s[i] - '0']++;
			}
			
			int ans = 0;
			for(long i = 0; i <= 10000000; i++) {
				char[] c = String.valueOf(i * i).toCharArray();
				int m = c.length;
				int[] cnt2 = new int[10];
				for(int j = 0; j < m; j++) {
					cnt2[c[j] - '0']++;
				}
				
				if(n - m > 0) {
					for(int j = 0; j < n - m; j++) cnt2[0]++;
				}
				
				boolean same = true;
				for(int j = 0; j < 10; j++) {
					if(cnt[j] != cnt2[j]) same = false;
				}
				
				if(same) ans++;
			}
			
			System.out.println(ans);
		}
	}
}