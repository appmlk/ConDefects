import java.util.Scanner;

public class Main{
	public static void main(String[] args){
		var sc = new Scanner(System.in);
		int n = sc.nextInt();
		int t = sc.nextInt();
		if(n > t){
		  System.out.println(-1);
		  sc.close();
		  System.exit(0);
		}
		var hori = new int[n];
		var vert = new int[n];
		int slash = 0;
		int backs = 0;
		for(int x = 1;x <= t; x++){
		  int m = sc.nextInt();
		  hori[(m - 1) / n]++;
		  vert[(m - 1) % n]++;
		  if(m % (n + 1) == 1)
		  backs++;
		  if(m != 1 && m % (n - 1) == 1 && m / n != n)
		  slash++;
		  if(x < n)
		  continue;
		  if(hori[(m - 1) / n] == n || vert[(m - 1) % n] == n ||slash == n || backs == n){
		    System.out.println(x);
		    System.exit(0);
		  }
		  
		}
		System.out.println(-1);
	}
}