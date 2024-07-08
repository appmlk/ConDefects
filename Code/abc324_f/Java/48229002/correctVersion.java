import java.util.Scanner;
import java.util.HashMap;
import java.util.Arrays;
class Main{
	public static void main(String[] args){
		long start = System.nanoTime();
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[][] path = new int[M][];
		for(int i=0;i<M;i++){
			int u = sc.nextInt();
			int v = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			path[i] = new int[]{u,v,b,c};
		}
		Arrays.sort(path,(a,b)->Integer.compare(a[0],b[0]));
		double a = 0;
		double b = 10000;
		while(a!=(a+b)/2&&b!=(a+b)/2){
			double c = (a+b)/2;
			HashMap<Integer,Double> map = new HashMap<>();
			map.put(1,0.0);
			for(int[] p:path)
				if(map.containsKey(p[0]))
						map.merge(p[1],map.get(p[0])+p[2]-c*p[3],Double::max);
			if(0<=map.get(N))
				a = c;
			else
				b = c;
		}
		System.out.printf("%.10f",a);
	}
}
