import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

public class Main {
    static long MOD = 998244353;
    int min = Integer.MAX_VALUE;
    int max = 0;
    int count = 0;
    int pattern = 0;

    Map<Long, Long> map = new HashMap<Long, Long>();

    public static void main(String[] args) throws Exception {
        // FileInputStream fis = new FileInputStream(new File("t.a"));
        var sc = new FastScanner();
        // Scanner sc = new Scanner(System.in);
        // var sc = new FastScanner(fis);
        // var pw = new FastPrintStream("t.y");
        var pw = new FastPrintStream();
        solve(sc, pw);
        sc.close();
        pw.flush();
        pw.close();

    }

    public static void solve(FastScanner sc, FastPrintStream pw) throws Exception {
        int n = sc.nextInt();
        int x = sc.nextInt();
        int y = sc.nextInt();
        PointX p[] = new PointX[n - 1];
        for (int i = 0; i < n - 1; i++) {
            p[i] = new PointX(sc.nextInt(), sc.nextInt());
        }
        long all = 0;
        for (int i = 0; i < n - 1; i++) {
            all += p[i].b;
        }
        int temp=24;
        long machi[] = new long[temp];
        for (int i = 0; i < temp; i++) {
            long now = i;
            for (int j = 0; j < n-1; j++) {
                if (now % p[j].a != 0) {
                    machi[i] += p[j].a - now % p[j].a;
                    now = now + (p[j].a - now % p[j].a);
                }
                now += p[j].b;
                now %= temp;
            }
        }
        int q = sc.nextInt();
        for (int i = 0; i < q; i++) {
            long t = sc.nextLong();
            pw.println(t +x+ machi[(int) ((t+x) % temp)] + all+y);
        }
    }

    public static long all(long start, long end, long count) {
        return (end + start) * count / 2;
    }

    public static int countA(int n, int a[], int price) {
        int min = 0;
        int max = n - 1;
        int re = -1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (a[mid] > price) {
                max = mid - 1;
            } else {
                re = mid;
                min = mid + 1;
            }
        }
        return re + 1;
    }

    public static int countB(int n, int b[], int price) {
        int min = 0;
        int max = n - 1;
        int re = -1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (b[mid] >= price) {
                max = mid - 1;
            } else {
                re = mid;
                min = mid + 1;
            }
        }
        return n - (re + 1);
    }

    public static String reverseString(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    public static boolean check(char c[][], int startx, int starty, int x, int y) {
        if (c[startx][starty] == '.') {
            return false;
        }
        char temp = c[startx][starty];
        for (int i = 0; i < 3; i++) {
            if (c[startx + i * x][starty + i * y] != temp) {
                return false;
            }
        }
        return true;
    }

    public static boolean compareString(String s, int indexb) {
        int length = Math.min(indexb, s.length() - indexb);
        for (int i = 0; i < length; i++) {
            if (s.charAt(i) < s.charAt(i + indexb)) {
                return true;
            } else if (s.charAt(i) > s.charAt(i + indexb)) {
                return false;
            }
        }
        if (indexb < s.length() - indexb) {
            return true;
        }
        return false;
    }

    public static boolean checkx(char c[][], char x[][], int hc, int wc, int hx, int wx) {
        for (int i = 0; i <= hc - hx; i++) {
            for (int j = 0; j <= wc - wx; j++) {
                boolean bool = true;
                for (int l = 0; l < hx; l++) {
                    for (int m = 0; m < wx; m++) {
                        if (c[i + l][j + m] == '#' || x[l][m] == '#') {
                            if (c[i + l][j + m] != x[l][m]) {
                                bool = false;
                            }
                        }
                    }
                }
                if (bool) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void refreshc(char c[][], char a[][], int ha, int wa, int hb, int wb) {
        for (int i = 0; i < ha; i++) {
            for (int j = 0; j < wa; j++) {
                c[i + hb][j + wb] = a[i][j];
            }
        }
    }

    public static void updateList(List<Point> list, int p[], int po, int position[]) {
        po += 2;
        list.add(new Point(po - 1, po - 1));
        int temp = p[po];
        p[po] = p[po - 1];
        p[po - 1] = p[po - 2];
        p[po - 2] = temp;
        position[p[po] - 1] = po;
        position[p[po - 1] - 1] = po - 1;
        position[p[po - 2] - 1] = po - 2;
        po -= 2;
    }

    public static void updateSet(Map<Long, Integer> map, Set<Long> set, long n) {
        int countM = map.get(n);
        countM--;
        if (countM == 0) {
            map.remove(n);
            set.remove(n);
        } else {
            map.put(n, countM);
        }
    }

    public static void insertSet(Map<Long, Integer> map, Set<Long> set, long n) {
        int countM = 0;
        if (set.contains(n)) {
            countM = map.get(n);
        } else {
            set.add(n);
        }
        countM++;
        map.put(n, countM);
    }

    public static int distance(int x1, int y1, int x2, int y2) {
        int distance = (int) Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)) + 1;
        return distance;
    }

    public static boolean nextPermutation(int[] arr) {
        int len = arr.length;
        int left = len - 2;
        while (left >= 0 && arr[left] >= arr[left + 1])
            left--;
        if (left < 0)
            return false;
        int right = len - 1;
        while (arr[left] >= arr[right])
            right--;
        {
            int t = arr[left];
            arr[left] = arr[right];
            arr[right] = t;
        }
        left++;
        right = len - 1;
        while (left < right) {
            {
                int t = arr[left];
                arr[left] = arr[right];
                arr[right] = t;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void testpattern(int n) {
        int count = 0;
        int re = 0;
        for (int x = 1; x * x <= n; x++) {
            count++;
        }
        System.out.println(count);
        re += count;
        count = 0;
        for (int x = 1; x * x <= n; x++) {
            for (int y = 1; y * x <= n; y++) {
                if (y != x) {
                    count += 3;
                    // System.out.println(x+" "+x+" "+y);
                }
            }
        }
        re += count;
        System.out.println(count);
        count = 0;
        for (int x = 1; x <= n; x++) {
            for (int y = 1; y <= n; y++) {
                for (int z = 1; z <= n; z++) {
                    if (x * y <= n && y * z <= n && x * z <= n) {
                        if (x != y && x != z && y != z) {
                            count++;
                        }
                    }
                }
            }
        }
        re += count;
        System.out.println(count);
        System.out.println(re);
    }

    public static int distance(char a[][], Point s, Point e, int h, int w) {
        int temp[][] = new int[h][w];
        List<Point> list = new ArrayList<>();
        list.add(s);
        temp[s.x][s.y] = 1;
        int count = 1;
        while (!list.isEmpty()) {
            List<Point> t = new ArrayList<>();
            for (Point p : list) {
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        if (Math.abs(i) != Math.abs(j)) {
                            if (i + p.x < h && i + p.x >= 0 && j + p.y >= 0 && j + p.y < w) {
                                if (e.x == i + p.x && e.y == j + p.y) {
                                    return count;
                                }
                                if (temp[i + p.x][j + p.y] == 0 && a[i + p.x][j + p.y] != '#') {
                                    t.add(new Point(i + p.x, j + p.y));
                                    temp[i + p.x][j + p.y]++;
                                }
                            }
                        }
                    }
                }
            }
            count++;
            list = t;
        }
        return Integer.MAX_VALUE / 3;
    }

    public static int[][] reverse(int n, int a[][]) {
        int temp[][] = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temp[n - 1 - j][i] = a[i][j];
            }
        }
        return temp;
    }

    public static boolean check(int a[][], int b[][], int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 1 && b[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static long testMethod(long a, long b) {
        if (a < b) {
            long t = b;
            b = a;
            a = t;
        }
        long re = 0;
        // long nowgcd = 1;
        while (b > 0) {
            if (a == b) {
                // pw.println(re + 1);
                return re + 1;
            }
            long gcd = Main.gcd(a, b);
            if (gcd == 1) {
                long sa = a - b;
                if (sa == 1) {
                    return re + b;
                }
                long min = a % sa;
                for (long i = 2; i * i <= sa; i++) {
                    if (sa % i == 0) {
                        min = Math.min(min, a % i);
                        min = Math.min(min, a % (sa / i));
                    }
                }
                re += min;
                a -= min;
                b -= min;
            } else {
                // nowgcd = gcd;
                re++;
                a = a / gcd - 1;
                b = b / gcd - 1;
            }
        }
        return re;
    }

    public static long countNext(long a, long b) {
        long re = -1;
        long min = 1;
        long max = b - 1;
        while (min <= max) {
            long mid = (min + max) / 2;
            if ((a - mid) / (b - mid) > 1) {
                re = mid;
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }
        return re;
    }

    public static int countTime(int max, int min, int i) {
        int times = max / i + i - 1;
        if (max % i != 0) {
            times++;
        }
        if (min <= i) {
            times++;
        } else {
            times = times + min / i;
            if (min % i != 0) {
                times++;
            }
        }
        return times;
    }

    public static boolean check(long s, long l, long k, long m, long n, long v) {
        if ((s - 1) / m != (s + l - 2) / m) {
            return false;
        }
        if (s + (k - 1) * m > n * m) {
            return false;
        }
        long temp = (s + s + (k - 1) * m) * k / 2;
        long temp2 = (temp + temp + (l - 1) * k) * l / 2;
        if (temp2 != v) {
            return false;
        }
        return true;
    }

    public static double result(int x[], int x1, int x2, int x3) {
        return 1.0 / (x[x1] * x[x2]) + 1.0 / (x[x2] * x[x3]) + 1.0 / (x[x1] * x[x3]);
    }

    public static int[] toArray(int temp, int n) {
        List<Integer> list = new ArrayList<Integer>();
        while (temp > 0) {
            list.add(temp % 2);
            temp /= 2;
        }
        int re[] = new int[n];
        for (int i = 0; i < list.size(); i++) {
            re[i] = list.get(i);
        }
        return re;
    }

    public static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    public static boolean make_so(long[] as, long[] ms) {
        while (true) {
            boolean updated = false;

            for (int fst = 0; fst < ms.length; fst++) {
                for (int snd = fst + 1; snd < ms.length; snd++) {
                    long gcd = gcd(ms[fst], ms[snd]);
                    if (gcd == 1) {
                        continue;
                    }

                    updated = true;

                    if (as[fst] % gcd != as[snd] % gcd) {
                        return false;
                    }

                    ms[fst] /= gcd;
                    ms[snd] /= gcd;
                    while (true) {
                        long gt = gcd(ms[fst], gcd);
                        if (gt == 1) {
                            break;
                        }

                        ms[fst] *= gt;
                        gcd /= gt;
                    }
                    ms[snd] *= gcd;
                    as[fst] %= ms[fst];
                    as[snd] %= ms[snd];
                }
            }

            if (!updated) {
                break;
            }
        }

        return true;
    }

    public static long mod_pow(long a, long e, long m) {
        if (e == 0) {
            return 1;
        } else if (e % 2 == 0) {
            long ret = mod_pow(a, e / 2, m);
            return (ret * ret) % m;
        } else {
            return (mod_pow(a, e - 1, m) * a) % m;
        }
    }

    // a and m must be co-prime.
    public static long mod_inv(long a, long m) {
        return (a == 1 ? 1 : (1 - m * mod_inv(m % a, a)) / a + m);
    }

    // O(N^2). ms[i] は互いに素でなくてはならない.
    // Π(ms[i]) がオーバーフローする場合でも, 最後に適切に MOD を取れば OK
    public static long chinese_remainder(long[] as, long[] ms, long MOD) {
        long[] vs = new long[ms.length];
        long[] sums = new long[ms.length];
        long[] invs = new long[ms.length];

        for (int i = 0; i < ms.length; i++) {
            invs[i] = 1; // mod(ms[i]) の中で 1 / (ms[0] * ms[1] ... ms[i - 1]) を求める
            sums[i] = as[i]; // mod(ms[i]) の中で, a[i] - v[0] - v[1]ms[0] ... を求める

            long mult = 1; // mod(ms[i]) の中で, ms[0] * ... * ms[i - 2] を求める
            for (int j = 0; j < i; j++) {
                sums[i] -= (vs[j] * mult) % ms[i];
                if (sums[i] < 0) {
                    sums[i] += ms[i];
                }

                invs[i] = (invs[i] * mod_inv(ms[j], ms[i])) % ms[i];
                mult = (mult * ms[j]) % ms[i];
            }

            vs[i] = (sums[i] * invs[i]) % ms[i];
        }

        // MOD を取る前に x = 0 かどうかを知る場合, ここで ∀i.(vs[i] == 0) チェック
        {
            boolean flg = true;
            for (final long v : vs) {
                if (v != 0) {
                    flg = false;
                    break;
                }
            }
            if (flg) { // ここに来るなら 全ての条件を x = 0 で満たす.
                return 0; // 正整数が欲しいなら、Π(ms[i]) の MOD が答え
            }
        }

        long ret = 0;
        for (int i = 0; i < ms.length; i++) {
            long mult = 1;
            for (int j = 0; j < i; j++) {
                mult *= ms[j];
                mult %= MOD;
            }

            ret += (vs[i] * mult) % MOD;
            ret %= MOD;
        }

        return ret;
    }

    public static long mulity(long l, int a[]) {
        for (int i = 0; i < a.length; i++) {
            if (l % a[i] == 0) {
                l /= a[i];
                a[i] = 1;
            }
        }
        return l;
    }

    public static int[][] reverse(int a[][]) {
        int b[][] = new int[2][2];
        b[0][0] = a[1][0];
        b[0][1] = a[0][0];
        b[1][0] = a[1][1];
        b[1][1] = a[0][1];
        return b;
    }

    public static void add(TreeSet<Integer> ts, Map<Integer, Integer> map, int value) {
        if (ts.contains(value)) {
            map.put(value, map.get(value) + 1);
        } else {
            ts.add(value);
            map.put(value, 1);
        }
    }

    public static void remove(TreeSet<Integer> ts, Map<Integer, Integer> map, int value) {
        if (map.get(value) == 1) {
            ts.remove(value);
            map.remove(value);
        } else {
            map.put(value, map.get(value) - 1);
        }
    }

    public static int merge(int n, int m, int e) {
        int merge = (n * (n - 1) / 2 - 2 * m) * e * 3 / 200;
        if ((n - 2 * m) * e % 100 != 0) {
            merge++;
        }
        return Math.max(3, merge);
    }

    public static boolean isMerge(int n, int m, int e, int count, int now) {
        int merge = merge(n, m, e);
        if (Math.abs(count - now) <= merge) {
            return true;
        }
        return false;
    }

    public static void setall(Set<Integer> set, int x) {
        if (x < 10) {
            set.add(x);
            return;
        }
        int to = 0;
        int one[] = new int[4];
        while (x > 0) {
            one[to] = x % 10;
            x /= 10;
            to++;
        }
        Main.permutation(one, 0, to - 1, set);
    }

    // mod. m での a の逆元 a^{-1} を計算する
    public static long modinv(long a, long m) {
        long b = m, u = 1, v = 0;
        while (b > 0) {
            long t = a / b;
            a -= t * b;
            long temp = a;
            a = b;
            b = temp;
            u -= t * v;
            temp = u;
            u = v;
            v = temp;
        }
        u %= m;
        if (u < 0)
            u += m;
        return u;
    }

    public long re(long n) {
        if ((n % 6 == 1 || n % 6 == 5) && n > 1) {
            n--;
        }
        if (map.containsKey(n)) {
            return map.get(n);
        }
        long t1 = re(n / 2);
        long t2 = re(n / 3);
        map.put(n / 2, t1);
        map.put(n / 3, t2);
        return t1 + t2;
    }

    public static long distance(Point s, Point e) {
        return (s.x - e.x) * (s.x - e.x) + (s.y - e.y) * (s.y - e.y);
    }

    public static int lowerBound(Integer[] a, int obj) {
        int l = 0, r = a.length - 1;
        while (r - l >= 0) {
            int c = (l + r) / 2;
            if (obj <= a[c]) {
                r = c - 1;
            } else {
                l = c + 1;
            }
        }
        return l;
    }

    public static long countRe(int high, int used[], long now, long min, long b[]) {
        long re = Long.MAX_VALUE;
        long temp = min;
        for (int i = high; i >= 0; i--) {
            if (used[i] == 0 && temp < now) {
                temp += b[i];
                if (temp >= now) {
                    re = Math.min(re, temp);
                    temp -= b[i];
                }
            }
        }
        if (re == Long.MAX_VALUE) {
            re = min;
        }
        return re;
    }

    public static void swap(int[] s, int i, int j) {
        int tmp = s[i];
        s[i] = s[j];
        s[j] = tmp;
    }

    public static void permutation(int[] s, int from, int to, Set<Integer> set) {
        if (to <= 0)
            return;
        if (from == to) {
            check(s, set, to);
        } else {
            for (int i = from; i <= to; i++) {
                swap(s, i, from);
                permutation(s, from + 1, to, set);
                swap(s, from, i);
            }
        }
    }

    public static void check(int[] s, Set<Integer> set, int to) {
        int temp = 0;
        for (int i = 0; i <= to; i++) {
            temp *= 10;
            temp += s[i];
        }
        set.add(temp);
    }

    public static long anothertoTen(long ano, int another) {
        long ten = 0;
        long now = 1;
        long temp = ano;
        while (temp > 0) {
            long i = temp % 10;
            ten += now * i;
            now *= another;
            temp /= 10;
        }
        return ten;
    }

    public static long tentoAnother(long ten, int another) {
        Stack<Long> stack = new Stack<Long>();
        while (ten > 0) {
            stack.add(ten % another);
            ten /= another;
        }
        long re = 0;
        while (!stack.isEmpty()) {
            long pop = stack.pop();
            re = re * 10 + pop;
        }
        return re;
    }

    // 2C5 = 5*4/(2*1)
    public static long fastXCY(long tempx, long temp) {
        tempx = tempx % MOD;
        temp %= MOD;
        tempx = modpow(tempx, (long) MOD - 2);
        temp = (temp * tempx) % MOD;
        return temp;
    }

    // 2C5 = 5*4/(2*1)
    public static long XCY(long x, long y) {
        long temp = 1;
        for (int i = 0; i < x; i++) {
            temp = (temp * (y - i)) % MOD;
        }
        long tempx = 1;
        for (int i = 2; i <= x; i++) {
            tempx = (tempx * i) % MOD;
        }
        tempx = modpow(tempx, (long) MOD - 2);
        temp = (temp * tempx) % MOD;
        return temp;
    }

    static long modpow(long N, Long K) {
        return BigInteger.valueOf(N).modPow(BigInteger.valueOf(K), BigInteger.valueOf(MOD)).longValue();
    }

    static long modpow(long N, Long K, long mod) {
        return BigInteger.valueOf(N).modPow(BigInteger.valueOf(K), BigInteger.valueOf(mod)).longValue();
    }

    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        if (a < b) {
            return gcd(b, a);
        }
        return gcd(b, a % b);
    }

}

class PointEx implements Comparable<PointEx> {
    String s;
    int index = 0;

    public PointEx(String t, int i) {
        s = new String(t);
        index = i;
    }

    @Override
    public int compareTo(PointEx o) {
        return this.s.compareTo(o.s);
    }
}

class Node implements Comparable<Node> {

    int tyoten;
    long minDistance;

    public Node(int t, long m) {
        tyoten = t;
        minDistance = m;
    }

    @Override
    public int compareTo(Node o) {
        int res = -1;
        if (this.minDistance - o.minDistance >= 0) {
            res = 1;
        }
        return res;
    }

}

class Vertex {
    String key;

    Vertex(String key) {
        this.key = key;
    }
}

class Edge {
    Vertex start;
    Vertex end;
    long key;

    Edge(Vertex start, Vertex end, long key) {
        this.start = start;
        this.end = end;
        this.key = key;
    }
}

class Target {
    int s;
    int e;
    long c;
}

class Point extends Object implements Comparable {
    int x;
    int y;

    public Point() {
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object j) {
        Point p = (Point) j;
        if (p.x == this.x && p.y == this.y) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (int) this.x + (int) this.y;
    }

    public int compareTo(Object p) {
        Point t = (Point) p;
        if (t.x != this.x) {
            return this.x - t.x;
        }
        return this.y - t.y;
    }

}

class PointX implements Comparable {
    long a;
    long b;

    public PointX(long x, long y) {
        a = x;
        b = y;
    }

    public int compareTo(Object p) {
        PointX t = (PointX) p;
        if (this.a < t.a) {
            return -1;
        }
        if (this.a > t.a) {
            return 1;
        }
        return 0;
    }

    public boolean equals(Object p) {
        PointX t = (PointX) p;
        return this.a == t.a && this.b == t.b;
    }

}

class PointTemp {
    int x;
    int y;
    int nx;
    int ny;

    public PointTemp(int tx, int ty, int tnx, int tny) {
        x = tx;
        y = ty;
        nx = tnx;
        ny = tny;
    }

    public String print() {
        StringBuffer sb = new StringBuffer();
        sb.append(x + " ");
        sb.append(y + " ");
        sb.append(nx + " ");
        sb.append(ny + " ");
        return sb.toString();
    }
}

class FastPrintStream implements AutoCloseable {
    private static final int BUF_SIZE = 1 << 15;
    private final byte[] buf = new byte[BUF_SIZE];
    private int ptr = 0;
    private final java.lang.reflect.Field strField;
    private final java.nio.charset.CharsetEncoder encoder;

    private java.io.OutputStream out;

    public FastPrintStream(java.io.OutputStream out) {
        this.out = out;
        java.lang.reflect.Field f;
        try {
            f = java.lang.String.class.getDeclaredField("value");
            // f.setAccessible(true);
        } catch (NoSuchFieldException | SecurityException e) {
            f = null;
        }
        this.strField = f;
        this.encoder = java.nio.charset.StandardCharsets.US_ASCII.newEncoder();
    }

    public FastPrintStream(java.io.File file) throws java.io.IOException {
        this(new java.io.FileOutputStream(file));
    }

    public FastPrintStream(java.lang.String filename) throws java.io.IOException {
        this(new java.io.File(filename));
    }

    public FastPrintStream() {
        this(System.out);
        try {
            java.lang.reflect.Field f = java.io.PrintStream.class.getDeclaredField("autoFlush");
            // f.setAccessible(true);
            f.set(System.out, false);
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException e) {
            // ignore
        }
    }

    public FastPrintStream println() {
        if (ptr == BUF_SIZE)
            internalFlush();
        buf[ptr++] = (byte) '\n';
        return this;
    }

    public FastPrintStream println(java.lang.Object o) {
        return print(o).println();
    }

    public FastPrintStream println(java.lang.String s) {
        return print(s).println();
    }

    public FastPrintStream println(char[] s) {
        return print(s).println();
    }

    public FastPrintStream println(char c) {
        return print(c).println();
    }

    public FastPrintStream println(int x) {
        return print(x).println();
    }

    public FastPrintStream println(long x) {
        return print(x).println();
    }

    public FastPrintStream println(double d, int precision) {
        return print(d, precision).println();
    }

    private FastPrintStream print(byte[] bytes) {
        int n = bytes.length;
        if (ptr + n > BUF_SIZE) {
            internalFlush();
            try {
                out.write(bytes);
            } catch (java.io.IOException e) {
                throw new RuntimeException();
            }
        } else {
            System.arraycopy(bytes, 0, buf, ptr, n);
            ptr += n;
        }
        return this;
    }

    public FastPrintStream print(java.lang.Object o) {
        return print(o.toString());
    }

    public FastPrintStream print(java.lang.String s) {
        if (strField == null) {
            return print(s.getBytes());
        } else {
            try {
                return print((byte[]) strField.get(s));
            } catch (IllegalAccessException e) {
                return print(s.getBytes());
            }
        }
    }

    public FastPrintStream print(char[] s) {
        try {
            return print(encoder.encode(java.nio.CharBuffer.wrap(s)).array());
        } catch (java.nio.charset.CharacterCodingException e) {
            byte[] bytes = new byte[s.length];
            for (int i = 0; i < s.length; i++) {
                bytes[i] = (byte) s[i];
            }
            return print(bytes);
        }
    }

    public FastPrintStream print(char c) {
        if (ptr == BUF_SIZE)
            internalFlush();
        buf[ptr++] = (byte) c;
        return this;
    }

    public FastPrintStream print(int x) {
        if (x == 0) {
            if (ptr == BUF_SIZE)
                internalFlush();
            buf[ptr++] = '0';
            return this;
        }
        int d = len(x);
        if (ptr + d > BUF_SIZE)
            internalFlush();
        if (x < 0) {
            buf[ptr++] = '-';
            x = -x;
            d--;
        }
        int j = ptr += d;
        while (x > 0) {
            buf[--j] = (byte) ('0' + (x % 10));
            x /= 10;
        }
        return this;
    }

    public FastPrintStream print(long x) {
        if (x == 0) {
            if (ptr == BUF_SIZE)
                internalFlush();
            buf[ptr++] = '0';
            return this;
        }
        int d = len(x);
        if (ptr + d > BUF_SIZE)
            internalFlush();
        if (x < 0) {
            buf[ptr++] = '-';
            x = -x;
            d--;
        }
        int j = ptr += d;
        while (x > 0) {
            buf[--j] = (byte) ('0' + (x % 10));
            x /= 10;
        }
        return this;
    }

    public FastPrintStream print(double d, int precision) {
        if (d < 0) {
            print('-');
            d = -d;
        }
        d += Math.pow(10, -d) / 2;
        print((long) d).print('.');
        d -= (long) d;
        for (int i = 0; i < precision; i++) {
            d *= 10;
            print((int) d);
            d -= (int) d;
        }
        return this;
    }

    private void internalFlush() {
        try {
            out.write(buf, 0, ptr);
            ptr = 0;
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void flush() {
        try {
            out.write(buf, 0, ptr);
            out.flush();
            ptr = 0;
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        try {
            out.close();
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int len(int x) {
        int d = 1;
        if (x >= 0) {
            d = 0;
            x = -x;
        }
        int p = -10;
        for (int i = 1; i < 10; i++, p *= 10)
            if (x > p)
                return i + d;
        return 10 + d;
    }

    private static int len(long x) {
        int d = 1;
        if (x >= 0) {
            d = 0;
            x = -x;
        }
        long p = -10;
        for (int i = 1; i < 19; i++, p *= 10)
            if (x > p)
                return i + d;
        return 19 + d;
    }
}

class FastScanner implements AutoCloseable {
    private final java.io.InputStream in;
    private final byte[] buf = new byte[2048];
    private int ptr = 0;
    private int buflen = 0;

    public FastScanner(java.io.InputStream in) {
        this.in = in;
    }

    public FastScanner() {
        this(System.in);
    }

    private boolean hasNextByte() {
        if (ptr < buflen)
            return true;
        ptr = 0;
        try {
            buflen = in.read(buf);
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
        return buflen > 0;
    }

    private int readByte() {
        return hasNextByte() ? buf[ptr++] : -1;
    }

    public boolean hasNext() {
        while (hasNextByte() && !(32 < buf[ptr] && buf[ptr] < 127))
            ptr++;
        return hasNextByte();
    }

    private StringBuilder nextSequence() {
        if (!hasNext())
            throw new java.util.NoSuchElementException();
        StringBuilder sb = new StringBuilder();
        for (int b = readByte(); 32 < b && b < 127; b = readByte()) {
            sb.appendCodePoint(b);
        }
        return sb;
    }

    public String next() {
        return nextSequence().toString();
    }

    public String next(int len) {
        return new String(nextChars(len));
    }

    public char nextChar() {
        if (!hasNextByte())
            throw new java.util.NoSuchElementException();
        return (char) readByte();
    }

    public char[] nextChars() {
        StringBuilder sb = nextSequence();
        int l = sb.length();
        char[] dst = new char[l];
        sb.getChars(0, l, dst, 0);
        return dst;
    }

    public char[] nextChars(int len) {
        if (!hasNext())
            throw new java.util.NoSuchElementException();
        char[] s = new char[len];
        int i = 0;
        int b = readByte();
        while (32 < b && b < 127 && i < len) {
            s[i++] = (char) b;
            b = readByte();
        }
        if (i != len) {
            throw new java.util.NoSuchElementException(
                    String.format("Next token has smaller length than expected.", len));
        }
        return s;
    }

    public long nextLong() {
        if (!hasNext())
            throw new java.util.NoSuchElementException();
        long n = 0;
        boolean minus = false;
        int b = readByte();
        if (b == '-') {
            minus = true;
            b = readByte();
        }
        if (b < '0' || '9' < b)
            throw new NumberFormatException();
        while (true) {
            if ('0' <= b && b <= '9') {
                n = n * 10 + b - '0';
            } else if (b == -1 || !(32 < b && b < 127)) {
                return minus ? -n : n;
            } else
                throw new NumberFormatException();
            b = readByte();
        }
    }

    public int nextInt() {
        return Math.toIntExact(nextLong());
    }

    public double nextDouble() {
        return Double.parseDouble(next());
    }

    public void close() {
        try {
            in.close();
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }

}

/**
 * @verified https://atcoder.jp/contests/practice2/tasks/practice2_j
 */
class SegTree<S> {
    final int MAX;

    final int N;
    final java.util.function.BinaryOperator<S> op;
    final S E;

    final S[] data;

    @SuppressWarnings("unchecked")
    public SegTree(int n, java.util.function.BinaryOperator<S> op, S e) {
        this.MAX = n;
        int k = 1;
        while (k < n)
            k <<= 1;
        this.N = k;
        this.E = e;
        this.op = op;
        this.data = (S[]) new Object[N << 1];
        java.util.Arrays.fill(data, E);
    }

    public SegTree(S[] dat, java.util.function.BinaryOperator<S> op, S e) {
        this(dat.length, op, e);
        build(dat);
    }

    private void build(S[] dat) {
        int l = dat.length;
        System.arraycopy(dat, 0, data, N, l);
        for (int i = N - 1; i > 0; i--) {
            data[i] = op.apply(data[i << 1 | 0], data[i << 1 | 1]);
        }
    }

    public void set(int p, S x) {
        exclusiveRangeCheck(p);
        data[p += N] = x;
        p >>= 1;
        while (p > 0) {
            data[p] = op.apply(data[p << 1 | 0], data[p << 1 | 1]);
            p >>= 1;
        }
    }

    public S get(int p) {
        exclusiveRangeCheck(p);
        return data[p + N];
    }

    public S prod(int l, int r) {
        if (l > r) {
            throw new IllegalArgumentException(String.format("Invalid range: [%d, %d)", l, r));
        }
        inclusiveRangeCheck(l);
        inclusiveRangeCheck(r);
        S sumLeft = E;
        S sumRight = E;
        l += N;
        r += N;
        while (l < r) {
            if ((l & 1) == 1)
                sumLeft = op.apply(sumLeft, data[l++]);
            if ((r & 1) == 1)
                sumRight = op.apply(data[--r], sumRight);
            l >>= 1;
            r >>= 1;
        }
        return op.apply(sumLeft, sumRight);
    }

    public S allProd() {
        return data[1];
    }

    public int maxRight(int l, java.util.function.Predicate<S> f) {
        inclusiveRangeCheck(l);
        if (!f.test(E)) {
            throw new IllegalArgumentException("Identity element must satisfy the condition.");
        }
        if (l == MAX)
            return MAX;
        l += N;
        S sum = E;
        do {
            l >>= Long.numberOfTrailingZeros(l);
            if (!f.test(op.apply(sum, data[l]))) {
                while (l < N) {
                    l = l << 1;
                    if (f.test(op.apply(sum, data[l]))) {
                        sum = op.apply(sum, data[l]);
                        l++;
                    }
                }
                return l - N;
            }
            sum = op.apply(sum, data[l]);
            l++;
        } while ((l & -l) != l);
        return MAX;
    }

    public int minLeft(int r, java.util.function.Predicate<S> f) {
        inclusiveRangeCheck(r);
        if (!f.test(E)) {
            throw new IllegalArgumentException("Identity element must satisfy the condition.");
        }
        if (r == 0)
            return 0;
        r += N;
        S sum = E;
        do {
            r--;
            while (r > 1 && (r & 1) == 1)
                r >>= 1;
            if (!f.test(op.apply(data[r], sum))) {
                while (r < N) {
                    r = r << 1 | 1;
                    if (f.test(op.apply(data[r], sum))) {
                        sum = op.apply(data[r], sum);
                        r--;
                    }
                }
                return r + 1 - N;
            }
            sum = op.apply(data[r], sum);
        } while ((r & -r) != r);
        return 0;
    }

    private void exclusiveRangeCheck(int p) {
        if (p < 0 || p >= MAX) {
            throw new IndexOutOfBoundsException(
                    String.format("Index %d out of bounds for the range [%d, %d).", p, 0, MAX));
        }
    }

    private void inclusiveRangeCheck(int p) {
        if (p < 0 || p > MAX) {
            throw new IndexOutOfBoundsException(
                    String.format("Index %d out of bounds for the range [%d, %d].", p, 0, MAX));
        }
    }

    // **************** DEBUG **************** //

    private int indent = 6;

    public void setIndent(int newIndent) {
        this.indent = newIndent;
    }

    @Override
    public String toString() {
        return toSimpleString();
    }

    public String toDetailedString() {
        return toDetailedString(1, 0);
    }

    private String toDetailedString(int k, int sp) {
        if (k >= N)
            return indent(sp) + data[k];
        String s = "";
        s += toDetailedString(k << 1 | 1, sp + indent);
        s += "\n";
        s += indent(sp) + data[k];
        s += "\n";
        s += toDetailedString(k << 1 | 0, sp + indent);
        return s;
    }

    private static String indent(int n) {
        StringBuilder sb = new StringBuilder();
        while (n-- > 0)
            sb.append(' ');
        return sb.toString();
    }

    public String toSimpleString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < N; i++) {
            sb.append(data[i + N]);
            if (i < N - 1)
                sb.append(',').append(' ');
        }
        sb.append(']');
        return sb.toString();
    }
}

class DSU {
    private int n;
    private int[] parentOrSize;

    public DSU(int n) {
        this.n = n;
        this.parentOrSize = new int[n];
        Arrays.fill(parentOrSize, -1);
    }

    int merge(int a, int b) {
        if (!(0 <= a && a < n) || !(0 <= b && b < n)) {
            return -1;
        }
        int x = leader(a);
        int y = leader(b);
        if (x == y)
            return x;
        if (-parentOrSize[x] < -parentOrSize[y]) {
            int tmp = x;
            x = y;
            y = tmp;
        }
        parentOrSize[x] += parentOrSize[y];
        parentOrSize[y] = x;
        return x;
    }

    boolean same(int a, int b) {
        if (!(0 <= a && a < n) || !(0 <= b && b < n)) {
            return false;
        }
        return leader(a) == leader(b);
    }

    int leader(int a) {
        if (parentOrSize[a] < 0) {
            return a;
        } else {
            parentOrSize[a] = leader(parentOrSize[a]);
            return parentOrSize[a];
        }
    }

    int size(int a) {
        if (!(0 <= a && a < n)) {
            return -1;
        }
        return -parentOrSize[leader(a)];
    }

    ArrayList<ArrayList<Integer>> groups() {
        int[] leaderBuf = new int[n];
        int[] groupSize = new int[n];
        for (int i = 0; i < n; i++) {
            leaderBuf[i] = leader(i);
            groupSize[leaderBuf[i]]++;
        }
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < n; i++) {
            result.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            result.get(leaderBuf[i]).add(i);
        }
        return result;
    }
}
