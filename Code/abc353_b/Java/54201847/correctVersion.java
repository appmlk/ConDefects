import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n, k;
		n = s.nextInt();
		k = s.nextInt();
		int a[] = new int[n];
		int i;
		for(i = 0; i < n; i++) {
			a[i] = s.nextInt();
		}
		
//		for(i = 0; i < n; i++) {
//			System.out.println(a[i]);
//		}
		
		int count = 0, result = k;
		for(i = 0; i < n; i++) {
			if(result > a[i]) {
				result -= a[i];
			}else if(result == a[i]) {
				result = k;
				count++;
			}else {
				count++;
				result = k - a[i];
			}
//			if(i == n-1 && k > result) {
//				count++;
//			}
		}
		if(result != k) {
			count++;
		}
		System.out.println(count);
		s.close();
	}

}
