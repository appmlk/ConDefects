import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    static StreamTokenizer stmInput = new StreamTokenizer(br);
    static int N = 1010;
    static long s[][] = new long[N][N];
    static int n, m;

    public static String readString() throws IOException{
        stmInput.nextToken();
        return stmInput.sval;
    }

    public static int readInt() throws IOException {
        stmInput.nextToken();
        return (int) stmInput.nval;
    }

    public static long f(int c, int d){
        long ans = 0;
        ans = ((long)c / n) * (d / n) * s[n][n] + (d / n) * s[c % n][n] + (c / n) * s[n][d % n] + s[c % n][d % n];
        return ans;
    }

    public static void solve() throws IOException{
        n = readInt();
        m = readInt();
        for(int i = 1; i <= n; i++){
            String str = readString();
            for(int j = 1; j <= n; j++){
                if (str.charAt(j - 1) == 'B') s[i][j] = 1;
            }
        }

        for(int i = 1; i <= n; i++){
            for (int j = 1; j <= n; j++){
                s[i][j] += s[i - 1][j] + s[i][j - 1] - s[i - 1][j - 1];
            }
        }

        while(m-- != 0){
            int a = readInt() + 1, b = readInt() + 1, c = readInt() + 1, d = readInt() + 1;
            pw.println(f(c, d) - f(c, b - 1) - f(a - 1, d) + f(a - 1, b - 1));
        }

    }

    public static void main(String[] args) throws IOException {
//        按照ascii码范围设置为普通字符
        stmInput.ordinaryChars('A', 'Z');
        stmInput.wordChars('A', 'Z');

//        处理多组测试数据
//        while(stmInput.nextToken() != StreamTokenizer.TT_EOF){
//            n = (int) stmInput.nval;
//            m = readInt();
//            solve();
//        }

        int T = 1;
        while(T-- != 0){
            solve();
        }

        pw.flush();
        pw.close();
        br.close();
    }

}
