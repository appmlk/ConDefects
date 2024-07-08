import com.sun.source.tree.Tree;
import com.sun.source.util.Trees;
import javax.print.attribute.standard.PrinterResolution;
import java.util.*;
import java.io.*;
public class Main  {
    static FastReader in;
    static PrintWriter out;
    static final Random random=new Random();
    static long mod= 998244353L;
    static boolean isPrime[];
    static  long p[];
    static long invFac[];
    public static void main(String args[]) throws IOException {
        in = new FastReader();
        out = new PrintWriter(System.out);
        boolean flag = false;
        int t = flag ? in.nextInt() : 1;
        int n = 1001;
        long inv[] = new long[n];
        inv[1] = 1;
        for(int i = 2; i < n; i++){
            inv[i] = mod-inv[(int)(mod%i)]*(mod/i)%mod;
        }
       p = new long[1001];
        invFac = new long[n];
        p[0] = 1;
        invFac[1] = 1;
        for(int i = 1; i < 1001; i++){
            p[i] = (i*p[i-1])%mod;
            if(i != 1){
                invFac[i] = (invFac[i-1]*inv[i])%mod;
            }

        }
        Main m = new Main();
        while (t-- > 0) {
            m.solve();
        }
        out.close();
    }
    long dp[][];

    void solve() {
        int k = in.nextInt();
        int c[] = new int[26];
        for(int i = 0; i < 26; i++) c[i] = in.nextInt();
        dp = new long[26][k+1];
        for(long it[] : dp){
            Arrays.fill(it, -1);
        }
        System.out.println(f(0, k, c, k)-1);

    }
   long f(int i,int k, int c[], int r){
        if(i == 26){
            return p[r-k];
        }
        if(dp[i][k] != -1) return dp[i][k];
        long aus = f(i+1, k, c, r);
        for(int j = 1; j <= c[i]; j++){
            if(k-j >= 0){
                aus = (aus+(f(i+1, k-j, c, r)*invFac[j])%mod)%mod;
            }
        }
        return dp[i][k] = aus;
   }

    long cal(long n){
        return n*(n-1)/2;
    }
    static < E > void println(E res)
    {
        System.out.println(res);
    }
    static < E > void print(E res)
    {
        System.out.println(res);
    }
    static int dir[][]=new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
    static int dir8[][]=new int[][]{{1,0},{-1,0},{0,1},{0,-1},{1,-1},{-1,-1},{-1,1},{1,1}};
    public static int binarySearch(int i,int nums[]){int a=(int)1e9;int left=0,right=nums.length-1;while(left<=right){int mid=(left+right)/2;if(nums[mid]>nums[i]){right=mid-1;a=mid;} else left=mid+1;}return a;}
    public static void print(int a[]) { StringBuilder str = new StringBuilder(""); for (int i : a) str.append(i+" "); System.out.println(str);};
    public static List<Integer> factors(int n){List<Integer> factors = new ArrayList<>();for (int i = 1; i <= Math.sqrt(n); i++) {if (n % i == 0) {factors.add(i);if (i != n / i) {factors.add(n / i);}}}return factors;}
    public static void println(){System.out.println();}
    public static int gcd(int a,int b){if(b==0)return a;return gcd(b,a%b);}
    public static int lcm(int a,int b){return (a/(gcd(a,b))*b);}
    public static boolean[] prime(int n){boolean a[]=new boolean[n+1];Arrays.fill(a,true);for(int i=2;i*i<=n;i++){if(a[i]==true){for(int j=i+i;j<=n;j+=i){a[i]=false;}}}return a;}
    public static boolean isPrime(int n) {if (n <= 1) {return false;}if (n <= 3) {return true;}if (n % 2 == 0 || n % 3 == 0) {return false;}for (int i = 5; i * i <= n; i = i + 6) {if (n % i == 0 || n % (i + 2) == 0) {return false;}}return true;}
    public static long factorial(int n) {if (n == 0) {return 1;}long fact = 1;for (int i = 1; i <= n; i++) {fact *= i;}return fact;}
    public static long power(long a, long b) {long result = 1;while (b > 0) {if ((b & 1) != 0) {result = (result * a)%mod;}a = (a * a)%mod;b >>= 1;}return result;}
    public static void read2DArray(int a[][],int n,int m){for(int i=0;i<n;i++){for(int j=0;j<m;j++)a[i][j]=in.nextInt();}}
    static class FastReader
    {
        BufferedReader br;
        StringTokenizer st;

        public FastReader()
        {
            br = new BufferedReader(new
                    InputStreamReader(System.in));
        }

        String next()
        {
            while (st == null || !st.hasMoreElements())
            {
                try
                {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException  e)
                {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
        int nextInt()
        {
            return Integer.parseInt(next());
        }

        long nextLong()
        {
            return Long.parseLong(next());
        }

        double nextDouble()
        {
            return Double.parseDouble(next());
        }
        String nextLine()
        {
            String str = "";
            try
            {
                str = br.readLine();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return str;
        }

        int [] readintarray(int n) {
            int res [] = new int [n];
            for(int i = 0; i<n; i++)res[i] = nextInt();
            return res;
        }
        long [] readlongarray(int n) {
            long res [] = new long [n];
            for(int i = 0; i<n; i++)res[i] = nextLong();
            return res;
        }
    }

}