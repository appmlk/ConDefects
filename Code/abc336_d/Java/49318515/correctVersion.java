import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);) {
			int n = Integer.parseInt(sc.next());
			int[] a = new int[n];
			for(int i = 0; i < n; i++) a[i] = Integer.parseInt(sc.next());
			int[] l = new int[n];
			int[] r = new int[n];
			l[0] = Math.min(a[0], 1);
			for(int i = 1; i < n; i++) l[i] = Math.min(a[i], l[i - 1] + 1);
			
			r[n - 1] = Math.min(a[n - 1], 1);
			for(int i = n - 2; i >= 0; i--) r[i] = Math.min(a[i], r[i + 1] + 1);
			
			int ans = 0;
			for(int i = 0; i < n; i++) ans = Math.max(ans, Math.min(l[i], r[i]));
			System.out.println(ans);
		}
	}
	
}

