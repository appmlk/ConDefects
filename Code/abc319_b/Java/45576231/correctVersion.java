import java.util.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;


@SuppressWarnings("unchecked")
public class Main {
    public static PrintWriter pw = new PrintWriter(System.out);

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;
 
        public FastReader()
        {
            br = new BufferedReader(
                new InputStreamReader(System.in));
        }
 
        String next()
        {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
 
        int nextInt() { return Integer.parseInt(next()); }
 
        long nextLong() { return Long.parseLong(next()); }
 
        double nextDouble()
        {
            return Double.parseDouble(next());
        }
 
        String nextLine()
        {
            String str = "";
            try {
                str = br.readLine();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }


    public static int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};
    
    
    public static class Tree
    {
        Node root;
    }
    
    public static class Node
    {
        int v;
        ArrayList<Node> children;
        
        Node(int v, ArrayList<Node> children)
        {
            this.v = v;
            this.children = children;
        }
        
    }
    
    public static void dfs(int v , Set<Integer> ss)
    {
        
        ss.add(v);
        
        ans++;
        for(int x : graph[v])
        {
            if(!ss.contains(x))
            {
                dfs(x,ss);
            }
        }
        ss.remove(v);
    }
    
    public static void sieveOfEratosthenes(int n)
    {
        // Create a boolean array "prime[0..n]" and initialize
        // all entries it as true. A value in prime[i] will
        // finally be false if i is Not a prime, else true.
        boolean prime[] = new boolean[n+1];
        for(int i=0;i<=n;i++)
            prime[i] = true;
         
        for(int p = 2; p*p <=n; p++)
        {
            // If prime[p] is not changed, then it is a prime
            if(prime[p] == true)
            {
                // Update all multiples of p
                for(int i = p*p; i <= n; i += p)
                    prime[i] = false;
            }
        }
         
        // Print all prime numbers
        for(int i = 2; i <= n; i++)
        {
            if(prime[i] == true)
                System.out.print(i + " ");
        }
    }


    public static ArrayList<Integer> prime_fast()
    {
        ArrayList<Integer>P=new ArrayList();
        boolean[]F=new boolean[32000];
        P.add(2);
        for(int i=3;i<32000;i+=2) {
            if (F[i]) continue;
            P.add(i);
            for (int j=i*2;j<32000;j+=i) F[j]=true;
        }
        return P;
    }
    
    // dsu
    public static int find(int x)
    {
        
        if (parent[x] != x) {
            
            parent[x] = find(parent[x]);
        }
 
        return parent[x];
    }
    public static void union(int x, int y)
    {
        // Find representatives of two sets
        int xRoot = find(x), yRoot = find(y);
 
        // Elements are in the same set, no need
        // to unite anything.
        if (xRoot == yRoot)
            return;
 
        // If x's rank is less than y's rank
        if (rank[xRoot] < rank[yRoot])
 
        {// Then move x under y  so that depth
            // of tree remains less
            parent[xRoot] = yRoot;
            rank[yRoot] +=rank[xRoot];
            rank[xRoot]=1;
        }
 
        // Else if y's rank is less than x's rank
        else if (rank[yRoot] < rank[xRoot])
        {
            // Then move y under x so that depth of
            // tree remains less
            parent[yRoot] = xRoot;
            rank[xRoot] +=rank[yRoot];
            rank[yRoot]=1;
        }
 
        else // if ranks are the same
        {
            // Then move y under x (doesn't matter
            // which one goes where)
            parent[yRoot] = xRoot;
 
            // And increment the result tree's
            // rank by 1
            rank[xRoot] = rank[xRoot] + rank[yRoot];
            rank[yRoot]=1;
        }
    }
    
    public static void makegraph(int n , int m)
    {
        for(int a=0;a<n;a++)
        {
            graph[a] = new ArrayList<>();
        }
        
        for(int a=0;a<m;a++)
        {
            int v1 = scn.nextInt()-1;
            int v2 = scn.nextInt()-1;
            
            graph[v1].add(v2);
            graph[v2].add(v1);
        }
    }
    
    public static int[] arrInt(int n)
    {
        
        int [] arr = new int[n];
        for(int a=0;a<n;a++)
        {arr[a] = scn.nextInt();}
        return arr;
    }
    
    public static long[] arrLong(int n)
    {
        
        long [] arr = new long[n];
        for(int a=0;a<n;a++)
        {arr[a] = scn.nextLong();}
        return arr;
    }
    
    public static int[] arrInt()
    {
        int n = scn.nextInt();
        int [] arr = new int[n];
        for(int a=0;a<n;a++)
        {arr[a] = scn.nextInt();}
        return arr;
    }
    
    public static long[] arrLong()
    {
        int n = scn.nextInt();
        long [] arr = new long[n];
        for(int a=0;a<n;a++)
        {arr[a] = scn.nextLong();}
        return arr;
    }
    
    public static void display(int [] arr)
    {
        for(int i : arr)
        {pw.print(i+" ");}
        pw.println();
    }
    
    public static void display(long [] arr)
    {
        for(long i : arr)
        {pw.print(i+" ");}
        pw.println();
    }


    // ==================== MATHEMATICAL FUNCTIONS ===========================

    private static int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    private static long gcd(long a, long b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    private static int lcm(int a, int b) {
        return (a / gcd(a, b)) * b;
    }

    private static long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }

    private static int mod_pow(long a, long b, long mod) {
        if (b == 0)
            return 1;
        int temp = mod_pow(a, b >> 1, mod);
        temp %= mod;
        temp = (int) ((1L * temp * temp) % mod);
        if ((b & 1) == 1)
            temp = (int) ((1L * temp * a) % mod);
        return temp;
    }

    private static long multiply(long a, long b) {
        return (((a % mod) * (b % mod)) % mod);
    }

    private static long divide(long a, long b) {
        return multiply(a, mod_pow(b, mod - 2,mod));
    }

    private static boolean isPrime(long n) {
        for (long i = 2; i * i <= n; i++)
            if (n % i == 0)
                return false;
        return true;
    }

    public static long fact(long n)
    {
 
        return (n == 1 || n == 0) ? 1 : n * fact(n - 1);
    }

public static long ncr(long n, long k)
    {
        long res = 1;
 
        // Since C(n, k) = C(n, n-k)
        if (k > n - k)
            k = n - k;
 
        // Calculate value of
        // [n * (n-1) *---* (n-k+1)] / [k * (k-1) *----* 1]
        for (long i = 0; i < k; ++i) {
            res *= (n - i);
            res /= (i + 1);
        }
 
        return res;
    }

    public static long power(long x, long y, long p)
    {
  
        // Initialize result
        long res = 1;
  
        // Update x if it is more than or
        // equal to p
        x = x % p;
  
        while (y > 0) {
  
            // If y is odd, multiply x
            // with result
            if (y % 2 == 1)
                res = (res * x) % p;
  
            // y must be even now
            y = y >> 1; // y = y/2
            x = (x * x) % p;
        }
  
        return res;
    }
  
    // Returns n^(-1) mod p
    public static long modInverse(long n, long p)
    {
        return power(n, p - 2, p);
    }
  
    // Returns nCr % p using Fermat's
    // little theorem.
    public static long nCrModPFermat(long n, long r,
                             long p)
    {
  
          if (n<r) 
              return 0;
      // Base case
        if (r == 0)
            return 1;
  
        // Fill factorial array so that we
        // can find all factorial of r, n
        // and n-r
        long[] fac = new long[(int)(n + 1)];
        fac[0] = 1;
  
        for (int i = 1; i <= n; i++)
            fac[i] = fac[i - 1] * i % p;
  
        return (fac[(int)n] * modInverse(fac[(int)r], p)
                % p * modInverse(fac[(int)(n - r)], p)
                % p)
            % p;
    }
    

    private static List<Integer> factors(int n) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; 1L * i * i <= n; i++)
            if (n % i == 0) {
                list.add(i);
                if (i != n / i)
                    list.add(n / i);
            }
        return list;
    }

    private static List<Long> factors(long n) {
        List<Long> list = new ArrayList<>();
        for (long i = 1; i * i <= n; i++)
            if (n % i == 0) {
                list.add(i);
                if (i != n / i)
                    list.add(n / i);
            }
        return list;
    }

// gp sum
    // starting from a^0 common factor = a , no. of terms = n, modulo m
    private static long gp_sum(long a, long n, long m) {
            if (n == 0) {
                return 0;
            }
            if (n % 2 != 0) {
                return (power(a, n - 1, m) + gp_sum(a, n - 1, m)) % m;
            }
            long t = gp_sum(a, n / 2, m);
            long b = power(a, n / 2, m);
            return (b + 1) * t % m;
        }

    // trie
static class Trie {
 
        static class Node {
            public Node[] children;
            public int sz = 0;
            Node() {
                children = new Node[26];
            }
        }
 
        private Node head;
 
        public Trie() {
            head = new Node();
        }
 
        public int query(String word) {
            Node curr = head;
            int n = word.length();
            for (int i = 0; i < n; ++i) {
                int ch = word.charAt(i) - 'a';
                if (curr.children[ch] != null && curr.children[ch].sz > 0) {
                    curr = curr.children[ch];
                } else {
                    return i;
                }
            }
            return n;
        }
 
        public void insert(String word) {
            Node curr = head;
            int n = word.length();
            for (int i = 0; i < n; ++i) {
                int ch = word.charAt(i) - 'a';
                if (curr.children[ch] == null) {
                    curr.children[ch] = new Node();
                }
                curr = curr.children[ch];
                curr.sz++;
            }
        }
 
        public void erase(String word) {
            Node curr = head;
            int idx = 0, n = word.length();
            while (idx < n) {
                int ch = word.charAt(idx) - 'a';
                curr = curr.children[ch];
                curr.sz--;
                ++idx;
            }
        }
    }
    
    static class LazyRangeSumSegmentTree {
    int n;
    int tree[];
    int lazy[];
 
    LazyRangeSumSegmentTree(int[] arr) {
      this.n = arr.length;
      int m = nextPowerOf2(n);
      this.tree = new int[m * 2 - 1];
      this.lazy = new int[m * 2 - 1];
      constructSegTree(arr, 0, n - 1, 0);
    }
 
    void constructSegTree(int arr[], int lo, int hi, int idx) {
      if (lo > hi) {
        return;
      }
      if (lo == hi) {
        tree[idx] = arr[lo];
        return;
      }
      int mid = (lo + hi) / 2;
      constructSegTree(arr, lo, mid, idx * 2 + 1);
      constructSegTree(arr, mid + 1, hi, idx * 2 + 2);
      tree[idx] = tree[idx * 2 + 1] + tree[idx * 2 + 2];
    }
 
    // Update by adding inc to all values in [b, e]
    public void updateRange(int b, int e, int inc)  {
      if (b > e) {
        return;
      }
      updateRangeInner(0, 0, n - 1, b, e, inc);
    }
 
    // Set value at a specific index.
    public void set(int index, int value)  {
      checkIndex(index);
      int vcurr = getRangeSum(index, index);
      int inc = value - vcurr;
      updateRange(index, index, inc);
    }
 
    // Gets value at the specific index
    public int getAt(int index) {
      checkIndex(index);
      return getRangeSum(0, 0, n - 1, index, index);
    }
 
    // Get sum in range [b, e]
    public int getRangeSum(int b, int e) {
      if (b < 0 || e > n - 1 || b > e) {
        return 0;
      }
      return getRangeSum(0, 0, n - 1, b, e);
    }
 
    private void checkIndex(int index) {
      if (index < 0 || index >= n) {
        throw new RuntimeException("Invalid index " + index);
      }
    }
 
    private void updateRangeInner(int idx, int lo, int hi, int b, int e, int inc) {
      applyLazy(idx, lo, hi);
      if (lo > hi || lo > e || hi < b) {
        return;
      }
      if (lo >= b && hi <= e) {
        tree[idx] += inc * (hi - lo + 1);
        if (lo != hi) {
          lazy[idx * 2 + 1] += inc;
          lazy[idx * 2 + 2] += inc;
        }
        return;
      }
      int mid = (lo + hi) / 2;
      updateRangeInner(idx * 2 + 1, lo, mid, b, e, inc);
      updateRangeInner(idx * 2 + 2, mid + 1, hi, b, e, inc);
      tree[idx] = tree[idx * 2 + 1] + tree[idx * 2 + 2];
    }
 
    private int getRangeSum(int idx, int lo, int hi, int b, int e) {
      applyLazy(idx, lo, hi);
      if (lo > hi || lo > e || hi < b) {
        return 0;
      }
      if (lo >= b && hi <= e) {
        return tree[idx];
      }
      int mid = (lo + hi) / 2;
      return getRangeSum(2 * idx + 1, lo, mid, b, e) +
          getRangeSum(2 * idx + 2, mid + 1, hi, b, e);
    }
 
    private void applyLazy(int idx, int lo, int hi) {
      if (lazy[idx] != 0) {
        tree[idx] += lazy[idx] * (hi - lo + 1);
        if (lo != hi) {
          lazy[idx * 2 + 1] += lazy[idx];
          lazy[idx * 2 + 2] += lazy[idx];
        }
        lazy[idx] = 0;
      }
    }
 
    public static int nextPowerOf2(int num){
      if (num == 0) {
        return 1;
      }
      if (num > 0 && (num & (num-1)) == 0) {
        return num;
      }
      while ((num & (num-1)) > 0) {
          num = num & (num - 1);
      }
      return num << 1;
    }
  }
    
    //public static ArrayList<ArrayList<Integer>> fans = new ArrayList<>();
    public static int [] parent = null;
    public static int [] rank = null;
    public static ArrayList<Integer>[] graph = null;
    public static int ans = 0;
    public static FastReader scn = new FastReader();
    public static long c = 1000000007;
    public static long mod = 1000000007;
    
    // use Collections.sort() instead of Arrays.sort()
    public static void main(String[] args)
    {
        scn = new FastReader();
        //graph = new ArrayList[n];
        //LazyRangeSumSegmentTree st = new LazyRangeSumSegmentTree(new int[n+1]);
            
        int n = scn.nextInt();
        
        int [] ans = new int[n+1];
        
        Arrays.fill(ans,-1);
        TreeSet<Integer> ss = new TreeSet<>();
        
        for(int a=1;a<=9;a++)
        {
            if(n%a==0)ss.add(a);
        }
        
        ans[0] = 1;
        for(int a=1;a<=n;a++)
        {
            for(int val : ss)
            {
                int v = n/val;
                if(a%v==0)
                {
                    ans[a] = val;
                    break;
                }
            }
        }
        
        for(int i : ans)
        {
            if(i==-1)
            {pw.print("-");}
            else
            {pw.print(i);}
        }
        
        pw.println();
        

            
                
            
        
        pw.close();}
    
}


    
    
    

    