
import java.util.Scanner;

public class Main {

	public static void main(String[] args)  {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		int a[] = new int[n];
		for(int i = 0; i < n; i++) {
			a[i] = sc.nextInt();
		}
		
		int maxDiff[] = new int[n];
		int minDiff[] = new int[n];
		int max = 0;
		int min = Integer.MAX_VALUE;
		
		for(int i = 0; i < n; i++) {
			if(i == 0) {
				maxDiff[i] = minDiff[i] = a[i] == 1 ? 1 : -1;
			}
			
			else {
				if(a[i] == 0) {
					maxDiff[i] = Math.max(-1, maxDiff[i - 1] - 1);
					minDiff[i] = Math.min(-1, minDiff[i - 1] - 1);
				}
				else {
					maxDiff[i] = Math.max(1, maxDiff[i - 1] + 1);
					minDiff[i] = Math.min(1, minDiff[i - 1] + 1);
				}
				
			}
			
			max = Math.max(max, maxDiff[i]);
			min = Math.min(min, minDiff[i]);
			
//			System.out.println("i = " + i + " " + maxDiff[i] + " " + minDiff[i]);
		}
		
//		System.out.println(max + " " + min);
		System.out.println(max - min + 1);
		
	}
}