
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		MyPoint[] points = new MyPoint[n];
		for(int i = 0; i < n; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			int p = sc.nextInt();
			
			points[i] = new MyPoint(x, y, p);
		}
		
		long dist[][] = warshallFloyd(points);
		
		long minMaxDist = INF;
		for(int i = 0; i < n; i++) {
			long tmp = 0;
			for(int j = 0; j < n; j++) {
				tmp = Math.max(tmp, dist[i][j]);
			}
			minMaxDist = Math.min(minMaxDist, tmp);
		}
		
		System.out.println((long)Math.ceil(minMaxDist));
    }
	
	static long INF = 1_000_000_000_000L;

	static long[][] warshallFloyd(MyPoint[] points){
		int n = points.length;

		long[][] dist = new long[n][n];

		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				if(i == j){
					dist[i][j] = 0;
				}
				else {
					dist[i][j] = (long)Math.ceil((double)points[i].dist(points[j]) / points[i].power);
				}
			}
		}
		

		for(int k = 0; k < n; k++){
			for(int i = 0; i < n; i++){
				for(int j = 0; j < n; j++){
					if(dist[i][k] == INF || dist[k][j] == INF){
						continue;
					}
					dist[i][j] = Math.min(dist[i][j], Math.max(dist[i][k], dist[k][j]));
				}
			}
		}

		return dist;
	}
}

class MyPoint {
	int x;
	int y;
	int power;
	
	MyPoint(int x, int y, int power){
		this.x = x;
		this.y = y;
		this.power = power;
	}
	
	long dist(MyPoint p) {
		return (long)Math.abs(this.x - p.x) + Math.abs(this.y - p.y);
	}
}