//package kg.my_algorithms.codechef;



import java.io.*;
import java.util.*;

public class Main {
    private static final FastReader fr = new FastReader();
    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(System.out);
//        int testCases = fr.nextInt();
//        for(int testCase=1;testCase<=testCases;testCase++) {
//        }
        long n = fr.nextLong();
        boolean[] visited = new boolean[1_00_00_01];
        List<Long> primes = new ArrayList<>();
        List<Long> prime_square = new ArrayList<>();
        for(int i=2;i<1_00_00_01;i++){
            if(!visited[i]){
                prime_square.add((long)i*i);
                primes.add((long)i);
                for(int j=i;j<1_00_00_01;j+=i) visited[j] = true;
            }
        }
        long ans = 0L;
        for(int i=0;i<prime_square.size();i++){
            for(int j=i+1;j<primes.size();j++){
                long cur = prime_square.get(i)*primes.get(j);
                if(cur>n || isMultiplicationOverflow(prime_square.get(i),primes.get(j))) break;
                int index = searchInsert(prime_square,n/cur);
                index -= (j+1);
                if(index<=0) break;
                ans += index;
            }
        }
        out.println(ans);
        out.flush();
    }
    public static boolean isMultiplicationOverflow(long a, long b) {
        if (a == 0 || b == 0) return false;
        long result = a * b;
        return a != result / b;
    }
    public static int searchInsert(List<Long> nums, long target) {
        int lo=0,hi=nums.size()-1;
        while(lo<=hi){
            int m=lo+(hi-lo)/2;
            if(nums.get(m) ==target) return m-1;
            if(nums.get(m) >target) hi=m-1;
            else lo=m+1;
        }
        return lo;
    }
}
class FastReader {
    BufferedReader br;
    StringTokenizer st;

    public FastReader()
    {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    String next() {
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
            if(st.hasMoreTokens()){
                str = st.nextToken("\n");
            }
            else{
                str = br.readLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}