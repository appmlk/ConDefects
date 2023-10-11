import java.util.*;

public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int same = 0;
		int[] a = new int[n + 1];
		long ans = 0;
		for(int i = 1;i <= n;i++) {
			int p = sc.nextInt();
			if(p == i) {
				same++;
			}a[i] = p;
		}ans += (same - 1) * same/2;
		long other = 0;
		for(int i = 1;i<= n;i++) {
			if(a[i] == i) {
				continue;
			}if(a[a[i]] == i) {
				other++;
			}
		}System.out.print(ans + other/2);
	}

}