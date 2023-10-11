import java.util.*;

public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int[] a = new int[n];
		int[] max = new int[n];
		Set<Integer> set = new TreeSet<>();
		int ansInd = n - k;
		for(int i = 0;i < n;i++) {
			a[i]  = Integer.parseInt(sc.next());
		}for(int i = 0;i < k;i++) {
			set.add(a[i]);
		}int renzoku = 0;
		for(int i = 1;i <= n - k;i++) {
			if(a[i - 1] < a[i]) {
				renzoku++;
			}else {
				renzoku = 0;
			}if(renzoku == k - 1) {
				ansInd = i;
				break;
			}if(i == n - k) {
				//System.out.println(renzoku + " " +   a[i]+ " " +i);
				ansInd = i - renzoku;
			}
		}Set<Integer> nextA = new TreeSet<>();
		for(int i = ansInd;i < ansInd + k;i++) {
			nextA.add(a[i]);
		}int plus = 0;
		for(int s:nextA) {
			a[ansInd + plus] = s;
			plus++;
		}StringBuilder sb = new StringBuilder();
		for(int i = 0;i < n;i++) {
			sb.append(a[i]);
			sb.append(" ");
		}System.out.print(sb);
	}
}