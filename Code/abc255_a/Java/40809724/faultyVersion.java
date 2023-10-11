import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner input = new Scanner(System.in);
		
		int a = input.nextInt() - 1;
		int b = input.nextInt() - 1;
		int [][] A = new int[2][2];
		
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				A[i][j] = input.nextInt();
			}
		
		System.out.println(A[a][b]);
		}
	}

}

