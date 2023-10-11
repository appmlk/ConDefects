
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(System.out);

		// アルファベットごとに文字数をカウントする
		ArrayList<Character> s = new ArrayList<Character>();
		ArrayList<Character> t = new ArrayList<Character>();

		int[] sAtcoder = new int[8];
		int[] tAtcoder = new int[8];

		for (char str : sc.next().toCharArray()) {
			switch (str) {
			case 'a':
				sAtcoder[0]++;
				break;
			case 't':
				sAtcoder[1]++;
				break;
			case 'c':
				sAtcoder[2]++;
				break;
			case 'o':
				sAtcoder[3]++;
				break;
			case 'd':
				sAtcoder[4]++;
				break;
			case 'e':
				sAtcoder[5]++;
				break;
			case 'r':
				sAtcoder[6]++;
				break;
			case '@':
				sAtcoder[7]++;
				break;
			default:
				s.add(str);
				break;
			}
		}

		for (char str : sc.next().toCharArray()) {
			switch (str) {
			case 'a':
				tAtcoder[0]++;
				break;
			case 't':
				tAtcoder[1]++;
				break;
			case 'c':
				tAtcoder[2]++;
				break;
			case 'o':
				tAtcoder[3]++;
				break;
			case 'd':
				tAtcoder[4]++;
				break;
			case 'e':
				tAtcoder[5]++;
				break;
			case 'r':
				tAtcoder[6]++;
				break;
			case '@':
				tAtcoder[7]++;
				break;
			default:
				t.add(str);
				break;
			}
		}

		// 準備完了 atcoderから比較していく
		for (int i = 0; i < 7; i++) {
			if (tAtcoder[i] == sAtcoder[i]) {
				continue;
			} else if (tAtcoder[i] > sAtcoder[i]) {
				int diff = tAtcoder[i] - sAtcoder[i];
				sAtcoder[7] -= diff;
			} else {
				int diff = sAtcoder[i] - tAtcoder[i];
				tAtcoder[7] -= diff;
			}
		}

		// ワイルドカードが0出ないければ一致しない
		if (sAtcoder[7] != tAtcoder[7]) {
			pw.println("No");
			pw.flush();
			return;
		}

		// その他の文字をみる
		Collections.sort(s);
		Collections.sort(t);
		for (int i = 0; i < s.size(); i++) {
			if (s.get(i) != t.get(i)) {
				pw.println("No");
				pw.flush();
				return;
			}
		}
		
		pw.println("Yes");
		pw.flush();

	}
}
