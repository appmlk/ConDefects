
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args)  {
		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();
		
		for(int i = 0; i < t; i++) {
			
			int a[] = new int[3];
			
			for(int j = 0; j < 3; j++) {
				a[j] = sc.nextInt();
			}
			
			int result = Integer.MAX_VALUE;
			
			Arrays.sort(a);
			
			if((a[1] - a[0]) % 3 == 0) {
				result = Math.min(a[1], result);
			}
			if((a[2] - a[0]) % 3 == 0) {
				result = Math.min(a[2], result);
			}
			if((a[2] - a[1]) % 3 == 0) {
				result = Math.min(a[1], result);
			}
			
			if(result < Integer.MAX_VALUE) {
				System.out.println(result);
			}
			else {
				System.out.println(-1);
			}
		}
	}		
}