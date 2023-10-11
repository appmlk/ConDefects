import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] array = new int[N];
		for(int i = 0; i < N; i++) {
			array[i] = sc.nextInt();
		}
		int max = 0;
		for(int i = 0; i < N; i++) {
			if(array[i] > max) {
				max = array[i];
			} else {
				break;
			}
		}
		StringBuilder ans = new StringBuilder();
		for(int i = 0; i < N; i++) {
			if(array[i] != max) {
				ans.append(array[i]);
				if(i != N - 1) {
					ans.append(" ");
				}
			}
		}
		System.out.println(ans);
		sc.close();
	}
}