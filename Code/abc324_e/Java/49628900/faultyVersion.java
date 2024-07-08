import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);) {
			int n = Integer.parseInt(sc.next());
			String t = sc.next();
			List<String> s = new ArrayList<String>();
			for(int i = 0; i < n; i++) s.add(sc.next());
			int[] pre = new int[n];
			for(int i = 0; i < n; i++) pre[i] = calc(s.get(i), t);
			int[] suf = new int[n];
			int[] cnt = new int[t.length() + 1];
			StringBuilder sb = new StringBuilder();
			String rt = sb.append(t).reverse().toString();
			for(int i = 0; i < n; i++) {
				sb.setLength(0);
				String rs = sb.append(s.get(i)).reverse().toString();
				suf[i] = calc(rs, rt);
				cnt[suf[i]]++;
			}
			
			int ans = 0;
			for(int i = 0; i < n; i++) {
				int l = t.length() - pre[i];
				for(int j = l; j <= t.length(); j++) ans += cnt[j];
			}
			
			System.out.println(ans);
		}
	}
	
	static int calc(String s, String t) {
		int g = 0;
		
		for(int i = 0; i < s.length(); i++) {
			if(g >= t.length()) break;
			if(s.charAt(i) == t.charAt(g)) g++;
		}
		
		return g;
	}
}