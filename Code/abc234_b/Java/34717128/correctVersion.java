import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

	static Scanner $ = new Scanner(System.in);
	static PrintWriter out = new PrintWriter(System.out);

	public static void main(String[] args) {

		// 234_
		
		int N = $.nextInt();
		
		int x[] = new int[N];
		int y[] = new int[N];
		
		for(int i=0; i<N; i++) {
			x[i] = $.nextInt();
			y[i] = $.nextInt();
		}
		
		double len = 0;
		double max = 0;
		for(int i=0; i<N; i++) {
			for(int j=i+1; j<N; j++) {
				int w = Math.abs(x[i] - x[j]);
				int h = Math.abs(y[i] - y[j]);
				
			 len = Math.sqrt(w*w+h*h);
			 max = Math.max(len, max);
			}
			
		}
		
		
		System.out.println(max);
		

		
		
		}
}