import java.util.*;
public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner  sc = new Scanner(System.in);
		int n = sc.nextInt();
		long k = sc.nextLong();
		long[] a = new long[n];
		long rem = 0;
		for(int i = 0;i < n;i++) {
			long ai = Long.parseLong(sc.next());
			a[i] = ai;
			rem += ai;
		}long left = 0;
		long right = k + 1;
		while(left < right - 1) {
			long mid = (left + right)/(long)2;
			long tmp = k;
			for(int i = 0;i < n;i++) {
				tmp -= Math.min(mid, a[i]);
				if(tmp < 0) {
					break;
				}
			}if(tmp >= 0) {
				left = mid;
				rem = tmp;
			}else {
				right = mid;
			}
		}StringBuilder sb = new StringBuilder();
		//System.out.println(right);
		for(int i = 0;i < n;i++) {
			a[i] = Math.max(0, a[i] - left);
			if(a[i] > 0 && rem > 0) {
				a[i] -= (long)1;
				rem -= (long)1;
			}sb.append(a[i]);
			sb.append(" ");
		}System.out.print(sb);
	}

}
