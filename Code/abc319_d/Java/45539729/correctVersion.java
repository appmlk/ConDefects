import java.util.Scanner;

public class Main {
	public static void main(final String[] args) {
		try (final Scanner sc = new Scanner(System.in)) {
			final int N = Integer.parseInt(sc.next());
			final int M = Integer.parseInt(sc.next());
			final int[] L = new int[N];
			long strLength = 0;
			long maxLetter = Integer.MIN_VALUE;
			for (int i = 0; i < N; i++) {

				L[i] = Integer.parseInt(sc.next()) + 1;
				strLength += L[i];
				if (maxLetter < L[i] - 1) {
					maxLetter = L[i] - 1;
				}
			}

			// 二分探索
			while (maxLetter + 1 < strLength) {
				final long middle = (maxLetter + strLength) / 2;
				int rows = 1; // 今の行数
				long length = 0; // 先頭から何文字目か
				for (int i = 0; i < N; ++i) {
					length += L[i]; // 行の末尾に追加してみる
					if (length > middle) { // はみ出たら
						++rows; // 改行して
						length = L[i]; // 先頭に追加
					}
				}
				if (rows > M) { // 縦幅が足りていなければ
					maxLetter = middle; // 答えは middle より大きい
				} else { // 縦幅が足りていなければ
					strLength = middle; // 答えは middle 以下
				}
			}
			// 答えから 1 を引いて出力
			System.out.println(strLength - 1);
		}
	}
}