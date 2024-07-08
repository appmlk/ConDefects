

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);	
		int q = sc.nextInt();
		int k = sc.nextInt();
		sc.nextLine();
		
		long[] dp = new long[k+1];
		dp[0] = 1;
		
		for(int i=0;i<q;i++) {
			
			String[] temp = sc.nextLine().split(" ");
			int num = Integer.parseInt(temp[1]);
			if(temp[0].equals("+")) {
				for(int j=k;j>=1;j--) {
					if(j-num>=0)dp[j] += dp[j-num];
					dp[j]%=998244353;
				}
			} else if(temp[0].equals("-")) {
				for(int j=1;j<=k;j++) {
					if(j-num>=0)dp[j]+=(998244353-dp[j-num]);
					dp[j]%=998244353;
				}
			}
			
			System.out.println(dp[k]);
			
		}
		
		
	}

}
