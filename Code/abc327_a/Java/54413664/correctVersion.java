import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.next());
		String s = sc.next();
		sc.close();
		boolean bool = false;
		
		for (int i = 0; i < n - 1; i++) {
			String str = s.substring(i, i + 2);
			if ("ab".equals(str) || "ba".equals(str)) {
				bool = true;
				break;
			}
		}
		System.out.println(bool ? "Yes" : "No");
	}
}