import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		String t = sc.next();
		String[] T = t.split("");
		sc.close();
		
		boolean b = false;
		int n = s.indexOf(T[0].toLowerCase());
		if (n != -1) {
			String u = s.substring(n + 1);
			int m = u.indexOf(T[1].toLowerCase());
			if (m != -1) {
				String v = u.substring(m + 1);
				int l = v.indexOf(T[2].toLowerCase());
				if (l != -1 || "X".equals(T[2])) {
					b = true;
				}
			}
		}
		System.out.println(b ? "Yes" : "No");
	}
}