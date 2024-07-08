import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);) {
			int n = Integer.parseInt(sc.next());
			int[] a= new int[n];
			int[] b = new int[200010];
			
			for(int i = 0; i < n; i++) a[i] = Integer.parseInt(sc.next());
			
			long ans = 0;
			for(int i = 0; i < n; i++) {
				int v = a[i];
				
				for(int j = 2; j * j <= a[i]; j++) {
					while(v % (j * j) == 0) {
						v /= j * j;
					}
				}
				
				ans += b[v];
				b[v]++;
			}
			
			ans += 1L * b[0] * (n - b[0]);
			System.out.println(ans);
		}
	}
}
