import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		long n = Long.parseLong(sc.next());
		long cube = 0L;
		for (long i = 1L; i * i * i < n; i++) {
			String s = i * i * i + "";
			if (check(s) == true) {
				cube = i * i * i;
			}
		}
		System.out.println(cube);
		
	}

	private static boolean check(String cube) {
		boolean f = false;
		if (cube.length() == 1) {
			return true;
		}
		for (int i = 0; i < cube.length() / 2; i++) {
			if (cube.charAt(i) == cube.charAt(cube.length() - i - 1)) {
				f = true;
			} else {
				f = false;
				return f;
			}
		}
		return f;
	}
}