import java.util.*;
import java.io.*;

// -------------------------Main class-------------------------

public class Main {

    static long MOD = 1000000007;

    // -------------------------Main function-------------------------
    public static void main(String args[]) throws IOException {
        FastReader sc = new FastReader();
        PrintWriter pw = new PrintWriter(System.out);
        int t = 1;
        for (int i = 0; i < t; i++) {
            int w = sc.nextInt();
            int h = sc.nextInt();
            int n = sc.nextInt();
            int a[][] = new int[n][2];
            for (int j = 0; j < n; j++) {
                a[j][0] = sc.nextInt();
                a[j][1] = sc.nextInt();
            }
            int xsize = sc.nextInt();
            int x[] = new int[xsize + 1];
            for (int j = 0; j < xsize; j++)
                x[j] = sc.nextInt();
            x[xsize] = w;
            int ysize = sc.nextInt();
            int y[] = new int[ysize + 1];
            for (int j = 0; j < ysize; j++)
                y[j] = sc.nextInt();
            y[ysize] = h;
            HashMap<Long, Integer> map = new HashMap<>();
            for (int j = 0; j < n; j++) {
                int currx = a[j][0];
                int curry = a[j][1];
                int l = 0;
                int r = xsize;
                int min_pos_x = 0;
                while (l <= r) {
                    int mid = l + (r - l) / 2;
                    if (x[mid] > currx) {
                        min_pos_x = mid;
                        r = mid - 1;
                    } else
                        l = mid + 1;
                }
                l = 0;
                r = ysize;
                int min_pos_y = 0;
                while (l <= r) {
                    int mid = l + (r - l) / 2;
                    if (y[mid] > curry) {
                        min_pos_y = mid;
                        r = mid - 1;
                    } else
                        l = mid + 1;
                }
                long value = 1l * (min_pos_x + 1) * (long) 1e6 + (min_pos_y + 1);
                map.put(value, map.getOrDefault(value, 0) + 1);
            }
            int min = n;
            int max = 0;
            for (Map.Entry ele : map.entrySet()) {
                min = Math.min(min, (int) ele.getValue());
                max = Math.max(max, (int) ele.getValue());
            }
            if (map.size() < (xsize + 1) * (ysize + 1) * 1l)
                min = 0;
            pw.write(min + " " + max + "\n");
        }
        pw.flush();
        pw.close();
    }

    // -------------------------Other functions-------------------------

    // Time Complexity : log(n)
    static long fast_power_mod(long n, long mod) {
        long pow2 = 2;
        long res = 1;
        while (n > 0) {
            if (n % 2 == 1)
                res = (res % mod * pow2 % mod) % mod;
            pow2 = (pow2 % mod * pow2 % mod) % mod;
            n >>= 1;
        }
        return res;
    }

    // Time Complexity : n*r
    static long nCrModpDP(long n, long r, long p) {
        long c[] = new long[(int) r + 1];
        c[0] = 1;
        for (long j = 1; j <= n; j++) {
            for (long k = Math.min(j, r); k > 0; k--)
                c[(int) k] = (c[(int) k] % p + c[(int) k - 1] % p) % p;
        }
        return c[(int) r];
    }

    // Time Complexity : log(n)
    static long nCrModpLucas(long n, long r, long p) {
        if (r == 0)
            return 1;
        long ni = n % p;
        long ri = r % p;
        return (nCrModpLucas(n / p, r / p, p) % p * nCrModpDP(ni, ri, p) % p) % p;
    }

    // Time Complexity : log(mod)
    static long inverseMOD(long x, long mod) {
        if (mod == 0)
            return 1;
        if (mod % 2 == 1)
            return (x * inverseMOD(x, mod - 1)) % MOD;
        else
            return inverseMOD((x * x) % MOD, mod / 2);
    }

    // Time Complexity : log(max(a,b))
    static long bitwiseAND(long a, long b) {
        long shiftcount = 0;
        while (a != b && a > 0) {
            shiftcount++;
            a = a >> 1;
            b = b >> 1;
        }
        return (long) (a << shiftcount);
    }

    // Time Complexity : n*m
    static void dfs(int j, ArrayList<ArrayList<Integer>> al, boolean[] visited) {
        visited[j] = true;
        for (int x = 0; x < al.get(j).size(); x++) {
            if (!visited[al.get(j).get(x)])
                dfs(al.get(j).get(x), al, visited);
        }
    }

    // Time Complexity : log(n) for composite numbers, n for prime numbers
    static long getPrimeFactors(long n) {
        int x = 2;
        long count = 0;
        // ArrayList<Integer> al=new ArrayList<>();
        while (n > 1) {
            if (n % x == 0) {
                // if(!al.contains(x))
                // al.add(x);
                count++;
                n /= x;
            } else
                x++;
        }
        return count;
    }

    // Time Complexity : log(n)
    static ArrayList<Integer> primeFactorization(int x, int[] spf) {
        HashMap<Integer, Integer> map = new HashMap<>();
        ArrayList<Integer> al = new ArrayList<>();
        while (x != 1) {
            if (!al.contains(spf[x]))
                al.add(spf[x]);
            map.put(spf[x], map.getOrDefault(spf[x], 0) + 1);
            x /= spf[x];
        }
        return al;
        // return map;
    }

    // Time Complexity : n*10
    static int[][] getBitMap(long[] a) {
        int n = a.length;
        int[][] bit_map = new int[n][32];
        for (int j = 0; j < n; j++)
            Arrays.fill(bit_map[j], 0);
        for (int j = 0; j < n; j++) {
            int counter = 0;
            while (a[j] != 0) {
                bit_map[j][counter] = (int) a[j] % 2;
                a[j] /= 2;
                counter++;
            }
        }
        return bit_map;
    }

    // Time Complexity : n*log(log(n))
    static ArrayList<Integer> sieveOfEratosthenes(int n) {
        boolean prime[] = new boolean[n + 1];
        for (int j = 0; j <= n; j++)
            prime[j] = true;
        for (long p = 2; p * p <= n; p++) {
            if (prime[(int) p]) {
                for (long j = p * p; j <= n; j += p)
                    prime[(int) j] = false;
            }
        }
        ArrayList<Integer> hs = new ArrayList<>();
        for (long j = 2; j <= n; j++) {
            if (prime[(int) j])
                hs.add((int) j);
        }
        return hs;
    }

    // Time Complexity : n
    static boolean sortedIncreasing(long[] a) {
        int f = 0;
        for (int j = 1; j < a.length; j++) {
            if (a[j] < a[j - 1])
                f = 1;
        }
        return f == 0 ? true : false;
    }

    // Time Complexity : n
    static boolean sortedDecreasing(int[] a) {
        int f = 0;
        for (int j = 1; j < a.length; j++) {
            if (a[j] > a[j - 1])
                f = 1;
        }
        return f == 0 ? true : false;
    }

    // Time Complexity : sqrt(n)
    static ArrayList<Integer> getFactors(int n) {
        ArrayList<Integer> al = new ArrayList<>();
        // int count=0;
        for (long i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                al.add((int) i);
                // count++;
                if (n / i != i) {
                    al.add((int) (n / i));
                    // count++;
                }
            }
        }
        return al;
        // return count;
    }

    // Time Complexity : n*log(n)
    static void sort(int[] a) {
        ArrayList<Integer> l = new ArrayList<>();
        for (int i : a)
            l.add(i);
        Collections.sort(l);
        for (int i = 0; i < a.length; i++)
            a[i] = l.get(i);
    }

    // Time Complexity : n*log(n)
    static void sort(long[] a) {
        ArrayList<Long> l = new ArrayList<>();
        for (long i : a)
            l.add(i);
        Collections.sort(l);
        for (int i = 0; i < a.length; i++)
            a[i] = l.get(i);
    }

    // Time Complexity : log(min(a,b))
    static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    // Time Complexity : log(min(a,b))
    static long gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    // Time Complexity : log(min(a,b))
    static long lcm(long a, long b) {
        return ((a / gcd(a, b)) * b);
    }

    // Time Complexity : log(min(a,b))
    static long lcm(int a, int b) {
        return ((a / gcd(a, b)) * b);
    }

    // Time Complexity : log(n)
    static long floorSqrt(long x) {
        if (x == 0 || x == 1)
            return x;
        long l = 1;
        long r = (long) Math.sqrt(x) + 1;
        long ans = 0;
        while (l <= r) {
            long mid = l + (r - l) / 2;
            long curr = mid * mid;
            if (curr == x)
                return mid;
            else if (curr > 0 && curr <= x) {
                ans = mid;
                l = mid + 1;
            } else
                r = mid - 1;
        }
        return ans;
    }

    // Time Complexity : log(n*logn)
    static long getRemainderSum(long[] a, long totalsum, int x) {
        long k = 0;
        outer: for (int mul = x;; mul += x) {
            int l = 0;
            int r = a.length - 1;
            int index = -1;
            while (l <= r) {
                int mid = l + (r - l) / 2;
                if (a[mid] >= mul) {
                    index = mid;
                    r = mid - 1;
                } else
                    l = mid + 1;
            }
            if (index == -1)
                break outer;
            else
                k += a.length - index;
        }
        return (totalsum - k * x);
    }

    // -------------------------Input class-------------------------

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

}

// -------------------------Other classes-------------------------

class Pair {

    int first = 0;
    int second = 0;
    // char first = 'a';
    // int second = -1;

    Pair(int x, int y) {
        first = x;
        second = y;
    }

    // Pair(char x, int y) {
    // first = x;
    // second = y;
    // }

}

class NewComparatorPair implements Comparator<Pair> {
    public int compare(Pair p1, Pair p2) {
        if (p1.first != p2.first)
            return Integer.compare(p1.first, p2.first);
        else
            return Integer.compare(p2.second, p1.second);
    }
}

class NewComparatorArray implements Comparator<int[]> {
    public int compare(int[] a, int[] b) {
        if (a[0] != b[0])
            return Integer.compare(a[0], b[0]);
        else
            return Integer.compare(b[1], a[1]);
    }
}