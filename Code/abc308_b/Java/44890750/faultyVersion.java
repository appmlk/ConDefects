import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		int M = scanner.nextInt();
		
		ArrayList<String> list = new ArrayList<>();
		int[] price = new int[M];
		String[] strs = new String[N];
		for(int i = 0; i < N; i++) {
			strs[i] = scanner.next();
		}
		
		for(int i = 0; i < M; i++) {
			String str = scanner.next();
			list.add(str);
		}
		
		int default_price = scanner.nextInt();
		for(int i = 0; i < N - 1; i++) {
			price[i] = scanner.nextInt();
		}
		
		int result = 0;
		for(String key : strs) {
			int money = list.contains(key) ? price[list.indexOf(key)] : default_price;
			result += money;
		}
		System.out.println(result);

	}

}