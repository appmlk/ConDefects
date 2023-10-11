import java.util.*;
import java.io.*;
import java.lang.reflect.Array;
import static java.lang.Math.*;
import java .util.Map.Entry;
public class Main{
    static long mod=(long)1e9+7;
    static int isPrimeLimit=(int)(1e6);
    static int limit=(int)(1e9);
    static class FastReader{
        BufferedReader br;
        StringTokenizer st;
        public FastReader(){
            br=new BufferedReader(new InputStreamReader(System.in));
        }
        String next(){
            while(st==null || !st.hasMoreTokens()){
                try {
                    st=new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
        int nextInt(){
            return Integer.parseInt(next());
        }
        long nextLong(){
            return Long.parseLong(next());
        }
        double nextDouble(){
            return Double.parseDouble(next());
        }
        String nextLine(){
            String str="";
            try {
                str=br.readLine().trim();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return str;
        }
    }
    static class FastWriter {
    private final BufferedWriter bw;

    public FastWriter() {
    this.bw = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    public void print(Object object) throws IOException {
    bw.append("" + object);
    }

    public void println(Object object) throws IOException {
    print(object);
    bw.append("\n");
    }

    public void close() throws IOException {
    bw.close();
    }
}
    static boolean isArrayPalin(long arr[])
    {
        int i=0;int j=arr.length-1;
        while(i<j)
        {
            if(arr[i]!=arr[j])
            return false;
            i++;j--;
        }
        return true;
    }
    static boolean isStringPalin(String s){
        int i=0,j=s.length()-1;
        while(i<j){
            if(s.charAt(i)!=s.charAt(j))
            return false;
            i++;j--;
        }
        return true;
    }
    static boolean isPrime(int isPrimeLimit)
    {
        boolean bool[]=new boolean[(isPrimeLimit+1)];
        for(int i=0;i<bool.length;i++)
        bool[i]=true;
        for(int i=2;i<=Math.sqrt(isPrimeLimit);i++)
        {
            for(int j=i*i;j<=isPrimeLimit;j+=i)
            bool[j]=false;
        }
        for(int i=2;i<bool.length;i++)
        {
            if(bool[i]==true)
            {
                if(i==isPrimeLimit)return true;
            }
        }
        return false;
    }
    static long gcd(long a,long b)
    {
        if(a==0)return b;
        if(b==0) return a;
        return gcd(b%a,a);
    }
    static int countSetBits(int n) //Brian Kernighan Algo O(log n) =>cnt num of 1's
    {
        int cnt=0;
        while(n>0)
        {
            n&=(n-1);
            cnt++;
        }
        return cnt;
    } 
    public static String sortString(String inputString)
    {
        char tempArray[] = inputString.toCharArray();
        Arrays.sort(tempArray);
        return new String(tempArray);
    }
    static long bigPower(long x, long y, long mod)
    {
        long res = 1; 
        x = x % mod;
    
        if (x == 0)
        return 0;  
    
        while (y > 0)
        {
        if ((y & 1) != 0)
            res = ((res%mod) * (x%mod)) % mod;
        y = y >> 1;  
        x = ((x%mod) * (x%mod)) % mod;
        }
        return res;
    }
    static class Pair{
        char x;int  y;
        Pair(char x,int y){
            this.x=x;
            this.y=y;
        }
    }
    static int findXOR(int n)
    {
        int mod = n % 4;
        if (mod == 0)
            return n;
        else if (mod == 1)
            return 1;
        else if (mod == 2)
            return n + 1;
        else if (mod == 3)
            return 0;
        return 0;
    }
    static long sum(long n){
        long s=0;
        while(n!=0){
            long rem=n%10;
            s+=rem;
            n/=10;
        }
        return s;
    }
    static int countDigit(long n)
    {
        return (int)Math.floor(Math.log10(n) + 1);
    }
    class DSU
    {
        public int[] dsu;
        public int[] size;
    
        public DSU(int N)
        {
            dsu = new int[N+1];
            size = new int[N+1];
            for(int i=0; i <= N; i++)
            {
                dsu[i] = i;
                size[i] = 1;
            }
        }
        //with path compression, no find by rank
        public int find(int x)
        {
            return dsu[x] == x ? x : (dsu[x] = find(dsu[x]));
        }
        public void merge(int x, int y)
        {
            int fx = find(x);
            int fy = find(y);
            dsu[fx] = fy;
        }
        public void merge(int x, int y, boolean sized)
        {
            int fx = find(x);
            int fy = find(y);
            size[fy] += size[fx];
            dsu[fx] = fy;
        }
    }
    public static void main(String[] args) {
    try {
            FastReader sc=new FastReader();
            FastWriter out = new FastWriter();
            // int testCases=sc.nextInt(); 
            int testCases=1;
            while(testCases-- > 0){
                int k=sc.nextInt();
                String ans="";
                for(int i=0;i<k;i++){
                    ans+=(char)(i+'A');
                }
                out.println(ans);
            }
            out.close();  
            } catch (Exception e) {
        return;
        }
    }   
}
