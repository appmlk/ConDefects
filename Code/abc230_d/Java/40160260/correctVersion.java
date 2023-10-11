import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int n;
	static int d;
	static Pair[] p;	
 
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		d = sc.nextInt();
		p = new Pair[n];		
		
		for(int i = 0; i < n ;i++) {
			p[i] = new Pair(sc.nextLong(), sc.nextLong());
		}
		
		Arrays.sort(p);
		
		long ans = 0;
		long x = Long.MIN_VALUE;
		long l = 0;
		long r = 0;
		
		for(int i = 0 ; i < n ; i++) {
			l = p[i].getL();
			r = p[i].getR();
			
			if(x + d - 1 < l) {
				ans++;
				x = r;
			}
		}
		
		System.out.println(ans);		
		
	}
}
 
class Pair implements Comparable<Pair>{ 
	long l;
	long r;
	
	Pair(long l ,long r) {
		this.l = l;
		this.r = r;
	}
	
	public long getL() {
		return l;
	}
	
	public long getR() {
		return r;
	}
 
	@Override
	public int compareTo(Pair o) {
		if(this.r < o.r) {
			return -1;
		}
		if(this.r == o.r && this.l < o.l) {
			return -1;
		}
		if(this.r == o.r && this.l == o.l) {
			return 0;
		}
		return 1;
	}	
}
