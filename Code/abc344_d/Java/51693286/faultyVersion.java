import java.io.*;
import java.util.Arrays;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    static StreamTokenizer stmInput = new StreamTokenizer(br);
    static String str;
    static int N = 110;
    static int f[][] = new int[N][N];
    static int n, m, INF = 0x3f3f3f3f;

    public static String readString() throws IOException{
        stmInput.nextToken();
        return stmInput.sval;
    }

    public static int readInt() throws IOException {
        stmInput.nextToken();
        return (int) stmInput.nval;
    }

    public static void solve() throws IOException {
        str = readString();
        n = readInt();
        m = str.length();
        for (int i = 0; i < N; i++) Arrays.fill(f[i], INF);
        f[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            int cnt = readInt();
            while (cnt-- != 0) {
                String s = readString();
                for (int j = 1; j <= m; j++) {
                    f[i][j] = Math.min(f[i][j], f[i - 1][j]);
                    if (s.equals(str.substring(Math.max(0, j - s.length()), j))) {
                        f[i][j] = Math.min(f[i][j], f[i - 1][j - s.length()] + 1);
                    }
                }
            }
        }
        pw.println(f[n][m] == INF ? -1 : f[n][m]);
    }

    public static void main(String[] args) throws IOException {
//        按照ascii码范围设置为普通字符
        stmInput.ordinaryChars('a', 'z');
        stmInput.wordChars('a', 'z');

        int T = 1;
        while(T-- != 0){
            solve();
        }

        pw.flush();
        pw.close();
        br.close();
    }

}
