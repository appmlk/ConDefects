import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.next());
		List<String> list = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			list.add(sc.next());
		}
		sc.close();

		//盤面生成
		String[][] S = new String[n][n];
		for (int i = 0; i < n; i++) {
			String[] s = list.get(i).split("");
			for (int j = 0; j < n; j++) {
				S[i][j] = s[j];
			}
		}

		//行ごとの〇の数をカウント
		List<Integer> column = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			int c = 0;
			for (int j = 0; j < n; j++) {
				if ("o".equals(S[i][j])) {
					c++;
				}
			}
			column.add(c);
		}

		//列ごとの〇の数をカウント
		List<Integer> row = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			int r = 0;
			for (int j = 0; j < n; j++) {
				if ("o".equals(S[j][i])) {
					r++;
				}
			}
			row.add(r);
		}

		//Lのカウント
		int sum = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if ("o".equals(S[i][j])) {
					sum += (column.get(i) - 1) * (row.get(j) - 1);
				}
			}
		}
		System.out.println(sum);
	}
}