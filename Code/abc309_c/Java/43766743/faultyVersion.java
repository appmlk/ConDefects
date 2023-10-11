import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		long total = 0;
		long ans = 0;
		int[][] arr = new int[n][2];
		for(int i = 0; i < n; i++) {
			arr[i][0] = sc.nextInt();
			arr[i][1] = sc.nextInt();
			total += arr[i][1];
		}
		Arrays.sort(arr, Comparator.comparingInt(a -> a[0]));
		for(int i = 0; i < n; i++) {
			if(total > k) {
				total -= arr[i][1];
				ans = arr[i][0];
			}else {
				System.out.println(ans + 1);
				break;
			}
						
		}
	}
}
