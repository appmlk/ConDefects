import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {
    private static final long MOD = 998244353L;
    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        Scanner sn = new Scanner(System.in);

        char[] s = sc.next().toCharArray();

        int cnt = 0;
        for(int i = 0; i < s.length; i++) {
            if (i != s.length - 1 && s[i] == '0') {
                Long zeroCnt = 1L;
                while (i + 1 <= s.length - 1 && s[i + 1] == '0') {
                    zeroCnt++;
                    i++;
                }
                cnt += Math.round(zeroCnt / 2.0);
            } else {
                cnt++;
            }
        }

        System.out.println(cnt);
    }

    private static long solve(int N, int[] A, int[] B) {
        long[] pow2 = new long[N + 1];
        pow2[0] = 1;
        for (int i = 1; i <= N; i++) {
            pow2[i] = (pow2[i - 1] * 2) % MOD;
        }

        long[] dp = new long[N + 1];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i <= N; i++) {
            if (A[i - 1] != B[i - 1]) {
                dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
            } else {
                dp[i] = dp[i - 1];
            }
        }

        return (pow2[N] - dp[N] + MOD) % MOD;
    }

    static int gcd(int a, int b) {
        while(a >= 1 && b >= 1) {
            if(a >= b) {
                a = (a % b);
            } else {
                b = (b % a);
            }
        }
        if (a != 0) return a;
        return b;
    }

    // 素因数分解を行う関数
    static Map<Long, Integer> factorize(long n) {
        Map<Long, Integer> factors = new HashMap<>();

        for (long i = 2; i * i <= n; i++) {
            while (n % i == 0) {
                n /= i;
                factors.put(i, factors.getOrDefault(i, 0) + 1);
            }
        }

        if (n != 1) {
            factors.put(n, 1);
        }

        return factors;
    }

    // p^eの約数の個数を求める関数
    static long countDivisors(long p, int e) {
        long res = 1;
        for (int i = 0; i <= e; i++) {
            res *= (i + 1);
        }
        return res;
    }

    // x^nを求める関数
    static long pow(long x, int n) {
        long res = 1;
        while (n > 0) {
            if ((n & 1) == 1) res *= x;
            x *= x;
            n >>= 1;
        }
        return res;
    }


    //入力文字列が一致するかどうかをチェックする再帰的関数
    //指定されたワイルドカードパターン
    public static boolean isMatch(String word, int n, String pattern, int m,
                                  Map<String, Boolean> lookup) {
        //入力の動的要素から一意のマップキーを作成します
        String key = n + "|" + m;

        //サブ問題が以前に見られた場合
        if (lookup.containsKey(key)) {
            return lookup.get(key);
        }

        //サブ問題が初めて見られるので、それを解決して
        //結果をマップに保存します

        //パターンの終わりに到達しました
        if (m == pattern.length()) {
            //入力文字列の終わりにも達した場合にのみtrueを返します
            lookup.put(key, (n == word.length()));
            return n == word.length();
        }

        //入力文字列が最後に達した場合、
        //パターンの残りの文字はすべて'*'です

        if (n == word.length()) {
            for (int i = m; i < pattern.length(); i++) {
                if (pattern.charAt(i) != '*') {
                    lookup.put(key, false);
                    return false;
                }
            }

            lookup.put(key, true);
            return true;
        }

        //現在のワイルドカード文字が「?」の場合またはの現在の文字
        //パターンは入力文字列の現在の文字と同じです

        if (word.charAt(m) == '?' || pattern.charAt(m) == '?' || pattern.charAt(m) == word.charAt(n)) {
            //パターンと入力文字列の次の文字に移動します
            lookup.put(key, isMatch(word, n + 1, pattern, m + 1, lookup));
        }

        //現在のワイルドカード文字が「*」の場合
        else if (pattern.charAt(m) == '*') {
            //入力文字列の次の文字に移動するか
            //'*'を無視して、パターン内の次の文字に移動します

            lookup.put(key, isMatch(word, n + 1, pattern, m, lookup) ||
                    isMatch(word, n, pattern, m + 1, lookup));
        } else {
            //パターン内の現在の文字がそうでないときにここに到達します
            //ワイルドカード文字であり、現在の文字と一致しません
            //入力文字列の文字

            lookup.put(key, false);
        }

        return lookup.get(key);
    }

    //文字列が特定のワイルドカードパターンと一致するかどうかを確認します
    public static String isMatch(String word, String pattern) {
        Map<String, Boolean> lookup = new HashMap<>();

        if (isMatch(word, 0, pattern, 0, lookup)) {
            return "Yes";

        } else {
            return "No";
        }
    }

    public static ArrayList<Boolean> es(Long n) {
        ArrayList<Boolean> flg = new ArrayList<Boolean>();

        flg.add(false);

        for (int i = 0; i < n - 1; i++) {
            flg.add(true);
        }

        for (int p = 2; p <= n; p++) {
            if (!flg.get(p)) continue;

            for (int q = p * 2; q <= n; q += p) {
                flg.set(q, false);
            }
        }

        return flg;
    }

    private static void dfs(int v, boolean[] flg, ArrayList<Integer>[] adjacent) {
        flg[v] = true;

        for (int i : adjacent[v]) {
            if (flg[i]) continue;
            dfs(i, flg, adjacent);
        }
        return;
    }

    private static long countOccurrences(String str, char ch) {
        return str.chars()
                .filter(c -> c == ch)
                .count();
    }

    private static long getCommonDivisor(long x, long y) {
        long biggerNum = Math.max(x, y);
        long smallerNum = Math.min(x, y);

        // 大きい方から小さい方を割った余を求める
        long surplus = biggerNum % smallerNum;

        // 割り切れていれば、それを返す
        if (surplus == 0) {
            return smallerNum;
        }
        // 割り切れなければ再帰的に自信を呼び出す
        surplus = getCommonDivisor(smallerNum, surplus);

        return surplus;
    }

    public static boolean isPrime(long num) {
        if (num < 2) return false;
        else if (num == 2) return true;
        else if (num % 2 == 0) return false; // 偶数はあらかじめ除く

        double sqrtNum = Math.sqrt(num);
        for (int i = 3; i <= sqrtNum; i += 2) {
            if (num % i == 0) {
                // 素数ではない
                return false;
            }
        }

        // 素数である
        return true;
    }

    // 組み合わせ
    public static long combination(int n, int r) {
        long c[][] = new long[n + 5][n + 5];

        //パスカルの三角形作成
        c[0][0] = 1;
        for (int i = 0; i < n + 3; i++) {
            for (int j = 0; j < n + 3; j++) {
                long tmp = c[i][j];
                c[i + 1][j] += tmp;
                c[i + 1][j + 1] += tmp;
            }
        }

        return c[n][r];
    }

    // 文字列比較
    public static boolean strCompare(String a, String b) {
        boolean result = false;
        int mod = 2147483647;

        if (a.hashCode() % mod == b.hashCode() % mod) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    public static Map<Integer, Set<Integer>> addGraph(Map<Integer, Set<Integer>> graph, int a, int b) {

        Set<Integer> set = graph.getOrDefault(a, new HashSet<>());
        set.add(b);
        graph.put(a, set);

        return graph;
    }
}

class FastScanner {
    private final InputStream in = System.in;
    private final byte[] buffer = new byte[1024];
    private int ptr = 0;
    private int buflen = 0;

    private boolean hasNextByte() {
        if (ptr < buflen) {
            return true;
        } else {
            ptr = 0;
            try {
                buflen = in.read(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (buflen <= 0) {
                return false;
            }
        }
        return true;
    }

    private int readByte() {
        if (hasNextByte()) return buffer[ptr++];
        else return -1;
    }

    private static boolean isPrintableChar(int c) {
        return 33 <= c && c <= 126;
    }

    private void skipUnprintable() {
        while (hasNextByte() && !isPrintableChar(buffer[ptr])) ptr++;
    }

    public boolean hasNext() {
        skipUnprintable();
        return hasNextByte();
    }

    public String next() {
        if (!hasNext()) throw new NoSuchElementException();
        StringBuilder sb = new StringBuilder();
        int b = readByte();
        while (isPrintableChar(b)) {
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }

    public long nextLong() {
        if (!hasNext()) throw new NoSuchElementException();
        long n = 0;
        boolean minus = false;
        int b = readByte();
        if (b == '-') {
            minus = true;
            b = readByte();
        }
        if (b < '0' || '9' < b) {
            throw new NumberFormatException();
        }
        while (true) {
            if ('0' <= b && b <= '9') {
                n *= 10;
                n += b - '0';
            } else if (b == -1 || !isPrintableChar(b)) {
                return minus ? -n : n;
            } else {
                throw new NumberFormatException();
            }
            b = readByte();
        }
    }
}

class UnionFind {
    int[] parent;
    int[] rank;

    public UnionFind(int n) {
        // 初期化コンストラクタ
        this.parent = new int[n + 1];
        this.rank = new int[n + 1];

        // 最初はすべてが根
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    /**
     * 要素の根を返す。
     * 経路圧縮付き。（1→3→2となっていて2をfindした際、1→3,2と木の深さを浅くする。）
     *
     * @param x
     * @return 要素xの根
     */
    public int find(int x) {
        if (x == parent[x]) {
            return x;
        } else {
            // 経路圧縮時はrank変更しない
            parent[x] = find(parent[x]);
            return parent[x];
        }
    }

    /**
     * ２つの要素が同じ集合に属するかどうかを返す。
     *
     * @param x
     * @param y
     * @return 同じ集合であればtrue
     */
    public boolean same(int x, int y) {
        return find(x) == find(y);
    }

    /**
     * 要素xが属する集合と要素yが属する集合を連結する。
     * 木の高さ（ランク）を気にして、低い方に高い方をつなげる。（高い方の根を全体の根とする。）
     *
     * @param x
     * @param y
     */
    public void unite(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);

        if (xRoot == yRoot) {
            // 属する集合が同じな場合、何もしない
            return;
        }

        // rankを比較して共通の根を決定する。
        // ※find時の経路圧縮はrank考慮しない
        if (rank[xRoot] > rank[yRoot]) {
            // xRootのrankのほうが大きければ、共通の根をxRootにする
            parent[yRoot] = xRoot;
        } else if (rank[xRoot] < rank[yRoot]) {
            // yRootのrankのほうが大きければ、共通の根をyRootにする
            parent[xRoot] = yRoot;
        } else {
            // rankが同じであれば、どちらかを根として、rankを一つ上げる。
            parent[xRoot] = yRoot;
            rank[xRoot]++;
        }
    }
}