import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		long S = 0, T = 0 , num = 0;
		long[] money = new long[N];
		
		for(int i=0; i<N; i++) {
			money[i] = sc.nextLong();
		}
		
			for(int i=0; i<N-1; i++) {
				S = sc.nextLong();
				T = sc.nextLong();
				if(money[i] > S) {
					num = money[i] / S;
					money[i+1] += num * T;
				}
			}
		System.out.println(money[N-1]);
	}
}