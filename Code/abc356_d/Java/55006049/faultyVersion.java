import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long n = l(sc);
		long m = l(sc);
		sc.close();
		long p = 998244353L;
		long count = 0L;
		long base = 1;
		while (m > 0) {
			if (m % 2 == 1) {
				count += n / (2 * base) * base + (long)Math.max(0, (n % (2 * base) - base));
			}
			m /= 2;
			count %= p;
			base *= 2;
		}
		p(count);
	}




	public static String s(Scanner sc) {
		return sc.next();
	}
	public static int i(Scanner sc) {
		return Integer.parseInt(sc.next());
	}
	public static long l(Scanner sc) {
		return Long.parseLong(sc.next());
	}
	public static void p(Object o) {
		System.out.print(o);
	}
	public static void pl(Object o) {
		System.out.println(o);
	}
	public static void sortI(List<Integer>... list) {
		for (List<Integer> c : list) {
			Collections.sort(c);
		}
	}
	public static void sortL(List<Long>... list) {
		for (List<Long> c : list) {
			Collections.sort(c);
		}
	}
	public static void reverseI(List<Integer>... list) {
		for (List<Integer> c : list) {
			Collections.reverse(c);
		}
	}
	public static void reverseL(List<Long>... list) {
		for (List<Long> c : list) {
			Collections.reverse(c);
		}
	}
	public static int gcd(int n, int m) {
		int a = Math.abs(n);
		int b = Math.abs(m);
		if (a < b) {
			int c = a;
			a = b;
			b = c;
		}
		int r = a % b;
		while (r != 0) {
			a = b;
			b = r;
			r = a % b;
		}
		return b;
	}
	public static long gcd(long n, long m) {
		long a = Math.abs(n);
		long b = Math.abs(m);
		if (a < b) {
			long c = a;
			a = b;
			b = c;
		}
		long r = a % b;
		while (r != 0) {
			a = b;
			b = r;
			r = a % b;
		}
		return b;
	}
	//base^index mod p を返す
	public static long powMod(long base, long index, long p) {
		long ans = 1;
		long a = base;
		while (index >= 1) {
			if (index % 2 == 1) {
				ans *= a;
				ans %= p;
			}
			a *= a;
			a %= p;
			index /= 2;
		}
		return ans % p;
	}
}
