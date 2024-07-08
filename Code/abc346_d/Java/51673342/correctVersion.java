import java.util.*;
import java.io.*;

// -------------------------Main class-------------------------

public class Main {

    static long MOD = (long)1e9+7;

    static int a[];

    static long b[];

    static long dp[][][];

    // -------------------------Main function-------------------------
    public static void main(String args[]) throws IOException {
        FastReader sc = new FastReader();
        PrintWriter pw = new PrintWriter(System.out);
        int t = 1;
        for (int i = 0; i <t; i++) {
            int n=sc.nextInt();
            String s=sc.next();
            a=new int[n];
            b=new long[n];
            for(int j=0;j<n;j++)
            {
                a[j]=Character.getNumericValue(s.charAt(j));
                b[j]=sc.nextLong();
            }
            dp=new long[n][2][2];
            for(int j=0;j<n;j++)
            {
                for(int k=0;k<2;k++)
                Arrays.fill(dp[j][k],-1);
            }
            pw.write(Math.min(getAns(1,a[0],0),b[0]+getAns(1,(a[0]+1)%2,0))+"\n");
        }
        pw.flush();
        pw.close();
    }

    static long getAns(int curr, int prev, int found_or_not)
    {
        if(curr==a.length)
        {
            if(found_or_not==0)
            return Long.MAX_VALUE-(long)1e9;
            else
            return 0;
        }
        else if(dp[curr][prev][found_or_not]!=-1)
        return dp[curr][prev][found_or_not];
        else
        {
            long currans=0;
            if(found_or_not==1)
            {
                if(a[curr]==prev)
                currans=b[curr]+getAns(curr+1,(a[curr]+1)%2,1);
                else
                currans=getAns(curr+1,a[curr],1);
            }
            else
            {
                long change=b[curr]+getAns(curr+1,(a[curr]+1)%2,a[curr]==prev?0:1);
                long notchange=getAns(curr+1,a[curr],a[curr]==prev?1:0);
                currans=Math.min(change,notchange);
            }
            return dp[curr][prev][found_or_not]=currans;
        }
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

    // Time Complexity : O(n)
    // Best mod values for hashing = 1e9 + 9
    static void prefixHashAndModInverse(String s, long[] prefixhash, long[] modInversePowP) 
    {
        long p = 31;
        long currhash = 0;
        long powP = 1;
        for(int j=0;j<s.length();j++) {
            char c = s.charAt(j);
            currhash = (currhash + (c - '0' + 1) * powP) % MOD;
            prefixhash[j] = currhash;
            powP = (powP * p) % MOD;
        }
        for(int j=0;j<s.length();j++)
        modInversePowP[j]=inverseMOD(power(p,j,MOD), MOD)%MOD;
    }

    // Use this for multiple nCr queries
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

    // Use this for multiple nCr queries
    // Time Complexity : log(n) * n * r
    static long nCrModpLucas(long n, long r, long p) {
        if (r == 0)
            return 1;
        long ni = n % p;
        long ri = r % p;
        return (nCrModpLucas(n / p, r / p, p) % p * nCrModpDP(ni, ri, p) % p) % p;
    }

    // Use this for single nCr calculation when n <= 1e6
    // Time Complexity : n
    public static long nCrModpFermat(long n, long r, long p) {
        if (n < r)
            return 0;
        if (r == 0)
            return 1;
        long[] fac = new long[(int) (n + 1)];
        fac[0] = 1;
        for (long i = 1; i <= n; i++)
            fac[(int)i] = (fac[(int)i - 1] * i) % p;
        return (fac[(int) n] * inverseMOD(fac[(int)r], p) % p * inverseMOD(fac[(int) (n - r)], p) % p) % p;
    }

    // Time Complexity : log(mod)
    static long inverseMOD(long x, long mod)
    {
        return power(x,mod-2,mod)%mod;
    }

    // Time Complexity : log(exponent)
    static long power(long base, long exponent, long mod) {
        long ans = 1;
        base = base % mod; 
        while (exponent > 0)
        {
            if (exponent%2!=0)
                ans = (ans * base) % mod;
            exponent/=2;
            base = (base * base) % mod;
        }
        return ans;
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
    static long[][] getBitMap(long[] a) {
        int n = a.length;
        long[][] bit_map = new long[n][60];
        for (int j = 0; j < n; j++)
            Arrays.fill(bit_map[j], 0);
        long b[] = new long[n];
        for (int j = 0; j < n; j++)
            b[j] = a[j];
        for (int j = 0; j < n; j++) {
            int counter = 0;
            while (b[j] != 0) {
                bit_map[j][counter] = b[j] % 2;
                b[j] /= 2;
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
        ArrayList<Integer> al = new ArrayList<>();
        for (long j = 2; j <= n; j++) {
            if (prime[(int) j])
                al.add((int) j);
        }
        return al;
    }

    // Time Complexity : n
    static boolean sortedIncreasing(int[] a) {
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
    static ArrayList<Long> getFactors(long n) {
        ArrayList<Long> al = new ArrayList<>();
        // int count = 0;
        for (long i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                al.add( i);
                // count++;
                if (n / i != i) {
                    al.add( (n / i));
                    // count++;
                }
            }
        }
        Collections.sort(al);
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

    static void sort(char[] a) {
        ArrayList<Character> l = new ArrayList<>();
        for (char i : a)
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

    // -------------------------BIT-------------------------

    // private static class BIT {
    //     long[] bit;
    //     long[] arr;
    //     int len;
 
    //     public BIT(int len) {
    //         bit = new long[len + 1];
    //         arr = new long[len];
    //         this.len = len;
    //     }
 
    //     public void add(int ind, long val) {
    //         arr[ind] += val;
    //         ind++;
    //         for (; ind <= len; ind += ind & -ind) {
    //             bit[ind] += val;
    //         }
    //     }
 
    //     public long query(int ind) {
    //         ind++;
    //         long sum = 0;
    //         for (; ind > 0; ind -= ind & -ind) {
    //             sum += bit[ind];
    //         }
    //         return sum;
    //     }
    // }

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

    // int first = 0;
    // int second = 0;
    // char first = 'a';
    // int second = -1;

    // Pair(int x, int y) {
    //     first = x;
    //     second = y;
    // }

    // Pair(char x, int y) {
    // first = x;
    // second = y;
    // }

}

class ArrayComparator implements Comparator<long[]> {
    public int compare(long[] a, long[] b) {
        return Long.compare(b[0], a[0]);
    }
}