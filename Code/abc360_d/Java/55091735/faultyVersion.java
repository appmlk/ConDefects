import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		long t = sc.nextLong();
		char[] s = sc.next().toCharArray();
		long[] x = new long[n];
		ArrayList<Long> a1 = new ArrayList<>();
		ArrayList<Long> a2 = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			x[i] = sc.nextLong();
			if(s[i]=='1') {
				a1.add(x[i]);
			}else {
				a2.add(x[i]);
			}
		}
		Collections.sort(a2);

		int ans = 0;
		for(Long it : a1) {
			int ok = a2.size();
			int ng = -1;
			while(ok-ng>1) {
				int mid = (ok+ng)/2;
				if(a2.get(mid)>=it) {
					ok = mid;
				}else {
					ng = mid;
				}
			}
			int tmp = ok;
			ok = a2.size();
			ng = -1;
			while(ok-ng>1) {
				int mid = (ok+ng)/2;
				if(a2.get(mid)>it+2*t) {
					ok = mid;
				}else {
					ng = mid;
				}
			}
			ans += ok-tmp;
		}

		System.out.println(ans);
	}

}
