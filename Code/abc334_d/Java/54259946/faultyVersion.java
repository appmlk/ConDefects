import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();//台数
		int q = sc.nextInt();//クエリ数
		List<Long> r = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			r.add(sc.nextLong());
		}
		Collections.sort(r);
		
		List<Long> sumR = new ArrayList<>();//i+1台引くのに必要なトナカイ数
		long sum = 0;
		for (int i = 0; i < n; i++) {
			sum += r.get(i);
			sumR.add(sum);
		}
		
		for (int i = 0; i < q; i++) {
			long x = sc.nextLong();
			System.out.println(ans(x, sumR));
		}
		sc.close();
	}
	
	public static long ans(long x, List<Long> sumR) {
		int s = 0;
		int g = sumR.size() - 1;
		int h = g / 2;
		boolean b = true;
		
		while (s < g) {
			h = (s + g) / 2;
			if (x == sumR.get(h)) {
				return h + 1;
			} else if (x > sumR.get(h)) {
				s = h + 1;
				b = true;
			} else {
				g = h;
				b = false;
			}
		}
		if (b && x > sumR.get(s)) {
			return s + 1;
		} else {
			return s;
		}
	}
}