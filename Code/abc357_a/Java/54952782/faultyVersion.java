

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner sc = new Scanner(System.in);
		//宇宙人の人数
		int N = sc.nextInt();
		//消毒液が消毒できる手の数
		int M = sc.nextInt();
		
		int[] H = new int[N];
		for (int i = 0;i < N ; i ++) {
			H[i] = sc.nextInt();
		}
		for (int j = 1 ; j < N; j ++) {
			M = M - H[j];
			if ( M <0) {
				System.out.println(j);
				return ;
			}
		} System.out.println(N);		
	}

}
