import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		//B - Mex
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N];
		int ans = 0;
		
		for(int i=0; i<N; i++) {
			arr[i] = sc.nextInt();
		}
		
		for(int i=1; i==i; i++) {
			
			int cnt = 0;
			for(int j=0; j<arr.length; j++) {
				
				if(arr[j] == i) cnt++;
			}
			
			if(cnt == 0) {
				ans = i;
				break;
			}
		}
		
		System.out.println(ans);
	}
}
