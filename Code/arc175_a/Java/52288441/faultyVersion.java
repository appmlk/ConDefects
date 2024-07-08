import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);) {
			int n = Integer.parseInt(sc.next());
			int[] ps = new int[n];
			for(int i = 0; i < n; i++) {
				ps[i] = Integer.parseInt(sc.next());
				ps[i]--;
			}
			char[] s = sc.next().toCharArray();
			final int mod = 998244353;
			
			int ans_left = 1;
			int ans_right = 1;
			boolean[] took_spoon = new boolean[n];
			for(int p : ps) {
				if(took_spoon[(p + 1) % n]) {
					if(s[p] == '?') ans_left *= 2;
				} else {
					if(s[p] == 'R') ans_left *= 0;
				}
				ans_left %= mod;
				
				if(took_spoon[(p + n - 1) % n]) {
					if(s[p] == '?') ans_right *= 2;
				} else {
					if(s[p] == 'L') ans_right *= 0;
				}
				ans_right %= mod;
				took_spoon[p] = true;
			}
			
			System.out.println(ans_left + ans_right);
		}
	}
}

