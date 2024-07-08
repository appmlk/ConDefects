import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner sc = new Scanner(System.in);
		int q = sc.nextInt();
		int [][] query = new int [q][2];
		ArrayList<Integer> list = new ArrayList<>();
		for(int i = 0; i < q; i++) {
			query[i][0] = sc.nextInt();
			query[i][1] = sc.nextInt();
		}
		for(int i = 0; i < q; i++) {
			if(query[i][0] == 1) {
				list.add(query[list.size()][1]);
			} else {
				System.out.println(list.get(list.size() - query[i][1]));
			}
		}

	}

}
