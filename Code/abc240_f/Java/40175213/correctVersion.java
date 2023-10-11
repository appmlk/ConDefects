import java.util.Scanner;
class Main{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		while(T-->0){
			int N = sc.nextInt();
			int M = sc.nextInt();
			long now = 0;
			long B = 0;
			long max = Long.MIN_VALUE;
			while(N-->0){
				int x = sc.nextInt();
				int y = sc.nextInt();
				long sum = (long)y*(y+1)/2;
				long k = 0<B&&x<0?max(1,min(y,-(B+x)/x+1)):1;
				max = max(max,now+B+x,now+B*k+k*(k+1)/2*x,now+y*B+sum*x);
				now += y*B+sum*x;
				B += (long)x*y;
			}
			System.out.println(max);
		}
	}
	private static long max(long... num){
		long ans = num[0];
		for(int i=1;i<num.length;i++)
			ans = Math.max(ans,num[i]);
		return ans;
	}
	private static long min(long... num){
		long ans = num[0];
		for(int i=1;i<num.length;i++)
			ans = Math.min(ans,num[i]);
		return ans;
	}
}
