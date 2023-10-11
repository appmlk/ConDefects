import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		var sc = new Scanner(System.in);
		var t = sc.nextInt();
		var n = new int[t];
		var s = new String[t];
		for (var i = 0; i < t; i++) {
			n[i] = sc.nextInt();
			s[i] = sc.next();
		}
		sc.close();

		var sb = new StringBuilder();
		for (var i = 0; i < t; i++) {
			var result = calc(n[i], s[i]);
			sb.append(result);
			sb.append("\r\n");
		}
		System.out.println(sb.toString());
	}

	private static String calc(int n, String s) {
		var chars = s.toCharArray();

		var count1 = 0;
		var count2 = 0;
		for (var i = 0; i < n; i++) {
			if (chars[i] == 'B') {
				break;
			}
			count1++;
		}
		for (var i = n-1; i >= 0; i--) {
			if (chars[i] == 'A') {
				break;
			}
			count2++;
		}
		if (count1 + count2 == n && count2 >= count1) {
			return "B";
		}

		return "A";
	}
}
