import java.util.Scanner;

/**
 * メインクラス.
 */
class Main {
    private static String S;

    /**
     * メイン処理.
     *
     * @param args 実行時引数
     */
    public static void main(String[] args) {
        // 標準入力を取得
        getInput();

        // 求解処理
        solve();
    }

    /**
     * 標準入力を取得する.
     */
    private static void getInput() {
        final Scanner scanner = new Scanner(System.in);
        S = scanner.next();
        scanner.close();
    }

    /**
     * 求解処理.
     */
    private static void solve() {
        int ans = S.lastIndexOf('a');
        if (ans > 0) {
            ans++;
        }
        System.out.println(ans);
    }
}
