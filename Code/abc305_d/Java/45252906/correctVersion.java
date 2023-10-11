/*


███╗░░░███╗░█████╗░██╗░░██╗░█████╗░███╗░░░███╗███████╗██████╗░  ██╗░░██╗░█████╗░░██████╗░██████╗░█████╗░███╗░░██╗
████╗░████║██╔══██╗██║░░██║██╔══██╗████╗░████║██╔════╝██╔══██╗  ██║░░██║██╔══██╗██╔════╝██╔════╝██╔══██╗████╗░██║
██╔████╔██║██║░░██║███████║███████║██╔████╔██║█████╗░░██║░░██║  ███████║███████║╚█████╗░╚█████╗░███████║██╔██╗██║
██║╚██╔╝██║██║░░██║██╔══██║██╔══██║██║╚██╔╝██║██╔══╝░░██║░░██║  ██╔══██║██╔══██║░╚═══██╗░╚═══██╗██╔══██║██║╚████║
██║░╚═╝░██║╚█████╔╝██║░░██║██║░░██║██║░╚═╝░██║███████╗██████╔╝  ██║░░██║██║░░██║██████╔╝██████╔╝██║░░██║██║░╚███║
╚═╝░░░░░╚═╝░╚════╝░╚═╝░░╚═╝╚═╝░░╚═╝╚═╝░░░░░╚═╝╚══════╝╚═════╝░  ╚═╝░░╚═╝╚═╝░░╚═╝╚═════╝░╚═════╝░╚═╝░░╚═╝╚═╝░░╚══╝

 */

import java.util.*;
import java.io.*;

public class Main {
    //**********************Code**************\\
    static Scanner sc = new Scanner(System.in);
    static PrintWriter pw = new PrintWriter(System.out);
    static int x, y, z;
    static int[] arr;
    static int dp[][];
    static boolean vis[];
    static ArrayList<Integer>[] adjlist;
    static int dx[] = {-1, 0, 1, 0, -1, 1, 1, -1};
    static int dy[] = {0, 1, 0, -1, 1, 1, -1, -1};
    static ArrayList<Pair> pq;

    public static void main(String[] args) throws IOException {
        int n = sc.nextInt();
        long arr[] = sc.nextlongArray(n);
        pq = new ArrayList<>();
        for (int i = 1; i < n; i++)
            if (i % 2 == 0)
                pq.add(new Pair(arr[i - 1], arr[i]));
        long prefix[] = new long[pq.size() + 1];
        for (int i = 1; i < prefix.length; i++)
            prefix[i] = prefix[i - 1] + pq.get(i - 1).second - pq.get(i - 1).first;
//        System.out.println(pq);
//        System.out.println(Arrays.toString(prefix));

        int q = sc.nextInt();
        while (q-- > 0) {
            long l = sc.nextLong();
            long r = sc.nextLong();
            int left = binarysearchleft((int) l);
            int right = binarysearchright((int) r);
            long ans = 0;
//            System.out.println(left + " " + right);
//             System.out.println(prefix[left] + " " + prefix[right]);
            if (left == right&&r <= pq.get(left).first) {

                ans = 0;
            } else if (l <= pq.get(left).first && r >= pq.get(right).second) {
                ans = prefix[right + 1] - prefix[left];
                // pw.println("first");
            } else if (l <= pq.get(left).first && r < pq.get(right).second) {
                ans = prefix[right] - prefix[left] + r - pq.get(right).first;
                // pw.println("second");
            } else if (l > pq.get(left).first && r >= pq.get(right).second) {
                ans = prefix[right + 1] - prefix[left + 1] + pq.get(left).second - l;
                // pw.println("third");
            } else  {
                ans = prefix[right] - prefix[left + 1] + pq.get(left).second - l + r - pq.get(right).first;
                // pw.println("fourth");
            }
            pw.println(ans);
//
        }


        pw.flush();

    }

    static int binarysearchleft(int val) {

        int low = 0;
        int high = pq.size() - 1;
        int ans = 0;
        while (low <= high) {
//            if (low + 1 == high)
//                return low;
            int mid = (low + high) / 2;
            if (pq.get(mid).first <= val && pq.get(mid).second >= val) {
                return mid;
            } else if (pq.get(mid).second <= val) {
                ans = mid + 1;
                low = mid + 1;
            } else
                high = mid - 1;
            //    System.out.println(low+" "+high);
        }
        return ans;
    }

    static int binarysearchright(int val) {

        int low = 0;
        int high = pq.size() - 1;
        int ans = 0;
        while (low <= high) {
            int mid = (low + high) / 2;

            if (pq.get(mid).first <= val && pq.get(mid).second >= val) {
                return mid;
            } else if (pq.get(mid).second <= val) {
                ans = mid;
                low = mid + 1;
            } else {

                high = mid - 1;
            }
        }
        return ans;
    }

    //**********************Scanner&helpers**************\\

    static class Scanner {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

        public Scanner(FileReader r) {
            br = new BufferedReader(r);
        }

        public String next() throws IOException {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        public long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        public String nextLine() throws IOException {
            return br.readLine();
        }

        public double nextDouble() throws IOException {
            String x = next();
            StringBuilder sb = new StringBuilder("0");
            double res = 0, f = 1;
            boolean dec = false, neg = false;
            int start = 0;
            if (x.charAt(0) == '-') {
                neg = true;
                start++;
            }
            for (int i = start; i < x.length(); i++)
                if (x.charAt(i) == '.') {
                    res = Long.parseLong(sb.toString());
                    sb = new StringBuilder("0");
                    dec = true;
                } else {
                    sb.append(x.charAt(i));
                    if (dec)
                        f *= 10;
                }
            res += Long.parseLong(sb.toString()) / f;
            return res * (neg ? -1 : 1);
        }

        public long[] nextlongArray(int n) throws IOException {
            long[] a = new long[n];
            for (int i = 0; i < n; i++)
                a[i] = nextLong();
            return a;
        }

        public Long[] nextLongArray(int n) throws IOException {
            Long[] a = new Long[n];
            for (int i = 0; i < n; i++)
                a[i] = nextLong();
            return a;
        }

        public int[] nextIntArray(int n) throws IOException {
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = nextInt();
            return a;
        }

        public Integer[] nextIntegerArray(int n) throws IOException {
            Integer[] a = new Integer[n];
            for (int i = 0; i < n; i++)
                a[i] = nextInt();
            return a;
        }

        public boolean ready() throws IOException {
            return br.ready();
        }

    }

    //*******helper methods*****\\
    static long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }

    public static long gcd(long f, long b) {
        if (f == 0)

            return b;

        return gcd(b % f, f);
    }

    public static long nCr(long n, long r) {
        return fact(n) / (fact(r) * fact(n - r));
        //return divm(fact[(int) n] , mulm(fact[(int) r] , fact[(int) (n - r)]));
    }

    // Returns factorial of n
    static long fact(long n) {
        if (n == 0)
            return 1;
        int res = 1;
        for (int i = 2; i <= n; i++)
            res = res * i;
        return res;
    }

    public static int count(int arr[], int c) {

        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == c)
                count++;
        }
        return count;

    }

    static void shuffle(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int a = (int) (Math.random() * arr.length);
            int b = (int) (Math.random() * arr.length);
            int tmp = arr[a];
            arr[a] = arr[b];
            arr[b] = tmp;
        }
    }

    static void bin(long n) {
        long i;
        System.out.print("0");
        for (i = 1 << 30; i > 0; i = i / 2) {
            if ((n & i) != 0) {
                System.out.print("1");
            } else {
                System.out.print("0");
            }
        }
    }

    static ArrayList<Integer> primes = new ArrayList<>();

    static void sieveOfEratosthenes(int n) {
        primes = new ArrayList<>();
        sieveOfEratosthenes1(n);

    }

    static void sieveOfEratosthenes1(int n) {
        // Create a boolean array "prime[0..n]" and
        // initialize all entries it as true. A value in
        // prime[i] will finally be false if i is Not a
        // prime, else true.
        boolean prime[] = new boolean[n + 1];
        for (int i = 0; i <= n; i++)
            prime[i] = true;
        prime[0] = prime[1] = false;

        for (int p = 2; p * p <= n; p++) {
            // If prime[p] is not changed, then it is a
            // prime
            if (prime[p] == true) {
                // Update all multiples of p greater than or
                // equal to the square of it numbers which
                // are multiple of p and are less than p^2
                // are already been marked.
                for (int i = p * p; i <= n; i += p)
                    prime[i] = false;
            }
        }

        // Print all prime numbers
        for (int i = 2; i <= n; i++) {
            if (prime[i] == true)
                primes.add(i);
        }
    }

    static ArrayList<Integer> primefactorsusingsieve(int n) {
        ArrayList<Integer> ans = new ArrayList<>();
        sieveOfEratosthenes(n);
        int i = 0;
        int o = primes.get(i);
        while (o * o <= n) {
            while (n % o == 0) {
                n /= o;
                ans.add(o);
            }
            i++;
            o = primes.get(i);
        }
        if (n != 1)
            ans.add(n);
        return ans;

    }

    final static int P = 1000000007, N = 1000002;

    static long[] fact = new long[N];

    static long addm(long x, long y) {
        return (x + y) % P;
    }

    static long subm(long l, long y) {
        return ((l - y) % P + P) % P;
    }

    static long mulm(long x, long y) {
        return (x * y) % P;
    }

    static long powrm(long x, long y) {
        long res = 1;
        while (y > 0) {
            if ((y & 1) == 1) {
                res = mulm(res, x);
            }
            y /= 2;
            x = mulm(x, x);
        }
        return res;
    }

    static long inv(long x) {
        return powrm(x, P - 2);
    }

    static long divm(long x, long y) {
        return mulm(x, inv(y));
    }

    static void calculate_factorials() {
        fact[0] = 1;
        for (int i = 1; i < N; i++) {
            fact[i] = mulm(fact[i - 1], i);
        }
    }

    public static List<Integer> primeFactors(int n) {
        List<Integer> factors = new ArrayList<>();
        while (n % 2 == 0) {
            factors.add(2);
            n /= 2;
        }
        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            while (n % i == 0) {
                factors.add(i);
                n /= i;
            }
        }
        if (n > 2) {
            factors.add(n);
        }
        return factors;
    }

    public static int binarySearch(long[] arr, long x) {
        int ans = arr.length - 1;
        int low = 0;
        int hight = arr.length - 1;
        while (low <= hight) {
            int mid = (low + hight) / 2;
            if (arr[mid] == x)
                return mid;
            if (arr[mid] > x) {
                ans = mid;
                hight = mid - 1;
            } else
                low = mid + 1;
        }
        return ans;
    }

    public static void dfs(int i) {
        vis[i] = true;
        for (int j : adjlist[i]) {
            if (!vis[j])
                dfs(j);
        }
    }

    public static void dijkstra(int a, long[] cost) {
        // q = new PriorityQueue<>();
        PriorityQueue<Pair> q = new PriorityQueue<>();
        q.add(new Pair(0, a));
        cost[a] = 0;
        while (!q.isEmpty()) {
            // System.out.println(q);
            long curnode = q.peek().first;
            long curcosr = q.peek().second;

            q.poll();

            if (vis[(int) curnode]) {

                // System.out.println("sdsdsd");
                continue;
            }
            vis[(int) curnode] = true;
            // !!!!take care here uncomment this part and change
            //static ArrayList<Integer>[] adjlist; to
            //static ArrayList<Pair>[] adjlist;

            //			for (Pair i : adjlist[(int) curnode]) {
            //				if (i.second + curcosr < cost[(int) i.first]) {
            //					cost[(int) i.first] = i.second + curcosr;
            //					q.add(new Pair(i.first, cost[(int) i.first]));
            //				}
            //
            //			}

        }
        // return -1;

    }

    //Tree map sorting
    //Firstly modify the ValueComparator according to the type of the key and the value
	/*how to use:
	1-initialize a HashMap and store whatever value you want in it
	2-initialize a TreeMap and call ValueComparator like the following example

	//Map<Long, Long> kk = new TreeMap<>(new ValueComparator(map));
	//kkHashMap.putAll(map);
	//map is the name of the HashMap  */

    static class ValueComparator implements Comparator<Long> {

        Map<Long, Long> map;

        public ValueComparator(Map<Long, Long> map) {
            this.map = map;
        }

        @Override
        public int compare(Long o1, Long o2) {
            // TODO Auto-generated method stub
            if (map.get(o1) >= map.get(o2)) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    static class Pair implements Comparable<Pair> {
        long first;
        long second;

        public Pair(long first, long second) {
            this.first = first;
            this.second = second;
        }

        public int compareTo(Pair p) {
            if (first != p.first)
                return Long.compare(first, p.first);
            else if (second != p.second)
                return Long.compare(second, p.second);
            else
                return 0;
        }

        public String toString() {
            return first + " " + second;

        }
    }


}
