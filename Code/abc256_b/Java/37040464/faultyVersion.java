import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] a = new int[n];
		for(int i = 0;i<n;i++) {
			a[i] = sc.nextInt();
		}
		int ans = n;
		int total = 0;
		for(int i = n-1;i>0;i--) {
			total +=a[i];
			if(total>=4)break;
			ans--;
		}
		
      System.out.println(ans);
	}
}
