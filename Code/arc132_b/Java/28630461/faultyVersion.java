
import java.util.Scanner;

public class Main {

	public static void main(String[] args)  {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		
		int a[] = new int[n];
		
		for(int i = 0; i < n; i++) {
			a[i] = sc.nextInt();
		}
		
		int index1 = -1;
		for(int i = 0; i < n; i++) {
			if(a[i] == 1) {
				index1 = i;
			}
		}
		
		
		if(a[0] == 1 && a[1] == 2) {
			System.out.println(0);
			return;
		}
		
		if(a[n - 1] == 1 && a[n - 2] == 2) {
			System.out.println(1);
			return;
		}	
		
		//前半が昇順かどうか
		boolean ascFirst = true;
		
		if(index1 == 0 || a[index1 - 1] == 2) {
			ascFirst = false;
		}
		
		int result = 0;
		
		if(ascFirst) {
			int left = index1;
			int right = n - index1;
			
			result = Math.min(left, right + 2);
		}
		else {
			int left = index1 + 1;
			int right = n - left;
			
			result = Math.min(left + 1, right + 2);
		}
		
		System.out.println(result);
	}
}