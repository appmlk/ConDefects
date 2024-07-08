
import java.util.Scanner;
import java.util.TreeSet;

public class Main {
//
	public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();
        int k = scan.nextInt();

        int[] p = new int[n+1];
        TreeSet<Integer> pSet = new TreeSet<Integer>();

        for (int i = 0; i < n; i++) {
        	p[scan.nextInt()] = i;
		}
		scan.close();

        for (int i = 1; i < k; i++) {
        	pSet.add(p[i]);
		}
		
		int ans = n;
		
		for (int j = 1; j <= n-k+1; j++) {
			pSet.add( p[j+k-1]);
//			System.out.println(pSet.last() +" "+ pSet.first());
			ans=Math.min(ans, pSet.last() - pSet.first());
			pSet.remove(p[j]);
		
		}
		System.out.println(ans);
	}
}
