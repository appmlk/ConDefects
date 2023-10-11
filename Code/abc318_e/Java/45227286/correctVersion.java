import java.util.*;
import java.io.*;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.math.BigInteger;

// https://leetcode.com/contest/weekly-contest-176/
class A {
    int a;
    A(int a) {
        this.a = a;
        return;
    }
}
public class Main {
    static class P {
        String g;
        int x , y;
        P(String g, int x , int y) {
            this.x = x;
            this.y = y;
            this.g = g;
        }
    }
    static class Node {
        long sum, pre;
        Node(long a, long b) {
            this.sum = a;
            this.pre = b;
        }
    }
    // static  class SegmentTree {
    //     int l , r; // range responsible for
    //     SegmentTree left , right;
    //     List<Integer> list;
    //     List<Long> ps;
    //     long val;
    //     long lazy = 0;
    //     SegmentTree(int l,int r,int a[]) {
    //         this.l = l;
    //         this.r = r;
    //         list = new ArrayList<>();
    //         ps = new ArrayList<>();
    //         if(l == r) {
    //             list.add(a[l]);
    //             ps.add(a[l]*1l);
    //             this.val = a[l];
    //             return;
    //         }
    //         int mid = l + (r-l)/2;
    //         this.left = new SegmentTree(l ,mid , a);
    //         this.right = new SegmentTree(mid + 1 , r,a);
    //         // this.val = (this.left.val + this.right.val);
    //         merge(this.list , this.left.list , this.right.list,this.ps);
    //     }
    //     public static void merge(List<Integer> A , List<Integer> B , List<Integer>C,List <Long> ps) {
    //         int i = 0, j = 0;
    //         while(i < B.size() && j < C.size()) {
    //             if(B.get(i) <= C.get(j)) A.add(B.get(i++));
    //             else  A.add(C.get(j++));
    //         }
    //         while(i < B.size())  A.add(B.get(i++));
    //         while(j < C.size())  A.add(C.get(j++));
    //         for(int index = 0;index < A.size();index++) {
    //             if(index > 0) ps.add(ps.get(index-1) + A.get(index));
    //             else ps.add(A.get(index)*1l);
    //         }
    //     }
    //     public long query(int left ,int right,int x) {
    //         if(right < left) return 0l;
    //         // if(this.lazy != 0) {
    //         //     this.val += (this.r - this.l + 1)*this.lazy;
    //         //     if(this.left != null)this.left.lazy += this.lazy;
    //         //     if(this.right != null) this.right.lazy += this.lazy;
    //         //     this.lazy = 0;
    //         // }
    //         if(this.l > right || this.r < left) return 0l;
    //         if(this.l >= left && this.r <= right) {
    //             int L =0 , R = this.list.size()-1;
    //             long ans1 = 0 , ans2 = 0;
    //             while(L <= R) {
    //                 int mid = (R-L)/2 + L;
    //                 if(this.list.get(mid) >= x) R = mid -1;
    //                 else {
    //                     long elem = mid + 1;
    //                     ans1 = elem*x - this.ps.get(mid);
    //                     L = mid + 1;
    //                 }
    //             }
    //             L = 0;R = this.ps.size()-1;
    //             while(L <= R) {
    //                 int mid = (R-L)/2 + L;
    //                 if(this.ps.get(mid) <= x) L = mid + 1;
    //                 else {
    //                     long elem = this.ps.size()-mid;
    //                     long cut = mid>0?this.ps.get(mid-1):0;
    //                     ans2 = this.ps.get(this.ps.size()-1) - cut - elem*x;
    //                     R = mid - 1;
    //                 }
    //             }
    //             return ans1 + ans2;
    //         }
    //         return (this.left.query(left , right,x) + this.right.query( left , right,x)) ;
    //     }
    //     public void pointUpdate(int index ,int val) {
    //         if(this.l > index || this.r < index) return;
    //         if(this.l == this.r && this.l == index) {
    //             this.val = val;
    //             return ;
    //         }
    //         this.left.pointUpdate(index ,val );
    //         this.right.pointUpdate(index , val);
    //         this.val = Math.min(this.left.val , this.right.val);
    //     }
    //     public void rangeUpdate(int left , int right, int x) {
    //         if(left > right) return ;
    //         if(this.lazy != 0) {
    //             this.val += 1l*(this.r - this.l  + 1)*this.lazy;
    //             if(this.left != null) this.left.lazy += this.lazy;
    //             if(this.right != null) this.right.lazy += this.lazy;
    //             this.lazy = 0;
    //         }
    //         if(this.l > right || this.r < left) return;
    //         if(this.l >= left && this.r <= right) {
    //             this.val += (this.r - this.l + 1)*x;
    //             if(this.left != null) this.left.lazy += x;
    //             if(this.right != null) this.right.lazy += x;
    //             return;
    //         }
    //         this.left.rangeUpdate(left , right , x);
    //         this.right.rangeUpdate(left , right , x);
    //         this.val = this.left.val + this.right.val;
    //     }
    //     // public long valueAtK(int k) {
    //     //     if(this.l > k || this.r < k) return 0;
    //     //     if(this.l == this.r && this.l == k) {
    //     //         return this.val;
    //     //     }
    //     //     return join(this.left.valueAtK(k) , this.right.valueAtK(k));
    //     // }
    //     public int join(int a ,int b) {
    //         return a + b;
    //     }
    // }
    // Proactively collaborate with cross-functional teams, including backend developers and web designers, to optimize usability and deliver exceptional user experiences.
    static  class SegmentTree {
        int l , r; // range responsible for
        SegmentTree left , right;
        long lcm = 0, limit = (long)1e9 + 7;
        SegmentTree(int l, int r, long a[]) {
            this.l = l;
            this.r = r;
            if (l == r) {
                this.lcm = a[l];
                return;
            }
            int mid = l + (r - l) / 2;
            this.left = new SegmentTree(l , mid , a);
            this.right = new SegmentTree(mid + 1 , r, a);
            this.lcm = this.left.lcm/gcd(this.left.lcm , this.right.lcm)*this.right.lcm;
            if(this.lcm >= limit) this.lcm = limit;
        }
        // public void merge(List<Integer>  A , List<Integer>  B , List<Integer> C) {
        //     int i = 0 , j = 0;
        //     while (i < B.size() && j < C.size()) {
        //         if (B.get(i) <= C.get(j)) A.add(B.get(i++));
        //         else A.add(C.get(j++));
        //     }
        //     while (i < B.size()) A.add(B.get(i++));
        //     while (j < C.size()) A.add(C.get(j++));
        //     // System.out.println(A);
        // }
        public long query(int left , int right) {
            if (right < left) return 1;
            // if (this.rem != 0)  {
            //     this.val ^= this.rem;
            //     if (this.left != null)this.left.rem ^=  this.rem;
            //     if (this.right != null)this.right.rem ^=  this.rem;
            //     this.rem = 0;
            // }

            if (this.l > right || this.r < left) return 1;
            if (this.l >= left && this.r <= right) return  this.lcm;
            long A =this.left.query(left , right) , B = this.right.query( left , right);
            long ans = A/gcd(A , B)*B;
            if(ans >= limit) ans = limit;
            return  ans;
        }
        // public void rangeUpdate1(int left , int right, int x) {
        //     if(left > right) return ;
        //     if(this.l > right || this.r < left) return;
        //     if(this.l >= left && this.r <= right) {
        //         this.val = 1l*(this.r - this.l + 1)*x;
        //         this.f.clear();
        //         this.f.put(x , this.r - this.l + 1);
        //         return;
        //     }
        //     this.left.rangeUpdate1(left , right , x);
        //     this.right.rangeUpdate1(left , right , x);
        //     merge(this.f , this.left.f , this.right.f);
        //     this.val = this.left.val+ this.right.val;
        // }
        // public void rangeUpdate(int left , int right, long x) {
        //     if (left > right) return ;
        //     if (this.rem != 0) {
        //         this.val ^= this.rem;
        //         if (this.left != null)this.left.rem ^=  this.rem;
        //         if (this.right != null)this.right.rem ^=  this.rem;
        //         this.rem = 0;
        //     }
        //     if (this.l > right || this.r < left) return;
        //     if (this.l >= left && this.r <= right) {
        //         this.val ^= x;
        //         if (this.left != null)this.left.rem ^=  x;
        //         if (this.right != null)this.right.rem ^=  x;
        //         return;
        //     }
        //     this.left.rangeUpdate(left , right , x);
        //     this.right.rangeUpdate(left , right , x);
        //     this.val = this.left.val ^ this.right.val;
        // }
    }
    static  class SegmentTree2 {
        int l , r; // range responsible for
        SegmentTree2 left , right;
        List<Integer> list;
        int val;
        SegmentTree2(int l, int r, int a[]) {
            // System.out.println("at " + l + " " + r);
            this.l = l;
            this.r = r;
            if (l == r) {
                // list.add(a[l]);
                this.val = a[l];
                return;
            }
            int mid = l + (r - l) / 2;
            this.left = new SegmentTree2(l , mid , a);
            this.right = new SegmentTree2(mid + 1 , r, a);
            this.val =  Math.min(this.left.val , this.right.val);
        }

        public int query(int left , int right) {
            if (this.l > right || this.r < left) return Integer.MAX_VALUE;
            if (this.l >= left && this.r <= right) {
                return this.val;
            }
            return Math.min(this.left.query(left , right) , this.right.query(left , right));
        }
        public void pointUpdate(int index , int val) {
            if (this.l > index || this.r < index) return;
            if (this.l == this.r && this.l == index) {
                this.val = val;
                return ;
            }
            this.left.pointUpdate(index , val );
            this.right.pointUpdate(index , val);
            this.val = Math.min(this.left.val , this.right.val);
        }
        // public void rangeUpdate(int left , int right) {
        //     if(this.l > right || this.r < left) return;
        //     if(this.l >= left && this.r <= right) {
        //         this.val += this.r-this.l + 1;
        //         System.out.println(" now "   + this.val);
        //         return ;
        //     }

        //     this.left.rangeUpdate(left , right );
        //     this.right.rangeUpdate(left , right );
        //     this.val = this.left.val + this.right.val;
        // }
        // public long valueAtK(int k) {
        //     if(this.l > k || this.r < k) return 0;
        //     if(this.l == this.r && this.l == k) {
        //         return this.val;
        //     }
        //     return join(this.left.valueAtK(k) , this.right.valueAtK(k));
        // }
        public int join(int a , int b) {
            return a + b;
        }
    }
    static class Hash {
        long hash[] , mod = (long)1e9 + 7 , powT[] , prime , inverse[];
        Hash(char []s)  {
            prime = 131;
            int n = s.length;
            powT = new long[n];
            hash = new long[n];
            inverse = new long[n];
            powT[0] = 1;
            inverse[n - 1] = pow(pow(prime  , n - 1 , mod), mod - 2 , mod);
            for (int i = 1; i < n; i++ ) {
                powT[i] = (powT[i - 1] * prime) % mod;
            }
            for (int i = n - 2; i >= 0; i -= 1)  {
                inverse[i] = (inverse[i + 1] * prime) % mod;
            }
            hash[0] = (s[0] - 'a' + 1);
            for (int i = 1; i < n; i++ ) {
                hash[i] = hash[i - 1] + ((s[i] - 'a' + 1) * powT[i]) % mod;
                hash[i] %= mod;
            }
        }
        public long hashValue(int l , int r) {
            if (l == 0) return hash[r] % mod;
            long ans = hash[r]  - hash[l - 1] + mod;
            ans %= mod;
            ans *= inverse[l];
            ans %= mod;
            return ans;
        }
    }
    static class ConvexHull {
        Stack<Integer>stack;
        Stack<Integer>stack1;
        int n;
        Point arr[];
        ConvexHull(Point arr[]) {
            n = arr.length;
            this.arr = arr;
            Arrays.sort(arr , (a , b)-> {
                if (a.x == b.x) return (int)(b.y - a.y);
                return (int)(a.x - b.x);
            });
            Point min = arr[0];
            stack =  new Stack<>();
            stack1 =  new Stack<>();
            stack.push(0);
            stack1.push(0);
            Point ob  = new Point(2, 2);
            for (int i = 1; i < n; i++) {
                if (stack.size() < 2) stack.push(i);
                else {
                    while (stack.size() >= 2) {
                        int a = stack.pop() , b = stack.pop() , c = i;
                        int dir = ob.cross(arr[b] , arr[a] , arr[c]);
                        if (dir < 0) {
                            stack.push(b);
                            stack.push(a);
                            stack.push(c);
                            break;
                        }
                        stack.push(b);
                    }
                    if (stack.size() < 2) {
                        stack.push(i);
                    }
                }
            }
            for (int i = 1; i < n; i++) {
                if (stack1.size() < 2) stack1.push(i);
                else {
                    while (stack1.size() >= 2) {
                        int a = stack1.pop() , b = stack1.pop() , c = i;
                        int dir = ob.cross(arr[b] , arr[a] , arr[c]);
                        if (dir > 0) {
                            stack1.push(b);
                            stack1.push(a);
                            stack1.push(c);
                            break;
                        }
                        stack1.push(b);
                    }
                    if (stack1.size() < 2) {
                        stack1.push(i);
                    }
                }
            }
        }
        public List<Point> getPoints() {
            boolean vis[] = new boolean[n];
            List<Point> list = new ArrayList<>();
            // for(int x : stack) {
            //     list.add(arr[x]);
            //     vis[x] = true;
            // }
            for (int x : stack1) {
                // if(vis[x]) continue;
                list.add(arr[x]);
            }
            return list;
        }
    }
    public static class Suffix implements Comparable<Suffix> {
        int index;
        int rank;
        int next;
        public Suffix(int ind, int r, int nr) {
            index = ind;
            rank = r;
            next = nr;
        }
        public int compareTo(Suffix s) {
            if (rank != s.rank) return Integer.compare(rank, s.rank);
            return Integer.compare(next, s.next);
        }
    }
    static class Point {
        long x , y;
        Point(long x , long y)  {
            this.x = x;
            this.y = y;
        }
        public Point sub(Point a, Point b) {
            return new Point(a.x - b.x , a.y - b.y);
        }
        public int cross(Point a , Point b , Point c) {
            Point g = sub(b, a) , l = sub(c, b);
            long ans = g.y * l.x - g.x * l.y;
            if (ans == 0) return 0;
            if (ans < 0) return -1;
            return 1;
        }
    }
    static class Kattio extends PrintWriter {
        private BufferedReader r;
        private StringTokenizer st;

        // standard input
        public Kattio() { this(System.in, System.out); }
        public Kattio(InputStream i, OutputStream o) {
            super(o);
            r = new BufferedReader(new InputStreamReader(i));
        }
        // USACO-style file input
        public Kattio(String problemName) throws IOException {
            super(new FileWriter(problemName + ".out"));
            r = new BufferedReader(new FileReader(problemName + ".in"));
        }

        // returns null if no more input
        public String next() {
            try {
                while (st == null || !st.hasMoreTokens())
                    st = new StringTokenizer(r.readLine());
                return st.nextToken();
            } catch (Exception e) { }
            return null;
        }

        public int nextInt() { return Integer.parseInt(next()); }
        public double nextDouble() { return Double.parseDouble(next()); }
        public long nextLong() { return Long.parseLong(next()); }
    }
    static HashMap<Integer , List<Integer>> graph;
    static Kattio sc = new Kattio();
    static long  mod  = (long)1e9 + 7;
    static String endl = "\n" , gap = " ";
    static HashMap<Integer , Long> value;
    static int size[];
    static int parent[] , strength[];
    // static long fac[];
    static long inv[];
    static boolean vis[];
    static HashSet<String> guess;
    static long primePow[];
    static int N;
    // static int dis[];
    static int height[];
    static long p[];
    static int endTime[];
    static int colx[] =  {1, -1, 0, 0};
    static int coly[] =  {0, 0, 1, -1};
    // static int time;
    static Long dp[][];
    // static HashMap<String , Long> dmap;
    static long dirpair[][];
    static HashSet<String> not;
    static SegmentTree tree;
    // static long dis[];
    static int gg[];
    static long fac[];
    static long finv[];
    public static void main(String[] args)throws IOException {
        int t = 1;
        // HashSet<Integer> list  = new HashSet<>();
        // int MAK = (int)1e5 + 1;
        // boolean seive[] = new boolean[MAK];
        // Arrays.fill(seive , true);
        // seive[1] = false;
        // seive[0] = false;
        // for (int i = 1; i < MAK; i++) {
        //     if(seive[i]) {
        //         for (int j = i+i; j < MAK; j+=i) {
        //             seive[j] = false;
        //         }
        //     }
        // }
        // for(int i = 2;i < MAK;i++) {
        //     if(seive[i]) list.add(i);
        // }
        int test_case = 1;
        while (t-- > 0) {
            // sc.print("Case #"+(test_case++)+": ");
            solve();

        } 
        sc.close();
    }
    
    
    public static void solve() throws IOException {
        int n = ri() , a[] = rai(n);
        HashMap<Integer , List<Integer>> map = new HashMap<>();
        for(int i = 0;i < n;i++) {
            map.putIfAbsent(a[i] , new ArrayList<>());
            map.get(a[i]).add(i);
        }
        long ans = 0;
        for(int x : map.keySet()) {
            long c = 1;
            long b = 0;
            for(int i = 1;i < map.get(x).size();i++) {
                b += (map.get(x).get(i) - map.get(x).get(i-1) - 1)*c;
                c++;
                ans += b;
            }
        }
        System.out.println(ans);

        // System.out.println(ans);
    }
    static long solve(int mask,int node,int N, int w[][]) {
        int max = (int)1e9 + 10;
        long inf = (long)1e17;
        int bit = Integer.bitCount(mask);
        if(bit == N) return 0;
        if(node >= N) return  inf;
        if(dp[mask][node] != null) return dp[mask][node];
        long ans = solve(mask , node + 1 , N,w);
        if(ans == inf) ans = 0;
        for(int i = 0;i < N;i++) {
            if((mask&(1<<i)) != 0) continue;
            if((mask&(1<<node)) != 0) continue;
            if(w[node][i] < max) {
                int nmask = mask|(1<<i);
                nmask |= (1<<node);
                long g = solve(nmask , node + 1 , N,w);
                if(g == inf) continue;
                ans = Math.max(ans , w[node][i] + g);
            }
        }
        return  dp[mask][node] = ans;
    }
    


    // void dfsLCA(int node, int par) {
    //     for(int next : graph.get(node)) {
    //         if(vis[next]) continue;
    //         lca[next][0] = node;
    //         for(int i = 1;i <= 30;i++) lca[next][i] = lca[lca[next][i-1]][i-1];
            
    //         dfs(next , node);
    //     }
    // }
    // static int getLCA(int a, int b , int height[]) {
    //     if(a == b) return a;
    //     int ha = height[a] , hb = height[b];
    //     // consedireing a as below to b;
    //     int log = 30;
    //     if(hb > ha) {
    //         int tmp = a;
    //         a = b;
    //         b = tmp;
    //     }

    //     int dif = Math.abs(ha - hb);
    //     for(int i = 0;i <= log;i++) {
    //         if((diff&(1<<i)) != 0) {
    //             a = lca[a][i];
    //         }
    //     }
    //     if(a == b) return  a;
    //     for(int i = log;i >= 0;i--) {
    //         if(lca[a][i] != lca[b][i]) {
    //             a = lca[a][i];
    //             b = lca[b][i];
    //         }
    //     }
    //     return  lca[a][0];

    // }
    
    public static long lcm(long x , long y) {
        return x / gcd(x , y) * y;
    }
    public static long ncr(int a , int b, long mod) {
        if (a == b) return 1l;
        return (((fac[a] * inv[b]) % mod) * inv[a - b]) % mod;
    }
    static long turnOffK(long n, long k) {
        return (n & ~(1l << (k)));
    }
    public static void swap(int i, int j, int arr[]) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static long max(long ...a) {
        return maxArray(a);
    }
    public static void swap(int i, int j, long arr[]) {
        long temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static void swap(int i, int j, char arr[]) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static String slope(Point a , Point b) {
        if ((a.x - b.x) == 0) return "inf";
        long n = a.y - b.y;
        if (n  == 0) return "0";
        long m = a.x - b.x;
        boolean neg = (n * m < 0 ? true : false);
        n = Math.abs(n);
        m = Math.abs(m);
        long g = gcd(Math.abs(n), Math.abs(m));
        n /= g;
        m /= g;
        String ans =  n + "/" + m;
        if (neg) ans = "-" + ans;
        return ans;
    }
    public static int lis(int A[], int size) {
        int[] tailTable = new int[size];
        int len;
        tailTable[0] = A[0];
        len = 1;
        for (int i = 1; i < size; i++) {
            if (A[i] < tailTable[0]) tailTable[0] = A[i];
            else if (A[i] > tailTable[len - 1]) tailTable[len++] = A[i];
            else tailTable[CeilIndex(tailTable, -1, len - 1, A[i])] = A[i];
        }
        return len;
    }
    public static int CeilIndex(int A[], int l, int r, int key) {
        while (r - l > 1) {
            int m = l + (r - l) / 2;
            if (A[m] >= key) r = m;
            else l = m;
        }
        return r;
    }
    public static int find(int node) {
        if (node == parent[node]) return node;
        return parent[node]  = find(parent[node]);
    }
    public static void merge(int a , int b ) {
        a = find(a);
        b = find(b);
        if (a == b) return;
        long mod = (long)1e9 + 7;
        if (size[a] >= size[b]) {
            parent[b] = a;
            size[a] += size[b];
        } else {
            parent[a] = b;
            size[b] += size[a];
        }
    }
    public static  void processPowerOfP(long arr[]) {
        int n = arr.length;
        arr[0] = 1;
        long mod  = (long)1e9 + 7;
        for (int i = 1; i < n; i++) {
            arr[i] = arr[i - 1] * 51;
            arr[i] %= mod;
        }
    }
    public static long hashValue(char s[]) {
        int n = s.length;
        long powerOfP[] = new long[n];
        processPowerOfP(powerOfP);
        long ans = 0;
        long mod  = (long)1e9 + 7;
        for (int i = 0; i < n; i++) {
            ans += (s[i] - 'a' + 1) * powerOfP[i];
            ans %= mod;
        }
        return ans;
    }
    public static void dfs(int r, int c, char arr[][]) {
        int n  = arr.length , m = arr[0].length;
        arr[r][c] = '#';
        for (int i = 0; i < 4; i++) {
            int nr = r + colx[i] , nc = c + coly[i];
            if (nr < 0 || nc < 0 || nc >= m || nr >= n) continue;
            if (arr[nr][nc] == '#') continue;
            dfs(nr, nc, arr);
        }
    }

    public static String getSlope(int a , int b, int x, int y) {
        if (a - x == 0) return "inf";
        if (b - y == 0) return "0";
        int n = b - y , d = a - x;
        boolean neg = (n * d < 0);
        n = Math.abs(n);
        d = Math.abs(d);
        return (neg ? "-" : "") + ((n / gcd(n , d)) + "/" + (d / gcd(n , d)));
    }

    public static boolean collinearr(long a[] , long b[] , long c[]) {
        return (b[1] - a[1]) * (b[0] - c[0]) == (b[0] - a[0]) * (b[1] - c[1]);
    }
    public static boolean isSquare(long sum) {
        long root = (int)Math.sqrt(sum);
        return root * root == sum;
    }
    public static int[] suffixArray(String s) {
        int n = s.length();
        Suffix[] su = new Suffix[n];
        for (int i = 0; i < n; i++) {
            su[i] = new Suffix(i, s.charAt(i) - '$', 0);
        }
        for (int i = 0; i < n; i++)
            su[i].next = (i + 1 < n ? su[i + 1].rank : -1);
        Arrays.sort(su);
        int[] ind = new int[n];
        for (int length = 4; length < 2 * n; length <<= 1) {
            int rank = 0, prev = su[0].rank;
            su[0].rank = rank;
            ind[su[0].index] = 0;
            for (int i = 1; i < n; i++) {
                if (su[i].rank == prev && su[i].next == su[i - 1].next) {
                    prev = su[i].rank;
                    su[i].rank = rank;
                } else {
                    prev = su[i].rank;
                    su[i].rank = ++rank;
                }
                ind[su[i].index] = i;
            }
            for (int i = 0; i < n; i++) {
                int nextP = su[i].index + length / 2;
                su[i].next = nextP < n ?
                             su[ind[nextP]].rank : -1;
            }
            Arrays.sort(su);
        }
        int[] suf = new int[n];
        for (int i = 0; i < n; i++)
            suf[i] = su[i].index;
        return suf;
    }
    public static boolean isPalindrome(String s) {
        int i = 0 , j = s.length() - 1;
        while (i <= j && s.charAt(i) == s.charAt(j)) {
            i++;
            j--;
        }
        return i > j;
    }


    public static void getPerm(int n , char arr[] , String s , List<String>list) {
        if (n == 0) {
            list.add(s);
            return;
        }
        for (char ch : arr) {
            getPerm(n - 1 , arr , s + ch, list);
        }
    }

    public static double getDis(int arr[][] , int  x, int y) {
        double ans = 0.0;
        for (int a[] : arr) {
            int x1 = a[0] , y1 = a[1];
            ans += Math.sqrt((x - x1) * (x - x1) + (y - y1) * (y - y1));
        }
        return ans;
    }


    public static void primeDivisor(HashMap<Long , Long >cnt , long num) {
        for (long i = 2; i * i <= num; i++) {
            while (num % i == 0) {
                cnt.put(i , (cnt.getOrDefault(i, 0l) + 1));
                num /= i;
            }
        }
        if (num > 2) {
            cnt.put(num , (cnt.getOrDefault(num, 0l) + 1));
        }
    }


    public static boolean isSubsequene(char a[],  char b[] ) {
        int i = 0 , j = 0;
        while (i < a.length && j  < b.length) {
            if (a[i] == b[j]) {
                j++;
            }
            i++;
        }
        return  j >= b.length;
    }
    public static long fib(int n , long M) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            long[][] mat = {{1, 1}, {1, 0}};
            mat = pow(mat, n - 1 , M);
            return mat[0][0];
        }
    }
    public static long[][] pow(long[][] mat, int n , long M) {
        if (n == 1) return mat;
        else if (n % 2 == 0) return pow(mul(mat, mat , M), n / 2 , M);
        else return mul(pow(mul(mat, mat, M), n / 2, M), mat , M);
    }
    static long[][] mul(long[][] p, long[][] q, long M) {
        long a = (p[0][0] * q[0][0] + p[0][1] * q[1][0]) % M;
        long b = (p[0][0] * q[0][1] + p[0][1] * q[1][1]) % M;
        long c = (p[1][0] * q[0][0] + p[1][1] * q[1][0]) % M;
        long d = (p[1][0] * q[0][1] + p[1][1] * q[1][1]) % M;
        return new long[][] {{a, b}, {c, d}};
    }
    public static long[] kdane(long arr[]) {
        int n = arr.length;
        long dp[] = new long[n];
        dp[0] = arr[0];
        long ans = dp[0];
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i - 1] + arr[i] , arr[i]);
            ans = Math.max(ans , dp[i]);
        }
        return dp;
    }
    public static void reverse(long arr[])  {
        int i = 0 , j = arr.length - 1;
        while (i < j) {
            swap(i , j , arr);
            i++;
            j--;
        }
    }
    public static void reverse(int arr[])  {
        int i = 0 , j = arr.length - 1;
        while (i < j) {
            swap(i , j , arr);
            i++;
            j--;
        }
    }

    public static int maxArray(int arr[]) {
        int ans = arr[0] ,  n = arr.length;
        for (int i = 1; i < n; i++) {
            ans = Math.max(ans , arr[i]);
        }
        return ans;
    }
    public static long maxArray(long arr[]) {
        long ans = arr[0];
        int  n = arr.length;
        for (int i = 1; i < n; i++) {
            ans = Math.max(ans , arr[i]);
        }
        return ans;
    }
    public static int minArray(int arr[]) {
        int ans = arr[0] , n = arr.length;
        for (int i = 0; i < n; i++ ) {
            ans = Math.min(ans , arr[i]);
        }
        return ans;
    }
    public static long minArray(long arr[]) {
        long ans = arr[0];
        int n = arr.length;
        for (int i = 0; i < n; i++ ) {
            ans = Math.min(ans , arr[i]);
        }
        return ans;
    }

    public static int sumArray(int arr[]) {
        int ans = 0;
        for (int x : arr) {
            ans  += x;
        }
        return ans;
    }
    public static long sumArray(long arr[]) {
        long ans = 0;
        for (long x : arr) {
            ans  += x;
        }
        return ans;
    }
    public static long rl() {
        return sc.nextLong();
    }
    public static char[] rac() {
        return sc.next().toCharArray();
    }
    public static String rs() {
        return sc.next();
    }
    public static char rc() {
        return sc.next().charAt(0);
    }
    public static  int [] rai(int n) {
        int ans[] = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = sc.nextInt();
        }
        return ans;
    }
    public static  long [] ral(int n) {
        long ans[] = new long[n];
        for (int i = 0; i < n; i++) {
            ans[i] = sc.nextLong();
        }
        return ans;
    }
    public static int ri() {
        return sc.nextInt();
    }

    public static int getValue(int num ) {
        int ans = 0;
        while (num > 0) {
            ans++;
            num = num & (num - 1);
        }
        return ans;
    }
    public static boolean isValid(int x , int y , int n, char arr[][], boolean visited[][][][])  {
        return x >= 0 && x < n && y >= 0 && y < n && !(arr[x][y] == '#');
    }

    static long inverse(long a , long mod) {
        return  pow(a , mod - 2 , mod);
    }
    public static long pow(long a , long b  , long mod) {
        if (b == 1) return a;
        if (b == 0) return 1;
        long ans = pow(a , b / 2 , mod) % mod;
        if (b % 2 == 0) {
            return (ans * ans) % mod;
        } else {
            return ((ans * ans) % mod * a) % mod;
        }
    }
    public static long pow(long a , long b ) {
        if (b == 1) return a;
        if (b == 0) return 1;
        long ans = pow(a , b / 2);
        if (b % 2 == 0) {
            return (ans * ans);
        } else {
            return ((ans * ans) * a);
        }
    }


    public static boolean isVowel(char ch) {
        if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') return true;
        if ((ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U'))  return true;
        return false;
    }


    public static int[] readarr()throws IOException {
        int n = sc.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] =  sc.nextInt();
        }
        return arr;
    }

    public static boolean isPowerOfTwo (long x) {
        return x != 0 && ((x & (x - 1)) == 0);
    }
    public static boolean isPrime(long num) {
        if (num == 1) return false;
        if (num <= 3) return true;
        if (num % 2 == 0 || num % 3 == 0) return false;
        for (long i = 5; i * i <= num; i += 6) {
            if (num % i == 0 || num % (i + 2) == 0) return false;
        }
        return true;
    }
    public static boolean isPrime(int num) {
        // System.out.println("At pr " + num);
        if (num == 1) return false;
        if (num <= 3) return true;
        if (num % 2 == 0 || num % 3 == 0) return false;
        for (int i = 5; i * i <= num; i += 6) {
            if (num % i == 0 || num % (i + 2) == 0) return false;
        }
        return true;
    }

    public static long gcd(long a , long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    public static int gcd(int a , int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
    public static int get_gcd(int a , int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
    public static long get_gcd(long a , long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

}
// Fenwick tree for range update and range sum
class Fenwick {
    public int[] initalize(int n) {
        int[] BITree = new int[n + 1];
        for (int i = 1; i <= n; i++) BITree[i] = 0;
        return BITree;
    }
    public long getSum(int BITree[], int index) {
        long sum = 0;
        index = index + 1;
        while (index > 0) {
            sum += BITree[index];
            index -= index & (-index);
        }
        return sum;
    }
    public void updateBIT(int BITree[], int n, int index, int val) {
        index = index + 1;
        while (index <= n) {
            BITree[index] += val;
            index += index & (-index);
        }
    }
    public long sum(int x, int BITTree1[], int BITTree2[]) {
        return (getSum(BITTree1, x) * x) - getSum(BITTree2, x);
    }
    public void updateRange(int BITTree1[], int BITTree2[], int n, int val, int l, int r) {
        updateBIT(BITTree1, n, l, val);
        updateBIT(BITTree1, n, r + 1, -val);
        updateBIT(BITTree2, n, l, val * (l - 1));
        updateBIT(BITTree2, n, r + 1, -val * r);
    }
    public long rangeSum(int l, int r, int BITTree1[], int BITTree2[]) {
        return sum(r, BITTree1, BITTree2) - sum(l - 1, BITTree1, BITTree2);
    }
    public int[] constructBITree(int n) {
        int[] BITree = new int[n + 1];
        for (int i = 1; i <= n; i++) BITree[i] = 0;
        return BITree;
    }
}