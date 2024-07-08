import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		int[] row = new int[N];
		int[] col = new int[N];

		ArrayList<ArrayList<Character>> map = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			String str = sc.next();

			ArrayList<Character> rowdata = new ArrayList<>();

			for (int s = 0; s < N; s++) {
				char c = str.charAt(s);
				if (c == 'o') {
					row[i] += 1;
					col[s] += 1;
				}

				rowdata.add(c);
			}

			map.add(rowdata);
		}

		int ans = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				char ch = map.get(r).get(c);

				if (ch == 'o') {
					ans += (row[r] - 1) * (col[c] - 1);
				}

			}
		}

		System.out.println(ans);
	}
}