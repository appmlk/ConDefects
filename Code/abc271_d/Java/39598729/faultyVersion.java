
import java.util.Arrays;
import java.util.Scanner;

public class Main {
		//2022/10/01  271
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		int n=sc.nextInt();
		int sum=sc.nextInt();
		
		int[][] nums=new int[n][2];
		for(int i=0;i<n;i++) {
			Arrays.fill(nums[i], 0);
		}
		
//		for(int[] i:nums) {
//			System.out.println("****");
//			for(int j:i) {
//				System.out.print(j+",");
//			}
//		}
//		//nums=>0,0,0,0,0,0
//		System.out.println("========");
//		
		for(int i=0;i<n;i++) {
			int a=sc.nextInt();
			int b=sc.nextInt();
			
			nums[i][0]=a;
			nums[i][1]=b;
		}
		
		int[][] dp=new int[n+1][sum+1];
		dp[0][0]=1;
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<=sum;j++) {
				if(dp[i][j]==0)continue;
				if(j+nums[i][0]<=sum) {
					dp[i+1][j+nums[i][0]]=1;
//					System.out.print("i="+i+"j="+j+",");
//					System.out.println("dp[i+1][j+nums[i][0]]="+dp[i+1][j+nums[i][0]]);
				}
				if(j+nums[i][1]<=sum) {
					dp[i+1][j+nums[i][1]]=1;
//					System.out.print("i="+i+"j="+j+",");
//					System.out.println("dp[i+1][j+nums[i][1]]="+dp[i+1][j+nums[i][1]]);
				}
				
			}
		}
//		for(int[] i:dp) {
//			System.out.println("****");
//			for(int j:i)System.out.print(j+".");
//		}
		
		
		
		if(dp[n][sum]==0) {
			System.out.println("No");
			//elseではゴールが存在することが確定
		}else {
			System.out.println("Yes");
			StringBuilder sb=new StringBuilder();
			for(int i=n-1;i>=0;i--) {
				if(sum>=nums[i][0] && dp[i][sum-nums[i][0]]==1) {
					sb.append("H");
					sum-=nums[i][1];
				}else {
					sb.append("T");
					sum-=nums[i][1];
				}
				
			}
			System.out.println(sb.reverse());
		}
    }
}
