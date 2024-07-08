import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	static int n;
	static char[] r;
	static char[] c;
	
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);) {
			n = Integer.parseInt(sc.next());
			r = sc.next().toCharArray();
			c = sc.next().toCharArray();
			
			int[] x = new int[n];
			for(int i = 0; i < n; i++) x[i] = i;
			int a = 0;
			do {
				int[] y = new int[n];
				for(int i = 0; i < n; i++) y[i] = i;
				do {
					int[] z = new int[n];
					for(int i = 0; i < n; i++) z[i] = i;
					do {
						char[][] ans = new char[n][n];
						for(int i = 0; i < n; i++) Arrays.fill(ans[i], '.');
						
						boolean ok = true;
						for(int i = 0; i < n; i++) ans[i][x[i]] = 'A';
						for(int i = 0; i < n; i++) {
							if(ans[i][y[i]] != '.') {
								ok = false;
								break;
							}
							ans[i][y[i]] = 'B';
						}
						
						if(!ok) continue;
						for(int i = 0; i < n; i++) {
							if(ans[i][z[i]] != '.') {
								ok = false;
								break;
							}
							ans[i][z[i]] = 'C';
						}
						
						if(ok && check(ans)) {
							System.out.println("Yes");
							for(int i = 0; i < n; i++) {
								for(char c : ans[i]) System.out.print(c);
								System.out.println();
							}
							return;
						}
					} while (nextPermutation(z));
				} while (nextPermutation(y));
			} while (nextPermutation(x));
			
			System.out.println("No");
			
		}
	}
	
	static boolean check(char[][] a) {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(a[i][j] != '.' && a[i][j] != r[i]) {
					return false;
				}
				if(a[i][j] == r[i]) {
					break;
				}
			}
			for(int j = 0; j < n; j++) {
				if(a[j][i] != '.' && a[j][i] != c[i]) {
					return false;
				}
				if(a[j][i] == c[i]) {
					break;
				}
			}
		}
		return true;
	}
	
	static boolean nextPermutation(int[] arr) {
		int len = arr.length;
		int index = len - 2;
		
		while(index >= 0 && arr[index] >= arr[index + 1]) index--;
		
		if(index < 0) return false;
		
		int target = index + 1;
		for(int i = target; i < arr.length; i++) {
			if(arr[i] > arr[index]) target = i;
		}
		
		int tmp = arr[index];
		arr[index] = arr[target];
		arr[target] = tmp;
		Arrays.sort(arr, index + 1, arr.length);
		
		return true;
	}
	
}


